import java.awt.*;
import java.awt.geom.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

public class Spiral extends Application {
    Canvas canvas;
    @Override
    public void start(Stage primaryStage) throws Exception {
        canvas = new Canvas(1920, 1080);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(new Group(canvas)));
        primaryStage.setTitle("Spiral");
        primaryStage.show();
    }


    public void draw(FXGraphics2D graphics) {
        graphics.translate(canvas.getWidth()/2, canvas.getHeight()/2);

        graphics.setColor(Color.RED);
        graphics.drawLine(-1000,0,1000,0);
        graphics.setColor(Color.GREEN);
        graphics.drawLine(0,-1000,0,1000);
        graphics.setColor(Color.BLACK);

        double scale = 40.0;
        double resolution = 0.01;
        double lastY = 100;
        double n = 1;

        for(double r = 0; r < 100; r += resolution)
        {
            double Ø = n * r;

            double x = r * Math.cos(Ø);
            double y = r * Math.sin(Ø);

            double lastØ = n * (r-resolution);
            double lastX = (r-resolution) * Math.cos(lastØ);

            graphics.draw(new Line2D.Double(x*scale, y*scale, lastX*scale, lastY*scale));
            lastY = y;
        }
    }



    public static void main(String[] args) {
        launch(Spiral.class);
    }

}
