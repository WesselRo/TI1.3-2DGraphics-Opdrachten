import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.geom.Line2D;

public class House extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Canvas canvas = new Canvas(1024, 768);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(new Group(canvas)));
        primaryStage.setTitle("House");
        primaryStage.show();
    }


    public void draw(FXGraphics2D graphics) {
        graphics.draw(new Line2D.Double(260, 260, 10,460));
        graphics.draw(new Line2D.Double(10, 460, 10, 760));
        graphics.draw(new Line2D.Double(10, 760, 510, 760));
        graphics.draw(new Line2D.Double(510, 760, 510, 460));
        graphics.draw(new Line2D.Double(510, 460, 260, 260));
        graphics.draw(new Line2D.Double(50, 760, 50, 560));
        graphics.draw(new Line2D.Double(50, 560, 150, 560));
        graphics.draw(new Line2D.Double(150, 560, 150, 760));
        graphics.draw(new Line2D.Double(200, 560 , 460 , 560));
        graphics.draw(new Line2D.Double(460, 560, 460,660));
        graphics.draw(new Line2D.Double(460, 660, 200, 660));
        graphics.draw(new Line2D.Double(200, 660, 200,560));
    }



    public static void main(String[] args) {
        launch(House.class);
    }

}
