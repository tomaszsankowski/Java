package main.game;

public abstract class Organizm {
    protected int sila, inicjatywa, x, y, poprzednieX, poprzednieY, wiek;
    protected Swiat swiat;

    public Organizm(int sila, int inicjatywa, int x, int y, Swiat swiat){
        this.sila = sila;
        this.inicjatywa = inicjatywa;
        this.x = x;
        this.y = y;
        this.poprzednieX = x;
        this.poprzednieY = y;
        this.wiek = 0;
        this.swiat = swiat;
    }
    public Organizm(int sila, int inicjatywa, int x, int y, int poprzednieX, int poprzednieY, int wiek, Swiat swiat){
        this.sila = sila;
        this.inicjatywa = inicjatywa;
        this.x = x;
        this.y = y;
        this.poprzednieX = poprzednieX;
        this.poprzednieY = poprzednieY;
        this.wiek = wiek;
        this.swiat = swiat;
    }

    //get
    public int getX() { return x; }
    public int getY() { return y; }
    public int getInicjatywa() { return this.inicjatywa; }
    public int getSila() { return this.sila; }
    public int getWiek() { return this.wiek; }

    //set
    public void setSila(int s) { this.sila = s; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setInicjatywa(int i) { this.inicjatywa = i; }
    public void ustawPoprzednie() { this.poprzednieX = this.x; this.poprzednieY = this.y;}

    //abstract
    public abstract String getImie();
    public abstract void akcja();
    public abstract void kolizja(Organizm atakujacy);
    public abstract String rysowanie();
    public abstract void rozmnozSie(int x, int y, Swiat swiat);

    public boolean czyOdbilAtak(Organizm atakujacy){
        return false;
    }
    //inne
    public void cofnijRuch() { this.x = this.poprzednieX; this.y = this.poprzednieY; }
    public boolean isHere(int x, int y) { return (this.x == x && this.y == y); }
    public String organizmToString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getImie());
        sb.append(" ");
        sb.append(this.sila).append(" ");
        sb.append(this.inicjatywa).append(" ");
        sb.append(this.x).append(" ");
        sb.append(this.y).append(" ");
        sb.append(this.poprzednieX).append(" ");
        sb.append(this.poprzednieY).append(" ");
        sb.append(this.wiek);
        return sb.toString();
    }
}