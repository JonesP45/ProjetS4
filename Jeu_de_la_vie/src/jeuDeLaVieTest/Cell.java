package jeuDeLaVieTest;

public class Cell implements Comparable<Object> {

    private int row;

    private int column;

    private int nbNeighbors;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        nbNeighbors = 1000;
    }

    public void addNeighbors(int neighbors) {
        nbNeighbors += neighbors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Cell))
            return false;
        Cell cell = (Cell) o;
        return getRow() == cell.getRow() && getColumn() == cell.getColumn();
    }

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

    public String toString() {
        return "[" + row + ", " + column + ", " + nbNeighbors + "]";
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getNbNeighbors() {
        return nbNeighbors;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setNbNeighbors(int nbNeighbors) {
        this.nbNeighbors = nbNeighbors;
    }

}
