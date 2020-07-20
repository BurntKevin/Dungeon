package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Portal;

public class PortalTest {
    @Test
    public void portalMatchTest(){

        // Check symmetry of portal relations
        Portal p1 = new Portal(1,1,1);
        Portal p2 = new Portal(2,2,1);
        
        ArrayList<Portal> ps = new ArrayList<Portal>();
        ps.add(p1);
        ps.add(p2);
        assertEquals(p2, p1.pairedPortal(ps));
        assertEquals(p1, p2.pairedPortal(ps));
    }

    @Test
    public void checkPortalsMatchTest(){
        // Checking if two different portals match
        Portal p1 = new Portal(1, 1, 1);
        Portal p2 = new Portal(2, 1, 0);
        assertFalse(p1.checkPortalsMatch(p1));
        assertFalse(p1.checkPortalsMatch(p2));

        // Checking if a pair of matching portals match
        Portal p3 = new Portal(3, 3, 0);
        assertTrue(p3.checkPortalsMatch(p2));
        assertFalse(p3.checkPortalsMatch(p1));
    }
}
