
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.World;
import org.dyn4j.dynamics.joint.PinJoint;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class AngryBirds extends Application {

    private ResizableCanvas canvas;
    private World world;
    private MousePicker mousePicker;
    private Camera camera;
    private boolean debugSelected = false;
    private ArrayList<GameObject> gameObjects;
    private Body bird;
    private Body catapult;

    @Override
    public void start(Stage stage) throws Exception {

        BorderPane mainPane = new BorderPane();

        // Add debug button
        javafx.scene.control.CheckBox showDebug = new CheckBox("Show debug");
        showDebug.setOnAction(e -> {
            debugSelected = showDebug.isSelected();
        });
        mainPane.setTop(showDebug);

        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());

        camera = new Camera(canvas, g -> draw(g), g2d);
        mousePicker = new MousePicker(canvas);

        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now) {
                if (last == -1) {
                    last = now;
                }
                update((now - last) / 1000000000.0);
                last = now;
                draw(g2d);
            }
        }.start();

        stage.setScene(new Scene(mainPane, 1920, 1080));
        stage.setTitle("Angry Birds");
        stage.show();
        draw(g2d);
    }

    private ArrayList<Body> boxes = new ArrayList<>();
    private ArrayList<GameObject> boxesAsGameObjects = new ArrayList<>();

    public void init() {
        world = new World();
        world.setGravity(new Vector2(0, -9.8));
        gameObjects = new ArrayList<GameObject>();

        catapult = new Body();
        catapult.getTransform().setTranslation(-7, -3.75);
        catapult.setMass(MassType.INFINITE);
        world.addBody(catapult);
        gameObjects.add(new GameObject("catapult.png", catapult, new Vector2(250, 0), 0.25));

        Body box = new Body();
        box.addFixture(Geometry.createRectangle(0.5, 0.5));
        box.getTransform().setTranslation(2, -2);
        box.setMass(MassType.NORMAL);
        world.addBody(box);
        gameObjects.add(new GameObject("box.png", box, new Vector2(0,0), 0.2));

        Body box1 = new Body();
        box1.addFixture(Geometry.createRectangle(0.5, 0.5));
        box1.getTransform().setTranslation(2, -3);
        box1.setMass(MassType.NORMAL);
        world.addBody(box1);
        gameObjects.add(new GameObject("box.png", box1, new Vector2(0,0), 0.2));

        Body box2 = new Body();
        box2.addFixture(Geometry.createRectangle(0.5, 0.5));
        box2.getTransform().setTranslation(3, -2);
        box2.setMass(MassType.NORMAL);
        world.addBody(box2);
        gameObjects.add(new GameObject("box.png", box2, new Vector2(0,0), 0.2));

        Body floor = new Body();
        floor.addFixture(Geometry.createRectangle(100, 1));
        floor.getTransform().setTranslation(0, -5);
        floor.setMass(MassType.INFINITE);
        world.addBody(floor);

        bird = new Body();
        bird.addFixture(Geometry.createCircle(0.15));
        bird.getTransform().setTranslation(catapult.getTransform().getTranslation());
        bird.setMass(MassType.NORMAL);
        world.addBody(bird);
        gameObjects.add(new GameObject("bird.png", bird, new Vector2(0,0), 0.08));

    }

    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        AffineTransform originalTransform = graphics.getTransform();

        graphics.setTransform(camera.getTransform((int) canvas.getWidth(), (int) canvas.getHeight()));
        graphics.scale(1, -1);

        for (GameObject go : gameObjects) {
            go.draw(graphics);
        }

        if (debugSelected) {
            graphics.setColor(Color.blue);
            DebugDraw.draw(graphics, world, 100);
        }

        canvas.setOnMouseClicked(event -> {
            bird.applyForce(new Vector2(5, 2), catapult.getTransform().getTranslation());
        });

        graphics.setTransform(originalTransform);
    }

    public void update(double deltaTime) {
        mousePicker.update(world, camera.getTransform((int) canvas.getWidth(), (int) canvas.getHeight()), 100);
        world.update(deltaTime);

    }

    public static void main(String[] args) {
        launch(AngryBirds.class);
    }

}
