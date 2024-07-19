package com.example.api;

import java.util.Arrays;

public class Jing {
    public String tictactoe(String[] board) {
        if (null == board || board.length == 0) {
            throw new IllegalArgumentException("board can not be empty.");
        }
        boolean withBlank = false;
        int horizontalTotal = 0;
        int verticalTotal = 0;
        int colNum = board[0].length();
        int xWinNum = (int)'X' * colNum;
        int oWinNum = (int)'O' * colNum;
        int leftDiagonal = 0;
        int rightDiagonal = 0;
        for (int i = 0;i < board.length;i ++) {
             horizontalTotal = 0;
             verticalTotal = 0;
            for (int j = 0;j < colNum;j ++) {
                if (' ' == board[i].charAt(j) || ' ' == board[j].charAt(i)) {
                    withBlank = true;
                }
                horizontalTotal += (int)board[i].charAt(j);
                verticalTotal += (int)board[j].charAt(i);
            }

            if (horizontalTotal == xWinNum || verticalTotal == xWinNum) {
                return  "X";
            }
            if (horizontalTotal == oWinNum || verticalTotal == oWinNum) {
                return "O";
            }

            leftDiagonal += board[i].charAt(i);
            rightDiagonal += board[i].charAt(colNum - 1 - i);
        }

        if (leftDiagonal == xWinNum || rightDiagonal == xWinNum) {
            return  "X";
        }
        if (leftDiagonal == oWinNum || rightDiagonal == oWinNum) {
            return "O";
        }

        if (withBlank) {
            return "Pending";
        } else {
            return "Draw";
        }




    }
}
