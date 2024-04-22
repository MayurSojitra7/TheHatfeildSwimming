package com.example;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Lesson {
    private final String day;
    private final String timeSlot;
    private final int gradeLevel;
    private final Coach coach;
    private final Set<Learner> learnersList;

    private final int lessonID;

    /**
     * Constructs a new Lesson with the specified details.
     *
     * @param lessonID   the lessonID assign to the each lesson
     * @param day        the day of the week the lesson takes place
     * @param gradeLevel the grade level of the lesson
     * @param timeSlot   the time slot of the lesson
     * @param coach      the coach assigned to the lesson
     */
    public Lesson(String day, String timeSlot, int gradeLevel, Coach coach, int lessonID) {
        this.coach = coach;
        this.lessonID = lessonID;
        this.gradeLevel = gradeLevel;
        this.learnersList = new HashSet<>();
        this.day = day;
        this.timeSlot = timeSlot;
    }


    /**
     * book the lesson for a learner. Checks for space availability,
     * grade level eligibility, and avoids duplicates.
     *
     * @return true if the booking was successful, else it returns false
     */


    public String getTimeSlot() {
        return this.timeSlot;
    }


    public int getLessonID() {
        return this.lessonID;
    }

    public Set<Learner> getLearners() {
        return new HashSet<>(this.learnersList);
    }

    public int getGradeLevel() {
        return this.gradeLevel;
    }

    public String getDay() {
        return this.day;
    }

    public boolean bookLesson(Learner learner) {
        return this.learnersList.size() >= 4 || this.learnersList.contains(learner) || learner.getGradeLevel() != this.gradeLevel && learner.getGradeLevel() + 1 != this.gradeLevel ? false : this.learnersList.add(learner);
    }

    public Coach getCoach() {
        return this.coach;
    }


}
