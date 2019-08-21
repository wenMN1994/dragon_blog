package com.dragon.project.blog.tag.mapper;

import com.dragon.project.blog.tag.domain.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/10 06:29
 * @description： 标签处理的mapper
 * @modified By：
 * @version: 1.0.0
 */
public interface TagMapper {
    /**
     * 根据id批量删除tag
     *
     * @param ids 需要删除的tag的id
     * @return 受影响的行数
     */
    int deleteTagByIds(Integer[] ids);

    /**
     * 根据条件查询tag集合
     *
     * @param tag 包含查询条件的tag类
     * @return 符合条件的集合
     */
    List<Tag> selectTagList(Tag tag);

    /**
     * 新增tag
     *
     * @param tag 需要新增的tag集合
     * @return 受影响的行数
     */
    int insertTag(Tag tag);

    /**
     * 根据Id获取tag的信息
     *
     * @param tagId 需要获取的tag的id
     * @return 查询出的tag
     */
    Tag selectTagById(Integer tagId);

    /**
     * 修改tag
     *
     * @param tag 需要修改的tag的实体类的信息
     * @return 受影响的行数
     */
    int updateTag(Tag tag);

    /**
     * 根据tag的标题查找tag
     *
     * @param tag 需要查找的tag的标题
     * @return 查找到的tag实体
     */
    Tag selectTagByTagTitle(String tag);

    /**
     * 根据tag的的title获取id集合
     *
     * @param tagTitles 需要获取的tag的String类型的数组
     * @return 符合条件的tag的List
     */
    List<Integer> selectTagIdsByTagTitles(@Param("tagTitles") String[] tagTitles);
}
