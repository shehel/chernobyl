package com.shehel.chernobyl.level;

import com.shehel.chernobyl.level.tile.GrassTile;
import com.shehel.chernobyl.level.tile.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by shehe on 8/14/2016.
 */
public class SpawnLevel extends Level {


    public SpawnLevel(String path) {
        super(path);
    }

    //can be done in different ways
    protected void loadLevel(String path) {
        try {
            BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
            int w = width = image.getWidth();
            int h = height = image.getHeight();
            tiles = new int[w * h];
            image.getRGB(0, 0, w, h, tiles, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Exception. Could not load level file!");
        }
    }

    //grass = 0xFF00
    //flower = 0xFFFF00
    //rock = 0x7F7F00
    protected void generateLevel() {
            //old efficient method of getting tile from file
            /*for (int i = 0; i < levelPixels.length; i++) {
                System.out.println("Index" + i + "   " +levelPixels[i]);
                if(levelPixels[i] == 0xff00ff00) tiles[i] = Tile.grass;
                if(levelPixels[i] == 0xffffff00) tiles[i] = Tile.flower;
                if(levelPixels[i] == 0xff7f7f00) tiles[i] = Tile.rock;
            }*/
    }
}
