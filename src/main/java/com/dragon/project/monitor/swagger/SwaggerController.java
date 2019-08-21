package com.dragon.project.monitor.swagger;

import com.dragon.framework.web.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/8/6 21:59
 * @description： swagger 接口
 * @modified By：
 * @version: 1.0.0
 */
@Controller
@RequestMapping("/monitor/swagger")
public class SwaggerController extends BaseController {
    @RequiresPermissions("monitor:swagger:view")
    @GetMapping()
    public String index() {
        return redirect("/swagger-ui.html");
    }
}
