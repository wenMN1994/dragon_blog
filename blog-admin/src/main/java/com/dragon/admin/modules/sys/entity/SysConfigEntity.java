package com.dragon.admin.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 系统配置信息
 *
 * @author Dragon Wen
 */
@Data
@TableName("sys_config")
public class SysConfigEntity {
	@TableId
	private Long id;
	/**
	 * 系统归属
	 */
	@NotBlank(message = "系统归属不能为空")
	private String paramBelongToSystem;
	/**
	 * 应用功能
	 */
	@NotBlank(message = "应用功能不能为空")
	private String paramFunction;
	/**
	 * 参数键
	 */
	@NotBlank(message="参数键不能为空")
	private String paramKey;
	/**
	 * 参数值
	 */
	@NotBlank(message="参数值不能为空")
	private String paramValue;
	/**
	 * 应用父级键
	 */
	private String parentParamKey;
	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 创建者
	 */
	private Long createBy;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新者
	 */
	private Long updateBy;

	/**
	 * 更新时间
	 */
	private Date updateTime;

}
