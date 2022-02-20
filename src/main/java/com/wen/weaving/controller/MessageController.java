package com.wen.weaving.controller;

import com.wen.weaving.entity.Message;
import com.wen.weaving.entity.vo.MessageConll.MessageInfo;
import com.wen.weaving.service.MessageImgsService;
import com.wen.weaving.service.MessageService;
import com.wen.weaving.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = {"建议反馈"},value = "建议反馈")
@RestController
@RequestMapping("/message")
public class MessageController {
    @Resource
    private MessageService messageService;
    @Resource
    private MessageImgsService messageImgsService;

    @ApiOperation(value = "提交建议信息")
    @PostMapping()
    private R<?> InsertMessage(@ApiParam(name = "",value = "",required = true)@RequestBody MessageInfo messageInfo){
        System.out.println("建议提交了");
        System.out.println(messageInfo);
        Message message = new Message();
        message.setUid(messageInfo.getId());
        message.setContent(messageInfo.getContent());
        message.setContactInfo(messageInfo.getContactInfo());
        int res = messageService.getBaseMapper().insert(message);
        if (res==1){
            messageImgsService.insertImgUrls(messageInfo.getImgUrls(), message.getId());
        }
        return R.ok();
    }
    @ApiOperation(value = "获取所有的建议反馈信息")
    @GetMapping("/all/{current}/{limit}")
    private R<?> getMessages(@PathVariable long current,@PathVariable long limit){
        List<MessageInfo> allMessageInfos = messageService.getAllMessageInfos(current, limit);
        return R.ok().data(allMessageInfos);
    }

    @ApiOperation(value = "获取一条信息")
    @GetMapping("/one/{id}")
    private R<?> getOneMessage(@PathVariable Integer id){
        MessageInfo oneMessage = messageService.getOneMessage(id);
        return R.ok().data(oneMessage);
    }
}
