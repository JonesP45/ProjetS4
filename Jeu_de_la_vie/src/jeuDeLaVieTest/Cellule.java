package jeuDeLaVieTest;

public class Cellule implements Comparable<Object>{

    private int x;
    private int y;

    public Cellule(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Cellule))
            return false;
        Cellule cellule = (Cellule) o;
        return getX() == cellule.getX() && getY() == cellule.getY();
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Cellule))
            throw new IllegalArgumentException("L'objet en paramètre n'est pas une cellule");
        Cellule cellule = (Cellule)o;
        if (x < cellule.getX())
            return -1;
        else if (x > cellule.getX())
            return 1;
        else {
            if (y > cellule.getY())
                return 1;
            else if (y < cellule.getY())
                return -1;
            else
                return 0;
        }
    }

    public String toString() {
        return "[" + x + ", " + y + "]";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}
