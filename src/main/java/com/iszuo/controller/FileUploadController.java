package com.iszuo.controller;

import com.iszuo.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class FileUploadController{

//    @PostMapping("/upload")
//    public Result<String> upload( /*筒化上传操作的工具类*/MultipartFile file) throws IOException{
//        System.out.println("123");
//        String originalFilename = file.getOriginalFilename();
//        System.out.println(originalFilename);
//        String filePath = "src/main/resources/uploadFile" + file.getOriginalFilename();
//        System.out.println(filePath);
//        // 把文件存储到本地磁盘上
//        file.transferTo(new File(filePath));
//        return Result.success("访问地址……");
//    }
}
