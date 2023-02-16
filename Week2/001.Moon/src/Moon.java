import java.awt.*;
import java.awt.geom.*;

import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class Moon extends Application {
    private ResizableCanvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Moon");
        primaryStage.show();
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }


    public void draw(FXGraphics2D g2d)
    {

        g2d.setTransform(new AffineTransform());
        g2d.setBackground(Color.white);
        g2d.clearRect(100, 100, (int) canvas.getWidth(), (int) canvas.getHeight());
        g2d.translate(this.canvas.getWidth() / 2, this.canvas.getHeight() / 2);

        Area a = new Area(new Ellipse2D.Double(0,-10,100,100));
        Area b = new Area(new Ellipse2D.Double(30,0,100,100));

        Area sub = new Area(b);
        sub.subtract(a);
        g2d.setColor(Color.black);
        g2d.fill(sub);
        g2d.setColor(Color.black);

    }


    public static void main(String[] args)
    {
        launch(Moon.class);
    }

}
