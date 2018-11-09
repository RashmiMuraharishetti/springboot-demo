package com.cognizant.playground.springbootdemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lessons")
public class LessonsController {

    private final LessonRepository repository;
    private final LazyService service;


    public LessonsController(LessonRepository repository, LazyService service){
        this.repository = repository;
        this.service = service;
    }
    @GetMapping
    public Iterable<Lesson> all(){
        return this.repository.findAll();
    }

    @PostMapping
    public Lesson create(@RequestBody Lesson lesson){
        return this.repository.save(lesson);
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody Lesson lesson) {

        // find Lesson object by id
        Optional<Lesson> targetLessonOptional = this.repository.findById(lesson.getId());
        Lesson targetLesson = null;
        if (targetLessonOptional.isPresent()) {
            targetLesson = targetLessonOptional.get();
        }

        // update values that are different
        String body = "";
        HttpStatus status;
        if(targetLesson != null) {

            this.repository.save(lesson);
            body = "Successful";
            status = HttpStatus.ACCEPTED;

        }
        else{
            body = "Record does not exist";
            status = HttpStatus.BAD_REQUEST;

        }
        return new ResponseEntity<String>(body,status);

    }

    @DeleteMapping
    public Lesson delete(@RequestHeader Long id) {
        this.repository.delete(new Lesson(id));

        return new Lesson(id);
    }

    @PostMapping("/lazy")
    public Integer allByDate(@RequestBody String dateString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        List<Lesson> lessons = null;
        try {
            lessons = service.getLessons(simpleDateFormat.parse(dateString.trim()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (Lesson lesson:lessons) {
            this.repository.save(lesson);
        }
        return lessons.size();
    }
}
