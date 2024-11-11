/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

import model.Profesor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Predmet;
import model.Zvanje;

/**
 *
 * @author Korisnik
 */
public class DBBroker {

    public boolean ubaciProfesoraUBazu(Profesor p) {
        
        try {
            String upit="INSERT INTO profesor SET ime=?, prezime=?, zvanje=?, email=?";
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(upit);
            
            ps.setString(1, p.getIme());
            ps.setString(2, p.getPrezime());
            ps.setString(3, p.getZvanje().toString());
            ps.setString(4, p.getEmail());
            
            int red=ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();
            return red>0;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    
    }

    public List<Profesor> vratiProfesore() {
        
        try {
            String upit="SELECT * FROM profesor";
            Statement st=Konekcija.getInstance().getConnection().createStatement();
            List<Profesor> lista=new ArrayList<>();
            ResultSet rs=st.executeQuery(upit);
            Profesor p;
            while(rs.next()){
                int id=rs.getInt("id");
                String ime=rs.getString("ime");
                String prezime=rs.getString("prezime");
                String zvanje=rs.getString("zvanje");
                p=new Profesor(id, ime, prezime, Zvanje.valueOf(zvanje), null);
                lista.add(p);
                
                
            }
            st.close();
            rs.close();
            
            
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Predmet> vratiPredmetePoProfesoru(Profesor prof) {
        
        try {
            List<Predmet> lista=new ArrayList<>();
            
            String upit="SELECT pred.id, pred.naziv, pred.kod, pred.esp FROM predmet pred JOIN angazovanje a ON a.predmet=pred.id JOIN profesor prof ON a.profesor=prof.id WHERE prof.id=?";
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(upit);
            
            ps.setInt(1, prof.getId());
            
            ResultSet rs=ps.executeQuery();
            Predmet p;
            while(rs.next()){
                
                int id=rs.getInt("pred.id");
                String naziv=rs.getString("pred.naziv");
                String kod=rs.getString("pred.kod");
                int esp=rs.getInt("pred.esp");
                
                
                p=new Predmet(id, naziv, kod, esp);
                lista.add(p);
                
            }
            rs.close();
            ps.close();
            
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Predmet> vratiPredmete() {
        
        try {
            List<Predmet> lista=new ArrayList<>();
            String upit="SELECT * FROM predmet";
            Statement st=Konekcija.getInstance().getConnection().createStatement();
            
            ResultSet rs=st.executeQuery(upit);
            Predmet p;
            while(rs.next()){
                int id=rs.getInt("id");
                String naziv=rs.getString("naziv");
                String kod=rs.getString("kod");
                int esp=rs.getInt("esp");
                p=new Predmet(id, naziv, kod, esp);
                lista.add(p);
                
            }
            
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean dodajAngazovanje(Profesor prof, Predmet pred) {
        
        try {
            
            
            
            String upit="INSERT INTO angazovanje (profesor, predmet) VALUES (?,?)";
            
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(upit);
            
            ps.setInt(1, prof.getId());
            ps.setInt(2, pred.getId());
            
            int red=ps.executeUpdate();
            ps.close();
            Konekcija.getInstance().getConnection().commit();
            return red>0;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public int brojAng(Profesor p) {
        
        try {
            String upit="SELECT COUNT(*) FROM angazovanje WHERE profesor="+p.getId()+" GROUP BY profesor ";
            Statement st=Konekcija.getInstance().getConnection().createStatement();
            
            ResultSet rs=st.executeQuery(upit);
            rs.next();
            int broj=rs.getInt(1);
            return broj;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    
    }
    
   

    public boolean obrisiAngazovanje(Profesor odabrani, Predmet p) {
        
    
        try {
            String upit="DELETE FROM angazovanje WHERE profesor="+odabrani.getId()+" AND predmet="+p.getId();
            Statement st=Konekcija.getInstance().getConnection().createStatement();
            
            int redovi=st.executeUpdate(upit);
            
             Konekcija.getInstance().getConnection().commit();
            return redovi>0;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    public boolean postojiAngazovanje(Profesor prof, Predmet pred){
        
        
        try {
            String upit="SELECT * FROM angazovanje WHERE profesor="+prof.getId()+" AND predmet="+pred.getId();
            Statement st=Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs=st.executeQuery(upit);
            
            
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
