package com.example.api;

import java.util.Arrays;
import java.util.Stack;

public class Dijsktra {
    private int v;

    public Dijsktra(int v) {
        this.v = v;
    }

    public Integer dij(int[][] arr, int start, int end) {
        int[] dis = new int[v];
        Arrays.fill(dis, 99999);
        for (int i = 0;i < v;i ++) {
            dis[i] = arr[start][i];
        }
        boolean[] visited = new boolean[v];
        visited[start] = true;

        int[] parent = new int[v];
        Arrays.fill(parent, -1);

        int preV = -1;
        for (int count = 1;count < v;count ++) {
            int min = 99999;
            for (int j = 0;j < v;j ++) {
                if (!visited[j] && dis[j] < min) {
                    min = dis[j];
                    preV = j;
                }
            }
            visited[preV] = true;
            for (int j = 0;j < v;j ++) {
                if (!visited[j] && dis[j] > arr[preV][j] + dis[preV]) {
                    dis[j] = arr[preV][j] + dis[preV];
                    parent[j] = preV;
                }
            }
        }

        if (dis[end] == 99999) {
            System.out.println("no way");
            return -1;
        } else {
            Integer res = dis[end];
            System.out.println("shortest: " + res);
            Stack<Integer> stack = new Stack<>();
            stack.push(end);
            while (parent[end] != -1) {
                stack.push(parent[end]);
                end = parent[end];
            }
            stack.push(start);
            System.out.print(stack.pop());
            while (!stack.isEmpty()) {
                System.out.print("->");
                System.out.print(stack.pop());
            }
            return res;
        }
    }

    private int minDistanceVertice(int dis[], boolean visited[]) {
        int min = 99999;
        int minIndex = -1;

        for (int i = 0;i < v;i ++) {
            if (!visited[i] && dis[i] <= min) {
                min = dis[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
}
