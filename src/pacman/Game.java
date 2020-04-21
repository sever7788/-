package pacman;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Game {
    Pacman pacman;
    Stage stage;
    Scene scene;
    MapData map;
    Image mapImg;
    Pane root;
    ArrayList<Block>objects = new ArrayList<>();
    boolean flX = true;
    boolean flY = false;
    Game(Stage stage){
        this.stage = stage;
    }
    public void initGame(){
        mapImg = new Image(getClass().getResourceAsStream("./img/map.png"));
        this.root = new Pane();
        scene = new Scene(root, 900, 600);

        pacman = new Pacman(root, scene);

        map = new MapData(root, mapImg, objects);
        map.drawMap();
        root.getChildren().addAll(pacman.imageView);
        pacman.imageView.setTranslateX(33);
        pacman.imageView.setTranslateY(33);
        stage.setScene(scene);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();
    }

    public void check(int direction){
        for(int indexObject = 0; indexObject < objects.size();indexObject++)
            if(pacman.imageView.getBoundsInParent().intersects(objects.get(indexObject).imageView.getBoundsInParent())){
                Block block = objects.get(indexObject);
                if(block.blockType == '1'){
                    switch(direction){
                        case 1: flY=false; pacman.moveY(2);  break;
                        case 2: flX=false; pacman.moveX(-2); break;
                        case 3: flY=false; pacman.moveY(-2); break;
                        case 4: flX=false; pacman.moveX(2);  break;
                    }

                }
                if(block.blockType == '2'){
                    block.pane.getChildren().remove(block.imageView);
                    objects.remove(indexObject);
                }
            }
    }

    public void update() {
        pacman.control();
        pacman.movePacman(pacman.animation.direction);
        check(pacman.animation.direction);
        flX = true; flY = true;
        if(pacman.followingDirection!=pacman.animation.direction) {
            pacman.movePacman(pacman.followingDirection);
            check(pacman.followingDirection);
        }
        if(flX && (pacman.followingDirection == 2||pacman.followingDirection == 4)){
            pacman.animation.reflectImage(pacman.followingDirection);
            pacman.followingDirection = 0;
        }
        if(flY && (pacman.followingDirection == 1||pacman.followingDirection == 3)){
            pacman.animation.reflectImage(pacman.followingDirection);
            pacman.followingDirection = 0;
        }
    }

}
