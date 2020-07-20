package test;

import org.json.JSONObject;

import java.util.ArrayList;

import unsw.dungeon.Entity;
import unsw.dungeon.DungeonLoader;
import unsw.dungeon.Player;
import unsw.dungeon.Wall;
import unsw.dungeon.Gnome;
import unsw.dungeon.LostGnome;
import unsw.dungeon.CamoGnome;
import unsw.dungeon.PickUp;
import unsw.dungeon.Exit;
import unsw.dungeon.Door;
import unsw.dungeon.Portal;

public class TestLoader extends DungeonLoader {
    private ArrayList<Entity> entities;

    public TestLoader(JSONObject json) {
        super(json);
        this.entities = new ArrayList<Entity>();
    }

    @Override
    public void onLoad(Player player) {
        entities.add(player);
    }

    @Override
    public void onLoad(Wall wall) {
        entities.add(wall);
    }

    @Override
    public void onLoad(Gnome gnome) {
        entities.add(gnome);
    }

    @Override
    public void onLoad(LostGnome lostGnome) {
        entities.add(lostGnome);
    }

    @Override
    public void onLoad(CamoGnome camoGnome) {
        entities.add(camoGnome);
    }

    @Override
    public void onLoad(PickUp pickup) {
        entities.add(pickup);
    }

    @Override
    public void onLoad(Exit exit) {
        entities.add(exit);
    }

    @Override
    public void onLoad(Door door) {
        entities.add(door);
    }

    @Override
    public void onLoad(Portal portal) {
        entities.add(portal);
    }

    public Player getPlayer() {
        for (Entity e: entities) {
            if (e instanceof Player) {
                return (Player) e;
            }
        }
        return null;
    }
}
