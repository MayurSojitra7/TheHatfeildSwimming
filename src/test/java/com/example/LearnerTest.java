package com.example;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
public class LearnerTest {
    @Test
    void BookLearner() {
        Learner learnerList = new Learner(14545, "Me", "Female", 7, "123-565-7890", 1);
        Lesson lessonList = new Lesson("Monday", "4-5pm", 3, new Coach("Manu"), 2);
        learnerList.bookLesson(lessonList);
        assertTrue(learnerList.getBookedLessons().contains(lessonList));
    }
}
