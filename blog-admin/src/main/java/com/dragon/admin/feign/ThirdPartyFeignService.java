package com.dragon.admin.feign;

import com.dragon.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/11/8 19:32
 * @description：第三方服务远程调用接口
 * @modified By：
 * @version: $
 */
@FeignClient("blog-third-party")
public interface ThirdPartyFeignService {

    /**
     * 删除阿里云oss上的文件
     * @param fileName
     * @return
     */
    @RequestMapping("/oss/deleteBatch")
    Result deleteBatchOssFile(@RequestBody() List<String> fileName);
}
