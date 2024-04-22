package com.example;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class SwimmingBookSystemTest {
    @Test
    void LearnerNewRegister() {
        SwimmingBookSystem swimmingBookSystem = new SwimmingBookSystem();
        ByteArrayInputStream in = new ByteArrayInputStream("Meet\nF\n4\n123-456-7890\n1\n".getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter learner name:");
        String name = scanner.nextLine();
        assertEquals("Meet", name);
        scanner.nextLine();
        int learnerId = Learner.generateLearnerId();
        System.out.println(name + " Registered successfully. Your learnerId is: " + learnerId);
        scanner.nextLine();

        System.out.println("Use Your Learner ID while booking lessons");
    }
}
