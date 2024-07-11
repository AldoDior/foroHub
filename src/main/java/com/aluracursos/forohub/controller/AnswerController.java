package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.answer.Answer;
import com.aluracursos.forohub.domain.answer.AnswerRegistrationRequest;
import com.aluracursos.forohub.domain.answer.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    private AnswerRepository answerRepository;

    @PostMapping
    public ResponseEntity<Answer> registerAnswer(@RequestBody AnswerRegistrationRequest request) {
        Answer answer = new Answer(request);
        answerRepository.save(answer);
        return ResponseEntity.status(HttpStatus.CREATED).body(answer);
    }

    @GetMapping
    public List<Answer> listAnswers() {
        return answerRepository.findAll();
    }

    @GetMapping("/{answerId}")
    public ResponseEntity<Answer> getAnswer(@PathVariable Long answerId) {
        return answerRepository.findById(answerId)
                .map(answer -> ResponseEntity.ok(answer))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{answerId}")
    public ResponseEntity<Answer> updateAnswer(@PathVariable Long answerId, @RequestBody AnswerRegistrationRequest request) {
        return answerRepository.findById(answerId)
                .map(existingAnswer -> {
                    existingAnswer.updateData(request);
                    answerRepository.save(existingAnswer);
                    return ResponseEntity.ok(existingAnswer);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{answerId}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long answerId) {
        if (answerRepository.existsById(answerId)) {
            answerRepository.deleteById(answerId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
