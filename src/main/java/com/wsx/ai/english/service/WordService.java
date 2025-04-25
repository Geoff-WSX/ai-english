package com.wsx.ai.english.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wsx.ai.english.entity.Word;

public interface WordService extends IService<Word> {
    Word getOne(Word appointment);
}