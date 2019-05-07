package gridworld.actor;

import gridworld.grid.Grid;
import gridworld.grid.Location;
import project.Main;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public abstract class Ghost extends MovableActor
{



    public Ghost( Color color )
    {
        super();
        setColor( color );
    }


}
