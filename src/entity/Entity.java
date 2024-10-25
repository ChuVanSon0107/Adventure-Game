package entity;

import main.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;;

public abstract class Entity {
    
    protected GamePanel gamePanel;
    protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    //BufferedImage: describes an Image with an accessible buffer of image data (we use to store our image files)

    //STATE
    protected int worldX, worldY;
    protected String direction = "down";
    protected int spriteNum = 1;
    protected int speed = 0;


    //COUNTER
    protected int spriteCounter = 0;


    public Entity(GamePanel gamePanel){
        this.gamePanel = gamePanel;

    }


    public abstract void update();
    public abstract void draw(Graphics2D graphics2D);


}
