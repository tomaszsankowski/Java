package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class MageRepository {
    private Collection<Mage> collection;

    public MageRepository()
    {
        this.collection = new ArrayList<Mage>();
    }
    public Optional<Mage> find(String name)
    {
        for (Mage mage : collection)
        {
            if (mage.getName().equals(name))
            {
                return Optional.of(mage);
            }
        }
        return Optional.empty();
    }

    public void delete(String name)
    {
        try {
            Mage mage = find(name).get();
            collection.remove(mage);
        }
        catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    public void save(Mage mage)
    {
        Optional<Mage> existingMage = find(mage.getName());
        if (existingMage.isPresent()) {
            throw new IllegalArgumentException();
        }
        else {
            collection.add(mage);
        }
    }
}
