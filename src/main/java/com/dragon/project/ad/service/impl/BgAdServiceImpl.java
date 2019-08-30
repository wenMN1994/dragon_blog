package com.dragon.project.ad.service.impl;

import com.dragon.common.constant.UserConstants;
import com.dragon.common.utils.DateUtils;
import com.dragon.common.utils.StringUtils;
import com.dragon.common.utils.text.Convert;
import com.dragon.project.ad.domain.BgAd;
import com.dragon.project.ad.mapper.BgAdMapper;
import com.dragon.project.ad.service.IBgAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 广告Service业务层处理
 * 
 * @author Dragon Wen
 * @date 2019-08-28
 */
@Service
public class BgAdServiceImpl implements IBgAdService 
{
    @Autowired
    private BgAdMapper bgAdMapper;

    /**
     * 查询广告
     * 
     * @param adId 广告ID
     * @return 广告
     */
    @Override
    public BgAd selectBgAdById(Long adId)
    {
        return bgAdMapper.selectBgAdById(adId);
    }

    /**
     * 查询广告列表
     * 
     * @param bgAd 广告
     * @return 广告
     */
    @Override
    public List<BgAd> selectBgAdList(BgAd bgAd)
    {
        return bgAdMapper.selectBgAdList(bgAd);
    }

    /**
     * 新增广告
     * 
     * @param bgAd 广告
     * @return 结果
     */
    @Override
    public int insertBgAd(BgAd bgAd)
    {
        bgAd.setCreateTime(DateUtils.getNowDate());
        return bgAdMapper.insertBgAd(bgAd);
    }

    /**
     * 修改广告
     * 
     * @param bgAd 广告
     * @return 结果
     */
    @Override
    public int updateBgAd(BgAd bgAd)
    {
        bgAd.setUpdateTime(DateUtils.getNowDate());
        return bgAdMapper.updateBgAd(bgAd);
    }

    /**
     * 删除广告对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBgAdByIds(String ids)
    {
        return bgAdMapper.deleteBgAdByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除广告信息
     * 
     * @param adId 广告ID
     * @return 结果
     */
    public int deleteBgAdById(Long adId)
    {
        return bgAdMapper.deleteBgAdById(adId);
    }

    @Override
    public String checkDataFlagUnique(BgAd bgAd) {
        BgAd info = bgAdMapper.selectAdByDataFlag(bgAd.getDataFlag());
        if (StringUtils.isNotNull(info)) {
            return UserConstants.AD_DATA_FLAG_NOT_UNIQUE;
        }
        return UserConstants.AD_DATA_FLAG__UNIQUE;
    }

    @Override
    public BgAd selectAdByDataFlag(Integer dataFlag) {
        return bgAdMapper.selectAdByDataFlag(dataFlag);
    }
}
