package gridworld.actor;

import gridworld.grid.Grid;

public class Pineapple extends Actor implements Edible {

public Pineapple(){
setColor(null);
}

    @Override
    public void action(Grid grid) {
        Score.inc(500); //500 points
    }

    @Override
    public void act() {
    }
}
