package com.cognizant.playground.springbootdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestController {
    @Mock
    private LessonRepository mockRepo;
    @Mock
    private LazyService mockLazy;

    @Test
    public void testGetRequest() {


        //assertTrue(true);

        //setup
        ArrayList expectedResult = new ArrayList();

        LessonsController lessonController = new LessonsController(mockRepo, mockLazy);
        when(mockRepo.findAll()).thenReturn(expectedResult);

        //Execute
        ArrayList actualResult = (ArrayList) lessonController.all();

        //Assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void testPostRequest() {
        //assertTrue(true);
        //setup
        Lesson expectedResult = new Lesson();

        LessonsController lessonController = new LessonsController(mockRepo, mockLazy);
        when(mockRepo.save(expectedResult)).thenReturn(expectedResult);

        //Execute
        Lesson actualResult = lessonController.create(expectedResult);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testUpdateRequest() {
        //Setup
        Lesson expectedResult = new Lesson();

        LessonsController lessonController = new LessonsController(mockRepo, mockLazy);


        //Execute
        Lesson actualResult = lessonController.create(expectedResult);

        //Assert
        then(mockRepo).should(times(1)).save(expectedResult);
    }

    @Test
    public void testDeleteRequest() {
        //assertTrue(true);
        //setup
        Lesson expectedResult = new Lesson((long) 2);

        LessonsController lessonController = new LessonsController(mockRepo, mockLazy);
        //when(mockRepo.delete(expectedResult)).thenReturn(expectedResult);

        //Execute
        Lesson actualResult = lessonController.delete(expectedResult.getId());

        //Assert
        assertEquals(expectedResult.getId(), actualResult.getId());
    }

    @Test
    public void testLazyRequest() {
        //assertTrue(true);

        // return number of records
        //setup
        Integer expectedResult = 2;
        Date date = new Date();
        String strDate = "2018-09-09";
        ArrayList<Lesson> lessons = new ArrayList<>();
        Lesson testLesson = new Lesson();
        lessons.add(testLesson);
        lessons.add(testLesson);

        LessonsController lessonController = new LessonsController(mockRepo, mockLazy);
        when(mockLazy.getLessons(date)).thenReturn(lessons);

        //Execute
        Integer actualResult = lessonController.allByDate(strDate);

        //Assert
        then(mockLazy).should(times(1)).getLessons(date);
        then(mockRepo).should(times(2)).save(testLesson);
        assertEquals(expectedResult, actualResult);
    }

}
