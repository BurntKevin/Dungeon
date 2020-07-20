package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Wall;
import unsw.dungeon.Door;
import unsw.dungeon.Portal;

public class BoulderTest {

    @Test
    public void initBoulderTest() {
        // Test that boulder is initialized correctly
        Boulder bt1 = new Boulder(0, 0);
        assertEquals(0, bt1.getX());
        assertEquals(0, bt1.getY());
        Boulder bt2 = new Boulder(9, 3);
        assertEquals(9, bt2.getX());
        assertEquals(3, bt2.getY());
    }

    @Test
    public void pushBoulderRight() {        
        Boulder bt1 = new Boulder(1,1);

        Portal pt = new Portal(0,0,0);        
        bt1.attemptPush(pt, "Right");
        assertEquals(1, bt1.getX());
        assertEquals(1, bt1.getY());

        Door dt = new Door(0,0,0);        
        bt1.attemptPush(dt, "Right");
        assertEquals(1, bt1.getX());
        assertEquals(1, bt1.getY());

        Wall wt = new Wall(0,0);        
        bt1.attemptPush(wt, "Right");
        assertEquals(1, bt1.getX());
        assertEquals(1, bt1.getY());

        Boulder bt2 = new Boulder(0,0);
        bt1.attemptPush(bt2, "Right");
        assertEquals(1, bt1.getX());
        assertEquals(1, bt1.getY());
    }

    @Test
    public void pushBoulderLeft() {
        Boulder bt1 = new Boulder(1,1);

        Portal pt = new Portal(0,0,0);        
        bt1.attemptPush(pt, "Left");
        assertEquals(1, bt1.getX());
        assertEquals(1, bt1.getY());

        Door dt = new Door(0,0,0);        
        bt1.attemptPush(dt, "Left");
        assertEquals(1, bt1.getX());
        assertEquals(1, bt1.getY());

        Wall wt = new Wall(0,0);        
        bt1.attemptPush(wt, "Left");
        assertEquals(1, bt1.getX());
        assertEquals(1, bt1.getY());

        Boulder bt2 = new Boulder(0,0);
        bt1.attemptPush(bt2, "Left");
        assertEquals(1, bt1.getX());
        assertEquals(1, bt1.getY());
    }

    @Test
    public void pushBoulderUp() {
        Boulder bt1 = new Boulder(1,1);

        Portal pt = new Portal(0,0,0);        
        bt1.attemptPush(pt, "Up");
        assertEquals(1, bt1.getX());
        assertEquals(1, bt1.getY());

        Door dt = new Door(0,0,0);        
        bt1.attemptPush(dt, "Up");
        assertEquals(1, bt1.getX());
        assertEquals(1, bt1.getY());

        Wall wt = new Wall(0,0);        
        bt1.attemptPush(wt, "Up");
        assertEquals(1, bt1.getX());
        assertEquals(1, bt1.getY());

        Boulder bt2 = new Boulder(0,0);
        bt1.attemptPush(bt2, "Up");
        assertEquals(1, bt1.getX());
        assertEquals(1, bt1.getY());
    }

    @Test
    public void pushBoulderDown() {
        Boulder bt1 = new Boulder(1,1);

        Portal pt = new Portal(0,0,0);        
        bt1.attemptPush(pt, "Down");
        assertEquals(1, bt1.getX());
        assertEquals(1, bt1.getY());

        Door dt = new Door(0,0,0);        
        bt1.attemptPush(dt, "Down");
        assertEquals(1, bt1.getX());
        assertEquals(1, bt1.getY());

        Wall wt = new Wall(0,0);        
        bt1.attemptPush(wt, "Down");
        assertEquals(1, bt1.getX());
        assertEquals(1, bt1.getY());

        Boulder bt2 = new Boulder(0,0);
        bt1.attemptPush(bt2, "Down");
        assertEquals(1, bt1.getX());
        assertEquals(1, bt1.getY());
    }
}