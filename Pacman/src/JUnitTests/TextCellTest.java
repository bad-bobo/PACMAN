package JUnitTests;

import gridworld.actor.TextCell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class TextCellTest
{
    /**
     * Testing the to string method
     */
    @Test public void toStringTest()
    {
        TextCell cell = new TextCell("Hello");
        assertEquals( "Hello", cell.toString() );
    }

}