package jeuDeLaVieTest;

public class Link<T> {

    private T element;
    private Link<T> next;

    public Link() {
        this.element = null;
        this.next = null;
    }

    public Link(T element) {
        this.element = element;
        this.next = null;
    }

    public Link(T element, Link<T> next) {
        this.element = element;
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Link))
            return false;
        Link link = (Link) o;
        return element.equals(link.element);
    }

    public Link<T> clone() {
        return new Link<T>(this.element, this.next);
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

    public Link<T> getNext() {
        return next;
    }

    public void setNext(Link<T> next) {
        this.next = next;
    }

}
