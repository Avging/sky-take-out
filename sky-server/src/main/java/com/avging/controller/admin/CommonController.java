package com.avging.controller.admin;

import com.avging.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 通用接口
 */
@RestController
@RequestMapping("/admin/common")
@Api(tags = "通用接口")
@Slf4j
public class CommonController {

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    //因为返回的是一段阿里云的网址，所以是返回值类型是String类型
    public Result<String> upload(MultipartFile file){
        log.info("文件上传：{}",file);
        return null;
    }
}
