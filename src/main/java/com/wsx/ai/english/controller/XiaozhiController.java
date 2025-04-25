package com.wsx.ai.english.controller;

import com.wsx.ai.english.assistant.MemoryChatAssistant;
import com.wsx.ai.english.bean.ChatForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Geoff")
@RestController
@RequestMapping("/geoff")
public class XiaozhiController {

    @Autowired
    private MemoryChatAssistant memoryChatAssistant;
    @Operation(summary = "对话")
    @PostMapping("/chat")
    public String chat(@RequestBody ChatForm chatForm)  {
        return memoryChatAssistant.chat(chatForm.getMemoryId(), chatForm.getMessage());
    }
}