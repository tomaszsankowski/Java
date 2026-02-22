package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class MageRepository {
    private Collection<MageDTO> collection;

    public MageRepository()
    {
        this.collection = new ArrayList<MageDTO>();
    }
    public Optional<MageDTO> find(String name)
    {
        for (MageDTO mage : collection)
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
            MageDTO mage = find(name).get();
            collection.remove(mage);
        }
        catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    public void save(MageDTO mage)
    {
        Optional<MageDTO> existingMage = find(mage.getName());
        if (existingMage.isPresent()) {
            throw new IllegalArgumentException();
        }
        else {
            collection.add(mage);
        }
    }
}
