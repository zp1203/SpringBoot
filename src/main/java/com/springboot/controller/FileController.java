package com.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * Created by zp on 2019/4/25.
 * @author zp
 */
@Controller
@RequestMapping(value = "/file/")
@Slf4j
public class FileController {

    /**
     * 获取 上传文件路径
     */
    @Value(value = "${filePath}")
    private String filePath;




    /**
     * 先展示 fileUploadAndDownload 页面
     *
     * @return
     */
    @RequestMapping(value = "uploadReady")
    public String upLoadReady(){
        return "fileUploadAndDownload";
    }




    /**
     * 在选择文件点击上传(单文件上传)
     * @param multipartFile
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "upload")
    public String upLoad(@RequestParam("cdssfile") MultipartFile multipartFile){
        log.info("单文件上传开始");
        //获得文件名
        String fileName = multipartFile.getOriginalFilename();
        //对文件名进行处理
        fileName = filePath + UUID.randomUUID() + fileName;
        //文件对象
        File file = new File(fileName);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        try {
            multipartFile.transferTo(file);
            return "上传成功";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";

    }

    /**
     * 在选择文件点击上传(多文件上传)
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "multiUpload")
    public void multUpload(@RequestParam("multiFile") MultipartFile[] multipartFiles,HttpServletRequest request){
        //获得文件名
//        String fileName = multipartFile.;
//        System.out.println("11");

    }




    /**
     * 文件的下载
     *
     */
    @RequestMapping(value = "downLoad/{mc}")
    public void downLoad(HttpServletResponse response, @PathVariable String mc) throws IOException {
//        String a = new String("hello.html".getBytes("gb2312"), "ISO8859-1" );
//        System.out.println(a);
        //找到文件
        File file = new File("D:\\web2\\"+mc);
        //文件流
        FileInputStream fileInputStream = new FileInputStream(file);

        response.setContentType("application/force-download");
        //设置下载的文件名
        response.setHeader("Content-disposition","attachment;fileName="+new String(mc.getBytes("gb2312"), "ISO8859-1" ));
        OutputStream os = response.getOutputStream();
        byte[] buf = new byte[1024];
        int  len = 0;
        while((len=fileInputStream.read(buf))!=-1){
            os.write(buf,0,len);
            os.flush();
        }
        os.close();
        fileInputStream.close();
    }

}
