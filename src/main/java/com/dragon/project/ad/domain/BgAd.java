package com.dragon.project.ad.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dragon.framework.aspectj.lang.annotation.Excel;
import com.dragon.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 广告对象 bg_ad
 * 
 * @author Dragon Wen
 * @date 2019-08-28
 */
public class BgAd extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增id */
    private Long adId;

    /** 广告名称 */
    @Excel(name = "广告名称")
    private String adName;

    /** 广告图片地址 */
    @Excel(name = "广告图片地址")
    private String adImg;

    /** 广告网址 */
    @Excel(name = "广告网址")
    private String adurl;

    /** 广告开始日期 */
    @Excel(name = "广告开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date adStartDate;

    /** 广告结束日期 */
    @Excel(name = "广告结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date adEndDate;

    /** 广告点击数 */
    @Excel(name = "广告点击数")
    private Long adClickNum;

    /** 广告类型 */
    @Excel(name = "广告类型")
    private String positionType;

    /** 广告位置代码 */
    @Excel(name = "广告位置代码")
    private Integer dataFlag;

    /** 删除标志 -1:删除 1:有效 */
    @Excel(name = "删除标志 -1:删除 1:有效")
    private String deleteFlag;

    public void setAdId(Long adId) 
    {
        this.adId = adId;
    }

    public Long getAdId() 
    {
        return adId;
    }
    public void setAdName(String adName) 
    {
        this.adName = adName;
    }

    public String getAdName() 
    {
        return adName;
    }
    public void setAdImg(String adImg)
    {
        this.adImg = adImg;
    }

    public String getAdImg()
    {
        return adImg;
    }
    public void setAdurl(String adurl) 
    {
        this.adurl = adurl;
    }

    public String getAdurl() 
    {
        return adurl;
    }
    public void setAdStartDate(Date adStartDate) 
    {
        this.adStartDate = adStartDate;
    }

    public Date getAdStartDate() 
    {
        return adStartDate;
    }
    public void setAdEndDate(Date adEndDate) 
    {
        this.adEndDate = adEndDate;
    }

    public Date getAdEndDate() 
    {
        return adEndDate;
    }
    public void setAdClickNum(Long adClickNum) 
    {
        this.adClickNum = adClickNum;
    }

    public Long getAdClickNum() 
    {
        return adClickNum;
    }
    public void setPositionType(String positionType) 
    {
        this.positionType = positionType;
    }

    public String getPositionType() 
    {
        return positionType;
    }
    public void setDataFlag(Integer dataFlag) 
    {
        this.dataFlag = dataFlag;
    }

    public Integer getDataFlag() 
    {
        return dataFlag;
    }
    public void setDeleteFlag(String deleteFlag) 
    {
        this.deleteFlag = deleteFlag;
    }

    public String getDeleteFlag() 
    {
        return deleteFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("adId", getAdId())
            .append("adName", getAdName())
            .append("adImg", getAdImg())
            .append("adurl", getAdurl())
            .append("adStartDate", getAdStartDate())
            .append("adEndDate", getAdEndDate())
            .append("adClickNum", getAdClickNum())
            .append("positionType", getPositionType())
            .append("dataFlag", getDataFlag())
            .append("deleteFlag", getDeleteFlag())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
