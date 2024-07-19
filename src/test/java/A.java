import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

public class A {
    int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) throws Exception {
        // 假设你已经有了给定的数列数据
        ArrayList<int[]> sequences = new ArrayList<>();
        sequences.add(new int[]{4, 5, 6, 12, 16, 21, 10});
        sequences.add(new int[]{19, 21, 27, 30, 34, 37, 5});
        sequences.add(new int[]{5, 23, 26, 30, 32, 33, 10});
        sequences.add(new int[]{1, 2, 14, 15, 17, 19, 9});
        sequences.add(new int[]{5, 23, 26, 30, 32, 33, 10});
        sequences.add(new int[]{19, 21, 27, 30, 34, 37, 5});
        sequences.add(new int[]{4, 5, 6, 12, 16, 21, 10});
        sequences.add(new int[]{12, 14, 17, 20, 25, 34, 10});
        sequences.add(new int[]{11, 13, 15, 20, 30, 38, 8});
        sequences.add(new int[]{6, 12, 13, 17, 28, 29, 1});
        sequences.add(new int[]{11, 19, 31, 32, 35, 38, 9});
        sequences.add(new int[]{6, 11, 13, 21, 28, 37, 3});
        sequences.add(new int[]{13, 20, 21, 22, 31, 40, 7});
        sequences.add(new int[]{10, 19, 21, 29, 36, 39, 10});
        sequences.add(new int[]{7, 10, 28, 33, 37, 39, 4});

        // 构建Weka Instances对象
        Instances dataset = buildDataset(sequences);

        // 使用多层感知器算法
        MultilayerPerceptron mlp = new MultilayerPerceptron();
        mlp.setLearningRate(0.1);
        mlp.setMomentum(0.2);
        mlp.setTrainingTime(2000);
        mlp.setHiddenLayers("3");
        mlp.buildClassifier(dataset);

        // 预测下一个可能的数列
        int[] nextSequence = new int[7];
        for (int i = 0; i < 6; i++) {
            Instance instance = new DenseInstance(dataset.numAttributes());
            instance.setDataset(dataset);
            for (int j = 0; j < i; j++) {
                instance.setValue(j, nextSequence[j]);
            }
            double prediction = mlp.classifyInstance(instance);
            nextSequence[i] = Math.max(1, Math.min(40, (int) Math.round(prediction)));
        }

        // 预测最后一位，范围在1到10之间
        Instance instance = new DenseInstance(dataset.numAttributes());
        instance.setDataset(dataset);
        for (int j = 0; j < 6; j++) {
            instance.setValue(j, nextSequence[j]);
        }
        double prediction = mlp.classifyInstance(instance);
        nextSequence[6] = Math.max(1, Math.min(10, (int) Math.round(prediction)));

        System.out.print("下一个可能的数列：");
        for (int num : nextSequence) {
            System.out.print(num + " ");
        }
    }

    private static Instances buildDataset(ArrayList<int[]> sequences) {
        // 设置数据集的属性
        ArrayList<weka.core.Attribute> attributes = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            attributes.add(new weka.core.Attribute("Position" + i));
        }

        // 创建数据集
        Instances dataset = new Instances("SequencePrediction", attributes, sequences.size());
        dataset.setClassIndex(dataset.numAttributes() - 1);

        // 添加每个数列到数据集
        for (int[] sequence : sequences) {
            Instance instance = new DenseInstance(dataset.numAttributes());
            instance.setDataset(dataset);
            for (int i = 0; i < sequence.length; i++) {
                instance.setValue(i, sequence[i]);
            }
            dataset.add(instance);
        }

        return dataset;
    }
}
