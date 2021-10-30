import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
 
public class Main extends Application {
  public static void main(String[] args) {
      launch(args);
  }
  
  @Override
  public void start(Stage primaryStage) {
    try {
      Pane root = FXMLLoader.load(getClass().getResource("Main.fxml"));
      
      Circle circle = new Circle(25, 25, 25, Color.YELLOW);
      Rectangle rect = new Rectangle(150, 30, Color.RED);
      Label control = new Label("Hello world");
      control.setFont(Font.font(42));
  
      circle.setTranslateX(50);
      circle.setTranslateY(50);
  
      rect.setTranslateX(150);
      rect.setTranslateY(50);
  
      control.setTranslateX(250);
      control.setTranslateY(150);

      root.getChildren().addAll(circle, rect, control);

      makeDraggable(circle);
      makeDraggable(rect);

      Scene scene = new Scene(root, 400, 400);
      //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private double startX;
  private double startY;

  private void makeDraggable(Node node) {
    node.setOnMousePressed(e -> {
      //calculate offset
      startX = e.getSceneX() - node.getTranslateX();
      startY = e.getSceneY() - node.getTranslateY();
    });

    node.setOnMouseDragged(e -> {
      //set
      node.setTranslateX(e.getSceneX() - startX);
      node.setTranslateY(e.getSceneY() - startY);
    });
  }
}