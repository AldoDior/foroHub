package com.aluracursos.forohub.domain.topic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "topic")
@Table(name = "Topics")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
@JsonIgnoreProperties({"answers"})
public class Topic {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private Long authorId;
    private LocalDate creationDate;
    private TopicStatus status;

    public Topic(TopicRegistrationRequest request) {
        this.title = request.getTitle();
        this.message = request.getMessage();
        this.authorId = request.getAuthorId();
        this.creationDate = LocalDate.now();
        this.status = TopicStatus.OPEN;
    }

    public void updateData(TopicUpdateRequest request) {
        this.title = request.getTitle();
        this.message = request.getMessage();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public TopicStatus getStatus() {
        return status;
    }

    public void setStatus(TopicStatus status) {
        this.status = status;
    }
}
