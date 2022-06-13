package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class Main extends Application {
    BorderPane bPane = new BorderPane();
    public static Pane pane = new Pane();
    public static double STRL = 1000 ;
    public static int BorderSTRL = 1050;
    public static double deltPaTre = (STRL/2) /2;

    @Override
    public void start(Stage primaryStage) throws Exception{
        forsteTrekant(10);
        primaryStage.setTitle("Kul tittel");
        bPane.setCenter(pane);
        Slider slider = new Slider(0,10,1);
            slider.setShowTickMarks(true);
            slider.setShowTickLabels(true);
            slider.setMajorTickUnit(1);
            slider.setBlockIncrement(1);
        bPane.setBottom(slider);

        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            pane.getChildren().clear();
            forsteTrekant((int)slider.getValue());
        });

        primaryStage.setScene(new Scene(bPane, BorderSTRL, BorderSTRL));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


    public static void forsteTrekant (int n) {
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(0.0, STRL, //Nederst til venstre
                STRL, STRL, //Nederst til høyre
                STRL/2, 0.0); //Midten på toppen
        polygon.setFill(Color.BLACK);
        pane.getChildren().add(polygon);
        tegnHool(n,deltPaTre,STRL/2,(STRL/2)+deltPaTre,STRL/2,STRL/2,STRL);
    }

    public static void tegnHool(int n, double xVenstre,double yVenstre, double xHoyre, double yHoyre, double xNede, double yNede) {
        if (n==0) {
            return;
        }
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(xVenstre, yVenstre, //Nederst til venstre
                xHoyre, yVenstre, //Nederst til høyre
                xNede, yNede); //Midten på toppen
        polygon.setFill(Color.WHITE);
        pane.getChildren().add(polygon);
        double xHalvparten = (xHoyre-xNede)/2;
        double yHalparten = (yNede-yVenstre)/2;

        tegnHool(n-1,xNede+xHalvparten,yVenstre+yHalparten,xHoyre+xHalvparten,yHoyre+yHalparten,xHoyre,yNede); // nede til Høyre
        tegnHool(n-1,xVenstre-xHalvparten,yVenstre+yHalparten,xNede-xHalvparten,yHoyre+yHalparten,xVenstre,yNede);//nede til venstre
        tegnHool(n-1,xNede-xHalvparten,yVenstre-yHalparten,xNede+xHalvparten,yHoyre-yHalparten,xNede,yVenstre);//Oppe
    }

}
