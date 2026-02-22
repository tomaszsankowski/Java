package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MageControllerTest {

    private MageRepository mageRepository;
    private MageController mageController;
    @BeforeEach
    public void setUp(){
         mageRepository = mock(MageRepository.class);
         mageController = new MageController(mageRepository);
    }
    @Test
    void find(){
        when(mageRepository.find("Gandalf")).thenReturn(Optional.of(new MageDTO ("Gandalf", 100)));
        assertThat(mageController.find("Gandalf")).isEqualTo("Mage{name='Gandalf', level=100}");

        when(mageRepository.find("Saruman")).thenReturn(Optional.of(new MageDTO ("Saruman", 0)));
        assertThat(mageController.find("Saruman")).isEqualTo("Mage{name='Saruman', level=0}");

        when(mageRepository.find("Radagast")).thenReturn(Optional.of(new MageDTO ("Radagast", 12)));
        assertThat(mageController.find("Radagast")).isEqualTo("Mage{name='Radagast', level=12}");

        when(mageRepository.find("")).thenReturn(Optional.of(new MageDTO ("", -100)));
        assertThat(mageController.find("")).isEqualTo("Mage{name='', level=-100}");
    }

    @Test
    void findReturnsNotFound(){
        when(mageRepository.find("Gandalf")).thenReturn(Optional.empty());
        assertThat(mageController.find("Gandalf")).isEqualTo("not found");

        when(mageRepository.find("")).thenReturn(Optional.empty());
        assertThat(mageController.find("")).isEqualTo("not found");
    }

    @Test
    void delete(){
        doNothing().when(mageRepository).delete("Gandalf");
        assertThat(mageController.delete("Gandalf")).isEqualTo("done");

        doNothing().when(mageRepository).delete("Saruman");
        assertThat(mageController.delete("Saruman")).isEqualTo("done");

        doNothing().when(mageRepository).delete("Radagast");
        assertThat(mageController.delete("Radagast")).isEqualTo("done");

        doNothing().when(mageRepository).delete("");
        assertThat(mageController.delete("")).isEqualTo("done");
    }

    @Test
    void deleteThrowsIllegalArgumentException(){
        doThrow(new IllegalArgumentException()).when(mageRepository).delete("Gandalf");
        assertThat(mageController.delete("Gandalf")).isEqualTo("not found");

        doThrow(new IllegalArgumentException()).when(mageRepository).delete("Saruman");
        assertThat(mageController.delete("Saruman")).isEqualTo("not found");

        doThrow(new IllegalArgumentException()).when(mageRepository).delete("Redegast");
        assertThat(mageController.delete("Redegast")).isEqualTo("not found");

        doThrow(new IllegalArgumentException()).when(mageRepository).delete("");
        assertThat(mageController.delete("")).isEqualTo("not found");
    }

    @Test
    void save(){
        doNothing().when(mageRepository).save(new MageDTO("Gandalf", 100));
        doNothing().when(mageRepository).save(new MageDTO("Saruman", 0));
        doNothing().when(mageRepository).save(new MageDTO("Radagast", 50));
        doNothing().when(mageRepository).save(new MageDTO("", -150));
        assertThat(mageController.save("Gandalf", 100)).isEqualTo("done");
        assertThat(mageController.save("Saruman", 0)).isEqualTo("done");
        assertThat(mageController.save("Radagast", 50)).isEqualTo("done");
        assertThat(mageController.save("", -150)).isEqualTo("done");
    }

    @Test
    void saveThrowsIllegalArgumentException(){
        doThrow(new IllegalArgumentException()).when(mageRepository).save(any(MageDTO.class));
        assertThat(mageController.save("Gandalf", 100)).isEqualTo("bad request");
        assertThat(mageController.save("Saruman", 0)).isEqualTo("bad request");
        assertThat(mageController.save("Radagast", 50)).isEqualTo("bad request");
        assertThat(mageController.save("", -150)).isEqualTo("bad request");
    }
}