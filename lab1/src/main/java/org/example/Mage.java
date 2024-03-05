package org.example;
import java.util.*;
import java.lang.Comparable;

public class Mage implements Comparable<Mage>{
    private final String name;
    private final int level;
    private final double power;
    private final Set<Mage> apprentices;

    public Mage(String name, int level, double power, int mode) {
        this.name = name;
        this.level = level;
        this.power = power;
        if(mode==1)
        {
            this.apprentices = new TreeSet<>();
        }
        else if(mode==2)
        {
            this.apprentices = new TreeSet<>();
        }
        else
        {
            this.apprentices = new TreeSet<>(new Mage.AlternativeCompare());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mage mage = (Mage) o;
        return level == mage.level && Double.compare(mage.power, power) == 0 && Objects.equals(name, mage.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level, power);
    }

    @Override
    public String toString() {
        return "Mage{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", power=" + power +
                '}';
    }

    public void print(int depth)
    {
        System.out.println("-".repeat(Math.max(0, depth)) + this);
        for (Mage element : apprentices) {
            element.print(depth + 1);
        }
    }

    public int apprenticesNumber()
    {
        int ctr = 0;
        for (Mage element : apprentices) {
            ctr++;
            ctr += element.apprenticesNumber();
        }
        return ctr;
    }

    void addApprentice(Mage o)
    {
        apprentices.add(o);
    }

    public void addToMap(Map<Mage,Integer> map)
    {
        map.put(this,apprenticesNumber());
        for (Mage element : apprentices) {
            element.addToMap(map);
        }
    }

    @Override
    public int compareTo(Mage o) {
        int nameCompare = this.name.compareTo(o.name);
        if(nameCompare != 0)
        {
            return nameCompare;
        }
        else
        {
            int levelCompare = Integer.compare(this.level,o.level);
            if(levelCompare != 0)
            {
                return levelCompare;
            }
            else
            {
                return Double.compare(this.power,o.power);
            }
        }
    }

    public static class AlternativeCompare implements Comparator<Mage> {
        @Override
        public int compare(Mage mage1, Mage mage2) {
            int levelCompare = Integer.compare(mage1.level, mage2.level);
            if (levelCompare != 0)
            {
                return levelCompare;
            }
            else
            {
                int powerCompare =  Double.compare(mage1.power, mage2.power);
                if (powerCompare != 0)
                {
                    return powerCompare;
                }
                else
                {
                    return mage1.name.compareTo(mage2.name);
                }
            }
        }
    }
}
