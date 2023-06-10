package vlad110kg.config;

import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class AiConfig {

    @Value("${ai.key}")
    private String apiKey;

    @Bean
    public OpenAiService openAIApi() {
        return new OpenAiService(apiKey, Duration.ofMinutes(10));
    }
}
