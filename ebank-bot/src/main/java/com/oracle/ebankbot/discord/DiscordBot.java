package com.oracle.ebankbot.discord;


import com.oracle.ebankbot.services.EBankAIAgent;
import com.zgamelogic.discord.annotations.DiscordController;
import com.zgamelogic.discord.annotations.DiscordMapping;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@DiscordController
public class DiscordBot {
    private EBankAIAgent eBankAIAgent;
    private static final int MAX_MESSAGE_LENGTH = 1900;

    public DiscordBot (EBankAIAgent eBankAIAgent) {
        this.eBankAIAgent = eBankAIAgent;
    }

    @DiscordMapping
    private void perform(MessageReceivedEvent event){
        if(event.getAuthor().isBot()) return;
        String query = event.getMessage().getContentRaw();
        String conversationId = event.getChannel().getId();
        String response = eBankAIAgent.chat(query,conversationId);
        //event.getChannel().sendMessage(response).queue();

        sendLongMessage( event, response);
    }


    private void sendLongMessage(MessageReceivedEvent event, String message) {

        if (message == null || message.isBlank()) {
            event.getChannel().sendMessage("No response generated.").queue();
            return;
        }

        for (int i = 0; i < message.length(); i += MAX_MESSAGE_LENGTH) {

            String part = message.substring(
                    i,
                    Math.min(i + MAX_MESSAGE_LENGTH, message.length())
            );

            event.getChannel().sendMessage(part).queue();
        }
    }

}
