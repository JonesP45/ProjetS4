package jeuDeLaVieTest;

public class Cellule implements Comparable<Object>{

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
        Cellule cellule = (Cellule) o;
        return getLigne() == cellule.getLigne() && getColonne() == cellule.getColonne();
    }

//    @Override
//    public int compareTo(Object o) {
//        if (!(o instanceof Cellule))
//            throw new IllegalArgumentException("L'objet en paramètre n'est pas une cellule");
//        Cellule cellule = (Cellule)o;
//        if (ligne < cellule.getLigne())
//            return -1;
//        else if (ligne > cellule.getLigne())
//            return 1;
//        else {
//            if (colonne > cellule.getColonne())
//                return 1;
//            else if (colonne < cellule.getColonne())
//                return -1;
//            else
//                return 0;
//        }
//    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Cellule))
            throw new IllegalArgumentException("L'objet en paramètre n'est pas une cellule");
        Cellule cellule = (Cellule)o;
        if (ligne > cellule.getLigne())
            return -1;
        else if (ligne < cellule.getLigne())
            return 1;
        else {
            if (colonne > cellule.getColonne())
                return 1;
            else if (colonne < cellule.getColonne())
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
