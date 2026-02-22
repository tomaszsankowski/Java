package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.mockito.Mockito;

class MageRepositoryTest {

    // assertEquals; assertNotEquals();
    // assertTrue(); assertFalse();
    // assertNull(); assertNotNull();

    @Test
    void find() {
        MageRepository mageRepository = new MageRepository();
        mageRepository.save(new MageDTO("Gandalf", 100));
        mageRepository.save(new MageDTO("Saruman", 99));
        mageRepository.save(new MageDTO("Radagast", 0));
        mageRepository.save(new MageDTO("", -100));
        assertEquals("Mage{name='Gandalf', level=100}", (new Mage(mageRepository.find("Gandalf").get())).toString());
        assertEquals("Mage{name='Saruman', level=99}", (new Mage(mageRepository.find("Saruman").get())).toString());
        assertEquals("Mage{name='Radagast', level=0}", (new Mage(mageRepository.find("Radagast").get())).toString());
        assertEquals("Mage{name='', level=-100}", (new Mage(mageRepository.find("").get())).toString());
    }

    @Test
    void findReturnEmptyOptional() {
        MageRepository mageRepository = new MageRepository();
        assertFalse(mageRepository.find("Gandalf").isPresent());
        assertFalse(mageRepository.find("Sauron").isPresent());
        assertFalse(mageRepository.find("").isPresent());
    }

    @Test
    void save() {
        MageRepository mageRepository = new MageRepository();

        mageRepository.save(new MageDTO("Gandalf", 100));
        mageRepository.save(new MageDTO("Saruman", 0));
        mageRepository.save(new MageDTO("Radagast", 50));
        mageRepository.save(new MageDTO("", -150));

        assertThat(mageRepository.find("Gandalf")).hasValueSatisfying(mage -> {
            assertThat(mage.getName()).isEqualTo("Gandalf");
            assertThat(mage.getLevel()).isEqualTo(100);
        });
        assertThat(mageRepository.find("Saruman")).hasValueSatisfying(mage -> {
            assertThat(mage.getName()).isEqualTo("Saruman");
            assertThat(mage.getLevel()).isEqualTo(0);
        });
        assertThat(mageRepository.find("Radagast")).hasValueSatisfying(mage -> {
            assertThat(mage.getName()).isEqualTo("Radagast");
            assertThat(mage.getLevel()).isEqualTo(50);
        });
        assertThat(mageRepository.find("")).hasValueSatisfying(mage -> {
            assertThat(mage.getName()).isEqualTo("");
            assertThat(mage.getLevel()).isEqualTo(-150);
        });
    }

    @Test
    void saveThrowIllegalArgumentException()
    {
        MageRepository mageRepository = new MageRepository();

        mageRepository.save(new MageDTO("Gandalf", 100));
        assertThat(mageRepository.find("Gandalf")).hasValueSatisfying(mage -> {
        assertThat(mage.getName()).isEqualTo("Gandalf");
        assertThat(mage.getLevel()).isEqualTo(100);
        });

        assertThrows(IllegalArgumentException.class, () -> {mageRepository.save(new MageDTO("Gandalf", 100));});
        assertThrows(IllegalArgumentException.class, () -> {mageRepository.save(new MageDTO("Gandalf", 15));});
        assertThrows(IllegalArgumentException.class, () -> {mageRepository.save(new MageDTO("Gandalf", 0));});
        assertThrows(IllegalArgumentException.class, () -> {mageRepository.save(new MageDTO("Gandalf", -125));});
    }

    @Test
    void delete() {
        MageRepository mageRepository = new MageRepository();

        mageRepository.save(new MageDTO("Gandalf", 100));
        mageRepository.save(new MageDTO("Saruman", 50));
        mageRepository.save(new MageDTO("Radagast", 0));
        mageRepository.save(new MageDTO("", -50));
        mageRepository.delete("Gandalf");
        mageRepository.delete("Saruman");
        mageRepository.delete("Radagast");
        mageRepository.delete("");
        assertThat(mageRepository.find("Gandalf")).isEmpty();
        assertThat(mageRepository.find("Saruman")).isEmpty();
        assertThat(mageRepository.find("Radagast")).isEmpty();
        assertThat(mageRepository.find("")).isEmpty();
    }

    @Test
    void deleteThrowIllegalArgumentException() {
        MageRepository mageRepository = new MageRepository();
        assertThrows(IllegalArgumentException.class, () -> mageRepository.delete("Gandalf"));
        assertThrows(IllegalArgumentException.class, () -> mageRepository.delete(""));
        assertThrows(IllegalArgumentException.class, () -> mageRepository.delete("43205*fda"));
    }
}