package com.dragon.project.ad.service;

import com.dragon.project.ad.domain.BgAd;
import java.util.List;

/**
 * 广告Service接口
 * 
 * @author Dragon Wen
 * @date 2019-08-28
 */
public interface IBgAdService 
{
    /**
     * 查询广告
     * 
     * @param adId 广告ID
     * @return 广告
     */
    public BgAd selectBgAdById(Long adId);

    /**
     * 查询广告列表
     * 
     * @param bgAd 广告
     * @return 广告集合
     */
    public List<BgAd> selectBgAdList(BgAd bgAd);

    /**
     * 新增广告
     * 
     * @param bgAd 广告
     * @return 结果
     */
    public int insertBgAd(BgAd bgAd);

    /**
     * 修改广告
     * 
     * @param bgAd 广告
     * @return 结果
     */
    public int updateBgAd(BgAd bgAd);

    /**
     * 批量删除广告
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBgAdByIds(String ids);

    /**
     * 删除广告信息
     * 
     * @param adId 广告ID
     * @return 结果
     */
    public int deleteBgAdById(Long adId);

    /**
     * 校验广告位置是否已有广告
     *
     * @param bgAd 广告信息
     * @return 结果
     */
    String checkDataFlagUnique(BgAd bgAd);

    /**
     * 根据广告位置代码查询广告内容
     * @param dataFlag 广告位置代码
     * @return 结果
     */
    BgAd selectAdByDataFlag(Integer dataFlag);
}
