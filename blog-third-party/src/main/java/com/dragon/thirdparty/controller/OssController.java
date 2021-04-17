package com.dragon.thirdparty.controller;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.dragon.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/10/22 22:46
 * @description：
 * @modified By：
 * @version: $
 */
@RestController
public class OssController {

    @Autowired
    OSS ossClient;

    /**
     * 请填写您的AccessKeyId
     */
    @Value("${spring.cloud.alicloud.access-key}")
    private String accessKeyId;

    /**
     * 请填写您的AccessKeySecret
     */
    @Value("${spring.cloud.alicloud.secret-key}")
    private String accessKeySecret;

    /**
     * 请填写您的 endpoint。
     */
    @Value("${spring.cloud.alicloud.oss.endpoint}")
    private String endpoint;

    /**
     * 请填写您的 bucketname
     */
    @Value("${spring.cloud.alicloud.oss.bucket}")
    private String bucket;

    @RequestMapping("/oss/policy")
    public Result policy(){

        //host的格式为 bucketname.endpoint
        String host = "https://" + bucket + "." + endpoint;
        // callbackUrl为 上传回调服务器的URL，请将下面的IP和Port配置为您自己的真实信息。
        // String callbackUrl = "http://88.88.88.88:8888";
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        //用户上传文件时指定的前缀。
        String dir = format+"/";

        Map<String, String> respMap = null;
        try {
            long expireTime = 30;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            // PostObject请求最大可支持的文件大小为5 GB，即CONTENT_LENGTH_RANGE为5*1024*1024*1024。
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);

            respMap = new LinkedHashMap<String, String>();
            respMap.put("accessid", accessKeyId);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
            // respMap.put("expire", formatISO8601Date(expiration));
        } catch (Exception e) {
            // Assert.fail(e.getMessage());
            System.out.println(e.getMessage());
        } finally {
            ossClient.shutdown();
        }
        return Result.ok().put("data", respMap);
    }

    /**
     * 删除阿里云oss上的文件
     * @param fileName
     * @return
     */
    @RequestMapping("/oss/delete")
    public Result delete(@RequestParam("fileName") String fileName){
        try {
            String[] split = fileName.split("/");
            fileName = split[3] + "/"+ split[4];
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
            ossClient.deleteObject(bucket, fileName);

        } catch (OSSException e) {
            e.printStackTrace();
            return Result.error();
        } catch (ClientException e) {
            e.printStackTrace();
            return Result.error();
        } finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }
        return Result.ok();
    }

    /**
     * 批量删除阿里云oss上的文件
     * @param fileName
     * @return
     */
    @RequestMapping("/oss/deleteBatch")
    public Result deleteBatch(@RequestBody() List<String> fileName){
        try {
            List<String> FileNameList = new ArrayList<String>();
            for (String file : fileName) {
                String[] split = file.split("/");
                String deleteFileName = split[3] + "/"+ split[4];
                FileNameList.add(deleteFileName);
            }
            ossClient.deleteObjects(new DeleteObjectsRequest(bucket).withKeys(FileNameList));
        } catch (OSSException e) {
            e.printStackTrace();
            return Result.error();
        } catch (ClientException e) {
            e.printStackTrace();
            return Result.error();
        } finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }
        return Result.ok();
    }
}
