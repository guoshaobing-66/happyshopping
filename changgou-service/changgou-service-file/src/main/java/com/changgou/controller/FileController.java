package com.changgou.controller;

import com.changgou.file.FastDFSFile;
import com.changgou.util.FastDFSClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ClassName FileController
 * @Description
 * @Author 传智播客
 * @Date 11:07 2020/12/9
 * @Version 2.1
 **/
@RestController
public class FileController {

    /**
     * @author 栗子
     * @Description 文件上传
     * @Date 11:21 2020/12/9
     * @param file
     * @return java.lang.String
     **/
    @PostMapping("/upload")
    public String upload(MultipartFile file) throws Exception {
        String name = file.getOriginalFilename();   // 原始的附件名称  1.jpg
        byte[] content = file.getBytes();           // 附件内容
        String ext = FilenameUtils.getExtension(name);  // 获取扩展名
        // 封装附件信息
        FastDFSFile fastDFSFile = new FastDFSFile(name, content, ext, "103", "tom");
        // 实现文件上传
        String[] result = FastDFSClient.uploadFile(fastDFSFile);
        // 获取附件所在的服务器ip以及端口地址
        String uri = FastDFSClient.getUri();
        String url = uri + "/" + result[0] + "/" + result[1];
        return url;
    }
}
