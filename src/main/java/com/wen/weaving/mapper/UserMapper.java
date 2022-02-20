package com.wen.weaving.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wen.weaving.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
