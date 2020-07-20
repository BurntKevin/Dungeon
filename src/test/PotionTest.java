package test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Potion;


public class PotionTest {
    /**
     * Potion initalisation test
     */
    @Test
    public void initPotionTest() {
        new Potion();
    }

    /**
     * Using a potion
     */
    @Test
    public void usingPotionTest() {
        // Creating potion
        Potion potion = new Potion();

        // Activating potion
        potion.usePotion();

        // Checking uses of the potion
        for (int i = 0; i < 10; i++) {
            assertTrue(potion.checkPotionActive());
            potion.minusInvTimer();
        }
        assertFalse(potion.checkPotionActive());
    }
}
