package com.wsx.ai.english.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wsx.ai.english.entity.Word;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WordMapper extends BaseMapper<Word> {

    Integer selectWordId(LambdaQueryWrapper<Word> queryWrapper);

    void removeWordById(LambdaQueryWrapper<Word> queryWrapper);
}