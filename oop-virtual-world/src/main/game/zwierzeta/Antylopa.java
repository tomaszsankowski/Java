package main.game.zwierzeta;
import main.game.*;
import java.util.Random;
public class Antylopa extends Zwierze{
    public Antylopa(int x, int y, Swiat swiat){ super(4,4,x,y,swiat); }
    public Antylopa(int sila, int inicjatywa, int x, int y, int poprzednieX, int poprzednieY, int wiek, Swiat swiat) {
        super(sila, inicjatywa, x, y, poprzednieX, poprzednieY, wiek, swiat);
    };

    @Override
    public String rysowanie(){
        return "antylopa.png";
    }
    @Override
    public void kolizja(Organizm atakujacy){
        Random rand = new Random();
        boolean willFight = rand.nextBoolean();
//dla 0 antylopa walczy, dla 1 ucieka
        if (!willFight) {
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
                swiat.zabijOrganizm(this);
                swiat.dodajLog(atakujacy.getImie() + " zabil " + this.getImie());
                return;
            }
            else {//atakujacy przegrywa
                swiat.dodajLog(atakujacy.getImie() + " zginal probujac atakowac " + this.getImie());
                swiat.zabijOrganizm(atakujacy);
                return;
            }
        }
        else {
            int direction = (int) (Math.random() * 4); //antylopa domyslnie przesuwa sie o jedno pole w lewo
            swiat.dodajLog("Antylopa uciekla przed atakiem!");
            if ((x > 0) && !swiat.isTaken(x - 1, y))
                x--;
            else if ((x < swiat.getM() - 1) && !swiat.isTaken(x + 1, y))
                x++;
            else if ((y < swiat.getN() - 1) && !swiat.isTaken(x, y+1))
                y++;
            else if ((y > 0) && !swiat.isTaken(x, y-1))
                y--;
            return;
        }
    }
    @Override
    public void rozmnozSie(int x, int y, Swiat swiat){
        Antylopa nowyOrganizm = new Antylopa(x, y, swiat);
        swiat.dodajOrganizm(nowyOrganizm);
    }

    @Override
    public String getImie() { return "Antylopa"; };
    @Override
    public void akcja() {
        ustawPoprzednie();
        int randomise = (int) (Math.random() * 8);
        if ((randomise == 0) && (x > 0)) {
            x--;
        } else if ((randomise == 1) && (x < swiat.getM() - 1)) {
            x++;
        } else if ((randomise == 2) && (y < swiat.getN() - 1)) {
            y++;
        } else if ((randomise == 3) && (y > 0)) {
            y--;
        } else if ((randomise == 4) && (x < swiat.getM() - 2)) {
            x += 2;
        } else if ((randomise == 5) && (y < swiat.getN() - 2)) {
            y += 2;
        } else if ((randomise == 6) && (y > 1)) {
            y -= 2;
        } else if ((randomise == 7) && (x > 1)) {
            x -= 2;
        }
        this.wiek++;
    }
}
