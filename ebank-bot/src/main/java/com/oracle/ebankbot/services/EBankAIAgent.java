package com.oracle.ebankbot.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;



@Service
public class EBankAIAgent {
    private ChatClient chatClient;

    public EBankAIAgent(ChatClient.Builder chatClient, ChatMemory chatMemory, ToolCallbackProvider tools) {

        this.chatClient = chatClient
                .defaultSystem(
                        " Vous êtes un assistant qui se charge à répondre aux questions d'utilisateurs à propos des clients et des comptes bancaires en fonction du contexte fourni. Si aucun contexte fourni, réponds avec je ne sais pas "
                )
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .defaultTools(tools)
                .build();
    }


    public String chat(String query, String conversationId) {

        return chatClient.prompt()
                .user(query)
                .advisors(advisor -> advisor.param(
                        ChatMemory.CONVERSATION_ID,
                        conversationId
                ))
                .call()
                .content();

    }
}
