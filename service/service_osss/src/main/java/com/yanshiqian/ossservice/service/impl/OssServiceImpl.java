package com.yanshiqian.ossservice.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import com.yanshiqian.ossservice.service.OssService;
import com.yanshiqian.ossservice.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = ConstantPropertiesUtils.END_POIND;
// 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            String fileName = uuid+originalFilename;
            String datePath = new DateTime().toString("yyyy/MM/dd");
            fileName = datePath+"/"+fileName;


// 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
            ossClient.putObject(bucketName, fileName, inputStream);

// 关闭OSSClient。
            ossClient.shutdown();
            String url = "https://"+bucketName+"."+endpoint+"/"+fileName;
            return url;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
