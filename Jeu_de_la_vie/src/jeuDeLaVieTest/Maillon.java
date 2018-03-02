package jeuDeLaVieTest;

public class Maillon<T> {

    private T element;
    private Maillon<T> suivant;

    public Maillon(T element) {
        this.element = element;
        this.suivant = null;
    }

    public Maillon(T element, Maillon<T> suivant) {
        this.element = element;
        this.suivant = suivant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Maillon))
            return false;
        Maillon maillon = (Maillon) o;
        return element == maillon.element;
    }

    public Maillon<T> clone() {
        return new Maillon<T>(this.element, this.suivant);
    }

    public String toString() {
        return element.toString() + "=>";
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public Maillon<T> getSuivant() {
        return suivant;
    }

    public void setSuivant(Maillon<T> suivant) {
        this.suivant = suivant;
    }

}
