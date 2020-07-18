package unsw.dungeon;

public class Log {
    private int nTreasureObtained;
    private int nSwordsUsed;
    private int nEnemiesKilled;
    private int nPotionsConsumed;
    private int nStepsTaken;
    private int nTeleports;
    private int nBowShotsHit;
    private int nBowShotsMissed;

    public Log() {
        nTreasureObtained = 0;
        nSwordsUsed = 0;
        nEnemiesKilled = 0;
        nPotionsConsumed = 0;
        nStepsTaken = 0;
        nTeleports = 0;
        nBowShotsHit = 0;
        nBowShotsMissed = 0;
    }

    public void logRangedAtk(Boolean hit) {
        if (hit) {
            nBowShotsHit++;
        }
        else {
            nBowShotsMissed++;
        }
    }

    public float calculateAcc() {
        if (nBowShotsHit == 0 && nBowShotsMissed == 0) {
            return 100;
        }
        return 100 * (nBowShotsHit / (nBowShotsHit + nBowShotsMissed));
    }

    public void logMovement() {
        nStepsTaken++;
    }

    public void logItem(PickUpItem item) {
        Item nextItem = item.getPickUpItem();
        if (nextItem instanceof Treasure) {
            nTreasureObtained++;
        } else if (nextItem instanceof Sword) {
            nSwordsUsed++;
        } else if (nextItem instanceof Potion) {
            nPotionsConsumed++;
        }
        } else {
            System.out.println("Got a new item");
        }
    }

}