package JUnitTests;

import gridworld.actor.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PointTest {
@Test
void point(){
Point p=new Point(5,55);
    assertEquals(5,p.getX());
    assertEquals(55,p.getY());
    Point l=new Point(34535,3453455);
    Point h=new Point(0,1);
    assertEquals(0,p.compareTo(l));
    assertEquals(0,p.compareTo(h));
    assertEquals(0,l.compareTo(h));


}
}
