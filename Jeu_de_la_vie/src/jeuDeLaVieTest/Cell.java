package jeuDeLaVieTest;

public class Cell implements Comparable<Object> {

    private int row;

    private int column;

    private int nbNeighbors;

    /**
     * Construit une cellule avec les paramètres donnés.
     * @param row Coordonnée de la ligne
     * @param column Coordonnée de la colonne.
     */
    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        nbNeighbors = 1000;
    }

    /**
     * Ajoute l'entier donné au nombre de voisins.
     * @param neighbors Nombre a ajouter.
     */
    public void addNeighbors(int neighbors) {
        nbNeighbors += neighbors;
    }

    /**
     * Test l'égalité d'objet.
     * @param o Objet a comparer à this.
     * @return true si les deux objets ont les mêmes valeurs pour tout les champs.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Cell))
            return false;
        Cell cell = (Cell) o;
        return getRow() == cell.getRow() && getColumn() == cell.getColumn();
    }

    /**
     * Compare deux cellules. Si l'objet en argument n'est pas une cellule, une IllegalArgumentException est générée.
     * @param o Objet a comparer
     * @return -1 si o est avant this, 1 sinon. 0 si les deux cellules ont même coordonnées.
     */
    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Cell))
            throw new IllegalArgumentException("L'objet en paramètre n'est pas une Cell");
        Cell cell = (Cell)o;
        if (row > cell.getRow())
            return -1;
        else if (row < cell.getRow())
            return 1;
        else {
            if (column > cell.getColumn())
                return 1;
            else if (column < cell.getColumn())
                return -1;
        }
        return 0;
    }

    /**
     * Renvoie une version affichable des valeurs des champs.
     * @return [row, column, nbNeighbors]
     */
    public String toString() {
        return "[" + row + ", " + column + ", " + nbNeighbors + "]";
    }

    /**
     * Renvoie le numéro de la ligne.
     * @return
     */
    public int getRow() {
        return row;
    }

    /**
     * Renvoie le numéro de la colonne.
     * @return
     */
    public int getColumn() {
        return column;
    }

    /**
     * Renvoie le nombre de voisins. (éventuellent décalé de 1000).
     * @return
     */
    public int getNbNeighbors() {
        return nbNeighbors;
    }

    /**
     * Remplace le numéro de la colonne par column.
     * @param column
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * Remblace le nombre de voisins par nbNeighbors.
     * @param nbNeighbors
     */
    public void setNbNeighbors(int nbNeighbors) {
        this.nbNeighbors = nbNeighbors;
    }

}