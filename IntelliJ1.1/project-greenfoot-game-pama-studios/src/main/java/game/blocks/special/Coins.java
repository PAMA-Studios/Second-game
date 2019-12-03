package game.blocks.special;

import game.Options;
import game.blocks.Blocks;
import game.blocks.normal.BreakableBlocks;
import greenfoot.Actor;

public class Coins extends Blocks {

    /** Represents how many coins this coin object is worth*/
    private int value;
    public boolean fromPSwitch = false;
    public int fromBreakableBlockID;

    /**
     * Calls the constructor with menu = false letting the game know this is not a main menu coin
     * 
     * @param ID        used to get what file should be displayed as the image of this object
     * @see #Coins(int, boolean) 
     */
    public Coins(int ID) {
        this(ID, false);
    }

    /**
     * Calls for the constructor in Blocks.java to set the image of the tile.
     * All of these images will be 1x1 in the grid. (Options.blockSize * Options.blockSize)
     *
     * @param ID    used to get what file should be displayed as the image of this object
     * @see Blocks#Blocks(int)
     */
    public Coins(int ID, boolean menu, int fromBlock) {
       this(ID, menu);
       this.fromBreakableBlockID = fromBlock;
    }
    public Coins(int ID, boolean menu){
        super(ID);
        if (menu) {
            getImage().scale(Options.blockSize * 2, Options.blockSize * 2);
        }
        switch(ID) {
            case 166:
                this.value = 1; //bronze
                break;
            case 167:
                this.value = 20; //gold
                break;
            case 168:
                this.value = 10; //silver
                break;
            default:
        }
    }

    /**
     * Gets the value of the coin as integer
     *
     * @return returns the {@link Coins#value} of the coin
     * @see Coins#value
     */
    public int getValue() {
        return value;
    }
}