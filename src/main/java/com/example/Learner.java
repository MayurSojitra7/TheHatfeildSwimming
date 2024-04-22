package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a learner in the Hatfield Junior Swimming School (HJSS).
 * Stores details such as name, gender, age, emergency contact, and grade level.
 * Also manages the learner's feedback on attended lessonsList.
 */
public class Learner {

    private final int learnersID;
    private final String name;
    private final String gender;
    private int age;
    private final String emergencyContactNumber;
    private int gradeLevel;
    private final Map<Lesson, String> attendedLessonsWithReview;
    private final Map<Lesson, Integer> attendedLessonsWithRatings;
    private List<Lesson> bookedForLessons;
    private List<Lesson> cancelledForLessons;
    private List<Lesson> attendedForLessons;



    /**
     * Constructs a new Learner with the specified details.
     * @param learnersID        the learner's ID
     * @param name             the learner's name
     * @param gender           the learner's gender
     * @param age              the learner's age
     * @param emergencyContactNumber the learner's emergency contact number
     * @param gradeLevel       the learner's current grade level in swimming
     */

    public Learner(int learnersID, String name, String gender, int age, String emergencyContactNumber, int gradeLevel ){
        if (learnersID != 0) {
            this.learnersID = learnersID;
        } else {
            this.learnersID = generateLearnerId();
        }
        this.name = name;
        this.gender = gender;
        this.setAge(age);
        this.emergencyContactNumber = emergencyContactNumber;
        this.setGradeLevel(gradeLevel);
        this.attendedLessonsWithReview = new HashMap<>();
        this.attendedLessonsWithRatings = new HashMap<>();
        this.bookedForLessons = new ArrayList<>();
        this.cancelledForLessons= new ArrayList<>();
        this.attendedForLessons = new ArrayList<>();

    }



    /**
     * Sets the grade level of the learner, ensuring it is between 0 and 5.
     *
     * @param gradeLevel the learner's grade level
     */

    public void setGradeLevel(int gradeLevel) {
        if (gradeLevel >= 1 && gradeLevel <= 5) {
            this.gradeLevel = gradeLevel;
        } else {
            throw new IllegalArgumentException("Grade level must be between 1 and 5.");
        }
    }

    /**
     * Records review for an attended lesson.
     *
     * @param lesson  the attended lesson
     * @param feedback the feedback given by learner to lesson
     */

    public void addFeedbackToLesson(Lesson lesson, String feedback) {
        this.attendedLessonsWithReview.put(lesson, feedback);
    }

    /**
     * Sets the age of the learner, ensuring it is between 4 and 11.
     *
     * @param age the learner's age
     */

    public void setAge(int age) {
        if (age >= 4 && age <= 11) {
            this.age = age;
        } else {
            throw new IllegalArgumentException("Age must be between 4 and 11.");
        }
    }


    /**
     * Records a rating for an attended lesson.
     *
     * @param lesson the attended lesson
     * @param rating the rating given by learner to the lesson
     */
    public void addNewRatingForLesson(Lesson lesson, int rating) {
        if (rating >= 1 && rating <= 5) {
            this.attendedLessonsWithRatings.put(lesson, rating);
        } else {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }
    }

    public int getLearnerID(){
        return learnersID;
    }
    public String getName(){
        return name;
    }

    public String getGender() {
        return gender;
    }

    public  int getAge(){
        return age;
    }

    public int getGradeLevel(){
        return gradeLevel;
    }

    /**
     * Updates the learner's grade level to the next level after attending a higher grade lesson.
     */
    public void upgradeGradeLevel() {
        if (this.gradeLevel < 5) { // Ensures the grade level does not exceed 5.
            this.gradeLevel += 1;
        }
    }

    /**
     * Gets a details of attended lessonsList with their feedback.
     *
     * @return a map of lessonsList to review strings
     */

    /**
     * Gets a map of attended lessonsList with their ratings.
     *
     * @return a map of lessonsList to integer ratings
     */
    public int getTotalAttendedLessons() {
        return attendedForLessons.size();
    }

    public void bookLesson(Lesson lesson) {
        bookedForLessons.add(lesson);
    }

    /**
     * Gets a List of Booked lessonsList with their respective learner.
     *
     * @return a List of lessonsList to Booked by  learnersList
     */
    public List<Lesson> getBookedLessons() {
        return bookedForLessons;
    }

    public static int generateLearnerId() {
        // Generate a random 5-digit learner ID
        int learnerId = (int) (Math.random() * 90000) + 10000; // Generate a 5-digit number
        return learnerId;
    }

    public int getTotalCancelledLessons() {
        return cancelledForLessons.size();
    }

    /**
     * Gets a List of Booked lessonsList with their respective learner.
     *
     * @return a List of lessonsList to Attended by  learnersList
     */
    public List<Lesson> getAttendedLessons(){
        return attendedForLessons;
    }
    public void attendLesson(Lesson lesson) {
        if (bookedForLessons.contains(lesson)) {
            // Remove the lesson from booked lessonsList
            bookedForLessons.remove(lesson);

            // Add the lesson to attended lessonsList
            attendedForLessons.add(lesson);

            // Check if the attended lesson's grade level is higher
            // If yes, upgrade the learner's grade level
            if (lesson.getGradeLevel() > gradeLevel) {
                upgradeGradeLevel();
            }
        } else {
            System.out.println("Cannot attend lesson. Lesson not booked.");
        }

    }
    public void displayChangeBooking(Lesson oldLesson, Lesson newLesson) {
        if (bookedForLessons.contains(oldLesson)) {
            // Remove the old lesson from booked lessonsList
            bookedForLessons.remove(oldLesson);

            // Add the new lesson to booked lessonsList
            bookedForLessons.add(newLesson);
        } else {
            System.out.println("Cannot change booking. Lesson not booked.");
        }
    }

    public int getTotalBookedLessons() {
        return bookedForLessons.size();
    }
    public void cancelForLesson(Lesson lesson){
        if (bookedForLessons.contains(lesson)) {
            // Remove the lesson from booked lessonsList
            bookedForLessons.remove(lesson);

            // Add the lesson to cancelled lessonsList
            cancelledForLessons.add(lesson);
        } else {
            System.out.println("Cannot cancel lesson. Lesson not booked.");
        }
    }



}
