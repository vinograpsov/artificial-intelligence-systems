package javaapplication1;
import java.awt.*;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class showTry {
    public static void show(ArrayList<Double> data,String title){

        XYSeries series = new XYSeries("XYGraph");
        for (int i = 0; i < data.size(); i++){
            series.add(Double.valueOf(i),data.get(i));
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createXYLineChart(
                title,//Tytuł
                "x- Lable", // x-axis Opis
                "y- Lable", // y-axis Opis
                dataset, // Dane
                PlotOrientation.VERTICAL, // Orjentacja wykresu /HORIZONTAL
                true, // pozkaż legende
                true, // podpowiedzi tooltips
                false
        );

        XYPlot plot = chart.getXYPlot();

        var renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        ChartFrame frame1=new ChartFrame("XYArea Chart",chart);
        frame1.setVisible(true);
        frame1.getChartPanel();
        frame1.setSize(500,400);
    }

}
