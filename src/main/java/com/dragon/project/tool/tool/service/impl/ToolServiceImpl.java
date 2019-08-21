package com.dragon.project.tool.tool.service.impl;

import com.dragon.project.tool.tool.domain.Tool;
import com.dragon.project.tool.tool.mapper.BgToolMapper;
import com.dragon.project.tool.tool.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/6/17 16:27
 * @description： 常用工具(Tool)$desc Service实现层
 * @modified By：
 * @version: 1.0.0
 */
@Service
public class ToolServiceImpl implements ToolService {

    @Autowired
    private BgToolMapper bgToolMapper;

    @Override
    public Tool selectToolById(Integer toolId) {
        return this.bgToolMapper.selectToolById(toolId);
    }

    @Override
    public List<Tool> selectToolList(Tool tool) {
        return this.bgToolMapper.selectToolList(tool);
    }

    @Override
    public int insertTool(Tool tool) {
        return bgToolMapper.insertTool(tool);
    }

    @Override
    public int updateTool(Tool tool) {
        return bgToolMapper.updateTool(tool);
    }

    @Override
    public int deleteToolByIds(Integer[] toolId) {
        return bgToolMapper.deleteToolByIds(toolId);
    }
}