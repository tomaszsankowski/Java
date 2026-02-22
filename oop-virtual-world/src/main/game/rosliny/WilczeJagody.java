package main.game.rosliny;

import main.game.*;
public class WilczeJagody extends Roslina{
    public WilczeJagody(int x, int y, Swiat swiat){ super(99,x,y,swiat); }
    public WilczeJagody(int sila, int inicjatywa, int x, int y, int poprzednieX, int poprzednieY, int wiek, Swiat swiat) {
        super(sila, inicjatywa, x, y, poprzednieX, poprzednieY, wiek, swiat);
    };

    @Override
    public String rysowanie(){
        return "jagody.png";
    }
    @Override
    public void kolizja(Organizm atakujacy){
        swiat.dodajLog(atakujacy.getImie() + " zginal probujac zjesc " + this.getImie());
        swiat.zabijOrganizm(atakujacy);
        return;
    }
    @Override
    public void rozmnozSie(int x, int y, Swiat swiat){
        WilczeJagody nowyOrganizm = new WilczeJagody(x, y, swiat);
        swiat.dodajOrganizm(nowyOrganizm);
    }

    @Override
    public String getImie() { return "WilczeJagody"; };
}
