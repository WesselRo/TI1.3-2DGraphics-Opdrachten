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

public class Colors extends Application {
    private ResizableCanvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Colors");
        primaryStage.show();
    }


    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        int j = 0;
        for (int i = 0; i < 650 ; i=i+50) {
            Area rectangle = new Area(new Rectangle(i, 0, 50, 50));
            if(j==0){
                graphics.setColor(Color.BLACK);
            }
            else if(j==1){
                graphics.setColor(Color.BLUE);
            }
            else if(j==2){
                graphics.setColor(Color.CYAN);
            }
            else if(j==3){
                graphics.setColor(Color.darkGray);
            }
            else if(j==4){
                graphics.setColor(Color.green);
            }
            else if(j==5){
                graphics.setColor(Color.lightGray);
            }
            else if(j==6){
                graphics.setColor(Color.magenta);
            }
            else if(j==7){
                graphics.setColor(Color.orange);
            }
            else if(j==8){
                graphics.setColor(Color.pink);
            }
            else if(j==9){
                graphics.setColor(Color.RED);
            }
            else if(j==10){
                graphics.setColor(Color.white);
            }
            else if(j==11){
                graphics.setColor(Color.yellow);
            }
            else if(j==12){
                graphics.setColor(Color.gray);
            }
            graphics.fill(rectangle);
            j++;
        }
    }


    public static void main(String[] args)
    {
        launch(Colors.class);
    }

}
