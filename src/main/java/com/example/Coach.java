package com.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Coach {
    private final String name;
    private final List<Integer> ratings;

    public Coach(String name) {
        this.name = name;
        this.ratings = new ArrayList<>();
    }

    public void updateRating(int rating) {
        if (rating >= 1 && rating <= 5) {
            this.ratings.add(rating);
        } else {
            System.out.println("Invalid rating. Must be between 1 and 5.");
        }
    }

    public double getCoachAverageRating() {
        if (this.ratings.isEmpty()) {
            return 0.0;
        } else {
            double sum = 0.0;

            int rating;
            for(Iterator<Integer> var3 = this.ratings.iterator(); var3.hasNext(); sum += (double)rating) {
                rating = (Integer)var3.next();
            }

            return sum / (double)this.ratings.size();
        }
    }

    public String getName() {
        return this.name;
    }

}
