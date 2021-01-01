package com.bs.union.util;

import io.minio.*;
import io.minio.messages.Bucket;
import io.minio.messages.ObjectLockConfiguration;
import io.minio.messages.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

/**
 * @Author:zrt
 * @Date:2020/12/26  18:25
 * @Description:com.bs.union.util
 * @version:1.0
 */
public class OssStoreFileUtil {

    /**
     * 用于判断当前bucket是否存在
     *
     * @param minioClient
     * @param bucketName
     * @return
     * @throws Exception
     */
    public static boolean  bucketExists(MinioClient minioClient,String bucketName) throws  Exception{
        boolean found =
                minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        return found;
    }
    /**
     * 删除文件夹的有效期
     *
     * @param minioClient
     * @param bucketName
     * @throws Exception
     */
    public static void  deleteBucketLifecycle(MinioClient minioClient,String bucketName) throws Exception{
        minioClient.deleteBucketLifecycle( DeleteBucketLifecycleArgs.builder().bucket(bucketName).build());
    }
    /**
     * 删除文件夹协议
     * @param minioClient
     * @param bucketName
     * @throws Exception
     */
    public static void deleteBucketPolicy(MinioClient minioClient,String bucketName) throws Exception{
        minioClient.deleteBucketPolicy(DeleteBucketPolicyArgs.builder().bucket(bucketName).build());
    }
    /**
     * 获取文件夹协议
     * @param minioClient
     * @param args
     * @return
     * @throws Exception
     */
    public String getBucketPolicy(MinioClient minioClient,GetBucketPolicyArgs args,String bucketName) throws  Exception{
        String config =
                minioClient.getBucketPolicy(GetBucketPolicyArgs.builder().bucket(bucketName).build());
        return config;
    }
    /**
     * 获取桶标签
     * @param minioClient
     * @param args
     * @param bucketName
     * @return
     * @throws Exception
     */
    public static Tags getBucketTags(MinioClient minioClient,GetBucketTagsArgs args,String bucketName) throws Exception{
        Tags tags = minioClient.getBucketTags(GetBucketTagsArgs.builder().bucket(bucketName).build());
        return tags;
    }
    public static ObjectLockConfiguration getObjectLockConfiguration(MinioClient minioClient,GetObjectLockConfigurationArgs args,String bucketName) throws Exception{
        ObjectLockConfiguration config =
                minioClient.getObjectLockConfiguration(
                        GetObjectLockConfigurationArgs.builder().bucket(bucketName).build());
        return config;
    }
    /**
     * 获取桶列表
     * @param minioClient
     * @return
     * @throws Exception
     */
    public static List<Bucket> listBuckets(MinioClient minioClient) throws Exception{
        List<Bucket> bucketList =
                 minioClient.listBuckets();
        return bucketList;
    }
    /**
     * 上传文件
     * @param minioClient
     * @param bucketName
     * @param objectName
     * @param in
     * @param size
     * @throws Exception
     */
    public void  putObject(MinioClient minioClient,String bucketName,String objectName,InputStream in,Integer size) throws Exception{
        minioClient.putObject(
                PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(
                        in, size, -1)
                        .contentType("video/mp4")
                        .build());
    }
}
