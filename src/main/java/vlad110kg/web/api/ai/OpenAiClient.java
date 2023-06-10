package vlad110kg.web.api.ai;

import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vlad110kg.web.api.map.AddressDto;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OpenAiClient implements AiClient {

    private static final String SYSTEM_ROLE = "system";
    private static final String ASSISTANT_ROLE = "assistant";

    private final OpenAiService aiService;

    @Value("${ai.model}")
    private String model;

    public List<String> askAbout(AddressDto addressDto) {
        var template = String.format("""
            Hello. I want to ask what do you know about the street %s in the %s,%s.
            1-2 sentences about it's history. Up to 10 sentences about person it's named for.
            """, addressDto.getRoad(), addressDto.getCity(), addressDto.getCountry());
        var msg = new ChatMessage();
        msg.setRole(SYSTEM_ROLE);
        msg.setContent(template);

        var req = ChatCompletionRequest.builder()
            .model(model)
            .messages(List.of(msg))
            .build();
        var completion = aiService.createChatCompletion(req);
        var choices = completion.getChoices();
        return choices
            .stream()
            .map(ChatCompletionChoice::getMessage)
            .filter(cm -> ASSISTANT_ROLE.equals(cm.getRole()))
            .map(ChatMessage::getContent)
            .toList();
    }


}
