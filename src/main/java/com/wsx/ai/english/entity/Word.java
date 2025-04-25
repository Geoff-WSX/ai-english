package com.wsx.ai.english.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Word {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String english;
    private String chinese;
    private String phonetic;
    private String example;
    private String createTime;
    private String updateTime;
}