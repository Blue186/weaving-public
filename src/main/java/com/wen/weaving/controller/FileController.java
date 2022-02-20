package com.wen.weaving.controller;

import com.wen.weaving.utils.FileUtil;
import com.wen.weaving.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Api(tags = {"文件上传相关接口"},value = "文件上传相关接口")
@RestController
@RequestMapping("/file")
public class FileController {
    @Resource
    private FileUtil fileUtil;

//    这里就是上传图片，返回图片地址的接口
    @ApiOperation(value = "上传图片接口")
    @PostMapping("/images")
    public R<?> uploadImages(@ApiParam(value = "图片的base64数组",name = "base64Str")@RequestBody String[] base64Str){
        System.out.println("图片上传了");
        if (base64Str==null){
            return R.error().message("图片为空");
        }
        List<String> urls = new ArrayList<>();
        for (String s : base64Str) {
            String url = fileUtil.uploadImage(s);
            if (url.equals("0")){
                return R.error().message("上传失败，上传图片数据为空");
            }
            if (url.equals("-1")){
                return R.error().message("上传失败，数据不合法");
            }
            if (url.equals("-2")){
                return R.error().message("上传图片格式不合法,支持jpg,ico,png,gif");
            }
            urls.add(url);
        }
        return R.ok().data(urls);
    }
}
