import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class Rainbow extends Application {
    private ResizableCanvas canvas;

    @Override
    public void start(Stage stage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        stage.setScene(new Scene(mainPane));
        stage.setTitle("Rainbow");
        stage.show();
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }



    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        graphics.translate((int) canvas.getWidth()/2, (int) canvas.getHeight()/2);
        //Font font = new Font("Serif", Font.PLAIN, 50);
//        AffineTransform tx = new AffineTransform();
//        tx.rotate(-.5*Math.PI);
       double centerX = canvas.getWidth()/2;
       double centerY = canvas.getHeight()/2;

        String woord = "Regenboog";
        double radius = Math.min(centerX, centerY) - 45;
        double angle = -90.0;
        double increase = 180 / woord.length();

        Font font = new Font("Dialog", Font.PLAIN, 100);
        graphics.setFont(font);

        for (int i = 0; i < woord.length(); i++) {
            char letterChar = woord.charAt(i);
            graphics.setColor(getRainbowColor(i, woord.length()));
            AffineTransform tx;
            tx = AffineTransform.getTranslateInstance(centerX, centerY+100);
            tx.rotate(Math.toRadians(angle + i * increase));
            tx.translate(0, -radius);
            graphics.setTransform(tx);

            graphics.drawString(Character.toString(letterChar), 0, 0);
        }
    }

    private Color getRainbowColor(int index, int total) {
        float kaas = (float) index / (float) total;
        return Color.getHSBColor(kaas, 1, 1);
    }


    public static void main(String[] args)
    {
        launch(Rainbow.class);
    }

    public void getWoord(String woord){

    }

}
