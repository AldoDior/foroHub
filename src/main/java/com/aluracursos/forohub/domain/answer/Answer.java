package com.aluracursos.forohub.domain.answer;

import jakarta.persistence.*;

@Entity
public class Answer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Long topicId;
    private Long userId;

    public Answer() {}

    public Answer(AnswerRegistrationRequest request) {
        this.content = request.getContent();
        this.topicId = request.getTopicId();
        this.userId = request.getUserId();
    }

    public void updateData(AnswerRegistrationRequest request) {
        this.content = request.getContent();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
