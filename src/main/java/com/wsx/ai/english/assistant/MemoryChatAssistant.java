package com.wsx.ai.english.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

/**
 * ClassName: MemoryChatAssistant
 * Description:
 *
 * @Author 王士新
 * @Create 2025/4/25 11:05
 * @Version 1.0
 */
@AiService(
        wiringMode = EXPLICIT,
        chatModel = "qwenChatModel",
        chatMemoryProvider = "chatMemoryProvider",
        tools = "wordTools"
)
public interface MemoryChatAssistant {
    /**
     * 分离聊天记录
     * @param memoryId
     * @param message
     * @return
     */
    @SystemMessage(fromResource = "english.txt")
    String chat(@MemoryId Long memoryId,@UserMessage  String message);
}
