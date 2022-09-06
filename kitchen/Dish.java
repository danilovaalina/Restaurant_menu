package com.javarush.task.task27.task2712.kitchen;

public enum Dish {
    FISH(25),
    STEAK(30),
    SOUP(15),
    JUICE(5),
    WATER(3);

    private int duration;

    Dish(int duration) {
        this.duration = duration;
    }

    public static String allDishesToString() {
        StringBuilder str = new StringBuilder();
        Dish [] dishes = values();
        for (Dish dish : dishes) {
            str.append(dish);
            if (dish != dishes[dishes.length - 1]) {
                str.append(", ");
            }
        }

        return str.toString();
    }

    public int getDuration() {
        return duration;
    }
}
