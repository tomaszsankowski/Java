package main.game.rosliny;
import main.game.*;
public class BarszczSosnowskiego extends Roslina{
    public BarszczSosnowskiego(int x, int y, Swiat swiat){ super(10,x,y,swiat); }
    public BarszczSosnowskiego(int sila, int inicjatywa, int x, int y, int poprzednieX, int poprzednieY, int wiek, Swiat swiat) {
        super(sila, inicjatywa, x, y, poprzednieX, poprzednieY, wiek, swiat);
    };

    @Override
    public String rysowanie(){
        return "barszcz.png";
    }
    @Override
    public void kolizja(Organizm atakujacy){
        swiat.dodajLog(atakujacy.getImie() + " zginal probujac zjesc " + this.getImie());
        swiat.zabijOrganizm(atakujacy);
        return;
    }
    @Override
    public void rozmnozSie(int x, int y, Swiat swiat){
        BarszczSosnowskiego nowyOrganizm = new BarszczSosnowskiego(x, y, swiat);
        swiat.dodajOrganizm(nowyOrganizm);
    }

    @Override
    public String getImie() { return "BarszczSosnowskiego"; };
    @Override
    public void akcja(){
        this.ustawPoprzednie();
        this.wiek++;
        swiat.zabijZwierze(x, y - 1);//zabija wszystkie zwierzeta dookola siebie
        swiat.zabijZwierze(x, y + 1);
        swiat.zabijZwierze(x + 1, y);
        swiat.zabijZwierze(x - 1, y);
        int randomise = (int) (Math.random() * 10);
        if ((randomise == 0)) {//10% szans na probe rozmnozenia
            int where = (int) (Math.random() * 4);
            if ((where == 0) && (x > 0))
                rozmnozSie(x - 1, y, swiat);
            else if ((where == 1) && (x < swiat.getM() - 1))
                rozmnozSie(x + 1, y, swiat);
            else if ((where == 2) && (y < swiat.getN() - 1))
                rozmnozSie(x, y + 1, swiat);
            else if ((where == 3) && (y > 0))
                rozmnozSie(x, y - 1, swiat);
        }
    }
}
