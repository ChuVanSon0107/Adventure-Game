package object;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Object {
    protected GamePanel gamePanel;
    protected UtilityTool utilityTool = new UtilityTool();
    
    protected BufferedImage image1, image2, image3;
    protected String name;
    

    public Object(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    protected BufferedImage setup(String imagePath,int width, int height){
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = utilityTool.scaleImage(image, width, height);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;

    }

    public BufferedImage getImage1(){
        return this.image1;
    }

    public BufferedImage getImage2(){
        return this.image2;
    }

    public BufferedImage getImage3(){
        return this.image3;
    }
}
