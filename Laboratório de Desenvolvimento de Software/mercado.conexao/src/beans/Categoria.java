/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

/**
 *
 * @author laboratorio
 */
public class Categoria {
    private String nomecat;
    private int id_cat;

    // Construtor padrão (vazio)
    public Categoria() {}

    // Construtor para inicializar id e nome
    public Categoria(int id_cat, String nomecat) {
        this.id_cat = id_cat;
        this.nomecat = nomecat;
    }

    public String getNomecat() {
        return nomecat;
    }

    public void setNomecat(String nomecat) {
        this.nomecat = nomecat;
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    // Para exibir o nome no JComboBox
    @Override
    public String toString() {
        return nomecat;
    }
}

