package com.dragon.project.chart.server.controller;

import com.dragon.framework.web.controller.BaseController;
import com.dragon.project.chart.server.domain.Server;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/01 13:43
 * @description： 服务器监控
 * @modified By：
 * @version: 1.0.0
 */
@Controller
@RequestMapping("/chart/server")
public class ServerController extends BaseController {
    private String prefix = "chart/server";

    @RequiresPermissions("chart:server:view")
    @GetMapping()
    public String server(ModelMap mmap) throws Exception {
        Server server = new Server();
        server.copyTo();
        mmap.put("server", server);
        return prefix + "/server";
    }
}
