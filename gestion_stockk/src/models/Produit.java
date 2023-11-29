package models;

public class Produit {
    private int produitId;
    private String imageP;
    private String nameP;
    private String description;
    private double price;
    private int qte;
    private boolean status;
    private int fournisseurId;
    private String fournisseurName;

    public Produit(int produitId, String imagePath, String nameP, String description, double price, int qte, boolean status, int fournisseurId) {
        this.produitId = produitId;
        this.imageP = imagePath;
        this.nameP = nameP;
        this.description = description;
        this.price = price;
        this.qte = qte;
        this.status = status;
        this.fournisseurId = fournisseurId;
        
    }

    // Getters and setters
    public int getProduitId() {
        return produitId;
    }

    public void setProduitId(int produitId) {
        this.produitId = produitId;
    }

    public String getImageP() {
        return imageP;
    }

    public void setImageP(String imageP) {
        this.imageP = imageP;
    }

    public String getNameP() {
        return nameP;
    }

    public void setNameP(String nameP) {
        this.nameP = nameP;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getFournisseurId() {
        return fournisseurId;
    }

    public void setFournisseurId(int fournisseurId) {
        this.fournisseurId = fournisseurId;
    }
 // Getter et Setter pour le nom du fournisseur
    public String getFournisseurName() {
        return fournisseurName;
    }

    public void setFournisseurName(String fournisseurName) {
        this.fournisseurName = fournisseurName;
    }
}
