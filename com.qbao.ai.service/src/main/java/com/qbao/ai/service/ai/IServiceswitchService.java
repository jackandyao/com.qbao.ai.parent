package com.qbao.ai.service.ai;

import com.qbao.ai.resposity.mybatis.model.ServiceSwitch;

public interface IServiceswitchService {

	ServiceSwitch findServiceSwitchByKey(String key);
}
