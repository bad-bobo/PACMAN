package gridworld.actor;

import gridworld.grid.Location;
import gridworld.gui.MenuMaker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Singleton class
public class Pacman extends MovableActor
{
//    private static Pacman SINGLE_INSTANCE = null;


    public Pacman()
    {
        super();
    }


//    public static Pacman getInstance()
//    {
//        if ( SINGLE_INSTANCE == null )
//        {
//            synchronized ( Pacman.class )
//            {
//                if ( SINGLE_INSTANCE == null )
//                {
//                    SINGLE_INSTANCE = new Pacman();
//                }
//            }
//        }
//        return SINGLE_INSTANCE;
//    }


    public void act()
    {

    }

}
