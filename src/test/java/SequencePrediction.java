import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.classifiers.functions.LinearRegression;

import java.util.ArrayList;
import java.util.List;

public class SequencePrediction {
    public static void main(String[] args) {
        // 创建一个属性列表
        ArrayList<Attribute> attributes = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            attributes.add(new Attribute("Number_" + (i + 1)));
        }
        attributes.add(new Attribute("Last_Number"));

        // 创建Instances对象
        Instances dataset = new Instances("Lottery", attributes, 0);

        // 添加训练数据
        double[][] data = {
                {11, 19, 31, 32, 35, 38, 9},
                {6, 12, 13, 17, 28, 29, 1},
                {11, 13, 15, 20, 30, 38, 8},
                {12, 14, 17, 20, 25, 34, 10},
                {4, 5, 6, 12, 16, 21, 10},
                {19, 21, 27, 30, 34, 37, 5},
                {5, 23, 26, 30, 32, 33, 10},
                {1, 2, 14, 15, 17, 19, 9}
        };

        for (double[] instance : data) {
            DenseInstance inst = new DenseInstance(7);
            for (int i = 0; i < 7; i++) {
                inst.setValue(attributes.get(i), instance[i]);
            }
            dataset.add(inst);
        }

        // 设置最后一个属性为类别
        dataset.setClassIndex(6);

        // 创建神经网络模型
        MultilayerPerceptron mlp = new MultilayerPerceptron();
        try {
            // 设置神经网络参数（这里使用默认参数）
            mlp.buildClassifier(dataset);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 使用模型进行预测
        Instance nextInstance = dataset.lastInstance();
        double[] predictedNumbers;
        try {
            predictedNumbers = mlp.distributionForInstance(nextInstance);
            System.out.print("Predicted Numbers: ");
            for (int i = 0; i < 6; i++) {
                System.out.print((int) predictedNumbers[i] + " ");
            }
            System.out.println((int) predictedNumbers[6]); // 输出最后一个数字
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
