package com.atguigu.scw.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;


@Component
@ConfigurationProperties(prefix = "oss")
@Data
public class OSSTemplate {

    private String scheme;
    private String endpoint;
    // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录
    // https://ram.console.aliyun.com 创建。
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String filePath;



    //文件上传方法：  表单必须以post方式提交请求，enctype必须设置为multipart/data..
    public String uploadImg(MultipartFile file) {//MultipartFile springmvc 的controller接受到的上传的一个文件表单项对象
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        // OSS 类是阿里云SDK中的api，需要引入依赖
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(scheme + endpoint, accessKeyId, accessKeySecret);

        // 上传文件流。
        InputStream inputStream;
        try {
            inputStream = file.getInputStream();
            String fileName = UUID.randomUUID().toString().replace("-", "") + "_"+file.getOriginalFilename();
            ossClient.putObject(bucketName, filePath + fileName, inputStream);
            // http://scw-190826.oss-cn-shanghai.aliyuncs.com/imgs/1.jpg 上传成功的文件访问路径
            // 协议： http://
            // 桶名：scw-190826
            // endpoint：oss-cn-shanghai.aliyuncs.com
            // 文件路径文件名：/imgs/1.jpg
            // 关闭OSSClient。
            String uploadPath = scheme + bucketName + "." + endpoint + "/" + filePath + fileName;
            // log.info("上传成功的文件路径: {}", uploadPath);
            ossClient.shutdown();
            return uploadPath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}