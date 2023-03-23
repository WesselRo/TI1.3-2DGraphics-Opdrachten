import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class FadingImage extends Application {
    private ResizableCanvas canvas;
    private BufferedImage[] image = new BufferedImage[2];
    
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
		if(last == -1)
                    last = now;
		update((now - last) / 1000000000.0);
		last = now;
		draw(g2d);
            }
        }.start();

        image[0] = ImageIO.read(getClass().getResource("images/image1.jpg"));
        image[1] = ImageIO.read(getClass().getResource("images/image2.jpg"));

        stage.setScene(new Scene(mainPane));
        stage.setTitle("Fading image");
        stage.show();
        draw(g2d);
    }

    private float fade = 0;
    private boolean up = true;
    private int i = 0;
    
    public void draw(FXGraphics2D graphics) {
        AffineTransform tx = new AffineTransform();
        graphics.setTransform(tx);
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int)canvas.getWidth(), (int)canvas.getHeight());
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        graphics.drawImage(image[0], tx, null);
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade));
        graphics.drawImage(image[1], tx, null);
    }


    public void update(double deltaTime) {

        if(fade>0.99 && up==true){
            waiting();
            if(waiting()){
                return;
            }
            up = false;
        }
        else if(fade<0.01 && up==false){
            waiting();
            if(waiting()){
                return;
            }
            up = true;
        }
        if(up){
            fade+=0.01;
        } else if (!up) {
            fade-=0.01;
        }

    }

    public boolean waiting() {
        i++;
        if(i == 101){
            i = 0;
        }
            if(i<100) {
                return true;
            }
            else {
                return false;
            }

    }

    public static void main(String[] args) {
        launch(FadingImage.class);
    }

}
