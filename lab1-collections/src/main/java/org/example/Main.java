package org.example;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        //System.out.println("Wybierz tryb przechowywania zmiennych, oto dostępne tryby :");
        //System.out.println("1 - brak sortowania (HashSet)");
        //System.out.println("2 - domyślne sortowanie (TreeSet)");
        //System.out.println("3 - alternatywne sortowanie (TreeSet)");
        int num = Integer.parseInt(args[0]);
        while(!(num == 1 || num == 2 || num == 3))
        {
            System.out.println("Zła opcja, wybierz liczbę spośród [1,2,3] !");
            return;
        }

        Set<Mage> set;

        if(num==1)
        {
            set = new HashSet<>();
        }
        else if(num==2)
        {
            set = new TreeSet<>();
        }
        else
        {
            set = new TreeSet<>(new Mage.AlternativeCompare());
        }

        Mage[] mages1 = {
                new Mage("Laila", 100, 1.5, num),
                new Mage("Wittchen", 15, 2.0, num),
                new Mage("Aaron", 15, 1.0, num)
        };

        Mage[] mages2 = {
                new Mage("Leya", 10, 1.0, num),
                new Mage("Luke", 10, 2.5, num)
        };

        Mage[] mages = {
                new Mage("Laila", 15, 4.5, num),
                new Mage("Laila", 15, 5.5, num),
                new Mage("Wittchen", 15, 5.5, num),
                new Mage("Perun", 13, 8.5, num),
                new Mage("Laila", 20, 1.0, num),
                new Mage("Voldemort", 25, 22.0, num),
                new Mage("Laila", 25, 11.0, num)
        };

        mages1[0].addApprentice(mages2[0]);
        mages1[0].addApprentice(mages2[1]);

        mages[2].addApprentice(mages1[0]);
        mages[2].addApprentice(mages1[1]);
        mages[2].addApprentice(mages1[2]);


        for(Mage element : mages)
        {
            set.add(element);
        }

        for(Mage element : set)
        {
            element.print(1);
        }

        System.out.println();
        System.out.println();
        System.out.println();

        Map<Mage, Integer> map;

        map = generateStatistics(set, num);

        for (Map.Entry<Mage, Integer> entry : map.entrySet())
        {
            System.out.println(entry.getKey() + "\t:\t" + entry.getValue());
        }
    }

    public static Map<Mage, Integer> generateStatistics(Set<Mage> set, int num) {

        Map<Mage, Integer> map;

        if(num==1)
        {
            map = new HashMap<>();
        }
        else if(num==2)
        {
            map = new TreeMap<>();
        }
        else
        {
            map = new TreeMap<>(new Mage.AlternativeCompare());
        }

        for (Mage element : set)
        {
            element.addToMap(map);
        }
        return map;
    }
}