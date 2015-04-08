package engine.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import View.ViewUtilities;


/**
 * View of the shop. Right now it is being tested using its own stage/launch().
 * 
 * @author Nathan Prabhu
 *
 */
public class View extends Application {

    private final static int SCENE_WIDTH = 600;
    private final static int SCENE_HEIGHT = 600;
    private final static int SHOP_WIDTH = 160;
    private final static int ITEM_COUNT = 12;

    private BorderPane pane;

    @Override
    public void start (Stage stage) {
        pane = new BorderPane();
        Scene scene = new Scene(pane, SCENE_WIDTH, SCENE_HEIGHT);
        stage.setScene(scene);

        // FlowPane contains the entire store. This is what should be moved around.
        FlowPane shopDisplay = new FlowPane();
        shopDisplay.setHgap(5);
        shopDisplay.setVgap(5);
        pane.setRight(shopDisplay);
        shopDisplay.setMaxWidth(SHOP_WIDTH);
        shopDisplay.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));

        // add Icons
        addIcons(shopDisplay);

        // add general gameWorld view
        BorderPane gameView = new BorderPane();
        pane.setCenter(gameView);
        gameView.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
        gameView.setOnMousePressed(mouseEvent -> {
            System.out.println("Relative coordinates: " +
                               ViewUtilities.normalizePixels(mouseEvent, gameView));
        });
        stage.show();
    }

    private void addIcons (FlowPane shopDisplay) {
        Map<String, String> shopImages = new HashMap<String, String>();
        String[] iconImages = new String[] { "/images/Bloons_DartMonkeyIcon.jpg",
                                            "/images/Bloons_TackShooterIcon.jpg" };
        String[] towerImages = new String[] { "/images/Bloons_DartMonkey.png",
                                             "/images/Bloons_TackShooter.png" };
        for (int i = 0; i < iconImages.length; i++) {
            shopImages.put(iconImages[i], towerImages[i]);
        }

        List<Node> items = new ArrayList<Node>();
        for (int i = 0; i < ITEM_COUNT / iconImages.length; i++) {
            shopImages.forEach( (icon, tower) -> {
                ItemGraphic item = new ItemGraphic(icon, tower);
                TransitionTower transitionTower = new TransitionTower(item.getTower());
                Node towerNode = transitionTower.getView();
                item.setOnMouseClicked(mouseEvent -> {
                    addTransitionTower(ViewUtilities.getMouseLocation(mouseEvent, towerNode),
                                       towerNode);
                });
                items.add(item);
            });
        }
        shopDisplay.getChildren().addAll(items);
    }

    private void addTransitionTower (Point2D initial, Node node) {
        Node bindedTower =
                ViewUtilities.bindCursor(node, pane.getScene(), initial, KeyCode.ESCAPE);
        pane.getChildren().add(bindedTower);
    }

    public static void main (String[] args) {
        launch(args);
    }

}
