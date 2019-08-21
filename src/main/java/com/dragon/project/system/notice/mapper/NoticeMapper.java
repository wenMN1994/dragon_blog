package com.dragon.project.system.notice.mapper;

import com.dragon.project.system.notice.domain.Notice;

import java.util.List;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/02 23:08
 * @description： 公告 数据层
 * @modified By：
 * @version: 1.0.0
 */
public interface NoticeMapper {
    /**
     * 查询通知公告信息
     *
     * @param noticeId 通知公告ID
     * @return 通知公告信息
     */
    public Notice selectNoticeById(Integer noticeId);

    /**
     * 查询通知公告列表
     *
     * @param notice 通知公告信息
     * @return 通知公告集合
     */
    public List<Notice> selectNoticeList(Notice notice);

    /**
     * 新增通知公告
     *
     * @param notice 通知公告信息
     * @return 结果
     */
    public int insertNotice(Notice notice);

    /**
     * 修改通知公告
     *
     * @param notice 通知公告信息
     * @return 结果
     */
    public int updateNotice(Notice notice);

    /**
     * 删除通知公告
     *
     * @param noticeId 通知公告ID
     * @return 结果
     */
    public int deleteNoticeById(Integer noticeId);

    /**
     * 批量删除通知公告
     *
     * @param noticeIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteNoticeByIds(String[] noticeIds);

    /**
     * 获取显示的notice
     *
     * @return notice集合
     */
    List<Notice> selectNoticeDisplay();
}
