package com.shehel.chernobyl.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by shehel on 8/2/2016.
 */
public class SpriteSheet {

    //path to spritesheet
    private String path;
    public final int SIZE;

    public int[] pixels;
    public static SpriteSheet projectile_spell = new SpriteSheet("/texture/sheets/projectiles/spell.png", 48);

    public static SpriteSheet tiles = new SpriteSheet("/texture/sheets/spritesheet.png", 256);

    static {
        //projectile_spell.load();
        //tiles.load();
    }
    public SpriteSheet(String path, int size) {
        this.path = path;
        this.SIZE = size;
        pixels = new int[SIZE * SIZE];
        load();


    }

    private void load() {
        //buffered image object is basically a image, set image = image of the path
        try {
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();

            //translates loaded image into pixels in the pixel array
            image.getRGB(0, 0, w, h, pixels, 0, w);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
