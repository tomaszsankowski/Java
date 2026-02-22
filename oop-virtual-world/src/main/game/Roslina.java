package main.game;

public abstract class Roslina extends Organizm {

    public Roslina(int sila, int x, int y, Swiat swiat) { super(sila, 0, x, y, swiat); }
    public Roslina(int sila, int inicjatywa, int x, int y, int poprzednieX, int poprzednieY, int wiek, Swiat swiat) { super(sila, 0, x, y, poprzednieX, poprzednieY, wiek, swiat); }

    @Override
    public void akcja(){
        this.ustawPoprzednie();
        this.wiek++;
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

    @Override
    public void kolizja(Organizm atakujacy){
        swiat.zabijOrganizm(this);
        swiat.dodajLog(atakujacy.getImie() + " zjadl " + this.getImie());
        return;
    }
}
