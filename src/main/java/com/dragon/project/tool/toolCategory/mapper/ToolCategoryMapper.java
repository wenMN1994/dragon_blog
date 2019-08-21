package com.dragon.project.tool.toolCategory.mapper;

import com.dragon.project.tool.toolCategory.domain.ToolCategory;

import java.util.List;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/6/17 16:27
 * @description： 工具的分类(ToolCategory)$desc Dao层
 * @modified By：
 * @version: 1.0.0
 */
public interface ToolCategoryMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param toolCategoryId 主键
     * @return 实例对象
     */
    ToolCategory selectToolCategoryById(Integer toolCategoryId);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param toolCategory 实例对象
     * @return 对象列表
     */
    List<ToolCategory> selectToolCategoryList(ToolCategory toolCategory);

    /**
     * 新增数据
     *
     * @param toolCategory 实例对象
     * @return 影响行数
     */
    int insertToolCategory(ToolCategory toolCategory);

    /**
     * 修改数据
     *
     * @param toolCategory 实例对象
     * @return 影响行数
     */
    int updateToolCategory(ToolCategory toolCategory);

    /**
     * 通过主键删除数据
     *
     * @param toolCategoryId 主键
     * @return 影响行数
     */
    int deleteToolCategoryByIds(Integer[] toolCategoryId);

}