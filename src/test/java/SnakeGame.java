
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class SnakeGame {
    LinkedList<Pair<Integer, Integer>> snakeBody;
    int[][] food;
    int foodCount;
    int width;
    int height;

    public SnakeGame(int width, int height, int[][] food) {
        this.food = food;
        this.width = width;
        this.height = height;
        this.snakeBody = new LinkedList<>();
        Pair<Integer, Integer> begin = Pair.of(0, 0);
        snakeBody.addFirst(begin);
    }

    public int move(String direction) {
        Pair<Integer, Integer> curPosition = snakeBody.peekFirst();
        int row = curPosition.getKey();
        int col = curPosition.getValue();
        switch (direction) {
            case "U":
                row --;
                break;
            case "D":
                row ++;
                break;
            case "L":
                col --;
                break;
            case "R":
                col ++;
                break;
        }
        //out
        if (row < 0 || row >= height || col < 0 || col >= width) {
            return -1;
        }

        Pair<Integer, Integer> newPosition = Pair.of(row, col);
        //bite itsself
        if (snakeBody.contains(newPosition)
                && !newPosition.equals(snakeBody.getLast())) {
            return  -1;
        }
        if (foodCount < food.length && food[foodCount][0] == row && food[foodCount][1] == col) {
            foodCount ++;
        } else {
            snakeBody.pollLast();
        }

        snakeBody.addFirst(newPosition);
        return snakeBody.size() - 1;
    }


    public static void main(String[] args) {

        int[][] food = {{1,2}, {0,1}};
        SnakeGame game = new SnakeGame(3,2,food);
        System.out.println(game.move("R"));
        System.out.println(game.move("D"));

        System.out.println(game.move("R"));

        System.out.println(game.move("U"));

        System.out.println(game.move("L"));

        System.out.println(game.move("U"));


    }
}
