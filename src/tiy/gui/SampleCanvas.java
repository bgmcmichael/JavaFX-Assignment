package tiy.gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class SampleCanvas extends Application {

    final double DEFAULT_SCENE_HEIGHT = 600;
    final double DEFAULT_SCENE_WIDTH = 800;
    double strokeSize = 2;
    int ballCounter =21;

    @Override
    public void start(Stage primaryStage) {
        Group rootGroup = new Group();

//        Scene mainScene = new Scene(rootGroup, 800, 600, Color.BLACK);


        Canvas canvas = new Canvas(DEFAULT_SCENE_WIDTH, DEFAULT_SCENE_HEIGHT);
        canvas.setFocusTraversable(true);

        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        graphicsContext.setStroke(Color.color(Math.random(), Math.random(), Math.random()));
        graphicsContext.setLineWidth(2);

        canvas.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.A) {
                graphicsContext.setFill(Color.color(Math.random(), Math.random(), Math.random()));
            }
            if (e.getCode() == KeyCode.C) {
                graphicsContext.clearRect(0, 0, DEFAULT_SCENE_WIDTH, DEFAULT_SCENE_HEIGHT);
            }

            if (e.getCode() == KeyCode.UP) {
                strokeSize++;
                if (strokeSize > 20){
                    strokeSize = 20;
                }
            }

            if (e.getCode() == KeyCode.DOWN) {
                strokeSize--;
                if (strokeSize < 2){
                    strokeSize = 2;
                }
            }

            if (e.getCode() == KeyCode.LEFT) {
                ballCounter--;
                if (ballCounter < 2){
                    ballCounter = 2;
                }
            }

            if (e.getCode() == KeyCode.RIGHT) {
                ballCounter++;
                if (ballCounter > 10000){
                    ballCounter = 10000;
                }
            }

        });

        canvas.setOnMouseDragged(e -> {
            int counter = 0;
            graphicsContext.clearRect(0, 0, DEFAULT_SCENE_WIDTH, DEFAULT_SCENE_HEIGHT);
            if (e.isShiftDown()) {
                while (counter < ballCounter) {
                    graphicsContext.setFill(Color.color(Math.random(), Math.random(), Math.random()));
                    graphicsContext.fillOval((int) (Math.random() * e.getX()), (int) (Math.random() * e.getY()), 15, 15);
                    ballCounter = (int) (e.getX() * e.getY()) / 15;
                    counter++;
                }
            } else {
                while (counter < ballCounter) {
                    graphicsContext.setFill(Color.color(Math.random(), Math.random(), Math.random()));
                    graphicsContext.fillOval((int) (Math.random() * e.getX()), (int) (Math.random() * e.getY()), 15, 15);
                    counter++;
                }
            }
        });


        canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent e) {
                System.out.println("x: " + e.getX() + ", y: " + e.getY());

                if (e.isShiftDown()) {
                    for (int counter = 0; counter < 4; counter++) {
                        graphicsContext.fillOval(e.getX() + ((counter + 1) * 10), e.getY() + ((counter + 1) * 10), strokeSize, strokeSize);
                    }
                } else {
                    graphicsContext.fillOval(e.getX(), e.getY(), strokeSize, strokeSize);
                }
            }
        });

        rootGroup.getChildren().add(canvas);
        Scene scene = new Scene(rootGroup, DEFAULT_SCENE_WIDTH, DEFAULT_SCENE_HEIGHT);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
