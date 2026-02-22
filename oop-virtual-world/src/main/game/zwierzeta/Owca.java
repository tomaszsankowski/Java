package main.game.zwierzeta;
import main.game.*;

public class Owca extends Zwierze{
    public Owca(int x, int y, Swiat swiat){ super(4,4,x,y,swiat); }
    public Owca(int sila, int inicjatywa, int x, int y, int poprzednieX, int poprzednieY, int wiek, Swiat swiat) {
        super(sila, inicjatywa, x, y, poprzednieX, poprzednieY, wiek, swiat);
    };

    @Override
    public String rysowanie(){
        return "owca.png";
    }
    @Override
    public void rozmnozSie(int x, int y, Swiat swiat){
        Owca nowyOrganizm = new Owca(x, y, swiat);
        swiat.dodajOrganizm(nowyOrganizm);
    }

    @Override
    public String getImie() { return "Owca"; };
}
