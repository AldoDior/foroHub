package com.aluracursos.forohub.domain.topic;

import com.aluracursos.forohub.domain.user.User;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    static void save(KafkaProperties.Retry.Topic topic) {
    }

    Optional<User> findUsuarioById(Long id);

    boolean existsByTituloAndMensaje(String titulo, String mensaje);
}
