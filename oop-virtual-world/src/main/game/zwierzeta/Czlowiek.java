package main.game.zwierzeta;
import main.game.*;

import java.awt.event.KeyEvent;

public class Czlowiek extends Zwierze{
    private int tarczaAlzura;
    public Czlowiek(int x, int y, Swiat swiat){
        super(5,4,x,y,swiat);
        tarczaAlzura = -5;
    }
    public Czlowiek(int sila, int inicjatywa, int x, int y, int poprzednieX, int poprzednieY, int wiek, Swiat swiat) {
        super(sila, inicjatywa, x, y, poprzednieX, poprzednieY, wiek, swiat);
        tarczaAlzura = -5;
    };

    @Override
    public String rysowanie(){
        return "czlowiek.png";
    }

    @Override
    public void rozmnozSie(int x, int y, Swiat swiat){
        Czlowiek nowyOrganizm = new Czlowiek(x, y, swiat);
        swiat.dodajOrganizm(nowyOrganizm);
    }
    public int getTarczaAlzura() { return this.tarczaAlzura; }
    public void setTarczaAlzura(int t) { this.tarczaAlzura = t; }
    public boolean ruchCzlowieka(int arrowKey, int m, int n){
        this.ustawPoprzednie();
        if (arrowKey == KeyEvent.VK_UP) {
            if (x > 0)
                setX(x - 1);
            else
                return false;
        }
        else if (arrowKey == KeyEvent.VK_DOWN) {
            if (x < m - 1)
                setX(x + 1);
            else
                return false;
        }
        else if (arrowKey == KeyEvent.VK_LEFT) {
            if (y > 0)
                setY(y - 1);
            else
                return false;
        }
        else if (arrowKey == KeyEvent.VK_RIGHT) {
            if (y < n - 1)
                setY(y + 1);
            else
                return false;
        }
        else
            return false;
        return true;
    }

    public boolean czyOdbilAtak(Organizm atakujacy){
        if (tarczaAlzura >= 0)
            return true;
        else
            return false;
    }

    @Override
    public String getImie() { return "Czlowiek"; };
    @Override
    public void akcja(){
        this.wiek++;
        if (tarczaAlzura > -5)
            tarczaAlzura--;
        if (tarczaAlzura >= 0)
            swiat.dodajLog("Zostalo " + Integer.toString(tarczaAlzura + 1) + " rund z tarcza Alzura!");
	    else if(tarczaAlzura==-5)
            swiat.dodajLog("Mozesz aktywowac tarcze Alzura !");
        else
            swiat.dodajLog("Mozesz aktywowac tarcze Alzura za " + Integer.toString(5+tarczaAlzura) + " rund!");
    }
}
