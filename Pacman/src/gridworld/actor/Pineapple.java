package gridworld.actor;

import gridworld.grid.Grid;

public class Pineapple extends Actor implements Food {

    public Pineapple() {
        setColor(null);
    }

    @Override
    public void eatAction(Grid grid) {
        Score.inc(500); //500 points
    }

    @Override
    public void act() {
    }
}
