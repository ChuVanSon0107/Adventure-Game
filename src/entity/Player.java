package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    private GamePanel gamePanel;
    private KeyHandler keyHandler;
    private int worldX, worldY;
    private final int screenX;
    private final int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);  
        
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        screenY = gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2);

        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

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

    public int getScreenX(){
        return screenX;
    }
    public int getScreenY(){
        return screenY;
    }
    public int getWorldX(){
        return worldX;
    }
    public int getWorldY(){
        return worldY;
    }



    @Override
    public void update() {
        if(keyHandler.upPressed == true || keyHandler.downPressed == true || keyHandler.leftPressed == true || keyHandler.rightPressed == true || keyHandler.enterPressed == true){
            if(keyHandler.upPressed == true){
                direction = "up";
            }
            else if(keyHandler.downPressed == true){
                direction = "down";
            }
            else if(keyHandler.leftPressed == true){
                direction = "left";
            }
            else if(keyHandler.rightPressed == true){
                direction = "right";
            }

            collisionOn = false;

            //CHECK TILE COLLISION
            checkTile();

            
            //IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(this.collisionOn == false){
                switch (direction) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
                }
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


    public void checkTile(){
        int leftWorldX = worldX + solidArea.x;
        int rightWorldX = worldX + solidArea.x + solidArea.width;
        int topWorldY = worldY + solidArea.y;
        int bottomWorldY = worldY + solidArea.y + solidArea.height;

        int leftCol = leftWorldX / gamePanel.tileSize;
        int rightCol = rightWorldX / gamePanel.tileSize;
        int topRow = topWorldY / gamePanel.tileSize;
        int bottomRow = bottomWorldY / gamePanel.tileSize;

        int tileNum1 , tileNum2;

        switch (this.direction) {
            case "up":
                topRow = (topWorldY - speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.getTileManager().mapTileNum[leftCol][topRow];
                tileNum2 = gamePanel.getTileManager().mapTileNum[rightCol][topRow];
                if(gamePanel.getTileManager().tile[tileNum1].collision == true || gamePanel.getTileManager().tile[tileNum2].collision == true ){
                    this.collisionOn = true;
                }
                break;
            case "down":
                bottomRow = (bottomWorldY + speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.getTileManager().mapTileNum[leftCol][bottomRow];
                tileNum2 = gamePanel.getTileManager().mapTileNum[rightCol][bottomRow];
                if(gamePanel.getTileManager().tile[tileNum1].collision == true || gamePanel.getTileManager().tile[tileNum2].collision == true ){
                    this.collisionOn = true;
                }
                break;
            case "left":
                leftCol = (leftWorldX - speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.getTileManager().mapTileNum[leftCol][topRow];
                tileNum2 = gamePanel.getTileManager().mapTileNum[leftCol][bottomRow];
                if(gamePanel.getTileManager().tile[tileNum1].collision == true || gamePanel.getTileManager().tile[tileNum2].collision == true ){
                    this.collisionOn = true;
                }
                break;
            case "right":
                rightCol = (rightWorldX + speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.getTileManager().mapTileNum[rightCol][topRow];
                tileNum2 = gamePanel.getTileManager().mapTileNum[rightCol][bottomRow];
                if(gamePanel.getTileManager().tile[tileNum1].collision == true || gamePanel.getTileManager().tile[tileNum2].collision == true ){
                    this.collisionOn = true;
                }
                break;
        }
    }

}
