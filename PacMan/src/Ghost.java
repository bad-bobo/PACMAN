import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.awt.*;

public class Ghost extends Actor {

    private Color color;

    public Ghost(Color color) {
        super();
        setColor(color);
    }

    @Override
    public void act() {

        moveTo(new Location(getLocation().getRow(), getLocation().getCol() + 1));

    }


}
