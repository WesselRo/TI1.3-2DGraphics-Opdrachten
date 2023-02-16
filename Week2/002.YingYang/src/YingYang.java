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
        Area big = new Area(new Ellipse2D.Double(0,0,400,400));
        Area whiteMedium = new Area(new Ellipse2D.Double(100,0,200,200));
        Area blackMedium = new Area(new Ellipse2D.Double(100,200,200,200));
        Area whiteSmall = new Area(new Ellipse2D.Double(0,0,100,100));
        Area blackSmall = new Area(new Ellipse2D.Double(0,0,100,100));
        GeneralPath path = new GeneralPath();
        Area yingYang = new Area(big);
        graphics.setColor(Color.BLACK);
        graphics.draw(big);
        graphics.fill(big);
        graphics.setColor(Color.WHITE);
        graphics.fill(whiteMedium);
        graphics.setColor(Color.BLACK);
        path.moveTo(200, 0);
        graphics.fill(path);


    }


    public static void main(String[] args)
    {
        launch(YingYang.class);
    }

}
