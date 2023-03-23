import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class MovingCharacter extends Application {
    private ResizableCanvas canvas;

    private BufferedImage[] image = new BufferedImage[17];
    private int x;
    private int teller = 0;
    private int numberOfHorizontalSprites = 8;
    private double spriteWidth = 64;
    private double spriteHeight = 64;
    private int row = 4;

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
        BufferedImage total = null;
        try {
            total = ImageIO.read(getClass().getResource("images/sprite.png"));
            for (int i = 0; i < numberOfHorizontalSprites; i++) {
                image[i] = total.getSubimage((int) spriteWidth * i, (int) spriteHeight * 4, (int) spriteWidth, (int) spriteHeight);
            }
            for (int i = 0; i < numberOfHorizontalSprites; i++) {
                image[8 + i] = total.getSubimage((int) spriteWidth * i, (int) spriteHeight * 5, (int) spriteWidth, (int) spriteHeight);
            }
            image[16] = total.getSubimage(8, 0, (int) spriteWidth, (int) spriteHeight);
        } catch (IOException e) {
            e.printStackTrace();
        }


        stage.setScene(new Scene(mainPane));
        stage.setTitle("Moving Character");
        stage.show();
        draw(g2d);
        canvas.setOnMouseClicked(event -> jump());
    }


    public void draw(FXGraphics2D graphics) {
        AffineTransform tx = new AffineTransform();
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        graphics.setBackground(Color.white);
        tx.translate(x, 100);
        tx.scale(0.75f, 0.75f);
        graphics.drawImage(image[teller2], tx, null);
    }

    int teller2 = 0;
    boolean jumping = false;

    public void update(double deltaTime) {
        if (teller2 < 8) {
            x += 1;
        }
        teller++;

        if (teller == numberOfHorizontalSprites * 10) {
            teller = 0;
            if (jumping) {
                teller2 = 8;
            } else {
                teller2 = 0;
            }
        } else if (teller % 10 == 0) {
            teller2++;
        }
        if (teller2 == 15) {
            jumping = false;
        }
    }

    public void jump() {

        jumping = true;
    }

    public static void main(String[] args) {
        launch(MovingCharacter.class);
    }

}
