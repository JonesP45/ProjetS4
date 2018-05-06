package jeuDeLaVieTest;

public class Link<T> {

    private T element;
    private Link<T> next;

    /**
     * Construit un maillon contenant element et pointant vers null.
     * @param element
     */
    public Link(T element) {
        this.element = element;
        this.next = null;
    }

    /**
     * Construit un maillon contenant element et pointant vers next.
     * @param element
     * @param next
     */
    public Link(T element, Link<T> next) {
        this.element = element;
        this.next = next;
    }

    /**
     * Vérifie l'égalité d'objet.
     * @param o
     * @return true si o est un maillon contenant le même élément que this.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Link))
            return false;
        Link link = (Link) o;
        return element.equals(link.element);
    }

    /**
     * Clone ce maillon. (element n'est pas cloné).
     * @return
     */
    public Link<T> clone() {
        return new Link<T>(this.element, this.next);
    }

    /**
     * Renvoie une version affichable du maillon.
     * @return elem=>
     */
    public String toString() {
        return element.toString() + "=>";
    }

    /**
     * Renvoie element.
     * @return
     */
    public T getElement() {
        return element;
    }

    /**
     * Renvoie le maillon suivant.
     * @return
     */
    public Link<T> getNext() {
        return next;
    }

    /**
     * Met next comme maillon suivant.
     * @param next
     */
    public void setNext(Link<T> next) {
        this.next = next;
    }

}