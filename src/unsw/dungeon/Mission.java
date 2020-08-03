package unsw.dungeon;

/**
 * Used as a general interface for quests
 */
public interface Mission {
    /**
     * Checks if the mission has been complete
     * @return Complete status of mission (Boolean)
     */
    public Boolean complete();

    public String descript();
}
