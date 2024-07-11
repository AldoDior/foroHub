package com.aluracursos.forohub.course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    List<Course> findAll();

    void save(Course course);

    Optional<Object> findById(Long courseId);

    boolean existsById(Long courseId);

    void deleteById(Long courseId);
}
