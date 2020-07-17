package unsw.dungeon;

public class Log {
    private int treasureObtained;
    private int swordsUsed;

    public Log() {
        treasureObtained = 0;
        swordsUsed = 0;
    }

    public void logItem(PickUpItem item) {
        if (item.getPickUpItem() instanceof Treasure) {
            treasureObtained++;
            System.out.println("Picked up treasure");
        } else if (item.getPickUpItem() instanceof Sword) {
            swordsUsed++;
            System.out.println("Picked up sword");
        } else {
            System.out.println("Got a new item");
        }
    }

    public int getTreasureObtained() {
        return treasureObtained;
    }
}