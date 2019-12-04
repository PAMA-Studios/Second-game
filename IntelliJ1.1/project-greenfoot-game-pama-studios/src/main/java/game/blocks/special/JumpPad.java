package game.blocks.special;
import greenfoot.GreenfootImage;
import game.Options;
import game.entities.Player;
import game.Physics;


public class JumpPad extends Physics {
    private GreenfootImage jumppadDown = new GreenfootImage("243.png");
    private GreenfootImage jumppadUp = new GreenfootImage("244.png");
    public boolean holding;

    public JumpPad(int ID){
        GreenfootImage image = new GreenfootImage ((ID) + ".png");
        image.scale((Options.blockSize),(Options.blockSize)); //scale to the size of 1 block
        setImage(image);
        jumppadDown.scale((int) (Options.blockSize / 20 * 18),(int) (Options.blockSize / 20 * 18));
        jumppadUp.scale((int) (Options.blockSize / 20 * 18),(int) (Options.blockSize / 20 * 18));
        setImage(jumppadUp);
        holding = false;
    }

    /**
     * Act method to keep the spring working fine with physics and compress when something is touching it
     * also keeps the gravity going.
     */
    public void act(){
        setDoubleX(getX());
        setDoubleY(getY());
        if (isTouching(Player.class) || isTouching(Bomb.class) || isTouching(JumpPad.class)) {
            setImage(jumppadDown);
        } else if (getImage() != jumppadUp){
            setImage(jumppadUp);
        }
        if (!holding || Player.dead) {
            updateGravity();
            jumpPad();
        }
    }
}
