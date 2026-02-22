package main.game.zwierzeta;
import main.game.*;

public class Wilk extends Zwierze{
    public Wilk(int x, int y, Swiat swiat){ super(9,5,x,y,swiat); }
    public Wilk(int sila, int inicjatywa, int x, int y, int poprzednieX, int poprzednieY, int wiek, Swiat swiat) {
        super(sila, inicjatywa, x, y, poprzednieX, poprzednieY, wiek, swiat);
    };

    @Override
    public String rysowanie(){
        return "wilk.png";
    }
    @Override
    public void rozmnozSie(int x, int y, Swiat swiat){
        Wilk nowyOrganizm = new Wilk(x, y, swiat);
        swiat.dodajOrganizm(nowyOrganizm);
    }

    @Override
    public String getImie() { return "Wilk"; };
}
