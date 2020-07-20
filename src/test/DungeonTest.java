package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Gnome;
import unsw.dungeon.Switch;
import unsw.dungeon.Boulder;
import unsw.dungeon.Enemy;

public class DungeonTest {

    @Test
    public void initDungeonTest(){
        
        // Check dungeon initialized to right size 
        Dungeon d1 = new Dungeon(10, 10);
        assertEquals(10, d1.getWidth());
        assertEquals(10, d1.getHeight());

        // Check that enemy entities are correctly returned
        Gnome e1 = new Gnome(0, 0, d1);
        Gnome e2 = new Gnome(1, 1, d1);
        d1.addEntity(e1);
        d1.addEntity(e2);

        ArrayList<Enemy> cmp = new ArrayList<Enemy>();
        cmp.add(e1);
        cmp.add(e2);
        assertEquals(cmp, d1.getEnemies());
    }

    @Test  
    public void countEnemiesTest() {

        // Check that 0 enemies when initialized
        Dungeon d1 = new Dungeon(10, 10);
        assertEquals(0,d1.enemiesRemaining());

        Gnome e1 = new Gnome(0, 0, d1);
        Gnome e2 = new Gnome(1, 1, d1);
        d1.addEntity(e1);
        d1.addEntity(e2);
        assertEquals(2,d1.enemiesRemaining());

        // Check that enemiesRemaining() updates when enemies are removed
        d1.removeEntity(e1);
        assertEquals(1,d1.enemiesRemaining());
    }

    @Test 
    public void checkSwitchesTest() {

        // Test allSwitchesActivated() function as new switches
        // and boulders are added and activated
        Dungeon d1 = new Dungeon(10, 10);
        Switch s1 = new Switch(1,1);
        d1.addEntity(s1);
        assertEquals(false, d1.allSwitchesActivated());

        Switch s2 = new Switch(2,2);
        d1.addEntity(s2);
        assertEquals(false, d1.allSwitchesActivated());

        Boulder b1 = new Boulder(1,1);
        d1.addEntity(b1);
        assertEquals(false, d1.allSwitchesActivated());

        Boulder b2 = new Boulder(1,2);
        d1.addEntity(b2);
        assertEquals(false, d1.allSwitchesActivated());

        Boulder b3 = new Boulder(2,2);
        d1.addEntity(b3);
        assertEquals(true, d1.allSwitchesActivated());

    }

    @Test
    public void adjMovesTest() {
        // Test validAdjacentMoves for different dungeon sizes
        Dungeon d1 = new Dungeon(10, 10);
        Boolean[] l1 = {true, true, true ,true};
        assertArrayEquals(l1, d1.validAdjacentMoves(5, 5));
        Boolean[] l2 = {false, true, false, true};
        assertArrayEquals(l2, d1.validAdjacentMoves(0, 0));
        Boolean[] l3 = {true, true, true, true};
        assertArrayEquals(l3, d1.validAdjacentMoves(4, 4));
        Dungeon d2 = new Dungeon(1, 1);
        Boolean[] l4 = {false, false, false, false};
        assertArrayEquals(l4, d2.validAdjacentMoves(0,0));
        Boolean[] l5 = {true, false, true, false};
        assertArrayEquals(l5, d2.validAdjacentMoves(4, 4));
    }
}