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
        } else if (item.getPickUpItem() instanceof Sword) {
            swordsUsed++;
        } else {
            System.out.println("Got a new item");
        }
    }
}