/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import model.Radnik;
import baza.DBBroker;
import model.Predmet;
import model.Profesor;

/**
 *
 * @author Korisnik
 */
public class Controller {

    private static Controller instance;
    private List<Radnik> listaRadnika = new ArrayList<>();
    private Radnik ulogovani;
    private DBBroker dbb = new DBBroker();
    

    private Controller() {

        Radnik r1 = new Radnik(1, "Ime1", "Prezime1", "admin1", "admin1");
        Radnik r2 = new Radnik(2, "Ime2", "Prezime2", "admin2", "admin2");
        Radnik r3 = new Radnik(3, "Ime3", "Prezime3", "admin3", "admin3");

        listaRadnika.add(r1);
        listaRadnika.add(r2);
        listaRadnika.add(r3);

    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public List<Radnik> getListaRadnika() {
        return listaRadnika;
    }

    public void setListaRadnika(List<Radnik> listaRadnika) {
        this.listaRadnika = listaRadnika;
    }

    public boolean ulogujSe(String email, String lozinka) {

        for (Radnik r : listaRadnika) {
            if (r.getEmail().equals(email) && r.getLozinka().equals(lozinka)) {
                ulogovani = r;
                return true;
            }
        }

        return false;

    }

    public Radnik getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Radnik ulogovani) {
        this.ulogovani = ulogovani;
    }

    public boolean ubaciProfesoraUBazu(Profesor p) {
        return dbb.ubaciProfesoraUBazu(p);
    }

    public List<Profesor> vratiProfesore() {
        return dbb.vratiProfesore();
    }

    public List<Predmet> vratiPredmetePoProfesoru(Profesor prof) {
        return dbb.vratiPredmetePoProfesoru(prof);
    }

    public List<Predmet> vratiPredmete() {
        return dbb.vratiPredmete();
    }

    public boolean dodajAngazovanje(Profesor prof, Predmet pred) {
        return dbb.dodajAngazovanje(prof,pred);
    }
    public int brojAng(Profesor p){
        return dbb.brojAng(p);
    }

    public boolean obrisiAngazovanje(Profesor odabrani, Predmet p) {
        return dbb.obrisiAngazovanje(odabrani, p);
    }
    public boolean postojiAngazovanje(Profesor prof, Predmet pred){
        return dbb.postojiAngazovanje(prof, pred);
    }

}
