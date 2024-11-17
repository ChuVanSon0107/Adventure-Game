package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
    private KeyHandler keyHandler;
    private final int screenX;
    private final int screenY;

    //PLAYER COUNTER
    private int invincibleCounter;//time not to be attacked by monster

    //PLAYER STATUS
    private int life;
    private int maxLife;
    private boolean invincible;//invincible = false => monster can attack, invincible = true => monster cant't attack


    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);  
        
        this.keyHandler = keyHandler;

        this.screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        this.screenY = gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2);

        this.solidArea = new Rectangle();
        this.solidArea.x = 8;
        this.solidArea.y = 16;
        this.solidAreaDefaultX = solidArea.x;
        this.solidAreaDefaultY = solidArea.y;
        this.solidArea.width = 32;
        this.solidArea.height = 32;

        this.setDefaultValues();
        this.getImage();
    }

    public void setDefaultValues(){

        this.worldX = gamePanel.tileSize * 23;
        this.worldY = gamePanel.tileSize * 21;
        this.speed = 4;
        this.direction = "down";

        //PLAYER STATUS
        this.maxLife = 6;
        this.life = maxLife;
        this.collisionOn = false;

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
    public int getMaxLife(){
        return maxLife;
    }
    public int getLife(){
        return life;
    }
    public int getInvincibleCounter(){
        return invincibleCounter;
    }
    public boolean getInvincible(){
        return invincible;
    }
    public void setInvincibleCounter(int invincibleCounter){
        this.invincibleCounter = invincibleCounter;
    }
    public void setInvincible(boolean invincible){
        this.invincible = invincible;
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

            this.collisionOn = false;

            //CHECK TILE COLLISION
            this.checkTile();

            //CHECK MONSTER COLLISION
            int monsterIndex = this.checkMonster(gamePanel.getMonsters());
            this.contactMonster(monsterIndex);
            
            //IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(this.collisionOn == false){
                switch (this.direction) {
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


        //invincible = true => monster can't attack, invincible = false => thay can attack
        if(invincible == true){
            invincibleCounter ++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void contactMonster(int i){
        if(i != -1){
            if(invincible == false){
                receiveDamage(1);
                invincible = true;
            }
        }
    }

    public void receiveDamage(int x){
        this.life -= x;
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
