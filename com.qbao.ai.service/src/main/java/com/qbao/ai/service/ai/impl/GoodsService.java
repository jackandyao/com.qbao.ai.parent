package com.qbao.ai.service.ai.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.qbao.ai.common.constant.Constant;
import com.qbao.ai.common.enums.DeviceEnum;
import com.qbao.ai.common.util.ListPage;
import com.qbao.ai.common.util.NoValidDataException;
import com.qbao.ai.common.util.PageManager;
import com.qbao.ai.model.dto.Banner;
import com.qbao.ai.model.dto.GoodsDto;
import com.qbao.ai.model.dto.Stuff;
import com.qbao.ai.model.dto.StuffDto;
import com.qbao.ai.model.dto.StuffPromotionDto;
import com.qbao.ai.model.dto.StuffRecommendDto;
import com.qbao.ai.model.dto.ZwStuffDto;
import com.qbao.ai.resposity.common.Page;
import com.qbao.ai.resposity.mybatis.dao.AdBannerDao;
import com.qbao.ai.resposity.mybatis.dao.StuffPromotionDao;
import com.qbao.ai.resposity.mybatis.dao.StuffRebateDao;
import com.qbao.ai.resposity.mybatis.dao.StuffRecommendDao;
import com.qbao.ai.resposity.mybatis.dao.UserDislikeStuffDao;
import com.qbao.ai.resposity.mybatis.model.AdBanner;
import com.qbao.ai.resposity.mybatis.model.StuffPromotion;
import com.qbao.ai.resposity.mybatis.model.StuffRebate;
import com.qbao.ai.resposity.mybatis.model.StuffRecommend;
import com.qbao.ai.resposity.mybatis.model.UserDislikeStuff;
import com.qbao.ai.resposity.mybatis.model.ZwStuff;
import com.qbao.ai.resposity.redis.cache.annotation.CacheType;
import com.qbao.ai.resposity.redis.cache.annotation.RedisCache;
import com.qbao.ai.service.ai.IBaseAnswerService;
import com.qbao.ai.util.BeanUtils;
import com.qbao.ai.util.HttpClientUtil;
import com.qbao.ai.util.HttpProcessException;
import com.qbao.ai.util.http.HttpConfig;

/**
 * @author liaijun
 * @createTime 17/4/14 下午2:28
 * $$LastChangedDate: 2017-06-01 11:28:22 +0800 (Thu, 01 Jun 2017) $$
 * $$LastChangedRevision: 238 $$
 * $$LastChangedBy: zhangjun $$
 */
@Service
public class GoodsService implements IBaseAnswerService  {
    Logger logger= Logger.getLogger(GoodsService.class);
    @Value("${search.domain}")
    private String search_domain;
    @Value("${search.zwstuff.url}")
    private String search_zwstuff_url;
    @Value("${search.sim.zwstuff.url}")
    private String search_sim_zwstuff_url;
    @Autowired
    private StuffPromotionDao stuffPromotionDao;
    @Autowired
    private StuffRebateDao stuffRebateDao;
    @Autowired
    private AdBannerDao bannerDao;
    @Autowired
    private CustomService customService;
    @Autowired
    private StuffRecommendDao stuffRecommendDao;
    @Autowired
    private UserDislikeStuffDao userDislikeStuffDao;
    @Override
    public Map<String,Object> answerInfo(String q, int device, String lat, String lon,Long userId,int page,int size,Set<String> set) {
        try {

            if(CollectionUtils.isNotEmpty(set)){
                q="";
                for(String str:set){
                    q+=str;
                }
            }
            Page<Stuff> stuff =searchZw(userId, q, "", page, size, device);
            GoodsDto goodsDto = new GoodsDto();
            List<Stuff> stuffs=stuff.getItems();
            goodsDto.setStuff(stuffs);
            goodsDto.setBanner(new ArrayList<>());
            if(CollectionUtils.isNotEmpty(stuffs)){
                Map<String, Object> response = new HashMap<String, Object>();
                response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
                response.put("questionType","goods");
                response.put("title","很开心等到你,小智很想你,来看看小智为你精选推荐^_^");
                response.put("success", true);
                response.put("message", "Ok");
                response.put("userId",userId);
                response.put("data",goodsDto);
                return response;
            }else{
                Map<String,Object> map=customService.answerInfo("",0,"","",null,0,0,null);
                map.put("userId",userId);
                return map;
            }

        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
        }
        Map<String,Object> map=customService.answerInfo("",0,"","",null,0,0,null);
        map.put("userId",userId);
        return map;
    }

    @RedisCache(expire = 60 * 30, clazz = Banner.class, cacheType = CacheType.LIST)
    public List<Banner> searchBanner(int size ){
        List<AdBanner> banners=bannerDao.findBannerByLocationId(size,new Date());
        if(CollectionUtils.isNotEmpty(banners)) {
            return BeanUtils.mapList(banners, Banner.class);
        }else{
            return new ArrayList<Banner>();
        }
    }

    @RedisCache(expire = 60 * 30, clazz = Stuff.class, cacheType = CacheType.PAGE)
    public Page<Stuff> searchZw(long userId, String kw, String source,  int page, int size,int device)
            throws UnsupportedEncodingException, HttpProcessException {
        ////默认按佣金高低排序
        String sort = "sort:promotion_rate:desc";
        String result = searchZwByUrl(userId,kw,source,sort,page,size);
        int total = 0;
        List<ZwStuff> list = new ArrayList<ZwStuff>();
        //        System.out.println(result);
        JSONObject resultObject = JSON.parseObject(result);
        String code = resultObject.getString("errorCode");
        if("0".equals(code)){
            JSONObject dataObject = resultObject.getJSONObject("data");
            total = dataObject.getInteger("totalCount");
            JSONArray dataArray = dataObject.getJSONArray("dataList");
            list.addAll(JSON.parseArray(dataArray.toJSONString(),ZwStuff.class));
        }else{
            logger.error("搜索站外数据异常 result:"+result);
            throw new RuntimeException("搜索站外数据异常");
        }
        Page<ZwStuff> zwStuffPage= new Page<ZwStuff>(total,page,size,list);
         return convertToZwStuffDto(zwStuffPage, device);
    }

    /**
     * 站外商品搜索
     * @param userId
     * @param kw
     * @param source
     * @param sort
     * @param page
     * @param size
     * @return
     * @throws UnsupportedEncodingException
     * @throws
     */
    @RedisCache(expire = 60 * 30, clazz = String.class, cacheType = CacheType.OBJECT)
    public String searchZwByUrl(long userId,String kw,String source,String sort,int page,int size) throws UnsupportedEncodingException, HttpProcessException {
        String url = this.createZwSearchUrl(userId,kw,source,sort,page,size);
        logger.info("SearchZw url : " + url);
        String result = HttpClientUtil.get(HttpConfig.custom().url(url));
        logger.info("SearchZw result : " + result);
        return result;
    }


    private String createZwSearchUrl(long userId,String kw,String source,String sort,int page,int size) throws UnsupportedEncodingException {
        String kwEncode = URLEncoder.encode(kw,"UTF-8");
        String filter ="";
        if(!StringUtils.isEmpty(source)) {
            String[] sources = source.split(",");
            StringBuffer sq = new StringBuffer("");
            int i = 0;
            for(String s:sources){
                if(i>0){
                    sq.append(" OR ");
                }
                sq.append("source:").append(s);
                i++;
            }
            filter = "{\"fq\":\""+sq.toString()+"\"}";
        }
        filter= URLEncoder.encode(filter,"UTF-8");
        return search_domain+search_zwstuff_url+"?kw="+kwEncode+"&user_id="+userId+"&sort="+sort+"&filter="+filter+"&p="+page+"&size="+size;
    }

    private Page<ZwStuffDto> convert2ZwStuffDto(Page<ZwStuff> zwStuffs,int device){
        List<ZwStuffDto> list = new ArrayList<>();
        int total = zwStuffs.getTotalNumber();
        for(ZwStuff zwStuff : zwStuffs.getItems()){
            ZwStuffDto dto = new ZwStuffDto();
            BeanUtils.copy(zwStuff,dto);
            dto.setRebateValue(findStuffRebateValue(dto.getStuffId()));
            dto.setLinkUrl(findPromtoionUrl(zwStuff, DeviceEnum.asDeviceEnum(device)));
            list.add(dto);
        }
        return new Page<ZwStuffDto>(total,zwStuffs.getCurrentIndex(),zwStuffs.getPageSize(),list);
    }
    private Page<Stuff> convertToZwStuffDto(Page<ZwStuff> zwStuffs,int device){
        List<Stuff> list = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("0.00");
        int total = zwStuffs.getTotalNumber();
        for(ZwStuff zwStuff : zwStuffs.getItems()){
            Stuff dto = new Stuff();

            dto.setStuffId(zwStuff.getId());
            dto.setImgUrl(zwStuff.getImgUrl());
            dto.setName(zwStuff.getName());

            dto.setPrice(new BigDecimal(df.format(zwStuff.getFinalPrice())));
            dto.setSaleCount(zwStuff.getOrderNum());
            dto.setSource(zwStuff.getSource());
            dto.setRebateValue(findStuffRebateValue(dto.getStuffId()));
            dto.setLinkUrl(findPromtoionUrl(zwStuff, DeviceEnum.asDeviceEnum(device)));
            list.add(dto);
        }
        return new Page<Stuff>(total,zwStuffs.getCurrentIndex(),zwStuffs.getPageSize(),list);
    }

    public String findPromtoionUrl(ZwStuff zwStuff, DeviceEnum device) {
        if (null != zwStuff && null != device) {
            if (DeviceEnum.IOS == device) {
                return zwStuff.getIosPromotionUrl();
            }
            if (DeviceEnum.ANDROID == device) {
                return zwStuff.getAndroidPromotionUrl();
            }
        }
        return "";
    }
    @RedisCache(expire = 60 * 30, clazz = String.class, cacheType = CacheType.OBJECT)
    public String findStuffRebateValue(long stuffId){
        StuffPromotion stuffPromotion = stuffPromotionDao.findStuffPromotionById(stuffId);
        if(stuffPromotion==null){
            logger.error("stuffId=["+stuffId+"] 在商品表中不存在");
            return "";
        }
        StuffRebate stuffRebate = stuffRebateDao.findStuffReBate(stuffPromotion.getRebateId());
        if(stuffRebate!=null) {
            if (stuffRebate.getIsAbsolute() == 1) {
                return Constant.FAN + stuffRebate.getValue().toBigInteger() + Constant.BAO;
            } else {
                BigDecimal rebateValue = stuffRebate.getValue();
                Integer promotionRate = stuffPromotion.getPromotionRate();
                if (rebateValue != null && promotionRate != null) {
                    return Constant.FAN + (rebateValue.multiply(new BigDecimal(promotionRate))).divide(new BigDecimal(10000)).toBigInteger() + Constant.BAOQUAN;
                } else {
                    return "";
                }

            }
        }else{
            return "";
        }
    }

    //找相似
    @RedisCache(expire = 60 * 30, clazz = StuffPromotionDto.class, cacheType = CacheType.OBJECT)
    public StuffPromotionDto findStuffByStuffId(long stuffId) throws NoValidDataException {
        StuffPromotion stuffPromotion = stuffPromotionDao.findStuffPromotionById(stuffId);
        stuffPromotion.setRebateValue(findStuffRebateValue(stuffPromotion.getId()));

        if(stuffPromotion==null){
            throw new NoValidDataException("此["+stuffId+"]不存在对应的商品记录");
        }
        StuffPromotionDto stuffPromotionDto = new StuffPromotionDto();
        BeanUtils.copy(stuffPromotion, stuffPromotionDto);
        return stuffPromotionDto;
    }


    @RedisCache(expire = 60 * 30, clazz = ZwStuffDto.class, cacheType = CacheType.PAGE)
    public Page<ZwStuffDto> searchZwSim(long userId,long stuffId,int page,int size,int device) throws UnsupportedEncodingException, HttpProcessException {
        StuffPromotion stuffPromotion = findStuffPromotion(stuffId);
        if(null != stuffPromotion){
            Page<ZwStuff> result = searchZwSim(userId,stuffPromotion.getName(),stuffPromotion.getCatId()+"","","",page,size);
            List<ZwStuff> newList = new ArrayList<ZwStuff>();
            for(ZwStuff zwStuff:result.getItems()){
                if(stuffId!=zwStuff.getId()){
                    newList.add(zwStuff);
                }
                if(newList.size()==(size-1)){
                    break;
                }
            }
            Page<ZwStuff> newResult = new Page<ZwStuff> (result.getTotalNumber(),result.getCurrentIndex(),result.getPageSize()-1,newList);
            return this.convert2ZwStuffDto(newResult,device);
        }
        return new Page<ZwStuffDto>();
    }

    @RedisCache(expire = 60*2,clazz =StuffPromotion.class,cacheType = CacheType.OBJECT)
    public StuffPromotion findStuffPromotion(long id) {
        return stuffPromotionDao.findStuffPromotionById(id);
    }
    
    @RedisCache(expire = 60 * 30, clazz = ZwStuff.class, cacheType = CacheType.PAGE)
    public Page<ZwStuff> searchZwSim(long userId, String kw, String cId, String source, String sort, int page, int size)
            throws UnsupportedEncodingException, HttpProcessException {
        String result = BizsearchZwSim(userId,kw,cId,source,sort,page,size);
        int total = 0;
        List<ZwStuff> list = new ArrayList<ZwStuff>();
        //        System.out.println(result);
        JSONObject resultObject = JSON.parseObject(result);
        String code = resultObject.getString("errorCode");
        if("0".equals(code)){
            JSONObject dataObject = resultObject.getJSONObject("data");
            total = dataObject.getInteger("totalCount");
            JSONArray dataArray = dataObject.getJSONArray("dataList");
            list.addAll(JSON.parseArray(dataArray.toJSONString(),ZwStuff.class));
        }else{
            logger.error("查询出错！ result:"+result);
            throw new RuntimeException("查询出错！");
        }
        return new Page<ZwStuff>(total,page,size,list);
    }
    
    @RedisCache(expire = 60 * 30, clazz = String.class, cacheType = CacheType.OBJECT)
    public String BizsearchZwSim(long userId,String kw,String cId,String source,String sort,int page,int size)
            throws UnsupportedEncodingException, HttpProcessException {
        String url = this.createZwSearchSimUrl(userId,kw,cId,source,sort,page,size);
        logger.info("SearchSim url : " + url);
        String result = HttpClientUtil.get(HttpConfig.custom().url(url));
        logger.info("SearchSim result : " + result);
        return result;
    }

    private String createZwSearchSimUrl(long userId,String kw,String cId,String source,String sort,int page, int size) throws UnsupportedEncodingException {
        String kwEncode = URLEncoder.encode(kw,"UTF-8");
        String filter ="";
        if(!StringUtils.isEmpty(source)) {
            String[] sources = source.split(",");
            StringBuffer sq = new StringBuffer("");
            int i = 0;
            for(String s:sources){
                if(i>0){
                    sq.append(" OR ");
                }
                sq.append("source:").append(s);
                i++;
            }
            filter = "{\"fq\":\""+sq.toString()+"\"}";
        }
        filter= URLEncoder.encode(filter,"UTF-8");
        return search_domain+search_sim_zwstuff_url+"?kw="+kwEncode+"&c_id="+cId+"&user_id="+userId+"&sort="+sort+"&filter="+filter+"&p="+page+"&size="+size;
    }

    //@RedisCache(expire = 60,clazz =StuffRecommendDto.class,cacheType = CacheType.LIST)
    public StuffDto stuffRecommend(Long userId,Integer page, Integer size,int device) {
        if(userId==null){
            userId = 0l;
        }
        List<StuffRecommendDto> responseResult = new LinkedList<StuffRecommendDto>();
        List<StuffPromotion> result = stuffRecommend(userId, page, size);
        if (CollectionUtils.isNotEmpty(result)) {
            StuffRecommendDto.Builder builder = new StuffRecommendDto.Builder();
            for (StuffPromotion tss : result) {
                responseResult.add(builder.withStuffId(tss.getId())
                        .withName(tss.getName())
                        .withPrice(tss.getFinalPrice())
                        .withImgUrl(tss.getImgUrl())
                        .withLinkUrl(findPromtoionUrl(tss, DeviceEnum.asDeviceEnum(device)))
                        .withSaleCount(tss.getOrderNum())
                        .withRebateValue(findStuffRebateValue(tss.getId()))
                        .withSource(tss.getSource())
                        .build());
            }
        }
        logger.info("total get [" + (CollectionUtils.isEmpty(result) ? 0 : result.size()) + "] StuffRecommend,userId=["+userId+"]page=[" + page + "], size=[" + size + "]");
        StuffDto dto=new  StuffDto();
        dto.setBanner(searchBanner(1));
        dto.setStuff(responseResult);
        return dto;
    }
    public String findPromtoionUrl(StuffPromotion stuffPromotion, DeviceEnum device) {

        if (null != stuffPromotion && null != device) {
            if (DeviceEnum.IOS == device) {
                return stuffPromotion.getIosPromotionUrl();
            }
            if (DeviceEnum.ANDROID == device) {
                return stuffPromotion.getAndroidPromotionUrl();
            }
        }
        return "";
    }
    public List<StuffPromotion> stuffRecommend(Long userId, Integer page, Integer size) {
        PageManager pageManager = new PageManager(page, size);
        List<Long> offlineRecIds = fetchStuffRecommend(userId);
        logger.info("fetch userId=[" + userId + "] off time recommend stuff [" + offlineRecIds.size() + " ] ids. and value : " + offlineRecIds);

        List<Long> disLikeIds = fetchDisLikeStuffIds(userId);
        logger.info("fetch userId=[" +userId + "] dislike stuff [" + disLikeIds.size() + " ] ids. and value : " + disLikeIds);

        List<Long> ids = ListUtils.subtract(offlineRecIds,disLikeIds);
        logger.info("userId=[" + userId + "]:  After remove the dis like stuff ids  the ids :" +ids );

        List<StuffPromotion> avaliableStuff = filterOfflineStuff(ids);
        return ListPage.page(page,size,avaliableStuff);

    }

    private List<StuffPromotion> filterOfflineStuff(List<Long> ids){
        List<StuffPromotion> result = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(ids)) {
            for (Long id : ids) {
                StuffPromotion stuffPromotion = findStuffPromotion(id);
                if (null != stuffPromotion) {
                    result.add(stuffPromotion);
                }else {
                    logger.info("stuff id =["+id+"]  is off line");
                }
            }
        }
        return  result;
    }
    @RedisCache(expire = 60*30,clazz =Long.class,cacheType = CacheType.LIST)
    public List<Long>  fetchDisLikeStuffIds(Long userId){
        List<Long> ids = Lists.newArrayList();
        UserDislikeStuff userDislikeStuff = userDislikeStuffDao.userDislikeStuff(userId);
        if(userDislikeStuff!=null){
            String stuffIds = userDislikeStuff.getStuffIds();
            String[] stuffIdsArr = stuffIds.split(",");
            for(String stuffId:stuffIdsArr){
                ids.add(Long.parseLong(stuffId));
            }
        }
        return ids;
    }
    @RedisCache(expire = 60*60,clazz =Long.class,cacheType = CacheType.LIST)
    private List<Long> fetchStuffRecommend(Long userId){
        List<Long> list = Lists.newArrayList();
        StuffRecommend stuffRecommend = stuffRecommendDao.stuffRecommend(userId);
        if(null == stuffRecommend) {
            stuffRecommend = stuffRecommendDao.stuffRecommend(0L);//默认用户
            logger.info("user id =["+userId+"], 该用户无推荐结果,使用默认推荐结果 ");
        }
        if (stuffRecommend != null && stuffRecommend.getStuffIds() != null) {
            String[] ids = stuffRecommend.getStuffIds().split(",");
            for (String id : ids) {
                if (id != null) {
                    list.add(Long.parseLong(id));
                }
            }
        }
        return list;
    }
}
