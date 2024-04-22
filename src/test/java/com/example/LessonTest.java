package com.example;
import org.junit.jupiter.api.Test;
public class LessonTest {
    @Test
    void BookLesson() {
        Lesson lessonList = new Lesson("Monday", "4-5pm", 3, new Coach("Darshan"), 1);
        Learner learnerList = new Learner(45644, "Kanu", "Female", 7, "123-456-7890", 1);
        lessonList.bookLesson(learnerList);
    }
}
