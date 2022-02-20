package com.wen.weaving.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wen.weaving.entity.Message;
import com.wen.weaving.entity.MessageImgs;
import com.wen.weaving.entity.vo.MessageConll.MessageInfo;
import com.wen.weaving.mapper.MessageMapper;
import com.wen.weaving.service.MessageImgsService;
import com.wen.weaving.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService{
    @Resource
    private MessageMapper messageMapper;
    @Resource
    private MessageImgsService messageImgsService;


    @Override
    public List<MessageInfo> getAllMessageInfos(long current, long limit) {
        Page<Message> page = new Page<>(current,limit);

        QueryWrapper<Message> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        page(page,wrapper);
        List<Message> records = page.getRecords();
        List<MessageInfo> infos = new ArrayList<>();
        for (Message record : records) {
            MessageInfo messageInfo = new MessageInfo();
            messageInfo.setId(record.getId());
            messageInfo.setContent(record.getContent());
            messageInfo.setContactInfo(record.getContactInfo());

            QueryWrapper<MessageImgs> wrapper_Img = new QueryWrapper<>();
            wrapper_Img.eq("mess_id",record.getId());
            List<MessageImgs> messageImgs = messageImgsService.getBaseMapper().selectList(wrapper_Img);
            List<String> urls = new ArrayList<>();
            for (MessageImgs messageImg : messageImgs) {
                urls.add(messageImg.getImgUrl());
            }
            messageInfo.setImgUrls(urls);
            infos.add(messageInfo);
        }
        return infos;
    }

    @Override
    public MessageInfo getOneMessage(Integer id) {
        Message message = baseMapper.selectById(id);
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setId(message.getId());
        messageInfo.setContent(message.getContent());
        messageInfo.setContactInfo(messageInfo.getContactInfo());
        QueryWrapper<MessageImgs> wrapper = new QueryWrapper<>();
        wrapper.eq("mess_id",id);
        List<MessageImgs> messageImgs = messageImgsService.getBaseMapper().selectList(wrapper);
        List<String> urls = new ArrayList<>();
        for (MessageImgs messageImg : messageImgs) {
            urls.add(messageImg.getImgUrl());
        }
        messageInfo.setImgUrls(urls);
        return messageInfo;
    }
}
