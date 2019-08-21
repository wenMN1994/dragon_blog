package com.dragon.project.tool.toolCategory.service.impl;

import com.dragon.project.tool.toolCategory.domain.ToolCategory;
import com.dragon.project.tool.toolCategory.mapper.ToolCategoryMapper;
import com.dragon.project.tool.toolCategory.service.ToolCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/6/17 16:27
 * @description： 工具的分类(ToolCategory)$desc Service实现层
 * @modified By：
 * @version: 1.0.0
 */
@Service
public class ToolCategoryServiceImpl implements ToolCategoryService {
    @Autowired
    private ToolCategoryMapper toolCategoryMapper;

    @Override
    public ToolCategory selectToolCategoryById(Integer toolCategoryId) {
        return this.toolCategoryMapper.selectToolCategoryById(toolCategoryId);
    }

    @Override
    public List<ToolCategory> selectToolCategoryList(ToolCategory toolCategory) {
        return this.toolCategoryMapper.selectToolCategoryList(toolCategory);
    }

    @Override
    public int insertToolCategory(ToolCategory toolCategory) {
        return toolCategoryMapper.insertToolCategory(toolCategory);
    }

    @Override
    public int updateToolCategory(ToolCategory toolCategory) {
        return toolCategoryMapper.updateToolCategory(toolCategory);
    }

    @Override
    public int deleteToolCategoryByIds(Integer[] toolCategoryId) {
        return toolCategoryMapper.deleteToolCategoryByIds(toolCategoryId);
    }
}