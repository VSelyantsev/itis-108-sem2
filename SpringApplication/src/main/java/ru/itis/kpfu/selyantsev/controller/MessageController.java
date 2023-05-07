package ru.itis.kpfu.selyantsev.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.kpfu.selyantsev.dto.request.MessageRequestDto;

@Controller
public class MessageController {

    @GetMapping("/chat")
    public String getChat() {
        return "chat";
    }

    @MessageMapping("/message")
    @SendTo("/topic/message")
    public MessageRequestDto messageRequestDto(MessageRequestDto messageDto) {
        return new MessageRequestDto(String.format("Hello, %s,", messageDto.getName()), "server");
    }
}
