import java.awt.*;
import java.awt.geom.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

public class Spirograph extends Application {
    private TextField v1;
    private TextField v2;
    private TextField v3;
    private TextField v4;
    private Canvas canvas;
    @Override
    public void start(Stage primaryStage) throws Exception {
        canvas = new Canvas(1920, 1080);
       
        VBox mainBox = new VBox();
        HBox topBar = new HBox();
        mainBox.getChildren().add(topBar);
        mainBox.getChildren().add(new Group(canvas));
        
        topBar.getChildren().add(v1 = new TextField("300"));
        topBar.getChildren().add(v2 = new TextField("1"));
        topBar.getChildren().add(v3 = new TextField("300"));
        topBar.getChildren().add(v4 = new TextField("10"));
                
        v1.textProperty().addListener(e -> draw(new FXGraphics2D(canvas.getGraphicsContext2D())));
        v2.textProperty().addListener(e -> draw(new FXGraphics2D(canvas.getGraphicsContext2D())));
        v3.textProperty().addListener(e -> draw(new FXGraphics2D(canvas.getGraphicsContext2D())));
        v4.textProperty().addListener(e -> draw(new FXGraphics2D(canvas.getGraphicsContext2D())));
        
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(mainBox));
        primaryStage.setTitle("Spirograph");
        primaryStage.show();
    }

    private boolean oneTime = true;

    public void draw(FXGraphics2D graphics) {
        //you can use Double.parseDouble(v1.getText()) to get a double value from the first textfield
        //feel free to add more textfields or other controls if needed, but beware that swing components might clash in naming
        if(oneTime){graphics.translate(650, 350); oneTime = false;}
        graphics.setColor(Color.white);
        graphics.fillRect(-650, -350, 1920, 1920);
        graphics.setColor(Color.black);

        double v1 = Double.parseDouble(this.v1.getText());
        double v2 = Double.parseDouble(this.v2.getText());
        double v3 = Double.parseDouble(this.v3.getText());
        double v4 = Double.parseDouble(this.v4.getText());
        double i = 0.001;

        for (double j = 0; j < Math.PI; j+= i) {
            int x = (int) (v1 * Math.cos(v2 * j) + v3 * Math.cos(v4 * j));
            int y = (int) (v1 * Math.sin(v2 * j) + v3 * Math.sin(v4 * j));
            j+= i;
            int x2 = (int) (v1 * Math.cos(v2 * j) + v3 * Math.cos(v4 * j));
            int y2 = (int) (v1 * Math.sin(v2 * j) + v3 * Math.sin(v4 * j));
            j-= i;
            graphics.drawLine(x, y, x2, y2);
        }
    }
    
    
    
    public static void main(String[] args) {
        launch(Spirograph.class);
    }

}
