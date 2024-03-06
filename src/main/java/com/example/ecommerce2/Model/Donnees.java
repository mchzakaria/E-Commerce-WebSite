package com.example.ecommerce2.Model;

public class Donnees {
    int id ; String nomProduit; String prix;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Donnees(int id, String nomProduit, String prix) {
        this.id = id ;
        this.nomProduit = nomProduit;
        this.prix = prix;
    }
    public String getNomProduit() {
        return nomProduit;
    }
    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }
    public String getPrix() {
        return prix;
    }
    public void setPrix(String prix) {
        this.prix = prix;
    }
}
