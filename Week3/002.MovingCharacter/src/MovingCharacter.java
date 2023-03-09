import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

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

public class MovingCharacter extends Application {
    private ResizableCanvas canvas;

    private BufferedImage image;

    @Override
    public void start(Stage stage) throws Exception
    {

        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());
//        new AnimationTimer() {
//            long last = -1;
//
//            @Override
//            public void handle(long now)
//            {
//                if (last == -1)
//                    last = now;
//                update((now - last) / 1000000000.0);
//                last = now;
//                draw(g2d);
//            }
//        }.start();
        try {
            image = ImageIO.read(getClass().getResource("/images/sprite.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setScene(new Scene(mainPane));
        stage.setTitle("Moving Character");
        stage.show();
        draw(g2d);
    }


    public void draw(FXGraphics2D graphics)
    {
        AffineTransform tx = new AffineTransform();
        tx.translate(400,400);
        tx.rotate(Math.toRadians(45.0f), image.getWidth()/2, image.getHeight()/2);
        tx.scale(0.75f, 0.75f);
        graphics.drawImage(image, tx, null);
    }


    public void update(double deltaTime)
    {
    }

    public static void main(String[] args)
    {
        launch(MovingCharacter.class);
    }

}
