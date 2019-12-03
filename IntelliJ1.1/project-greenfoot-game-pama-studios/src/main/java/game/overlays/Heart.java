package game.overlays;

import greenfoot.Actor;
import greenfoot.GreenfootImage;
import game.Options;
import game.entities.Player;

/**
 *
 */
public class Heart extends Actor {

    /** set to what heart this is, so what health it should look at (heart 1 will be full at 2 or more hp and half at 1) */
    private int heart;
    /** gets checked first frame to get the startX and startY */
    private boolean started = false;
    /** Initial starting coordinates of the heart */
    private int startX, startY;
    /** Image for when the heart is half full */
    private GreenfootImage half = new GreenfootImage("heartHalf.png");
    /** Image for when the heart is full */
    private GreenfootImage full = new GreenfootImage("heartFull.png");
    /** Image for when the heart is empty */
    private GreenfootImage empty = new GreenfootImage("heartEmpty.png");

    public Heart(int heart) {
        this(heart, false);
    }
    /**
     * Constructor, the value given here is which spot it takes
     * giving a value of 1 would mean the last heart, value of 3 would be the first when total health is 6
     * hearts have 2 hp each (full & half full)
     *
     * @param heart         give which heart this is, 1 would be the last heart
     */
    public Heart(int heart, boolean bonusheart) {
        this.heart = heart;
        if (bonusheart) {
            //set yellow heart imgs
        }
        half.scale((Options.blockSize),(Options.blockSize));
        full.scale((Options.blockSize),(Options.blockSize));
        empty.scale((Options.blockSize),(Options.blockSize));
        renderHeart();

    }

    /**
     * Renders the hearts in the right place and with the right value based
     * on what was set in the constructor.
     */
    private void renderHeart() {
        //get what state the heart is in
        String state;
        if (Player.health == (heart * 2 - 1)) state = "half";
        else if (Player.health >= heart * 2) state = "full";
        else state = "empty";
        //set the right image based on the state
        if (state.equals("half")) {
            setImage(half);
        } else if (state.equals("full")) {
            setImage(full);
        } else {
            setImage(empty);
        }
    }

    /**
     * Act method called every frame to set the hearts to the location they were started at.
     * Start locations are set in the world when using addObject(actor, x, y);
     */
    public void act() {
        //get start location if first frame
        if (!started) {
            startX = getX();
            startY = getY();
            started = true;
        } else { //set the heart to where it started to overwrite any kind of camera or entity offset (rlly shouldnt move as its a HUD)
            setLocation(startX, startY);
        }
        //update the image of the heart
        renderHeart();
    }


}
