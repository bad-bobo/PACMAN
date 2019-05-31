package JUnitTests;

import org.junit.jupiter.api.Test;
import project.Main;
import project.Mechanics;

import static org.junit.jupiter.api.Assertions.*;


class MainTest
{

    /**
     * Visually see if the map looks good, no broken walls etc
     */
    @Test void main()
    {
        Main.main(new String[]{});
        Main.world.show();
        Mechanics.sleep( 1000 );
    }


    @Test void loadGame()
    {
    }
}