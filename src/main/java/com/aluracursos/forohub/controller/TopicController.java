package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.topic.Topic;
import com.aluracursos.forohub.domain.topic.TopicRegistrationRequest;
import com.aluracursos.forohub.domain.topic.TopicRepository;
import com.aluracursos.forohub.domain.topic.TopicUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @PostMapping
    public ResponseEntity<KafkaProperties.Retry.Topic> createTopic(@RequestBody TopicRegistrationRequest request) {
        KafkaProperties.Retry.Topic topic = new KafkaProperties.Retry.Topic();
        TopicRepository.save(topic);
        return ResponseEntity.status(HttpStatus.CREATED).body(topic);
    }

    @GetMapping
    public List<Topic> listTopics() {
        return topicRepository.findAll();
    }

    @GetMapping("/{topicId}")
    public ResponseEntity<Topic> getTopic(@PathVariable Long topicId) {
        return topicRepository.findById(topicId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{topicId}")
    public ResponseEntity<Topic> updateTopic(@PathVariable Long topicId, @RequestBody TopicUpdateRequest request) {
        return topicRepository.findById(topicId)
                .map(existingTopic -> {
                    existingTopic.updateData(request);
                    topicRepository.save(existingTopic);
                    return ResponseEntity.ok(existingTopic);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{topicId}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long topicId) {
        if (topicRepository.existsById(topicId)) {
            topicRepository.deleteById(topicId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}




