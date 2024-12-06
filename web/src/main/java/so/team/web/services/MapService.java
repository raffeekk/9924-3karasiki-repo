package so.team.web.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class MapService {

    private final WebClient webClient;

    public String fetchMapData(String location) {
        // Замените на ваш реальный API ключ и URL Yandex
        String apiKey = System.getenv("YANDEX_API_KEY");
        String apiUrl = "https://api-maps.yandex.ru/2.1/?apikey=" + apiKey + "&lang=ru_RU";

        return apiUrl; // Возвращаем только URL для интеграции с фронтендом
    }
}