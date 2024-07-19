package com.example.api;

import org.apache.commons.lang3.tuple.Pair;

import java.util.LinkedList;

public class Snake {
    private LinkedList<Pair<Integer, Integer>> snakeBody;

    private int width;

    private int height;

    private int[][] food;

    private int foodCount;

    public Snake(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.food = food;
        this.snakeBody = new LinkedList<>();
        this.snakeBody.add(Pair.of(0, 0));
    }

    public int move(String direction) {
        Pair<Integer, Integer> head = snakeBody.peekFirst();
        Integer row = head.getKey();
        Integer col = head.getValue();
        switch (direction) {
            case "D":
                row ++;
                break;
            case "U":
                row --;
                break;
            case "L":
                col --;
                break;
            case "R":
                col ++;
                break;
        }
        if (row < 0 || row >= height || col < 0 || col >= width) {
            return -1;
        }

        Pair<Integer, Integer> newPosition = Pair.of(row, col);

        if (snakeBody.contains(newPosition) && !newPosition.equals(snakeBody.peekLast())) {
            return -1;
        }
        snakeBody.addFirst(newPosition);
        if (foodCount < food.length && food[foodCount][0] == row && food[foodCount][1] == col) {
            foodCount ++;
        } else {
            snakeBody.pollLast();
        }
        return foodCount;
    }

    public static void main(String[] args) {

        int[][] food = {{1,2}, {0,1}};
        Snake game = new Snake(3,2,food);
        System.out.println(game.move("R"));
        System.out.println(game.move("D"));

        System.out.println(game.move("R"));

        System.out.println(game.move("U"));

        System.out.println(game.move("L"));

        System.out.println(game.move("U"));


    }
}
