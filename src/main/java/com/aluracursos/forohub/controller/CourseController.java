package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.course.Course;
import com.aluracursos.forohub.course.CourseRegistrationRequest;
import com.aluracursos.forohub.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping
    public ResponseEntity<Object> registerCourse(@RequestBody CourseRegistrationRequest request) {
        Course course = new Course(request);
        courseRepository.save(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }

    @GetMapping
    public List<Course> listCourses() {
        return courseRepository.findAll();
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Object> getCourse(@PathVariable Long courseId) {
        return courseRepository.findById(courseId)
                .map(course -> ResponseEntity.ok(course))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<Object> updateCourse(@PathVariable Long courseId, @RequestBody CourseRegistrationRequest request) {
        return courseRepository.findById(courseId)
                .map(existingCourse -> {
                    try {
                        existingCourse.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    courseRepository.save((Course) existingCourse);
                    return ResponseEntity.ok(existingCourse);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId) {
        if (courseRepository.existsById(courseId)) {
            courseRepository.deleteById(courseId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
