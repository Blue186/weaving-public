package com.wen.weaving.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wen.weaving.entity.ColorListImgs;
import com.wen.weaving.mapper.ColorListImgsMapper;
import com.wen.weaving.service.ColorListImgsService;
import org.springframework.stereotype.Service;

@Service
public class ColorListImgsImpl extends ServiceImpl<ColorListImgsMapper, ColorListImgs> implements ColorListImgsService {
}
