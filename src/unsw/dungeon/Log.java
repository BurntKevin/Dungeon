package unsw.dungeon;

import java.util.ArrayList;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleFloatProperty;

/**
 * Keeps track of user progress
 */
public class Log {
    private FloatProperty nTreasureObtained;
    private FloatProperty nDeaths;
    private FloatProperty nSwordsUsed;
    private FloatProperty nBowsUsed;
    private FloatProperty nEnemiesKilled;
    private FloatProperty nPotionsConsumed;
    private FloatProperty nStepsTaken;
    // private int nTeleports;
    private FloatProperty nBowShotsHit;
    private FloatProperty nBowShotsMissed;
    private FloatProperty nBowShotsDryFire;

    private FloatProperty nTotalBowShots;
    private FloatProperty bowAccuracy;

    /**
     * Initalises log
     */
    public Log() {
        nTreasureObtained = new SimpleFloatProperty(0);
        nDeaths = new SimpleFloatProperty(0);
        nSwordsUsed = new SimpleFloatProperty(0);
        nBowsUsed = new SimpleFloatProperty(0);
        nEnemiesKilled = new SimpleFloatProperty(0);
        nPotionsConsumed = new SimpleFloatProperty(0);
        nStepsTaken = new SimpleFloatProperty(0);
        // nTeleports = 0;
        nTotalBowShots = new SimpleFloatProperty();
        bowAccuracy = new SimpleFloatProperty(0);
        nBowShotsHit = new SimpleFloatProperty(0);
        nBowShotsMissed = new SimpleFloatProperty(0);
        nBowShotsDryFire = new SimpleFloatProperty(0);
    }

    /**
     * Logs ranged attack
     * @param hit Shot hit or missed (Boolean)
     */
    public void logRangedAtk(Boolean hit) {
        if (hit) {
            incrementFloatProperty(nBowShotsHit);
        }
        else {
            incrementFloatProperty(nBowShotsMissed);
        }
        updAccuracy();
        updRangedAtks();
    }

    /**
     * Returns the accuracy of the player
     * @return Accuracy of player (float)
     */
    private void updAccuracy() {
        if (nBowShotsHit.getValue() == 0 && nBowShotsMissed.getValue() == 0) {
            bowAccuracy.setValue(100.0);
            return;
        }
        bowAccuracy.setValue(100.0 * ((float) nBowShotsHit.getValue() / nTotalBowShots.getValue()));
    }

    public void updRangedAtks() {
        nTotalBowShots.setValue(nBowShotsHit.getValue() + nBowShotsMissed.getValue());
    }

    /**
     * Logs the amount of steps taken
     */
    public void logMovement() {
        incrementFloatProperty(nStepsTaken);
    }

    /**
     * Obtains steps taken by player
     * @return Steps taken (int)
     */
    public float getSteps() {
        return nStepsTaken.getValue();
    }

    /**
     * Logs the items used by the player
     * @param item Item used (Item)
     */
    public void logItem(PickUp item) {
        Item nextItem = item.getItemFromPickUp();
        if (nextItem instanceof Treasure) {
            incrementFloatProperty(nTreasureObtained);
        } 
        else if (nextItem instanceof Sword) {
            incrementFloatProperty(nSwordsUsed);
        }
        else if (nextItem instanceof Bow) {
            incrementFloatProperty(nBowsUsed);
        }
        else if (nextItem instanceof Potion) {
            incrementFloatProperty(nPotionsConsumed);
        } 
        else {
            System.out.println("Got a new item");


        }
    }

    /**
     * Logs the amount of treasure obtained by the player
     * @return Treasure obtained by player (int)
     */
    public float getNTreasureObtained() {
        return nTreasureObtained.getValue();
    }

    /**
     * Obtains the number of swords used by the player
     * @return Number of swords (int)
     */
    public float getSwords() {
        return nSwordsUsed.getValue();
    }

    /**
     * Obtains the number of potions used by the player
     * @return Number of potions (int)
     */
    public float getPotions() {
        return nPotionsConsumed.getValue();
    }

    public void logDryFireRanged() {
        incrementFloatProperty(nBowShotsDryFire);
    }

    private void incrementFloatProperty(FloatProperty toUpd) {
        toUpd.setValue(toUpd.getValue()+1);
    }

    public ArrayList<FloatProperty> trackedProperties() {
        ArrayList<FloatProperty> properties = new ArrayList<>();
        properties.add(nStepsTaken);
        properties.add(nDeaths);
        properties.add(nEnemiesKilled);
        properties.add(nTreasureObtained);
        properties.add(nTotalBowShots);
        properties.add(bowAccuracy);
        return properties;
    }

    public void logStep() {
        incrementFloatProperty(nStepsTaken);
    }

    public void logDeath() {
        incrementFloatProperty(nDeaths);
    }

    public void logKill() {
        incrementFloatProperty(nEnemiesKilled);
    }

    public void logSword() {
        incrementFloatProperty(nSwordsUsed);
    }

    public void logBow() {
        incrementFloatProperty(nBowsUsed);
    }

    public void logPotion() {
        incrementFloatProperty(nPotionsConsumed);
    }

    public void logBowShot() {
        incrementFloatProperty(nTotalBowShots);
        updAccuracy();
    }
}
