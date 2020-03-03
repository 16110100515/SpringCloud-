package com.atguigu.scw;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ScwProjectApplicationTests {

    @Test
    public void contextLoads() throws FileNotFoundException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String scheme = "http://";
        String endpoint = "oss-cn-shanghai.aliyuncs.com";
// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = "LTAI4FuQ3xnbA8STQivyXZRM";
        String accessKeySecret = "EFf2B1zg5gZhlHtB4wCJaGMBQkUZD0";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(scheme+endpoint, accessKeyId, accessKeySecret);

// 上传文件流。
        InputStream inputStream = new FileInputStream(new File("C:\\Users\\forstudy\\Desktop\\2020.jpg"));
        String bucketName = "lsj-200303";
        String filePath = "imgs/";
        String fileName = UUID.randomUUID().toString().replace("-","")+ "_2020.jpg";
        ossClient.putObject(bucketName, filePath+fileName, inputStream);

//        https://lsj-200303.oss-cn-shanghai.aliyuncs.com/imgs/2020.jpg 上传成功文件访问路径
// 关闭OSSClient。
        String uploadPath = scheme+bucketName+"."+endpoint+"/"+filePath+fileName;
        log.info("上传成功文件路径：{}",uploadPath);
        ossClient.shutdown();
    }

}
