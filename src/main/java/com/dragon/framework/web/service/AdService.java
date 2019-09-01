package com.dragon.framework.web.service;

import com.dragon.common.utils.StringUtils;
import com.dragon.project.ad.domain.BgAd;
import com.dragon.project.ad.service.IBgAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/8/29 15:53
 * @description：html调用 thymeleaf 实现广告管理
 * @modified By：
 * @version: 1.0.0
 */
@Service("ad")
public class AdService {
    @Autowired
    private IBgAdService bgAdService;


    /**
     *
     * @param dataFlag
     * @return
     */
    public String getAdName(Integer dataFlag){
        BgAd ad = bgAdService.selectAdByDataFlag(dataFlag);
        if (StringUtils.isNotNull(ad)){
            return ad.getAdName();
        }
        return "null";
    }

    /**
     *
     * @param dataFlag
     * @return
     */
    public String getAdImg(Integer dataFlag){
        BgAd ad = bgAdService.selectAdByDataFlag(dataFlag);
        if (StringUtils.isNotNull(ad)){
            return ad.getAdImg();
        }
        return "null";
    }

    /**
     *
     * @param dataFlag
     * @return
     */
    public String getAdUrl(Integer dataFlag){
        BgAd ad = bgAdService.selectAdByDataFlag(dataFlag);
        if (StringUtils.isNotNull(ad)){
            return ad.getAdurl();
        }
        return "null";
    }

}
