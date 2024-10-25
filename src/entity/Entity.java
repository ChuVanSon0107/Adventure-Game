package entity;

import main.GamePanel;
import main.UtilityTool;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;;

public abstract class Entity {
    
    protected GamePanel gamePanel;
    protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    //BufferedImage: describes an Image with an accessible buffer of image data (we use to store our image files)
    protected UtilityTool utilityTool = new UtilityTool();
    protected Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    protected int solidAreaDefaultX, solidAreaDefaultY;

    //STATE
    protected int worldX, worldY;
    protected String direction = "down";
    protected int spriteNum = 1;
    protected int speed = 0;
    protected boolean collisionOn = false;


    //COUNTER
    protected int spriteCounter = 0;


    public Entity(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public abstract void update();
    public abstract void draw(Graphics2D graphics2D);



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

}
