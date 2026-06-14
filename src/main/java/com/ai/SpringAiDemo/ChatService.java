package com.ai.SpringAiDemo;

import lombok.AllArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ChatService {

    private final ChatModel chatModel;

    public String getResponse(String prompt){
        return chatModel.call(prompt);
    }

    public String getResponseOptions(String prompt){
        ChatResponse chatResponse = chatModel.call(
                new Prompt(prompt,
                        OpenAiChatOptions.builder()
                                .model("gpt-5.4-mini")
                                .temperature(0.4)
                                .build())
        );
        return chatResponse.getResult().getOutput().getText();
    }
}
