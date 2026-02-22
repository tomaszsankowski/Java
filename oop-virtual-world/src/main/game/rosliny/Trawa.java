package main.game.rosliny;

import main.game.*;
public class Trawa extends Roslina{
    public Trawa(int x, int y, Swiat swiat){ super(0,x,y,swiat); }
    public Trawa(int sila, int inicjatywa, int x, int y, int poprzednieX, int poprzednieY, int wiek, Swiat swiat) {
        super(sila, inicjatywa, x, y, poprzednieX, poprzednieY, wiek, swiat);
    };

    @Override
    public String rysowanie(){
        return "trawa.png";
    }

    @Override
    public void rozmnozSie(int x, int y, Swiat swiat){
        Trawa nowyOrganizm = new Trawa(x, y, swiat);
        swiat.dodajOrganizm(nowyOrganizm);
    }

    @Override
    public String getImie() { return "Trawa"; };
}
