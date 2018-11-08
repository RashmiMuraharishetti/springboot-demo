package com.cognizant.playground.springbootdemo;

import org.springframework.data.repository.CrudRepository;

public interface LessonRepository extends CrudRepository<Lesson, Long> {

}
