package main;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Towers")
@NamedQuery(name = "Tower.findByName", query = "SELECT m FROM Tower m WHERE m.name = :name")
public class Tower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private int height;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mage> mages;

    public Tower() {
    }

    public Tower(String name, int height, List<Mage> mages) {
        this.name = name;
        this.height = height;
        this.mages = mages;
    }

    public void addMage(Mage mage){
        mages.add(mage);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<Mage> getMages() {
        return mages;
    }

    public void setMages(List<Mage> mages) {
        this.mages = mages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tower tower = (Tower) o;
        return height == tower.height && Objects.equals(id, tower.id) && Objects.equals(name, tower.name) && Objects.equals(mages, tower.mages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, height, mages);
    }

    @Override
    public String toString() {
        return "Tower{" +
                "name='" + name + '\'' +
                ", height=" + height +
                '}';
    }
}