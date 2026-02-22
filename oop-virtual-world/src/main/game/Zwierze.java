package main.game;
import main.game.zwierzeta.Owca;

import java.lang.Math;

public abstract class Zwierze extends Organizm{
    public Zwierze(int sila, int inicjatywa, int x, int y, Swiat swiat) { super(sila, inicjatywa, x, y, swiat); }
    public Zwierze(int sila, int inicjatywa, int x, int y, int poprzednieX, int poprzednieY, int wiek, Swiat swiat) { super(sila, inicjatywa, x, y, poprzednieX, poprzednieY, wiek, swiat); }

    public void rozmnazanie() {

        int where = (int) (Math.random()*4);//proba rozmnozenia
        if ((where == 0) && (x > 0) && swiat.isTaken(x - 1, y)){
            rozmnozSie(x - 1, y, swiat);
            swiat.dodajLog(this.getImie() + " rozmnaza sie!");
            return;
        }
        else if ((where == 1) && (x < swiat.getM() - 1) && swiat.isTaken(x + 1, y)){
            rozmnozSie(x + 1, y, swiat);
            swiat.dodajLog(this.getImie() + " rozmnaza sie!");
            return;
        }
        else if ((where == 2) && (y < swiat.getN() - 1) && swiat.isTaken(x, y + 1)){
            rozmnozSie(x, y + 1, swiat);
            swiat.dodajLog(this.getImie() + " rozmnaza sie!");
            return;
        }
        else if ((where == 3) && (y > 0) && swiat.isTaken(x, y - 1)){
            rozmnozSie(x, y - 1, swiat);
            swiat.dodajLog(this.getImie() + " rozmnaza sie!");
            return;
        }
        swiat.dodajLog(this.getImie() + " probowal sie rozmnozyc, ale mu nie wyszlo!");
        return;
    }
    @Override
    public void kolizja(Organizm atakujacy){
        if (this.czyOdbilAtak(atakujacy)) {//odbijanie ataku
            swiat.dodajLog(this.getImie() + " odbil atak " + atakujacy.getImie());
            atakujacy.cofnijRuch();
            return;
        }
        else if (this.getImie() == atakujacy.getImie()) {//rozmnazanie
            if ((atakujacy instanceof Zwierze) && swiat.sprawdzCzyZyje(atakujacy) && (this instanceof Zwierze) && swiat.sprawdzCzyZyje(this)) {
                atakujacy.cofnijRuch();
                this.rozmnazanie();
            }
            return;
        }
        else if (atakujacy.getSila() >= sila) {//atakujacy wygrywa
            swiat.dodajLog(atakujacy.getImie() + " zabil " + this.getImie());
            swiat.zabijOrganizm(this);
            return;
        }
        else {//atakujacy przegrywa
            swiat.dodajLog(atakujacy.getImie() + " zginal probujac atakowac " + this.getImie());
            swiat.zabijOrganizm(atakujacy);
            return;
        }
    }

    @Override
    public void akcja(){
        this.ustawPoprzednie();
        this.wiek++;
        int randomise =(int) (Math.random()*4);
        if ((randomise == 0) && (x > 0))
            x--;
        else if ((randomise == 1) && (x < swiat.getM()-1))
            x++;
        else if ((randomise == 2) && (y < swiat.getN()-1))
            y++;
        else if ((randomise == 3) && (y<0))
            y--;
    }
}