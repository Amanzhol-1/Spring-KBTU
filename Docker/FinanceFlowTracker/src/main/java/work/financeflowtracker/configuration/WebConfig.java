package work.financeflowtracker.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Позволяет CORS-запросы к всем эндпоинтам
                .allowedOrigins("http://localhost:3000") // URL вашего фронтенда
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Разрешенные методы
                .allowedHeaders("*") // Разрешенные заголовки
                .allowCredentials(true); // Разрешение на отправку учетных данных
    }
}

