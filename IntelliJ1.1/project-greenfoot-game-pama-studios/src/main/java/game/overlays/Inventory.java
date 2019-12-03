package game.overlays;

import greenfoot.Actor;
import greenfoot.GreenfootImage;
import game.blocks.special.Keys;
import game.Options;
import game.entities.Player;

public class Inventory extends Actor
{
    private GreenfootImage inventory = new GreenfootImage("glassPanel.png");
    private String purpose;

    private boolean started = false; //gets checked first frame to get the startX and startY
    private int startX; //gets set to where the heart was placed initially
    private int startY; //gets set to where the heart was placed initially


    public Inventory(String purpose) {
        this.purpose = purpose;
        inventory.scale((Options.blockSize),(Options.blockSize));
        setImage(inventory);
        if (purpose.equals("item")) {
            getImage().setTransparency(0);
        }
    }
    public void act() {
        //get start location if first frame
        if (!started) {
            if (Options.bonusHeartUnlocked) {
                setLocation(getX() + Options.blockSize, getY()); //move it a bit over to make space for the bonus heart
            }
            startX = getX();
            startY = getY();
            started = true;
        } else { //set the heart to where it started to overwrite any kind of camera or entity offset (rlly shouldnt move as its a HUD)
            setLocation(startX, startY);
        }
        if (purpose.equals("item")) {
            switch (Player.inventoryItem) {
                case "blueKey":
                    setImage(Keys.blueKey);
                    break;
                case "greenKey":
                    setImage(Keys.greenKey);
                    break;
                case "redKey":
                    setImage(Keys.redKey);
                    break;
                case "yellowKey":
                    setImage(Keys.yellowKey);
                    break;
                default:
                    setImage(inventory);
                    break;
            }
        }
    }
}