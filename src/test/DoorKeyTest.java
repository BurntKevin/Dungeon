package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import unsw.dungeon.Door;
import unsw.dungeon.Key;

import javafx.beans.property.SimpleBooleanProperty;

public class DoorKeyTest {

    @Test
    public void initDoorTest() {
        Door dt = new Door(0,0,0);
        assertEquals(false, dt.checkOpen());
        // Check that door is closed on initialization 
        SimpleBooleanProperty bt = new SimpleBooleanProperty(false);
        assertEquals(bt, dt.doorOpened());
    }

    @Test
    public void initKeyTest() {
        Key kt = new Key(1);
        // Check that keyID is initialized to the correct value
        assertEquals(1, kt.getKeyID());
    }

    @Test
    public void initDefaultKeyTest() {
        Key kt = new Key();
        // Check that keyID is initialized to the correct value
        assertEquals(-1, kt.getKeyID());
        assertEquals(false, kt.checkCarryingKey());
        // Check that Key values are properly updated when new key equipped
        Key newkt = new Key(5);
        kt.equipKey(newkt);
        assertEquals(newkt.getKeyID(), kt.getKeyID());
        assertEquals(true, kt.checkCarryingKey());
    }

    @Test
    public void doorKeyMatchTest() {
        // Create a Key and Door pair
        Door dt = new Door(0,0,0);
        Key  kt = new Key(0);
        // Sanity check that Key id is initialized correctly
        assertEquals(0, kt.getKeyID());
        // Check that the correct key opens the door
        assertEquals(true, dt.attemptUnlock(kt));
        assertEquals(true, dt.checkOpen());
        SimpleBooleanProperty bt = new SimpleBooleanProperty(true);
        assertEquals(bt, dt.doorOpened());
        // Check useKey()
        kt.useKey();
        assertEquals(false, kt.checkCarryingKey());
    }

    @Test
    public void doorKeyNoMatchTest() {
        // Create an incompatible Key and Door
        Door dt = new Door(0,0,0);
        Key  kt = new Key(2);
        // Check that the door is not opened using the wrong key
        assertEquals(false, dt.attemptUnlock(kt));
        assertEquals(false, dt.checkOpen());
        SimpleBooleanProperty bt = new SimpleBooleanProperty(false);
        assertEquals(bt, dt.doorOpened());
        
    }
}