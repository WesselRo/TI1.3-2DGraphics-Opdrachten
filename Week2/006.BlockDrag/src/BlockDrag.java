import java.awt.*;
import java.awt.geom.*;

import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class BlockDrag extends Application {
    ResizableCanvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Block Dragging");
        primaryStage.show();



        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }

    private Point2D location = new Point2D.Double(20,30);
    private Rectangle block = new Rectangle((int)location.getX(), (int)location.getY(),60, 60);
    private Point2D locationBlockMid = new Point2D.Double(location.getX()+(block.getWidth()/2), location.getY()+(block.getHeight()/2));
    private Point2D locationOnBlock;

    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        AffineTransform tx = new AffineTransform();
        tx.translate(location.getX() - block.getWidth()/2, location.getY() - block.getHeight()/2);

        graphics.draw(block);
        canvas.setOnMousePressed(e -> mousePressed(e));
        canvas.setOnMouseReleased(e -> mouseReleased(e));
        canvas.setOnMouseDragged(e -> mouseDragged(e));

    }


    public static void main(String[] args)
    {
        launch(BlockDrag.class);
    }
private boolean onBlock = false;
    private int xdif;
    private int ydif;
    private void mousePressed(MouseEvent e)
    {
        Point2D mouseLocation = new Point2D.Double(e.getX(), e.getY());
        if(mouseLocation.getX()>=location.getX()&&mouseLocation.getX()<=location.getX()+block.getWidth()&&mouseLocation.getY()>=location.getY()&&mouseLocation.getY()<=location.getY()+block.getHeight()){
            locationOnBlock = mouseLocation;
            onBlock = true;
            xdif = (int)locationOnBlock.getX()-(int)location.getX();
            ydif = (int)locationOnBlock.getY()-(int)location.getY();
        }
    }

    private void mouseReleased(MouseEvent e)
    {
        onBlock = false;
    }

    private void mouseDragged(MouseEvent e)
    {
        if(onBlock) {
            location = new Point2D.Double((int)e.getX()-xdif, (int)e.getY()-ydif);
            block.setLocation((int)location.getX(), (int)location.getY());
            draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        }
    }

}