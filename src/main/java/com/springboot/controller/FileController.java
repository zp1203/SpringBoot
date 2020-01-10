package com.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.springboot.service.DocumentService;
import com.springboot.utils.ResultUtils;
import com.springboot.utils.result.Result;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zp on 2019/4/25.
 * @author zp
 */
@Controller
@RequestMapping(value = "/file/")
@Slf4j
public class FileController {

    @Autowired
    private DocumentService documentService;

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
     * * 在选择文件点击上传(多文件上传)
     * @param request
     * @param response
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "multiUpload")
    public void multUpload(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        if (ServletFileUpload.isMultipartContent(request)) {
            MultipartHttpServletRequest multiReq = (MultipartHttpServletRequest) request;
            HashMap<String, MultipartFile> fileMap = (HashMap<String, MultipartFile>) multiReq.getFileMap();
            InputStream inputStream = null;
            FileOutputStream outputStream = null;
            try {
                //上传是否成功
                boolean status=false;
                //获取文件名
                String fileName = "";
                //获取上传路径
                String url="";
                for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
                    MultipartFile file = entry.getValue();
                    inputStream = file.getInputStream();
                    File fileDir = new File(filePath);
                    if (!fileDir.exists() || !fileDir.isDirectory()) {
                        fileDir.mkdir();
                    }
                    File uploadFile = new File(filePath + UUID.randomUUID() + file.getOriginalFilename());
                    if (uploadFile.exists()) {
                        uploadFile.delete();
                    }
                    uploadFile.createNewFile();
                    outputStream = new FileOutputStream(uploadFile);
                    byte[] bs = new byte[1024];
                    int len = 0;
                    while ((len = inputStream.read(bs)) > 0) {
                        outputStream.write(bs);
                    }
                    System.out.println(file.getOriginalFilename() + "上传成功");
                    status=true;
                    fileName=file.getOriginalFilename();
                    url = filePath + UUID.randomUUID() + file.getOriginalFilename();

                }
                //为返回到页面做准备
                Map<String,Object> m = new HashMap<>(3);
                m.put("status",status);
                m.put("fileName",fileName);
                m.put("fileUrl",url);
                response.getWriter().write(JSON.toJSONString(m));

            } catch (Exception e) {
                e.printStackTrace();
                Map<String,Object> m = new HashMap<>(1);
                m.put("status",false);
                response.getWriter().write(JSON.toJSONString(m));
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Map<String,Object> m = new HashMap<>(1);
                    m.put("status",false);
                    response.getWriter().write(JSON.toJSONString(m));
                }

            }
        }

    }

    @ResponseBody
    @RequestMapping(value = "saveFile")
    public Result<Boolean> saveFile(@RequestParam String fileName, @RequestParam String fileUrl){
        try {
            documentService.saveDocument(fileName,fileUrl);
            return ResultUtils.generate(true);
        }catch (Exception e){
            return ResultUtils.generate(false);
        }

    }



    /**
     * 文件下载
     * @param response
     * @param mc
     * @throws IOException
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
