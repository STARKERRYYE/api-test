package com.example.api;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.util.Arrays;
import java.util.Optional;
import java.util.Stack;

public class TreeQ {

       class ListNode {
      int val;
      ListNode next;
      ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

  @Test
  public void f() {
      int[][] grid = new int[][]{{1,2,3},{4,5,6}};

      int row = grid.length;
      int column = grid[0].length;
      for (int i = 0;i < row;i ++) {
          for (int j = 0;j < column;j ++) {
              if (i == 0 && j == 0) {
                  continue;
              }
              if (i == 0) {
                  grid[0][j] = grid[0][j - 1] + grid[0][j];
              } else if (j == 0) {
                  grid[i][0] = grid[i - 1][0] + grid[i][j];
              } else {
                  grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
              }
          }
      }

      System.out.println(grid[row - 1][column - 1]);
  }

}
