package main.game.rosliny;

import main.game.*;
public class Guarana extends Roslina{
    public Guarana(int x, int y, Swiat swiat){ super(0,x,y,swiat); }
    public Guarana(int sila, int inicjatywa, int x, int y, int poprzednieX, int poprzednieY, int wiek, Swiat swiat) {
        super(sila, inicjatywa, x, y, poprzednieX, poprzednieY, wiek, swiat);
    };

    @Override
    public String rysowanie(){
        return "guarana.png";
    }
    @Override
    public void kolizja(Organizm atakujacy){
        atakujacy.setSila(atakujacy.getSila() + 3);//zwieksza sile organizmu, ktory ja je o 3
        swiat.zabijOrganizm(this);
        swiat.dodajLog(atakujacy.getImie() + " zjadl " + this.getImie());
        return;
    }
    @Override
    public void rozmnozSie(int x, int y, Swiat swiat){
        Guarana nowyOrganizm = new Guarana(x, y, swiat);
        swiat.dodajOrganizm(nowyOrganizm);
    }

    @Override
    public String getImie() { return "Guarana"; };
}
