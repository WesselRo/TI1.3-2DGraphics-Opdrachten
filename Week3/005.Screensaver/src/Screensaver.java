import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class Screensaver extends Application {
    private ResizableCanvas canvas;

    private Point point1;
    private Point point2;
    private Point point3;
    private Point point4;
    private Point point5;
    private Point point6;
    private Point point7;
    private Point point8;
    private Point lastPoint;

    @Override
    public void start(Stage stage) throws Exception
    {

        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());
        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now)
            {
                if (last == -1)
                    last = now;
                update((now - last) / 1000000000.0);
                last = now;
                draw(g2d);
            }
        }.start();

        stage.setScene(new Scene(mainPane));
        stage.setTitle("Screensaver");
        stage.show();
        draw(g2d);
    }

    private ArrayList<Line2D> linesNow = new ArrayList<>();

    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        for (Point point:points
             ) {
            if (point.getLocation().getX() >= canvas.getWidth() || point.getLocation().getX() <= 0){
                point.setToX(point.getToX()*-1);
            }
            if (point.getLocation().getY() >= canvas.getHeight() || point.getLocation().getY() <= 0){
                point.setToY(point.getToY()*-1);
            }
            point.setLocation(new Point2D.Double(point.getToX() + point.getLocation().getX(), point.getToY() + point.getLocation().getY()));
        }
        graphics.drawLine((int)point1.getLocation().getX(), (int)point1.getLocation().getY(), (int)point2.getLocation().getX(), (int)point2.getLocation().getY());
        graphics.drawLine((int)point2.getLocation().getX(), (int)point2.getLocation().getY(), (int)point3.getLocation().getX(), (int)point3.getLocation().getY());
        graphics.drawLine((int)point3.getLocation().getX(), (int)point3.getLocation().getY(), (int)point4.getLocation().getX(), (int)point4.getLocation().getY());
        graphics.drawLine((int)point4.getLocation().getX(), (int)point4.getLocation().getY(), (int)point5.getLocation().getX(), (int)point5.getLocation().getY());
        graphics.drawLine((int)point5.getLocation().getX(), (int)point5.getLocation().getY(), (int)point6.getLocation().getX(), (int)point6.getLocation().getY());
        graphics.drawLine((int)point6.getLocation().getX(), (int)point6.getLocation().getY(), (int)point7.getLocation().getX(), (int)point7.getLocation().getY());
        graphics.drawLine((int)point7.getLocation().getX(), (int)point7.getLocation().getY(), (int)point8.getLocation().getX(), (int)point8.getLocation().getY());
        graphics.drawLine((int)point8.getLocation().getX(), (int)point8.getLocation().getY(), (int)point1.getLocation().getX(), (int)point1.getLocation().getY());


    }

    private ArrayList<Point> points = new ArrayList<>();

    public void init()
    {
        this.point1 = new Point(new Point2D.Double(526, 61), 1, -1);
        this.point2 = new Point(new Point2D.Double(462, 185), 1, 1);
        this.point3 = new Point(new Point2D.Double(4, 7), 1, 1);
        this.point4 = new Point(new Point2D.Double(412, 289), -1, -1);
        this.point5 = new Point(new Point2D.Double(578, 61), 1, 1);
        this.point6 = new Point(new Point2D.Double(312, 302), -1, 1);
        this.point7 = new Point(new Point2D.Double(499, 458), 1, -1);
        this.point8 = new Point(new Point2D.Double(46, 83), 1, 1);
        points.add(point1);
        points.add(point2);
        points.add(point3);
        points.add(point4);
        points.add(point5);
        points.add(point6);
        points.add(point7);
        points.add(point8);
        lastPoint = point1;
    }

    public void update(double deltaTime)
    {

    }

    public static void main(String[] args)
    {
        launch(Screensaver.class);
    }

}
