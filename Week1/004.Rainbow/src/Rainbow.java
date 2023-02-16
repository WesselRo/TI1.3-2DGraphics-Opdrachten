import java.awt.*;
import java.awt.geom.*;

import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

public class Rainbow extends Application {
    Canvas canvas = new Canvas(1920, 1000);

    @Override
    public void start(Stage primaryStage) throws Exception {

        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(new Group(canvas)));
        primaryStage.setTitle("Rainbow");
        primaryStage.show();
    }


    public void draw(FXGraphics2D graphics) {
        graphics.translate(this.canvas.getWidth() / 2, this.canvas.getHeight() / 2);
        graphics.rotate(Math.toRadians(180));


        double i = 1;
        for (double j = 0; j < Math.PI; j += Math.PI / 20000) {
            float x1 = (float) (300 * Math.cos(j));
            float y1 = (float) (300 * Math.sin(j));
            float x2 = (float) (400 * Math.cos(j));
            float y2 = (float) (400 * Math.sin(j));
            graphics.setColor(Color.getHSBColor((float) i / 20000, 1f, 1f));
            graphics.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
            i++;
        }
    }


    public static void main(String[] args) {
        launch(Rainbow.class);
    }

}
