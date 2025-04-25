package com.wsx.ai.english.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsx.ai.english.entity.Word;
import com.wsx.ai.english.mapper.WordMapper;
import com.wsx.ai.english.service.WordService;
import org.springframework.stereotype.Service;

@Service
public class WordServiceImpl extends ServiceImpl<WordMapper, Word> implements WordService {

    /**
     * 查询单词是否存在
     * @param
     * @return
     */
    @Override
    public Word getOne(Word word) {
        LambdaQueryWrapper<Word> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Word::getEnglish, word.getEnglish());

        Word newWord = baseMapper.selectOne(queryWrapper);
        return newWord;
    }
}