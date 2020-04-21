package pacman;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class Block {
    Image image = new Image(getClass().getResourceAsStream("./img/map.png"));
    ImageView imageView;
    int width = 32;
    int height = 32;
    char blockType;
    Pane pane;
    public Block(char blockType, int xBlock, int yBlock, Pane pane) {
        this.pane = pane;
            imageView = new ImageView(image);
            this.pane.getChildren().addAll(imageView);
            imageView.setTranslateX(xBlock*width); imageView.setTranslateY(yBlock*height);
            this.blockType = blockType;
        switch (blockType) {
            case '0':
                imageView.setViewport(new Rectangle2D(0,256,width,height));
                break;
            case '1':
                imageView.setViewport(new Rectangle2D(160,256,width,height));
                break;
            case '2':
                imageView.setViewport(new Rectangle2D(10,170,10,10));
                break;
        }
    }

}
