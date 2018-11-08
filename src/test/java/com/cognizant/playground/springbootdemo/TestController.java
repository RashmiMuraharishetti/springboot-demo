package com.cognizant.playground.springbootdemo;
import com.cognizant.playground.springbootdemo.LessonsController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.cognizant.playground.springbootdemo.LessonsController;

import javax.validation.constraints.AssertTrue;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestController  {
    @Mock
    private LessonRepository mockRepo;

    @Test
    public void testGetRequest(){
        //assertTrue(true);

        //setup
        ArrayList expectedResult = new ArrayList();

        LessonsController lessonController = new LessonsController(mockRepo);
        when(mockRepo.findAll()).thenReturn(expectedResult);

        //Execute
        ArrayList actualResult = (ArrayList) lessonController.all();

        //Assert
        assertEquals(expectedResult,actualResult);

    }
    @Test
    public void testPostRequest(){
        //assertTrue(true);
        //setup
        Lesson expectedResult = new Lesson();

        LessonsController lessonController = new LessonsController(mockRepo);
        when(mockRepo.save(expectedResult)).thenReturn(expectedResult);

        //Execute
        Lesson actualResult = lessonController.create(expectedResult);

        //Assert
        assertEquals(expectedResult,actualResult);
    }
    @Test
    public void testDeleteRequest(){
        //assertTrue(true);
        //setup
        Lesson expectedResult = new Lesson((long) 2);

        LessonsController lessonController = new LessonsController(mockRepo);
        //when(mockRepo.delete(expectedResult)).thenReturn(expectedResult);

        //Execute
        Lesson actualResult = lessonController.delete(expectedResult.getId());

        //Assert
        assertEquals(expectedResult.getId(),actualResult.getId());
    }

}
