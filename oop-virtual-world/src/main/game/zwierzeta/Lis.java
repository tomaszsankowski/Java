package main.game.zwierzeta;
import main.game.*;

public class Lis extends Zwierze{
    public Lis(int x, int y, Swiat swiat){ super(3,7,x,y,swiat); }
    public Lis(int sila, int inicjatywa, int x, int y, int poprzednieX, int poprzednieY, int wiek, Swiat swiat) {
        super(sila, inicjatywa, x, y, poprzednieX, poprzednieY, wiek, swiat);
    };

    @Override
    public String rysowanie(){
        return "lis.png";
    }
    @Override
    public void rozmnozSie(int x, int y, Swiat swiat){
        Lis nowyOrganizm = new Lis(x, y, swiat);
        swiat.dodajOrganizm(nowyOrganizm);
    }

    @Override
    public String getImie() { return "Lis"; };
    @Override
    public void akcja(){
        this.ustawPoprzednie();
        this.wiek++;
        while(true) {//sprawdza czy pole na ktore poszedlby lis jest zajete, a jesli tak to czy sila oponenta jest wieksza od jego wlasnej
            int randomise = (int) (Math.random()*4);
            if ((randomise == 0) && (x > 0)) {
                if (!swiat.isTaken(x - 1, y)) {
                    Organizm okupant = swiat.getOkupant(x - 1, y);
                    if (okupant.getSila() <= this.sila) {
                        x--;
                        return;
                    }
                }
                else {
                    x--;
                    return;
                }
            }
            else if ((randomise == 1) && (x < swiat.getM() - 1)) {
                if (!swiat.isTaken(x + 1, y)) {
                    Organizm okupant = swiat.getOkupant(x + 1, y);
                    if (okupant.getSila() <= this.sila) {
                        x++;
                        return;
                    }
                }
                else {
                    x++;
                    return;
                }
            }
            else if ((randomise == 2) && (y < swiat.getN() - 1)) {
                if (!swiat.isTaken(x, y + 1)) {
                    Organizm okupant = swiat.getOkupant(x, y + 1);
                    if (okupant.getSila() <= this.sila) {
                        y++;
                        return;
                    }
                }
                else {
                    y++;
                    return;
                }
            }
            else if ((randomise == 3) && (y > 0)) {
                if (!swiat.isTaken(x, y - 1)) {
                    Organizm okupant = swiat.getOkupant(x, y - 1);
                    if (okupant.getSila() <= this.sila) {
                        y--;
                        return;
                    }
                }
                else {
                    y--;
                    return;
                }
            }
        }
    }
}
