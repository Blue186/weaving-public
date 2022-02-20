package com.wen.weaving.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wen.weaving.entity.NoteList;
import com.wen.weaving.mapper.NoteListMapper;
import com.wen.weaving.service.NoteListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NoteListServiceImpl extends ServiceImpl<NoteListMapper, NoteList> implements NoteListService {
    @Resource
    private NoteListMapper noteListMapper;
}
