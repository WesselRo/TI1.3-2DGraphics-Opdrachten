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

public class GradientPaintExercise extends Application {
    private BorderPane mainPane = new BorderPane();
    private ResizableCanvas canvas = new ResizableCanvas(g -> draw(g), mainPane);

    @Override
    public void start(Stage primaryStage) throws Exception
    {


        mainPane.setCenter(canvas);
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("GradientPaint");
        primaryStage.show();
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));

    }



    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        float radius1 = 100;
        float[] dist1 = {0.0f, 0.2f, 1.0f};
        Point2D center1 = new Point2D.Float((int) canvas.getWidth()/2, (int) canvas.getHeight()/2);
        Color[] colors1 = {Color.YELLOW, Color.green, Color.PINK};
        RadialGradientPaint radialGradientPaint1 = new RadialGradientPaint(center1, radius1, dist1, colors1, MultipleGradientPaint.CycleMethod.REFLECT);
        graphics.setPaint(radialGradientPaint1);
        Area area1 = new Area(new Rectangle((int) canvas.getWidth(), (int) canvas.getHeight()));
        graphics.fill(area1);
        canvas.setOnMouseDragged(e ->
        {
            float radius = 100;
            float[] dist = {0.0f, 0.2f, 1.0f};
            Point2D center = new Point2D.Float((float)e.getX(),(float) e.getY());
            Color[] colors = {Color.YELLOW, Color.green, Color.PINK};
            RadialGradientPaint radialGradientPaint = new RadialGradientPaint(center, radius, dist, colors, MultipleGradientPaint.CycleMethod.REFLECT);
            graphics.setPaint(radialGradientPaint);
            Area area = new Area(new Rectangle((int) canvas.getWidth(), (int) canvas.getHeight()));
            graphics.fill(area);
        });


    }


    public static void main(String[] args)
    {
        launch(GradientPaintExercise.class);
    }

}
