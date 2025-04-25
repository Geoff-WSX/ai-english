package com.wsx.ai.english.tools;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wsx.ai.english.entity.Word;
import com.wsx.ai.english.mapper.WordMapper;
import com.wsx.ai.english.service.WordService;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WordTools {

    @Autowired
    private WordService wordService;

    @Tool(name = "添加单词", value = "根据参数，先执行工具方法addAndQueryWord查询单词是否存在，如果存在就直接返回已存在的数据，如果不存在就把单词保存到数据库")
    public Word addAndQueryWord(Word word) {
        //查找数据库中是否包含对应的单词记录
        Word wordDB = wordService.getOne(word);
        if (wordDB == null) {
            word.setId(null);//防止大模型幻觉设置了id
            wordService.save(word);
            //保存到数据库
            return word;
        } else {
            //查询到单词
            return wordDB;
        }
    }

    @Tool(name = "删除单词", value = "当用户想要删除库中单词时，根据参数，查询单词是否存在，如果存在则删除单词记录并返回删除单词成功，否则返回删除单词失败")
    public String removeWord(Word word) {

        Word wordDB = wordService.getOne(word);
        if (wordDB != null) {
            //删除预约记录
            if (wordService.removeById(wordDB.getId())) {
                return "删除单词成功";
            } else {
                return "删除单词失败";
            }
        }
        //删除失败
        return "单词不存在";
    }

    @Autowired
    private WordMapper wordMapper;

    @Tool(name = "查询所有单词", value = "根据参数，查询库中所有单词")
    public List<Word> checkWord() {
        List<Word> words = wordMapper.selectList(null);
        return words;
    }

    @Tool(name = "根据单词查询id", value = "根据参数，查询库中单词id")
    public List<Integer> checkWordId(List<Word> word) {
        List<Integer> ids = null;
        word.forEach(word1 -> {
            LambdaQueryWrapper<Word> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Word::getEnglish, word1.getEnglish());
            ids.add(wordMapper.selectWordId(queryWrapper));
        });
        return ids;
    }

    @Tool(name = "根据id删除的单词", value = "根据参数，删除库中单词")
    public String removeWordById(List<Integer> ids) {
        ids.forEach(id -> {
            LambdaQueryWrapper<Word> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Word::getId, id);
            wordMapper.removeWordById(queryWrapper);
        });
        return "删除成功";
    }
}