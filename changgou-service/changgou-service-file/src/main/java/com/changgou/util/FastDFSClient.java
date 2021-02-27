package com.changgou.util;

import com.changgou.file.FastDFSFile;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

/**
 * @ClassName FastDFSClient
 * @Description 操作FastDFS的工具类
 * @Author 传智播客
 * @Date 10:51 2020/12/9
 * @Version 2.1
 **/
public class FastDFSClient {

    // 首先：需要连接FastDFS
    // 初始化FastDFS的配置文件
    static {
        try {
            String filename = "fdfs_client.conf";
            String conf_filename = new ClassPathResource(filename).getPath();
            // 加载配置文件
            ClientGlobal.init(conf_filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author 栗子
     * @Description 附件上传操作
     * @Date 11:06 2020/12/9
     * @param fastDFSFile 封装好的附件信息
     * @return java.lang.String[]
     **/
    public static String[] uploadFile(FastDFSFile fastDFSFile){
        int a;

        try {
            // 取出附件信息
            byte[] file_buff = fastDFSFile.getContent();    // 附件内容
            String file_ext_name = fastDFSFile.getExt();    // 附件扩展名称
            NameValuePair[] meta_list = new NameValuePair[1]; // 元数据
            meta_list[0] = new NameValuePair(fastDFSFile.getAuthor());  // 存放的用户信息

            // 1、创建跟踪服务器客户端
            TrackerClient trackerClient = new TrackerClient();
            // 2、通过跟踪服务器客户端获取服务器端
            TrackerServer trackerServer = trackerClient.getConnection();
            // 3、创建存储服务器客户端
            StorageClient storageClient = new StorageClient(trackerServer, null);
            // 4、文件上传成功后，返回的是图片地址
            // String[] result数组：图片地址  2个元素
            // 0：附件所在的组  1：服务所在的磁盘目录位置
            String[] result = storageClient.upload_file(file_buff, file_ext_name, meta_list);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 获取服务器的ip以及端口
    public static String getUri(){
        try {
            // 获取跟踪服务器ip
            TrackerClient trackerClient = new TrackerClient();
            // 2、通过跟踪服务器客户端获取服务器端
            TrackerServer trackerServer = trackerClient.getConnection();
            String ip = trackerServer.getInetSocketAddress().getAddress().getHostAddress();
            // 获取端口
            int port = ClientGlobal.getG_tracker_http_port();
            String uri = "http://" + ip + ":" + port;
            return uri;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @author 栗子
     * @Description 附件下载
     * @Date 11:52 2020/12/9
     * @param group_name        组名
     * @param remote_filename   文件所在的服务器地址（磁盘目录）
     * @return byte[]
     **/
    public static byte[] downloadFile(String group_name, String remote_filename){
        try {

            // 1、创建跟踪服务器客户端
            TrackerClient trackerClient = new TrackerClient();
            // 2、通过跟踪服务器客户端获取服务器端
            TrackerServer trackerServer = trackerClient.getConnection();
            // 3、创建存储服务器客户端
            StorageClient storageClient = new StorageClient(trackerServer, null);
            // 4、文件下载
            byte[] bytes = storageClient.download_file(group_name, remote_filename);
            return bytes;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @author 栗子
     * @Description 文件删除
     * @Date 12:03 2020/12/9
     * @param group_name
     * @param remote_filename
     * @return void
     **/
    public static void deleteFile(String group_name, String remote_filename){
        try {

            // 1、创建跟踪服务器客户端
            TrackerClient trackerClient = new TrackerClient();
            // 2、通过跟踪服务器客户端获取服务器端
            TrackerServer trackerServer = trackerClient.getConnection();
            // 3、创建存储服务器客户端
            StorageClient storageClient = new StorageClient(trackerServer, null);
            // 4、文件删除
            storageClient.delete_file(group_name, remote_filename);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author 栗子
     * @Description 获取附件信息
     * @Date 12:06 2020/12/9
     * @param group_name
     * @param remote_filename
     * @return org.csource.fastdfs.FileInfo
     **/
    public static FileInfo getFileInfo(String group_name, String remote_filename){
        try {

            // 1、创建跟踪服务器客户端
            TrackerClient trackerClient = new TrackerClient();
            // 2、通过跟踪服务器客户端获取服务器端
            TrackerServer trackerServer = trackerClient.getConnection();
            // 3、创建存储服务器客户端
            StorageClient storageClient = new StorageClient(trackerServer, null);
            // 4、获取附件信息
            FileInfo fileInfo = storageClient.get_file_info(group_name, remote_filename);
            return fileInfo;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @author 栗子
     * @Description 获取存储服务器信息
     * @Date 12:11 2020/12/9
     * @param groupName
     * @return org.csource.fastdfs.StorageServer
     **/
    public static StorageServer getStorageServer(String groupName){
        try {

            // 1、创建跟踪服务器客户端
            TrackerClient trackerClient = new TrackerClient();
            // 2、通过跟踪服务器客户端获取服务器端
            TrackerServer trackerServer = trackerClient.getConnection();
            // 3、获取存储服务器
            StorageServer storeStorage = trackerClient.getStoreStorage(trackerServer, groupName);
            return storeStorage;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @author 栗子
     * @Description 获取多个存储服务器信息（集群）
     * @Date 12:11 2020/12/9
     * @param groupName
     * @return org.csource.fastdfs.StorageServer
     **/
    public static StorageServer[] getStorageServers(String groupName){
        try {

            // 1、创建跟踪服务器客户端
            TrackerClient trackerClient = new TrackerClient();
            // 2、通过跟踪服务器客户端获取服务器端
            TrackerServer trackerServer = trackerClient.getConnection();
            // 3、获取多个存储服务器
            StorageServer[] storeStorages = trackerClient.getStoreStorages(trackerServer, groupName);
            return storeStorages;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
