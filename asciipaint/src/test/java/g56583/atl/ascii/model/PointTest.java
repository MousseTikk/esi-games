package g56583.atl.ascii.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {


    @Test
    public void testGetX() {
        System.out.println("testGetX");
        Point point = new Point(3.0, 4.0);
        assertEquals(3.0, point.getX(), 0.01);
    }

    @Test
    public void testGetY() {
        System.out.println("testGetY");
        Point point = new Point(3.0, 4.0);
        assertEquals(4.0, point.getY(), 0.01);
    }

    @Test
    public void testMove() {
        System.out.println("testMove");
        Point point = new Point(3.0, 4.0);
        point.move(1.0, 2.0);
        assertEquals(4.0, point.getX(), 0.01);
        assertEquals(6.0, point.getY(), 0.01);
    }

    @Test
    public void testDistanceTo() {
        System.out.println("testDistanceTo");
        Point point1 = new Point(1.0, 1.0);
        Point point2 = new Point(4.0, 5.0);
        double distance = point1.distanceTo(point2);
        assertEquals(5.0, distance, 0.01);
    }

}