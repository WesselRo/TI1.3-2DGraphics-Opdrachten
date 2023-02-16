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

public class YingYang extends Application {
    private ResizableCanvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Ying Yang");
        primaryStage.show();
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }


    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        graphics.translate(20, 20);
        Area big = new Area(new Ellipse2D.Double(0,0,400,400));
        Area whiteMedium = new Area(new Ellipse2D.Double(100,0,200,200));
        Area blackMedium = new Area(new Ellipse2D.Double(100,200,200,200));
        Area whiteSmall = new Area(new Ellipse2D.Double(175,275,50,50));
        Area blackSmall = new Area(new Ellipse2D.Double(175,75,50,50));
        Area rectangle = new Area(new Rectangle(200, 400));
        Area yingYang = new Area(big);
        yingYang.subtract(rectangle);
        graphics.setColor(Color.BLACK);
        graphics.fill(yingYang);
        graphics.setColor(Color.WHITE);
        graphics.fill(whiteMedium);
        graphics.setColor(Color.BLACK);
        graphics.fill(blackMedium);
        graphics.draw(big);
        graphics.fill(blackSmall);
        graphics.setColor(Color.WHITE);
        graphics.fill(whiteSmall);


    }


    public static void main(String[] args)
    {
        launch(YingYang.class);
    }

}
