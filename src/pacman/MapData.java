package pacman;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class MapData {
    String map[];
    Image image;
    Pane pane;
    Block block;
    ImageView imageView;
    ArrayList<Block> objects;
    public MapData(Pane pane, Image image, ArrayList<Block> objects){
        this.image = image;
        this.pane = pane;
        this.objects = objects;
        map = new String[]{
                "1111111111111111111111111111",
                "1000000000000000000000000001",
                "1011110100000000000010111101",
                "1010010111111011111110100101",
                "1010010000001010000000100101",
                "1011110101101010111010111101",
                "1010111111101110111111101101",
                "1010110000000000000000101101",
                "1010110101111111111010101101",
                "1010000101000000001010001101",
                "1011011101111111111011011101",
                "1000000000000000000000000001",
                "1011111101111101111101111101",
                "1011011101110000011101110101",
                "1000000000000111000000000001",
                "1111111111111111111111111111"
        };
    }

    public void drawMap(){
        for(int yBlock = 0; yBlock < map.length; yBlock++)
            for(int xBlock = 0; xBlock < map[yBlock].length(); xBlock++){
                block = new Block(map[yBlock].charAt(xBlock), xBlock, yBlock, pane);
                objects.add(block);
                if(map[yBlock].charAt(xBlock)=='0'){
                    block = new Block('2', xBlock, yBlock, pane);
                    objects.add(block);
                }

            }
    }
}
