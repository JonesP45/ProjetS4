package jeuDeLaVieTest;

public class Maillon {

    private Cellule c;
    private Maillon suivant;

    public Maillon(Cellule c) {
        this.c = c;
        this.suivant = null;
    }

    public Maillon(Cellule c, Maillon suivant) {
        this.c = c;
        this.suivant = suivant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Maillon))
            return false;
        Maillon maillon = (Maillon) o;
        return getC() == maillon.getC();
    }

    public Maillon clone() {
        return new Maillon(this.c, this.suivant);
    }

    public String toString() {
        return c.toString() + "=>";
    }

    public Cellule getC() {
        return c;
    }

    public void setC(Cellule c) {
        this.c = c;
    }

    public Maillon getSuivant() {
        return suivant;
    }

    public void setSuivant(Maillon suivant) {
        this.suivant = suivant;
    }

}
