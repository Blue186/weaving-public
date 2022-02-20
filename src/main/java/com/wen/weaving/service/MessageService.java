package com.wen.weaving.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wen.weaving.entity.Message;
import com.wen.weaving.entity.vo.MessageConll.MessageInfo;

import java.util.List;

public interface MessageService extends IService<Message> {
    List<MessageInfo> getAllMessageInfos(long current,long limit);
    MessageInfo getOneMessage(Integer id);
}
