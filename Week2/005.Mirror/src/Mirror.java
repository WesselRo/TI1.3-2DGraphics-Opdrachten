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

public class Mirror extends Application {
    ResizableCanvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Mirror");
        primaryStage.show();
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }


    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        graphics.translate((int) canvas.getWidth()/2, (int) canvas.getHeight()/2);

        graphics.setColor(Color.black);
        graphics.drawLine(-1000,0,1000,0);
        graphics.drawLine(0,-1000,0,1000);

        Rectangle rectangle = new Rectangle(100, 100);
        rectangle.setLocation(0-((int)rectangle.getWidth()/2), 150-((int)rectangle.getHeight()/2));
        graphics.draw(rectangle);
        graphics.setColor(Color.BLUE);
        rectangle.setLocation((int)(2.5*rectangle.getX())-((int)rectangle.getWidth()/2), ((int)(rectangle.getY()/2.5)-((int)rectangle.getHeight()/2)));
        graphics.draw(rectangle);

    }


    public static void main(String[] args)
    {
        launch(Mirror.class);
    }

}
