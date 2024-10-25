package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import tile.Tile;

public class UtilityTool {
    public BufferedImage setup(String imagePath,int width, int height){
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = scaleImage(image, width, height);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;

    }

    public void setup(Tile tile, String imageName, int width, int height, boolean collision){
        try{
            tile = new Tile();
            tile.image = ImageIO.read(getClass().getResourceAsStream("/res/tile/" + imageName + ".png"));
            tile.image = scaleImage(tile.image, width, height);
            tile.collision = collision;
        }
        catch(IOException e){
            e.printStackTrace();
        } 
    }

    public BufferedImage scaleImage(BufferedImage original, int width, int height){
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();

        return scaledImage;
    }
}
