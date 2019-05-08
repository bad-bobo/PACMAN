package gridworld.actor;

import gridworld.grid.Grid;

import java.awt.*;


public class Pellet extends Actor implements Edible
{
    public Pellet()
    {
        super();
        setColor( new Color( 247, 255, 160 ) );
    }


    public void act()
    {

    }

    public void action(Grid grid){
    Score.inc();
    }

}
