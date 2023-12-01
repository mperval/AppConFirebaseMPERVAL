package ies.carrillo.android.appconfirebasemperval.models;

import java.util.ArrayList;

public class Superhero {
    private long id;
    private String name;
    private ArrayList<String> powers;
    private boolean active;

    public Superhero() {
    }

    public Superhero(long id, String name, ArrayList<String> powers, boolean active) {
        this.id = id;
        this.name = name;
        this.powers = powers;
        this.active = active;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getPowers() {
        return powers;
    }

    public void setPowers(ArrayList<String> powers) {
        this.powers = powers;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Superhero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", powers=" + powers +
                ", active=" + active +
                '}';
    }
}
