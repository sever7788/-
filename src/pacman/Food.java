package pacman;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Food {
    Image image;
    ImageView imageView;
    public Food(Pane pane){
        image = new Image(getClass().getResourceAsStream("./img/map.png"));
        imageView = new ImageView(image);
        imageView.setViewport(new Rectangle2D(10,170,10,10));
    }

}
