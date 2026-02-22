package main.game;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import main.game.rosliny.*;
import main.game.zwierzeta.*;
import main.game.*;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Scanner;


public class Swiat {
    private Organizm[] organizmy;
    private boolean[] czyZyje;
    private int iloscOrganizmow;
    private int m, n; // wymiary swiata
    private List<String> logi;
    private int tarczaAlzuraCzlowieka;

    public String getlog(int i){
        return logi.get(i);
    }
    public int iloscLogow() { return logi.size(); }
    public void wyczyscLogi() { logi.clear(); }
    public void zapiszSwiat() {
        String nazwa = JOptionPane.showInputDialog(null, "Wprowadź nazwe pliku:", "ZAPIS", JOptionPane.PLAIN_MESSAGE);
        if(nazwa!=null && nazwa!=""){
            nazwa += ".txt";
            File plik = new File(nazwa);
            try {
                FileWriter writer = new FileWriter(plik);
                if (plik.createNewFile()) {
                    System.out.println("BLAD");
                    return;
                } else {
                    writer.write(m + " " + n + " " + iloscOrganizmow + " " + tarczaAlzuraCzlowieka + "\n");
                    for (int i = 0; i < iloscOrganizmow; i++) {
                        writer.write(czyZyje[i] + " " + organizmy[i].organizmToString() + "\n");
                    }
                    writer.close();
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void wczytajSwiat() {
                try {
                    String nazwa = JOptionPane.showInputDialog(null, "Wprowadź nazwe pliku:", "WCZYTYWANIE", JOptionPane.PLAIN_MESSAGE);
                    if(nazwa!=null && nazwa!=""){
                        nazwa += ".txt";
                        File plik = new File(nazwa);
                        if (!plik.exists()) {
                            System.out.println("BLAD");
                            return;
                        } else {
                            BufferedReader reader = new BufferedReader(new FileReader(plik));
                            String line;
                            line = reader.readLine();
                            String[] parts = line.split(" ");
                            this.m = Integer.parseInt(parts[0]);
                            this.n = Integer.parseInt(parts[1]);
                            this.iloscOrganizmow = Integer.parseInt(parts[2]);
                            this.tarczaAlzuraCzlowieka = Integer.parseInt(parts[3]);
                            this.organizmy = new Organizm[this.iloscOrganizmow];
                            this.czyZyje = new boolean[this.iloscOrganizmow];
                            for (int i = 0; i < this.iloscOrganizmow; i++) {
                                line = reader.readLine();
                                parts = line.split(" ");
                                boolean czyZyjeBool = Boolean.parseBoolean(parts[0]);
                                this.czyZyje[i] = czyZyjeBool;
                                String orgName = parts[1];
                                int sila = Integer.parseInt(parts[2]);
                                int inicjatywa = Integer.parseInt(parts[3]);
                                int x = Integer.parseInt(parts[4]);
                                int y = Integer.parseInt(parts[5]);
                                int poprzednieX = Integer.parseInt(parts[6]);
                                int poprzednieY = Integer.parseInt(parts[7]);
                                int wiek = Integer.parseInt(parts[8]);
                                if (orgName.equals("Antylopa"))
                                    this.organizmy[i] = new Antylopa(sila, inicjatywa, x, y, poprzednieX, poprzednieY, wiek, this);
                                else if (orgName.equals("BarszczSosnowskiego"))
                                    this.organizmy[i] = new BarszczSosnowskiego(sila, inicjatywa, x, y, poprzednieX, poprzednieY, wiek, this);
                                else if (orgName.equals("Guarana"))
                                    this.organizmy[i] = new Guarana(sila, inicjatywa, x, y, poprzednieX, poprzednieY, wiek, this);
                                else if (orgName.equals("Lis"))
                                    this.organizmy[i] = new Lis(sila, inicjatywa, x, y, poprzednieX, poprzednieY, wiek, this);
                                else if (orgName.equals("Mlecz"))
                                    this.organizmy[i] = new Mlecz(sila, inicjatywa, x, y, poprzednieX, poprzednieY, wiek, this);
                                else if (orgName.equals("Owca"))
                                    this.organizmy[i] = new Owca(sila, inicjatywa, x, y, poprzednieX, poprzednieY, wiek, this);
                                else if (orgName.equals("Trawa"))
                                    this.organizmy[i] = new Trawa(sila, inicjatywa, x, y, poprzednieX, poprzednieY, wiek, this);
                                else if (orgName.equals("WilczeJagody"))
                                    this.organizmy[i] = new WilczeJagody(sila, inicjatywa, x, y, poprzednieX, poprzednieY, wiek, this);
                                else if (orgName.equals("Wilk"))
                                    this.organizmy[i] = new Wilk(sila, inicjatywa, x, y, poprzednieX, poprzednieY, wiek, this);
                                else if (orgName.equals("Zlow"))
                                    this.organizmy[i] = new Zlow(sila, inicjatywa, x, y, poprzednieX, poprzednieY, wiek, this);
                                else if (orgName.equals("Czlowiek")) {
                                    this.organizmy[i] = new Czlowiek(sila, inicjatywa, x, y, poprzednieX, poprzednieY, wiek, this);
                                    Czlowiek czl = (Czlowiek) organizmy[i];
                                    czl.setTarczaAlzura(this.tarczaAlzuraCzlowieka);
                                }
                            }
                            return;
                        }
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
    }

    private void sortujOrganizmy() {
        for (int i = 0; i < this.iloscOrganizmow - 1; i++) {
            for (int j = 0; j < this.iloscOrganizmow - i - 1; j++) {
                if (this.organizmy[j].getInicjatywa() < this.organizmy[j + 1].getInicjatywa()) {
                    Organizm buffer = this.organizmy[j];
                    this.organizmy[j] = this.organizmy[j + 1];
                    this.organizmy[j + 1] = buffer;
                    boolean boolBuffer = this.czyZyje[j];
                    this.czyZyje[j] = this.czyZyje[j + 1];
                    this.czyZyje[j + 1] = boolBuffer;
                }
                else if (this.organizmy[j].getInicjatywa() == this.organizmy[j + 1].getInicjatywa()) {
                    if (this.organizmy[j].getWiek() < this.organizmy[j + 1].getWiek()) {
                        Organizm buffer = this.organizmy[j];
                        this.organizmy[j] = this.organizmy[j + 1];
                        this.organizmy[j + 1] = buffer;

                        boolean boolBuffer = this.czyZyje[j];
                        this.czyZyje[j] = this.czyZyje[j + 1];
                        this.czyZyje[j + 1] = boolBuffer;
                    }
                }
            }
        }
    }


    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    private boolean czyZajete(int x, int y, int iterator) {
        for (int i = 0; i < iterator; i++) {
            if(organizmy[i] == null)
                continue;
            if (organizmy[i].isHere(x,y) && czyZyje[i])
                return false;
        }
        return true;
    }

    public boolean isTaken(int x, int y) {
        for (int i = 0; i < iloscOrganizmow; i++) {
            if(organizmy[i] == null)
                continue;
            if (organizmy[i].isHere(x,y) && czyZyje[i])
                return false;
        }
        return true;
    }

    public Organizm getOkupant(int x, int y) {
        for (int i = 0; i < iloscOrganizmow; i++) {
            if(organizmy[i] == null)
                continue;
            if (czyZyje[i] && organizmy[i].isHere(x,y))
                return organizmy[i];
        }
        return null;
    }

    public void zabijOrganizm(int x, int y) {
        for (int i = 0; i < iloscOrganizmow; i++) {
            if (organizmy[i].isHere(x, y)) {
                czyZyje[i] = false;
            }
        }
    }

    public void zabijOrganizm(Organizm biedak) {
        for (int i = 0; i < iloscOrganizmow; i++) {
            if (organizmy[i].isHere(biedak.getX(), biedak.getY())&&organizmy[i].getImie()==biedak.getImie()) {
                czyZyje[i] = false;
                break;
            }
        }
    }

    public boolean sprawdzCzyZyje(Organizm org) {
        for (int i = 0; i < iloscOrganizmow; i++) {
            if (organizmy[i] == org) {
                return czyZyje[i];
            }
        }
        return false;
    }

    public boolean sprawdzCzyZyje(int x, int y) {
        for (int i = 0; i < iloscOrganizmow; i++) {
            if (czyZyje[i] && organizmy[i].isHere(x,y)) {
                return true;
            }
        }
        return false;
    }

    public void zabijZwierze(int x, int y){
        for (int i = 0; i < iloscOrganizmow; i++) {
            if ( (organizmy[i] instanceof Zwierze) && czyZyje[i]) {
                if (organizmy[i].isHere(x, y)){
                    czyZyje[i] = false;
                    logi.add(organizmy[i].getImie() + " za bardzo zblizyl sie do barszczu sosnowskiego");
                }
            }
        }
    }

    public void dodajLog(String str){
        logi.add(str);
    }

    public Swiat(int m, int n) {
        this.m = m;
        this.n = n;
        int pola = m * n;
        this.iloscOrganizmow = ((pola - pola % 100) / 100) * 10 + 1;
        this.organizmy = new Organizm[iloscOrganizmow];
        this.czyZyje = new boolean[iloscOrganizmow];
        czyZyje[0] = true;
        organizmy[0] = new Czlowiek(m/2, n/2, this);
        this.tarczaAlzuraCzlowieka = -5;
        for (int i = 1; i < iloscOrganizmow ; i++) {
            czyZyje[i] = true;
            int losx = (int)(Math.random() * m);
            int losy = (int)(Math.random() * n);
            while (!czyZajete(losx, losy, i)) {
                losx = (int)(Math.random() * m);
                losy = (int)(Math.random() * n);
            }
            if (i % 10 == 0) organizmy[i] = new Antylopa(losx, losy, this);
            else if (i % 10 == 1) organizmy[i] = new BarszczSosnowskiego(losx, losy, this);
            else if (i % 10 == 2) organizmy[i] = new Guarana(losx, losy, this);
            else if (i % 10 == 3) organizmy[i] = new Lis(losx, losy, this);
            else if (i % 10 == 4) organizmy[i] = new Mlecz(losx, losy, this);
            else if (i % 10 == 5) organizmy[i] = new Owca(losx, losy, this);
            else if (i % 10 == 6) organizmy[i] = new Trawa(losx, losy, this);
            else if (i % 10 == 7) organizmy[i] = new WilczeJagody(losx, losy, this);
            else if (i % 10 == 8) organizmy[i] = new Wilk(losx, losy, this);
            else if (i % 10 == 9) organizmy[i] = new Zlow(losx, losy, this);
        }
        this.logi = new ArrayList<String>();
    }

    public void dodajOrganizm(Organizm organizm) {
        if (isTaken(organizm.getX(), organizm.getY())) {
            Organizm[] nowaTablica = new Organizm[iloscOrganizmow + 1];
            boolean[] noweCzyZyje = new boolean[iloscOrganizmow + 1];
            for (int i = 0; i < iloscOrganizmow; i++) {
                nowaTablica[i] = organizmy[i];
                if (czyZyje[i] == true)
                    noweCzyZyje[i] = true;
                else
                    noweCzyZyje[i] = false;
            }
            nowaTablica[iloscOrganizmow] = organizm;
            noweCzyZyje[iloscOrganizmow] = true;
            iloscOrganizmow++;
            organizmy = nowaTablica;
            czyZyje = noweCzyZyje;
        }
        else
            organizm = null;
    }
    public boolean wykonajTure(KeyEvent e){
        int czlowiekIndeks= -1;
        this.sortujOrganizmy();
        int iterations = iloscOrganizmow;
        for (int i = 0; i < iterations; i++) {
            if(organizmy[i] instanceof Czlowiek){// tura czlowieka
                czlowiekIndeks = i;
                Czlowiek czl = (Czlowiek) organizmy[i];
                this.tarczaAlzuraCzlowieka = czl.getTarczaAlzura();
                if (!czyZyje[i])
                    return true;
                else{
                    organizmy[i].akcja();
                    if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                        czl.ruchCzlowieka(e.getKeyCode(), m, n);
                        for (int k = 0; k < iterations; k++) {
                            if (czyZyje[k] && (k != i) && (organizmy[i].getX() == organizmy[k].getX()) && (organizmy[i].getY() == organizmy[k].getY())) {
                                organizmy[k].kolizja(organizmy[i]);
                            }
                        }
                    }
                }
            }
            else{//tura innego organizmu
                if (czyZyje[i]) {
                    organizmy[i].akcja();
                    for (int k = 0; k < iterations; k++) {
                        if (czyZyje[k] && (k != i) && (organizmy[i].getX() == organizmy[k].getX()) && (organizmy[i].getY() == organizmy[k].getY())) {
                            organizmy[k].kolizja(organizmy[i]);
                        }
                    }
                }
            }
        }
        if (czlowiekIndeks>=0 && !czyZyje[czlowiekIndeks])
            return true;
        return false;
    }

    public String zwrocPlik(int i, int j) {
        for (int k = 0; k < iloscOrganizmow; k++) {
            if ((organizmy[k].isHere(i, j)) && czyZyje[k]) {
                return organizmy[k].rysowanie();
            }
        }
        return "noanimal";
    }

    public void aktywujTarczeAlzura(){
        for (int i = 0; i < iloscOrganizmow; i++) {
            if(organizmy[i] instanceof Czlowiek){
                Czlowiek czl = (Czlowiek) organizmy[i];
                if (czl.getTarczaAlzura() == -5)
                    czl.setTarczaAlzura(4);
                else
                    dodajLog("Nie mozna narazie aktywowac super mocy!");
                return;
            }
        }
    }
}