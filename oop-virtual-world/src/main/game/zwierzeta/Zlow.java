package main.game.zwierzeta;
import main.game.*;

public class Zlow extends Zwierze{
    public Zlow(int x, int y, Swiat swiat){ super(2,1,x,y,swiat); }
    public Zlow(int sila, int inicjatywa, int x, int y, int poprzednieX, int poprzednieY, int wiek, Swiat swiat) {
        super(sila, inicjatywa, x, y, poprzednieX, poprzednieY, wiek, swiat);
    };

    @Override
    public String rysowanie(){
        return "zlow.png";
    }

    @Override
    public void rozmnozSie(int x, int y, Swiat swiat){
        Zlow nowyOrganizm = new Zlow(x, y, swiat);
        swiat.dodajOrganizm(nowyOrganizm);
    }

    public boolean czyOdbilAtak(Organizm atakujacy){
        if (atakujacy.getSila() < 5) {
            return true;
        }
        else
            return false;
    }
    @Override
    public String getImie() { return "Zlow"; };
    @Override
    public void akcja(){
        this.ustawPoprzednie();
        this.wiek++;
        int moveIf0 = (int) (Math.random()*4);
        int randomise = (int) (Math.random()*4);
        if (moveIf0 % 4 == 0) {
            if ((randomise == 0) && (x > 0))
                x--;
            else if ((randomise == 1) && (x < swiat.getM() - 1))
                x++;
            else if ((randomise == 2) && (y < swiat.getN() - 1))
                y++;
            else if ((randomise == 3) && (y < 0))
                y--;
        }
    }
}
