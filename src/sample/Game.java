package sample;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game {
    Stage stage;
    Image IMAGE = new Image(getClass().getResourceAsStream(""));
    int COUNT;
    int COLUMNS;
    int OFFSET_X;
    int OFFSET_Y;
    int WIDTH;
    int HEIGHT;

    Game(Stage stage){
        this.stage = stage;
    }
    public void initGame(){
        Pane root = new Pane();
        Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);
    }
}
