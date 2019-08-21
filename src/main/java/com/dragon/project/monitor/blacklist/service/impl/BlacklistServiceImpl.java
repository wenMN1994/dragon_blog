package com.dragon.project.monitor.blacklist.service.impl;

import com.dragon.common.constant.BlacklistConstants;
import com.dragon.common.utils.security.ShiroUtils;
import com.dragon.project.monitor.blacklist.domain.Blacklist;
import com.dragon.project.monitor.blacklist.mapper.BlacklistMapper;
import com.dragon.project.monitor.blacklist.service.BlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/4 09:55
 * @description： 黑名单服务实现类
 * @version: 1.0.0
 */
@Service
public class BlacklistServiceImpl implements BlacklistService {
    @Autowired
    BlacklistMapper blacklistMapper;

    @Override
    public List<Blacklist> selectBlacklistList(Blacklist blacklist) {
        return blacklistMapper.selectBlacklistList(blacklist);
    }

    @Override
    public int deleteBlacklistByIds(Integer[] ids) {
        return blacklistMapper.deleteBlacklistByBlacklistIds(ids);
    }

    @Override
    public Blacklist selectBlacklistById(Integer blacklistId) {
        return blacklistMapper.selectBlacklistById(blacklistId);
    }

    @Override
    public void cleanBlacklist() {
        blacklistMapper.cleanBlacklist();
    }

    @Override
    public int insertBlacklist(Blacklist blacklist) {
        blacklist.setCreateBy(ShiroUtils.getLoginName());
        return blacklistMapper.insertBlacklist(blacklist);
    }

    @Override
    public void updateNewestBlacklist(Integer id, String requestURI) {
        blacklistMapper.updateNewestBlacklist(id, requestURI);
    }

    @Override
    public boolean checkIpIsInBlacklist(String ip) {
        Blacklist blacklist = blacklistMapper.selectBlacklistByIp(ip);
        return blacklist == null ? BlacklistConstants.IP_NOT_IN_BLACKLIST : BlacklistConstants.IP_IN_BLACKLIST;
    }

    @Override
    public Blacklist selectBlacklistByIp(String ip) {
        return blacklistMapper.selectBlacklistByIp(ip);
    }

    @Override
    public int insertBlacklist(String ipAddr) {
        Blacklist blacklist = new Blacklist();
        blacklist.setIpAddr(ipAddr);
        return blacklistMapper.insertBlacklist(blacklist);
    }

    @Override
    public int updateBlacklist(Blacklist blacklist) {
        return blacklistMapper.updateBlacklist(blacklist);
    }
}
