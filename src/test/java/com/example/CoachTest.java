package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class CoachTest {
    @Test
    void CoachUpdateRating() {
        Coach coach = new Coach("Akash");
        coach.updateRating(4);
        assertEquals(4, coach.getCoachAverageRating());
    }
}
