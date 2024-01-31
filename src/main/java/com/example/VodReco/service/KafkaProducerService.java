package com.example.VodReco.service;

import com.example.VodReco.dto.model.EveryDescription;
import com.example.VodReco.dto.model.EveryGenre;
import com.example.VodReco.dto.model.ToModelDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.util.Json;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private static final String TOPIC = "producing-test";
    @Autowired
    private final KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private final ObjectMapper objectMapper;

    public void sendMessages(List<EveryDescription> descriptionList, List<EveryGenre> genreList) {
        sendMessage("{\"description_data\":" + convertListToJson(descriptionList) + "}");
        sendMessage("{\"genre_data\":" + convertListToJson(genreList));
    }
    public void sendMessage(String message) {
//        System.out.println("description_data = " + toModelDto.getToModelData().get("description_data"));
//        System.out.println("genre_data = " + toModelDto.getToModelData().get("genre_data"));
        kafkaTemplate.send(TOPIC, message);

        // 이 send를 오버라이드해서 message파라미터의 자료형을 각각 다르게 하고 싶다.
        //kafkaTemplate를 상속해서 send메서드를 재정의한 뒤 사용해도 될까? (231114) -> 개어려움 불가~
    }

    private String convertListToJson(List<?> list) {
        try {
            return objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting list to JSON", e);
        }
    }

}
