package com.example.api;

import com.example.api.controller.Canva;
import org.junit.jupiter.api.Test;

public class CanvaTest {

    @Test
    public void f() {
        String[] testCase = new String[]{"O  ","   ","   "};
        String[][] t = new String[][] {{"sdf"},{"wer"}};
        Canva canva = new Canva();
        int[][] testBoard = {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
        canva.gameOfLife(testBoard);
    }

    @Test
    public void ff() {
        Clock clock = new  Clock();
        System.out.println(clock.angleClock(1, 57));
    }
}
