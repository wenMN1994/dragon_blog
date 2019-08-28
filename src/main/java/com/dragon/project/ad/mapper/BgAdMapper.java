package com.dragon.project.ad.mapper;

import com.dragon.project.ad.domain.BgAd;
import java.util.List;

/**
 * 广告Mapper接口
 * 
 * @author Dragon Wen
 * @date 2019-08-28
 */
public interface BgAdMapper 
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
     * 删除广告
     * 
     * @param adId 广告ID
     * @return 结果
     */
    public int deleteBgAdById(Long adId);

    /**
     * 批量删除广告
     * 
     * @param adIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBgAdByIds(String[] adIds);
}
