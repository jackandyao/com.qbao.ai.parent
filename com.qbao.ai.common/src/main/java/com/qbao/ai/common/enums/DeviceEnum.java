package com.qbao.ai.common.enums;

import java.util.HashMap;

/**
 * @author wangping
 * @createTime 2017/3/30 下午3:18
 * $$LastChangedDate: 2017-03-30 15:52:45 +0800 (周四, 30 三月 2017) $$
 * $$LastChangedRevision: 587 $$
 * $$LastChangedBy: wangping $$
 */
public enum DeviceEnum {

    IOS(1),ANDROID(2);

    private Integer value;
    private DeviceEnum(Integer value) {
        this.value = value;
    }

    private static HashMap<Integer, DeviceEnum> maps = new HashMap<>();
    static {
        for (DeviceEnum device : DeviceEnum.values()) {
            maps.put(device.value, device);
        }
    }

    public static DeviceEnum asDeviceEnum(Integer value) {
        return maps.get(value);
    }

    public static void main(String[] args){
        System.out.println(DeviceEnum.asDeviceEnum(1) == DeviceEnum.IOS);
    }

}
