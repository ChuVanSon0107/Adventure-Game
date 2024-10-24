package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity {

    private GamePanel gamePanel;
    private KeyHandler keyHandler;
    private int worldX, worldY;
    private final int screenX;
    private final int screenY;
    private UtilityTool utilityTool = new UtilityTool();

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);  
        
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        screenY = gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2);

        setDefaultValues();
        getImage();
    }

    public void setDefaultValues(){

        worldX = gamePanel.tileSize * 23;
        worldY = gamePanel.tileSize * 21;
        speed = 4;
        direction = "down";

    }

    public void getImage(){

        up1 = utilityTool.setup("/res/boy_walk/boy_up_1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = utilityTool.setup("/res/boy_walk/boy_up_2", gamePanel.tileSize, gamePanel.tileSize);
        down1 = utilityTool.setup("/res/boy_walk/boy_down_1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = utilityTool.setup("/res/boy_walk/boy_down_2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = utilityTool.setup("/res/boy_walk/boy_left_1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = utilityTool.setup("/res/boy_walk/boy_left_2", gamePanel.tileSize, gamePanel.tileSize);
        right1 = utilityTool.setup("/res/boy_walk/boy_right_1", gamePanel.tileSize, gamePanel.tileSize);
        right2 = utilityTool.setup("/res/boy_walk/boy_right_2", gamePanel.tileSize, gamePanel.tileSize);
        
    }



    @Override
    public void update() {
        if(keyHandler.upPressed == true || keyHandler.downPressed == true || keyHandler.leftPressed == true || keyHandler.rightPressed == true || keyHandler.enterPressed == true){
            if(keyHandler.upPressed == true){
                direction = "up";
                worldX -= speed;

            }
            else if(keyHandler.downPressed == true){
                direction = "down";
                worldX += speed;
            }
            else if(keyHandler.leftPressed == true){
                direction = "left";
                worldY -= speed;
            }
            else if(keyHandler.rightPressed == true){
                direction = "right";
                worldY += speed;
            }

            spriteCounter ++;
            if(spriteCounter > 12){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

        }
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
        case "up":
            if(spriteNum == 1){ image = up1; }
            else if(spriteNum == 2){ image = up2; }
            break;
        case "down":
            if(spriteNum == 1){ image = down1; }
            else if(spriteNum == 2){ image = down2; }
            break;
        case "left":
            if(spriteNum == 1){ image = left1; }
            else if(spriteNum == 2){ image = left2; }
            break;
        case "right":
            if(spriteNum == 1){ image = right1; }
            else if(spriteNum == 2){ image = right2; }
            break;
        }

        graphics2D.drawImage(image, tempScreenX, tempScreenY, null);

    }

}
