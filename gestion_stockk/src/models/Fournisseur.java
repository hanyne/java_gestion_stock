package models;

public class Fournisseur {
    private int fournisseurId;
    private int cin;
    private String fName;
    private String lName;
    private int tel;
    private String email;
    private String adr;

    public Fournisseur(int fournisseurId, int cin, String fName, String lName, int tel, String email, String adr) {
        this.fournisseurId = fournisseurId;
        this.cin = cin;
        this.fName = fName;
        this.lName = lName;
        this.tel = tel;
        this.email = email;
        this.adr = adr;
    }
    public Fournisseur() {
        
    }
    public int getFournisseurId() {
        return fournisseurId;
    }

    public void setFournisseurId(int fournisseurId) {
        this.fournisseurId = fournisseurId;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    // Exemple de méthode pour obtenir les informations du fournisseur sous forme de chaîne
    @Override
    public String toString() {
        return "Fournisseur{" +
                "fournisseurId=" + fournisseurId +
                ", cin=" + cin +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", tel=" + tel +
                ", email='" + email + '\'' +
                ", adr='" + adr + '\'' +
                '}';
    }
}
