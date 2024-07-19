package com.example.api.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

public class CyclicBarrierTest {
    public static void main(String[] args) {
        AtomicInteger a = new AtomicInteger();
        System.out.println(a.get());
        System.out.println(a.addAndGet(3));
        System.out.println(a.getAndAdd(2));
        System.out.println(a.get());

        int numIndicators = 5;
        CyclicBarrier barrier = new CyclicBarrier(numIndicators, new KpiAggregator());

        for (int i = 0; i < numIndicators; i++) {
            new Thread(new KpiIndicatorCalculator(barrier, i)).start();
        }
    }
}

class KpiIndicatorCalculator implements Runnable {
    private CyclicBarrier barrier;
    private int indicatorId;

    public KpiIndicatorCalculator(CyclicBarrier barrier, int indicatorId) {
        this.barrier = barrier;
        this.indicatorId = indicatorId;
    }

    @Override
    public void run() {
        try {
            double result = calculateIndicator(indicatorId);
            KpiAggregator.addResult(indicatorId, result);
            barrier.await(); // 等待其他指标计算完成
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    private double calculateIndicator(int indicatorId) {
        // 模拟计算指标的耗时操作
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double result = Math.random() * 100; // 模拟指标计算结果
        System.out.println("Indicator " + indicatorId + " calculated: " + result);
        return result;
    }
}

class KpiAggregator implements Runnable {
    private static double[] results = new double[5];

    public static synchronized void addResult(int indicatorId, double result) {
        results[indicatorId] = result;
    }

    @Override
    public void run() {
        double kpi = 0;
        for (double result : results) {
            kpi += result; // 简单求和作为示例，实际可能是复杂的计算
        }
        kpi /= results.length; // 求平均值作为最终KPI
        System.out.println("Final KPI calculated: " + kpi);
    }
}