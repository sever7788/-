package pacman;

import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class NPC {
    Image IMAGE = null;
    int COUNT = 3;
    int COLUMNS = 3;
    int OFFSET_X = 0;
    int OFFSET_Y = 0;
    int WIDTH = 32;
    int HEIGHT = 32;
    ImageView imageView;
    SpriteAnimation animation;
    Pane pane;
    public NPC(Pane pane, String imageName){
        this.IMAGE = new Image(getClass().getResourceAsStream(imageName));
        this.pane = pane;
        imageView = new ImageView(IMAGE);
        imageView.setViewport(new Rectangle2D(OFFSET_X,OFFSET_Y,WIDTH,HEIGHT));
        animation = new SpriteAnimation(
                imageView,
                Duration.millis(300),
                COUNT, COLUMNS,
                OFFSET_X,OFFSET_Y,WIDTH, HEIGHT);
        animation.play();
    }



    public void moveX(int x){
        boolean right = x > 0;
        for(int i = 0; i < Math.abs(x); i++) {
            if (right) imageView.setTranslateX(imageView.getTranslateX() + 1);
            else imageView.setTranslateX(imageView.getTranslateX() - 1);
        }
    }
    public void moveY(int y) {
        boolean down = y > 0;
        for (int i = 0; i < Math.abs(y); i++) {
            if (down) imageView.setTranslateY(imageView.getTranslateY() + 1);
            else imageView.setTranslateY(imageView.getTranslateY() - 1);
        }
    }
}
