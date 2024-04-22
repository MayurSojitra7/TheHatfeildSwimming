package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Manages the all the operations and user interactions for the Swimming System .
 * maages learnersList, coachList, lessonsList, bookings, Reviews, and generating reports for Learners and Coaches.
 */
public class SwimmingBookSystem {

    private final List<Coach> coachList = new ArrayList<>();
    private final List<Learner> learnersList = new ArrayList<>();
    private final List<Lesson> lessonsList = new ArrayList<>();

    private int incrementId;
    private final Scanner scanner = new Scanner(System.in);

    public SwimmingBookSystem() {
        this.incrementId = 1;
        generateCoaches();
        generateLessons();
        registeredLearners();
    }

    /**
     * generate 3 coachList.
     */
    private void generateCoaches() {
        coachList.add(new Coach("Teja"));
        coachList.add(new Coach("Manu"));
        coachList.add(new Coach("Kalu"));
    }

    /**
     * generate lessonsList with coachList and grade levels.
     */
    private void generateLessons() {
        String[] availableDay = {"Monday", "Wednesday", "Friday", "Saturday"};
        String[][] timeSlots = {{"4-5pm", "5-6pm", "6-7pm"}, {"2-3pm", "3-4pm"}};
        for (int gradeLevel = 1; gradeLevel <= 5; gradeLevel++) {
            for (String day : availableDay) {
                String[] slots = day.equals("Saturday") ? timeSlots[1] : timeSlots[0];
                for (String slot : slots) {
                    lessonsList.add(new Lesson(day, slot, gradeLevel, coachList.get(gradeLevel % coachList.size()), incrementId));
                    incrementId++;
                }
            }
        }
    }

    /**
     * generate 12 learnersList and add into system.
     */
    private void registeredLearners() {
        learnersList.add(new Learner(84655, "Kani", "Male", 5, "564-856-7890", 2));
        learnersList.add(new Learner(67390, "Nanu", "Male", 7, "442-356-7890", 4));
        learnersList.add(new Learner(42865, "Soni", "Female", 8, "634-289-0123", 1));
        learnersList.add(new Learner(71215, "Sonika", "Male", 9, "254-656-7890", 2));
        learnersList.add(new Learner(47578, "Tina", "Male", 5, "284-446-3890", 3));
        learnersList.add(new Learner(32852, "Meel", "Male", 8, "450-456-3890", 4));
        learnersList.add(new Learner(62947, "Reena", "Male", 6, "436-456-7894", 5));
    }

    /**
     * Display the main menu and processes user can select from choices.
     */
    void mainSystem() {
        while (true) {
            System.out.println("\n---  Welcome to The Hatfield Junior Swimming School System --->");
            System.out.println("Select an option:");
            System.out.println("1. Register new learner");
            System.out.println("2. Book swimming lesson");
            System.out.println("3. change and cancel lesson");
            System.out.println("4. Attend swimming lesson");
            System.out.println("5. Monthly learner report");
            System.out.println("6. Monthly coach report");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choiceID = scanner.nextInt();
            scanner.nextLine();

            switch (choiceID) {
                case 1 -> newLearnerRegistration();
                case 2 -> bookingLesson();
                case 3 -> changeOrCancelBooking();
                case 4 -> attendLesson();
                case 5 -> userLearnerReport();
                case 6 -> coachReport();
                case 7 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option, please try again.");
            }
        }
    }

    /**
     * Register a new learner to the system.
     */
    private void newLearnerRegistration() {
        System.out.println("Enter learner name:");
        String name = scanner.nextLine();
        System.out.print("Enter Gender (Male/Female): ");
        String genderInput = scanner.nextLine();
        String gender;
        if (genderInput.equalsIgnoreCase("M")) {
            gender = "Male";
        } else if (genderInput.equalsIgnoreCase("F")) {
            gender = "Female";
        } else {
            System.out.println("Invalid gender input. Defaulting to 'Male'.");
            gender = "Male"; // Default to 'Male' if invalid input
        }
        System.out.println("Enter age:");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter emergency contact number:");
        String contact = scanner.nextLine();
        System.out.println("Enter current grade level (1-5):");
        int gradeLevel = scanner.nextInt();
        scanner.nextLine();
        int learnerId = Learner.generateLearnerId();
        learnersList.add(new Learner(learnerId, name, gender, age, contact, gradeLevel));
        System.out.println(name + " Registered successfully. Your learnerId is: " + learnerId);
        System.out.println("Use Your Learner ID while booking lessonsList");
    }




    /**
     *  the timetable by Coach.
     */
    public void timetableOrCoach(String coachName) {
        System.out.println("Timetable for Coach " + coachName + ":");
        for (Lesson lesson : lessonsList) {
            if (lesson.getCoach().getName().equalsIgnoreCase(coachName)) {
                System.out.println("-".repeat(70));
                System.out.println("Day: " + lesson.getDay() + ", TimeSlot: " + lesson.getTimeSlot() + ", GradeLevel: " + lesson.getGradeLevel() + ", Lesson ID: " + lesson.getLessonID() + ", Vacancy: " + (4 - lesson.getLearners().size()));
            }
        }
    }

    /**
     *  Available Days and Time slots
     */
    public void availableDaysOrTimeSlots() {
        System.out.println("Available Days and Times:");
        System.out.println("Monday: 4-5pm, 5-6pm, 6-7pm");
        System.out.println("Wednesday: 4-5pm, 5-6pm, 6-7pm");
        System.out.println("Friday: 4-5pm, 5-6pm, 6-7pm");
        System.out.println("Saturday: 2-3pm, 3-4pm");
    }



    /**
     * Booking system for book the lesson.
     */
    private void bookingLesson() {
        System.out.println("Select an option:");
        System.out.println("1. Available timetable by day");
        System.out.println("2. Available timetable by grade");
        System.out.println("3. Available timetable by coach");
        System.out.println("Enter Your Choice:");
        int optionId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        switch (optionId) {
            case 1 -> {
                availableDaysOrTimeSlots();
                System.out.println("Enter Day: ");
                String day = scanner.nextLine();
                timeTableOrDay(day);
                break;
            }
            case 2 -> {
                System.out.println("Enter GradeLevel: ");
                int grade = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                timeTableAndGrade(grade);
                break;
            }
            case 3 -> {
                availableCoachRating();
                System.out.println("Enter Coach's Name: ");
                String coachName = scanner.nextLine();
                timetableOrCoach(coachName);
                break;
            }
            default -> System.out.println("Invalid option. Please try again.");
        }
        System.out.println("Enter Learner ID: ");
        int id = scanner.nextInt();
        Learner learner = learnersList.stream().filter(l -> l.getLearnerID() == id).findFirst().orElse(null);

        if (learner == null) {
            System.out.println("Learner not found.");
            return;
        }

        System.out.print("Enter lesson ID: ");
        int lessonId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Check if the lesson ID is already booked by the learner
        if (learner.getBookedLessons().stream().anyMatch(lesson -> lesson.getLessonID() == lessonId)) {
            System.out.println("You have already booked this lesson. Please another choose lesson.");
            return;
        }

        Optional<Lesson> optionalLesson = lessonsList.stream().filter(l -> l.getLessonID() == lessonId).findFirst();

        if (optionalLesson.isPresent()) {
            Lesson lesson = optionalLesson.get();
            lesson.bookLesson(learner);
            learner.bookLesson(lesson);
            System.out.println(" Congratulations! Lesson booked successfully.");
        } else {
            System.out.println("Failed to book the lesson. Please check the details and try again.");
        }

    }
    /**
     *  Available coachList with their ratings
     */
    public void availableCoachRating() {
        System.out.println("Available Coaches:");
        for (Coach coach : coachList) {
            double averageRating = coach.getCoachAverageRating();
            System.out.println(coach.getName() + " -Rating: " + averageRating);
        }
    }
    /**
     *  the timetable by GradeLevel.
     */
    public void timeTableAndGrade(int gradeLevel) {
        System.out.println("Timetable for Grade " + gradeLevel + ":");
        for (Lesson lesson : lessonsList) {
            if (lesson.getGradeLevel() == gradeLevel) {
                System.out.println("-".repeat(70));
                System.out.println("Day: " + lesson.getDay() + ", TimeSlot: " + lesson.getTimeSlot() + ", Coach: " + lesson.getCoach().getName() + ", Lesson ID: " + lesson.getLessonID() + ", Vacancy: " + (4 - lesson.getLearners().size()));
            }
        }
    }


    /**
     *  the timetable by Day.
     */
    public void timeTableOrDay(String day) {
        System.out.println("Timetable for " + day + ":");
        for (Lesson lesson : lessonsList) {
            if (lesson.getDay().equalsIgnoreCase(day)) {
                System.out.println("-".repeat(70));
                System.out.println("Time: " + lesson.getTimeSlot() + ", Grade Level: " + lesson.getGradeLevel() + ", Coach: " + lesson.getCoach().getName() + ", Lesson ID: " + lesson.getLessonID() + ", Vacancy: " + (4 - lesson.getLearners().size()));
            }
        }
    }

    /**
     * change or cancle the booking.
     */

    private void changeOrCancelBooking() {

        System.out.println("Select an Option: ");
        System.out.println("1. Change lesson");
        System.out.println("2. Cancel Lesson: ");
        System.out.println("Enter Your Choice: ");

        int select = scanner.nextInt();
        scanner.nextLine(); // consume new line

        switch (select) {
            case 1 -> ChangeTheBookedLesson();
            case 2 -> CancelTheBookedLesson();
            default -> System.out.println("Invalid option. Please try again.");
        }

    }


    /**
     * Change the previous booked lesson.
     */

    private void ChangeTheBookedLesson() {
        System.out.println("Enter learner ID for change or cancel the booking: ");
        int learnerId = scanner.nextInt();

        Learner learner = findLearnerId(learnerId);

        if (learner == null) {
            System.out.println("Learner not found.");
            return;
        }
        // Display the learner's booked lessonsList
        BookedLessonsForLearner(learnerId);

        // Prompt for lesson ID to  change
        System.out.println("Enter the lesson ID you want to change or change:");
        int oldLessonId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Find the lesson
        Optional<Lesson> oldLesson = findLessonId(oldLessonId);

        if (oldLesson.isPresent()) {
            // Prompt for new lesson ID
            System.out.println("Enter the new lesson ID:");
            int newLessonId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Find the new lesson
            Optional<Lesson> newLesson = findLessonId(newLessonId);

            if (newLesson.isPresent()) {
                // change the booking
                learner.displayChangeBooking(oldLesson.get(), newLesson.get());
                System.out.println("Booking changed successfully.");
            } else {
                System.out.println("New lesson not found.");
            }
        } else {
            System.out.println("Old lesson not found.");
        }


    }

    /**
     * Cancel the booked lesson.
     */

    private void CancelTheBookedLesson() {
        System.out.println("Enter learner ID for change or cancel the booking: ");
        int learnerId = scanner.nextInt();

        Learner learner = findLearnerId(learnerId);


        if (learner == null) {
            System.out.println("Learner not found.");
            return;
        }
        // Display the learner's booked lessonsList
        BookedLessonsForLearner(learnerId);

        // Prompt for lesson ID to cancel
        System.out.println("Enter the lesson ID you want to cancel:");
        int lessonId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Find the lesson
        Optional<Lesson> lessonToCancel = findLessonId(lessonId);

        if (lessonToCancel.isPresent()) {
            learner.cancelForLesson(lessonToCancel.get());
            System.out.println("Booking canceled successfully.");
        } else {
            System.out.println("Lesson not found.");
        }


    }


    /**
     * Attend the booked lessonm.
     */

    private void attendLesson() {
        System.out.println("Enter learner ID for attending the lesson: ");
        int learnerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Learner learner = findLearnerId(learnerId);

        if (learner == null) {
            System.out.println("Learner not found.");
            return;
        }
        // Display the learner's booked lessonsList
        BookedLessonsForLearner(learnerId);

        // Prompt for lesson ID to attend
        System.out.println("Enter the lesson ID you want to attend:");
        int lessonId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Find the lesson
        Optional<Lesson> lessonToAttend = findLessonId(lessonId);

        if (lessonToAttend.isPresent()) {
            // Attend the lesson
            learner.attendLesson(lessonToAttend.get());
            System.out.println("Lesson attended successfully.");

            System.out.println("Enter your rating (1 to 5): ");
            int rating = scanner.nextInt();
            if (rating < 1 || rating > 5) {
                System.out.println("Invalid rating. Rating must be between 1 and 5.");
                return;
            }

            scanner.nextLine(); // Consume newline
            System.out.println("Enter your review about lesson you have attended:");
            String review = scanner.nextLine();

            learner.addFeedbackToLesson(lessonToAttend.get(), review);
            learner.addNewRatingForLesson(lessonToAttend.get(), rating);
            System.out.println("Feedback and rating submitted. Thank you!");
            // Pass rating directly to coach
            lessonToAttend.get().getCoach().updateRating(rating);
        }

    }

    /**
     * find the learner  information by their learner id
     *
     * @param learnerId
     */
    private Learner findLearnerId(int learnerId) { 
        return learnersList.stream().filter(learner -> learner.getLearnerID() == learnerId).findFirst().orElse(null);
    }

    /**
     * find the lesson information by lesson id
     *
     * @param lessonId
     */
    private Optional<Lesson> findLessonId(int lessonId) {
        return lessonsList.stream().filter(lesson -> lesson.getLessonID() == lessonId).findFirst();
    }

    /**
     * Display the booked lesson information by learner using learner id
     *
     * @param learnerId
     */
    private void BookedLessonsForLearner(int learnerId) {
        Learner learner = findLearnerId(learnerId);
        if (learner != null) {
            System.out.println("Booked Lessons for Learner with ID " + learnerId + ":");
            for (Lesson lesson : learner.getBookedLessons()) {
                System.out.println("Lesson ID: " + lesson.getLessonID());
                System.out.println("Day: " + lesson.getDay());
                System.out.println("Time: " + lesson.getTimeSlot());
                System.out.println("Coach: " + lesson.getCoach().getName());
                System.out.println("Grade Level: " + lesson.getGradeLevel());
                System.out.println("------------------------");
            }
        } else {
            System.out.println("Learner not found.");
        }
    }
    /**
     * Generates reports about learnersList  activities with booked, cancelled and attended lessonsList.
     */
    private void userLearnerReport() {
        System.out.println("-".repeat(100));
        System.out.format("%-12s%-8s%-5s%-10s%-12s%-8s%-8s%-10s%-8s%-8s%n", "Learner ID", "Name", "Age", "Gender", "GradeLevel", "Booked", "Cancel", "Attended", "Time", "Coach");
        System.out.println("-".repeat(100));
        IntStream.range(0, learnersList.size()).forEach(i -> {
            Learner learner = learnersList.get(i);
            Lesson lesson = lessonsList.get(i);
            System.out.format("%-12s%-8s%-5s%-10s%-12s%-8s%-8s%-10s%-8s%-8s%n", learner.getLearnerID(), learner.getName(), learner.getAge(), learner.getGender(), learner.getGradeLevel(), learner.getTotalBookedLessons(), learner.getTotalCancelledLessons(), learner.getTotalAttendedLessons(), lesson.getTimeSlot(), lesson.getCoach().getName());
        });
    }

    /**
     * Generates report about  each coachList with their names and respective ratings.
     */
    private void coachReport() {
        System.out.println("-".repeat(40));
        System.out.format("%-20s%-20s%n", "Coach Name", "Average Rating");
        System.out.println("-".repeat(40));
        IntStream.range(0, coachList.size()).forEach(i -> {
            Coach coach = coachList.get(i);
            System.out.format("%-20s%-20s%n", coach.getName(), coach.getCoachAverageRating());
        });
    }
}