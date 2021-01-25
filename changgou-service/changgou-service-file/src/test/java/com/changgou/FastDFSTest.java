package com.changgou;

import com.changgou.util.FastDFSClient;
import org.apache.commons.io.IOUtils;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageServer;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @ClassName FastDFSTest
 * @Description
 * @Author 传智播客
 * @Date 11:53 2020/12/9
 * @Version 2.1
 **/
public class FastDFSTest {

    // 下载附件
    @Test
    public void testDownloadFile() throws Exception {

        String group_name = "group1";
        String remote_filename = "M00/00/00/wKjThF_QQ2mAYjTzAAL6RQQtvo0155.jpg";
        // 下载
        byte[] bytes = FastDFSClient.downloadFile(group_name, remote_filename);

        // 将字节入到磁盘
        IOUtils.write(bytes, new FileOutputStream("D:\\103.jpg"));
    }

    // 删除附件
    @Test
    public void testDeleteFile() throws Exception {

        String group_name = "group1";
        String remote_filename = "M00/00/00/wKjThF_QQ2mAYjTzAAL6RQQtvo0155.jpg";
        // 删除
        FastDFSClient.deleteFile(group_name, remote_filename);
    }

    // 获取附件信息
    @Test
    public void testGetFileInfo() throws Exception {

        String group_name = "group1";
        String remote_filename = "M00/00/00/wKjThF_QTXiAS1nBAAL6RQQtvo0907.jpg";
        // 删除
        FileInfo fileInfo = FastDFSClient.getFileInfo(group_name, remote_filename);
        System.out.println("ip:" + fileInfo.getSourceIpAddr());
        System.out.println("size:" + fileInfo.getFileSize());
        System.out.println("date:" + fileInfo.getCreateTimestamp());
    }

    // 获取存储服务器信息
    @Test
    public void testGetStorageServer() throws Exception {

        String groupName = "group1";
        StorageServer storageServer = FastDFSClient.getStorageServer(groupName);
        System.out.println("ip:" + storageServer.getInetSocketAddress().getAddress().getHostAddress());
        System.out.println("port:" + storageServer.getInetSocketAddress().getPort());
        System.out.println("index:" + storageServer.getStorePathIndex());
    }

    // 获取多个存储服务器信息
    @Test
    public void testGetStorageServers() throws Exception {

        String groupName = "group1";
        StorageServer[] storageServers = FastDFSClient.getStorageServers(groupName);
        StorageServer storageServer = storageServers[0];
        System.out.println("ip:" + storageServer.getInetSocketAddress().getAddress().getHostAddress());
        System.out.println("port:" + storageServer.getInetSocketAddress().getPort());
        System.out.println("index:" + storageServer.getStorePathIndex());
    }
}
