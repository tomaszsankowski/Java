package main;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Mages")
@NamedQuery(name = "Mage.findByName", query = "SELECT m FROM Mage m WHERE m.name = :name")
public class Mage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int level;

    @ManyToOne
    private Tower tower;

    @Override
    public String toString() {
        return "Mage{" +
                "name='" + name + '\'' +
                ", level=" + level +
                '}';
    }

    public Mage() {
    }

    public Mage(String name, int level, Tower tower) {
        this.name = name;
        this.level = level;
        this.tower = tower;
        if(tower!=null){
            tower.addMage(this);
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setTower(Tower tower) {
        this.tower = tower;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public Tower getTower() {
        return tower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mage mage = (Mage) o;
        return level == mage.level && Objects.equals(id, mage.id) && Objects.equals(name, mage.name) && Objects.equals(tower, mage.tower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, level, tower);
    }
}
