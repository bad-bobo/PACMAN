package gridworld.actor;

/**
 * TextCell is the "Actor pretender" class.  It inherits from Actor.
 * Without the minimal TextCellDisplay class, TextCells objects
 * would display as "?".
 * <p>
 * TextCells display as black text (from toString) on a white background.
 * All the normal text "options" apply.
 *
 * @author George Peck
 * @author TextCell.java
 * @version Nov 15, 2007
 */
public class TextCell extends Actor
{

    String text;


    /**
     * Constructs a TextCell of a given color.
     *
     * @param str text to display
     */
    public TextCell( String str )
    {
        text = str;

    }


    /**
     * Stub, a textCell does not move
     */
    @Override public void act()
    {

    }


    /**
     * Converts the textcell string to a string that can fit in the cell
     * This is from the original gridworld
     * @return a String
     */
    @Override public String toString()
    {
        if ( text.length() == 1 )
            return "  " + text + " ";
        else if ( text.length() == 2 )
            return " " + text + " ";
        else
            return text;
    }
}