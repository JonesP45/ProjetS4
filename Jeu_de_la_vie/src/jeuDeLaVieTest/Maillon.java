package jeuDeLaVieTest;

public class Maillon {

    private Cellule cellule;
    private Maillon suivant;

    public Maillon(Cellule cellule) {
        this.cellule = cellule;
        this.suivant = null;
    }

    public Maillon(Cellule cellule, Maillon suivant) {
        this.cellule = cellule;
        this.suivant = suivant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Maillon))
            return false;
        Maillon maillon = (Maillon) o;
        return cellule == maillon.cellule;
    }

    public Maillon clone() {
        return new Maillon(this.cellule, this.suivant);
    }

    public String toString() {
        return cellule.toString() + "=>";
    }

    public Cellule getCellule() {
        return cellule;
    }

    public void setCellule(Cellule cellule) {
        this.cellule = cellule;
    }

    public Maillon getSuivant() {
        return suivant;
    }

    public void setSuivant(Maillon suivant) {
        this.suivant = suivant;
    }

}
