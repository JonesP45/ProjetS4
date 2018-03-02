package jeuDeLaVieTest;

public class Cellule implements Comparable {

    private int ligne;
    private int colonne;

    public Cellule(int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Cellule))
            return false;
        Cellule Cellule = (Cellule) o;
        return getLigne() == Cellule.getLigne() && getColonne() == Cellule.getColonne();
    }

//    @Override
//    public int compareTo(Object o) {
//        if (!(o instanceof Cellule))
//            throw new IllegalArgumentException("L'objet en paramètre n'est pas une Cellule");
//        Cellule Cellule = (Cellule)o;
//        if (ligne < Cellule.getLigne())
//            return -1;
//        else if (ligne > Cellule.getLigne())
//            return 1;
//        else {
//            if (colonne > Cellule.getColonne())
//                return 1;
//            else if (colonne < Cellule.getColonne())
//                return -1;
//            else
//                return 0;
//        }
//    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Cellule))
            throw new IllegalArgumentException("L'objet en paramètre n'est pas une Cellule");
        Cellule Cellule = (Cellule)o;
        if (ligne > Cellule.getLigne())
            return -1;
        else if (ligne < Cellule.getLigne())
            return 1;
        else {
            if (colonne > Cellule.getColonne())
                return 1;
            else if (colonne < Cellule.getColonne())
                return -1;
        }
        return 0;
    }

    public String toString() {
        return "[" + ligne + ", " + colonne + "]";
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

}
