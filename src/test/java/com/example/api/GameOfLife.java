package com.example.api;

import java.util.HashSet;
import java.util.Set;

public class GameOfLife {
    int row;
    int col;
    private static final int[][] directions = {{-1, -1},{-1, 0},{-1, 1},{0, -1},{0, 1},{1, -1},{1, 0},{1, 1}};
    public void gameOfLife(int[][] board) {
        row = board.length;
        col = board[0].length;
        int[][] copyBoard = new int[row][col];
        for (int i = 0;i < row;i ++) {
            for (int j = 0;j < col;j ++) {
                copyBoard[i][j] = board[i][j];
            }
        }
        int liveNeighbors = 0;
        boolean changeByNextGeneration;
        for (int i = 0;i < row;i ++) {
            for (int j = 0;j < col;j ++) {
                changeByNextGeneration = false;
                liveNeighbors = getLiveNeighborCount(i, j, copyBoard);
                if (1 == copyBoard[i][j]) {
                    if (liveNeighbors < 2 || liveNeighbors > 3) {
                        changeByNextGeneration = true;
                    }
                } else if (0 == copyBoard[i][j]) {
                    if (liveNeighbors == 3) {
                        changeByNextGeneration = true;
                    }
                }

                if (changeByNextGeneration) {
                    board[i][j] = (copyBoard[i][j] + 1) & 1;
                } else {
                    board[i][j] = copyBoard[i][j];
                }
            }

        }

        for (int i = 0;i < row;i ++) {
            for (int j = 0;j < col;j ++) {
                System.out.print(board[i][j] + ",");
            }
            System.out.println();
        }
    }

    private int getLiveNeighborCount(int i, int j, int[][] board) {
        int liveNeighbors = 0;

        for (int num = 0;num < directions.length;num ++) {
            int row = directions[num][0] + i;
            int col = directions[num][1] + j;
            if (judge(row, col) && board[row][col] == 1) {
                liveNeighbors ++;
            }
        }
        return liveNeighbors;
    }

    private boolean judge(int i, int j) {
        if (i >= 0 && i < row && j >= 0 && j < col) {
            return true;
        } else {
            return false;
        }
    }


}
