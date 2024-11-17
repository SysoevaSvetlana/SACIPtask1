import java.util.Random;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        int[] inputSizes = {10000000, 20000000, 30000000, 40000000, 50000000};
        List<Long> durations = new ArrayList<>();


        for (int size : inputSizes) {
            int[] inputArray = generateRandomArray(size);
            long duration = 0;
            for (int i = 0; i < 99; i++) {
                long startTime = System.nanoTime();
                int[] compressedArray = ArrayCompressor.compressArray(inputArray);
                long endTime = System.nanoTime();
                duration += endTime - startTime;
            }

            durations.add(duration/99);
            System.out.println("Input size: " + size + ", Time: " + duration + " ns");
        }

        SwingUtilities.invokeLater(() -> createAndShowGraph(inputSizes, durations));
    }

    private static void createAndShowGraph(int[] inputSizes, List<Long> durations) {
        XYSeries series = new XYSeries("Array Compression");
        for (int i = 0; i < inputSizes.length; i++) {
            series.add(inputSizes[i], durations.get(i));
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Array Compression Performance",
                "Input Size",
                "Time (ns)",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        ChartPanel chartPanel = new ChartPanel(chart);
        JFrame frame = new JFrame("Array Compression Performance");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(100);
        }
        return arr;
    }

}