package unsw.dungeon;

/**
 * Keeps track of user progress
 */
public class Log {
    private int nTreasureObtained;
    private int nSwordsUsed;
    // private int nEnemiesKilled;
    private int nPotionsConsumed;
    private int nStepsTaken;
    // private int nTeleports;
    private int nBowShotsHit;
    private int nBowShotsMissed;

    /**
     * Initalises log
     */
    public Log() {
        nTreasureObtained = 0;
        nSwordsUsed = 0;
        // nEnemiesKilled = 0;
        nPotionsConsumed = 0;
        nStepsTaken = 0;
        // nTeleports = 0;
        nBowShotsHit = 0;
        nBowShotsMissed = 0;
    }

    /**
     * Logs ranged attack
     * @param hit Shot hit or missed (Boolean)
     */
    public void logRangedAtk(Boolean hit) {
        if (hit) {
            nBowShotsHit++;
        }
        else {
            nBowShotsMissed++;
        }
    }

    /**
     * Returns the accuracy of the player
     * @return Accuracy of player (float)
     */
    public float calculateAcc() {
        if (nBowShotsHit == 0 && nBowShotsMissed == 0) {
            return 100;
        }
        return 100 * (nBowShotsHit / (nBowShotsHit + nBowShotsMissed));
    }

    /**
     * Logs the amount of steps taken
     */
    public void logMovement() {
        nStepsTaken++;
    }

    /**
     * Obtains steps taken by player
     * @return Steps taken (int)
     */
    public int getSteps() {
        return nStepsTaken;
    }

    /**
     * Logs the items used by the player
     * @param item Item used (Item)
     */
    public void logItem(PickUp item) {
        Item nextItem = item.getItemFromPickUp();
        if (nextItem instanceof Treasure) {
            nTreasureObtained++;
        } 
        else if (nextItem instanceof Sword) {
            nSwordsUsed++;
        } 
        else if (nextItem instanceof Potion) {
            nPotionsConsumed++;
        } 
        else {
            System.out.println("Got a new item");
        }
    }

    /**
     * Logs the amount of treasure obtained by the player
     * @return Treasure obtained by player (int)
     */
    public int getNTreasureObtained() {
        return nTreasureObtained;
    }

    /**
     * Obtains the number of swords used by the player
     * @return Number of swords (int)
     */
    public int getSwords() {
        return nSwordsUsed;
    }

    /**
     * Obtains the number of potions used by the player
     * @return Number of potions (int)
     */
    public int getPotions() {
        return nPotionsConsumed;
    }
}