import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class Spotlight extends Application {
    private ResizableCanvas canvas;
    private BufferedImage puzzel;

    @Override
    public void start(Stage stage) throws Exception {

        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());
        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now) {
                if (last == -1)
                    last = now;
                update((now - last) / 1000000000.0);
                last = now;
                draw(g2d);
            }
        }.start();

        stage.setScene(new Scene(mainPane));
        stage.setTitle("Spotlight");
        stage.show();
        draw(g2d);
    }


    public Point2D.Double location = new Point2D.Double(200, 150);


    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());


        Shape shape = new Rectangle2D.Double(location.getX()-50, location.getY()-50, 100, 100);
        graphics.setColor(Color.BLUE);
        graphics.draw(shape);
        graphics.clip(shape);

        AffineTransform tx = new AffineTransform();
        graphics.drawImage(puzzel, tx, null);

        canvas.setOnMouseMoved(event -> {
            location.setLocation(event.getX(), event.getY());
        });
        graphics.setClip(null);
    }

    public void init() {

        try {
            puzzel = ImageIO.read(getClass().getResource("/puzzel.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void update(double deltaTime) {

    }

    public static void main(String[] args) {
        launch(Spotlight.class);
    }

}
