package com.qbao.ai.service.ai.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qbao.ai.resposity.mybatis.dao.ServiceSwitchDao;
import com.qbao.ai.resposity.mybatis.model.ServiceSwitch;
import com.qbao.ai.resposity.redis.cache.annotation.CacheType;
import com.qbao.ai.resposity.redis.cache.annotation.RedisCache;
import com.qbao.ai.service.ai.IServiceswitchService;

/**
 * @author zhangjun
 * @createTime 2017/3/30 16:43
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
@Service
public class ServiceswitchServiceImpl implements IServiceswitchService {

    Logger logger = LoggerFactory.getLogger(ServiceswitchServiceImpl.class);

    @Autowired
    ServiceSwitchDao serviceSwitchDao;

	@Override
	@RedisCache(expire = 60 * 1, clazz = ServiceSwitch.class, cacheType = CacheType.OBJECT)
	public ServiceSwitch findServiceSwitchByKey(String key) {
		return serviceSwitchDao.findServiceSwitchByKey(key);
	}

}
