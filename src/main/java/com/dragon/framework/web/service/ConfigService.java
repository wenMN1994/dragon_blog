package com.dragon.framework.web.service;

import com.dragon.project.system.config.service.IConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/6/17 16:27
 * @description：  html调用 thymeleaf 实现参数管理
 * @modified By：
 * @version: 1.0.0
 */
@Service("config")
public class ConfigService {
    @Autowired
    private IConfigService configService;

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数名称
     * @return 参数键值
     */
    public String getKey(String configKey) {
        return configService.selectConfigByKey(configKey);
    }

    public String getRemark(String configKey){
        return configService.selectConfigRemarkByKey(configKey);
    }

}
