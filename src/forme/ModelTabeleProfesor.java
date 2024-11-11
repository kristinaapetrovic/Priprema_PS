/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Profesor;

/**
 *
 * @author Korisnik
 */
public class ModelTabeleProfesor extends AbstractTableModel {

    private List<Profesor> lista = new ArrayList<>();
    private String kolone[] = {"ime", "prezime", "zvanje"};

    public List<Profesor> getLista() {
        return lista;
    }

    public void setLista(List<Profesor> lista) {
        this.lista = lista;
    }

    public ModelTabeleProfesor(List<Profesor> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Profesor p = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return p.getIme();
            case 1:
                return p.getPrezime();
            case 2:
                return String.valueOf(p.getZvanje());
            default:
                return "n/a";
        }

    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    void dodajProfesora(Profesor prof) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
