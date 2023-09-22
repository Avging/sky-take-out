package com.avging.controller.admin;

import com.avging.constant.MessageConstant;
import com.avging.result.Result;
import com.avging.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * 通用接口
 */
@RestController
@RequestMapping("/admin/common")
@Api(tags = "通用接口")
@Slf4j
public class CommonController {
    @Autowired
    private AliOssUtil aliOssUtil;

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    //因为返回的是一段阿里云的网址，所以是返回值类型是String类型
    public Result<String> upload(MultipartFile file){
        log.info("文件上传：{}",file);


        try {
            //原始文件名
            String originalFilename = file.getOriginalFilename();
            //截取原始文件名后缀  .png
            String substring = originalFilename.substring(originalFilename.indexOf("."));
            //构造新文件名
            String ObjectName = UUID.randomUUID().toString() + substring;

            //文件的请求路径
            String filePath = aliOssUtil.upload(file.getBytes(), ObjectName);
            return Result.success(filePath);
        } catch (IOException e) {
            log.error("文件上传失败：{}",e);
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
