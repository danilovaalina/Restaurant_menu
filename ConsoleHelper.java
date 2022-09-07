public class ConsoleHelper {
    package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

    public class ConsoleHelper {
        private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        public static void writeMessage(String message) {
            System.out.println(message);
        }

        public static String readString() throws IOException {
            return reader.readLine();
        }

        public static List<Dish> getAllDishesForOrder() throws IOException {
            List<Dish> dishes = new ArrayList<>();
            ConsoleHelper.writeMessage("Please choose a dish from the list: \n" +
                    Dish.allDishesToString() +
                    "\n or type 'exit' to complete the order");
            while (true) {
                String dishName = readString().trim();
                if (dishName.equals("exit")) {
                    break;
                }
                try {
                    Dish dish = Dish.valueOf(dishName);
                    dishes.add(dish);
                    writeMessage(dishName + "has been successfully added to your order");
                } catch (Exception e) {
                    writeMessage(dishName + " hasn't been detected");
                }
            }
            return dishes;
        }
    }

}