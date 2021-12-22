package agh.ics.oop.gui;

import agh.ics.oop.AbstractWorldMap;
import javafx.application.Platform;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;

public class SideBox {
    VBox mainBox;
    TopBox topBox;
    LineChart<Number, Number> chart;
    XYChart.Series<Number, Number> animals;
    XYChart.Series<Number, Number> plants;
    XYChart.Series<Number, Number> avgEnergy;
    XYChart.Series<Number, Number> avgLifeTime;
    XYChart.Series<Number, Number> avgChildrenNo;
    AbstractWorldMap map;
    int daysNo;

    public SideBox(AbstractWorldMap map, Thread thread, int counter) throws FileNotFoundException {
        topBox = new TopBox(map);
        this.map = map;
        this.daysNo = counter;
        HBox box = topBox.getTopBox();
        VBox chart = new VBox(getChart(0));
        HBox downBox = new Buttons(map, thread, true).getButtons();
        mainBox = new VBox(box, chart, downBox);
    }

    public VBox getSideBox(){
        return mainBox;
    }

    public LineChart getChart(int days) {
        if (this.chart == null) {
            this.chart = new LineChart<>(new NumberAxis(), new NumberAxis());
            this.chart.setCreateSymbols(false);
            animals = new XYChart.Series<>();
            animals.setName("Number of animals");
            plants = new XYChart.Series<>();
            plants.setName("Number of plants");
            avgEnergy = new XYChart.Series<>();
            avgEnergy.setName("Average energy");
            avgLifeTime = new XYChart.Series<>();
            avgLifeTime.setName("Average living time");
            avgChildrenNo = new XYChart.Series<>();
            avgChildrenNo.setName("Average number of children");
            this.chart.getData().add(animals);
            this.chart.getData().add(plants);
            this.chart.getData().add(avgEnergy);
            this.chart.getData().add(avgLifeTime);
            this.chart.getData().add(avgChildrenNo);
            this.chart.getLegendSide();
        } else {
            Platform.runLater(() -> animals.getData().add(new XYChart.Data<>(days, map.animalsQuantity)));
            Platform.runLater(() -> plants.getData().add(new XYChart.Data<>(days, map.plantsQuantity)));
            Platform.runLater(() -> avgEnergy.getData().add(new XYChart.Data<>(days, map.avgEnergy)));
            Platform.runLater(() -> avgLifeTime.getData().add(new XYChart.Data<>(days, map.avgLifeTime)));
            Platform.runLater(() -> avgChildrenNo.getData().add(new XYChart.Data<>(days, map.avgChildrenNo)));
        }
        return chart;
    }
}
