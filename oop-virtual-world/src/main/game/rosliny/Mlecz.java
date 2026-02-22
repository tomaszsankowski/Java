package main.game.rosliny;

import main.game.*;
public class Mlecz  extends Roslina{
    public Mlecz(int x, int y, Swiat swiat){ super(0,x,y,swiat); }
    public Mlecz(int sila, int inicjatywa, int x, int y, int poprzednieX, int poprzednieY, int wiek, Swiat swiat) {
        super(sila, inicjatywa, x, y, poprzednieX, poprzednieY, wiek, swiat);
    };

    @Override
    public String rysowanie(){
        return "mlecz.png";
    }
    @Override
    public void rozmnozSie(int x, int y, Swiat swiat){
        Mlecz nowyOrganizm = new Mlecz(x, y, swiat);
        swiat.dodajOrganizm(nowyOrganizm);
    }

    @Override
    public String getImie() { return "Mlecz"; };
    @Override
    public void akcja(){
        this.ustawPoprzednie();
        this.wiek++;
        int randomise;
        for (int i = 0; i < 3; i++) {
            randomise = (int) (Math.random()*10);
            if ((randomise == 0)) {//10% szans na probe rozmnozenia
                int where = (int) (Math.random()*4);
                if ((where == 0) && (x > 0)){
                    rozmnozSie(x - 1, y, swiat);
                    return;
                }
                else if ((where == 1) && (x < swiat.getM() - 1)){
                    rozmnozSie(x + 1, y, swiat);
                    return;
                }
                else if ((where == 2) && (y < swiat.getN() - 1)){
                    rozmnozSie(x, y + 1, swiat);
                    return;
                }
                else if ((where == 3) && (y > 0)){
                    rozmnozSie(x, y - 1, swiat);
                    return;
                }
            }
        }
    }
}
