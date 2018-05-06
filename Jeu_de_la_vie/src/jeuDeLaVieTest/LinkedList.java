package jeuDeLaVieTest;

public class LinkedList<T extends Comparable<Object>> {

	private Link<T> head;

	/**
	 * Construit une liste vide.
	 */
	public LinkedList() {
		head = null;
	}

	/**
	 * Contstruit une liste avec une tête.
	 * @param head
	 */
	public LinkedList(Link<T> head) {
		this.head = head;
	}

	/**
	 * Indique si une liste est vide.
	 * @return true si la liste est vide.
	 */
	public boolean isEmpty() {
		return (head == null);
	}

	/**
	 * Test l'égalité d'objet.
	 * @param o Objet a comparer à this.
	 * @return true si les deux objets ont les mêmes valeurs pour chaque élémént de la liste.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof LinkedList))
			return false;
		LinkedList<?> that = (LinkedList<?>) o;
		Link<?> tmp1 = this.getHead();
		Link<?> tmp2 = that.getHead();
		while ((tmp1 != null) && (tmp2 != null)) {
			if (!tmp1.equals(tmp2))
				return false;
			tmp1 = tmp1.getNext();
			tmp2 = tmp2.getNext();
			if (((tmp1 == null) && (tmp2 != null)) || ((tmp2 == null) && (tmp1 != null)))
				return false;
		}
		return true;
	}

	/**
	 * Indique si un élément est contenu dans la liste. (la liste doit être triée avec des élément comparable dans l'odre croissant).
	 * @param element Element a rechercher.
	 * @return true si l'élément est dans la liste.
	 */
	public boolean contains(T element) {
		if (this.isEmpty())
			return false;
		else {
			Link<T> tmp = head;
			while (tmp != null && tmp.getElement().compareTo(element) < 0) {
				tmp = tmp.getNext();
			}
			return (tmp != null && tmp.getElement().equals(element));
		}
	}

	/**
	 * Indique si un élément est contenu dans la liste. (la liste doit être triée avec des élément comparable dans l'odre décroissant).
	 * @param element Element a rechercher.
	 * @return true si l'élément est dans la liste.
	 */
	public boolean containsReverse(T element) {
		if (this.isEmpty())
			return false;
		else {
			Link<T> tmp = head;
			while (tmp != null && tmp.getElement().compareTo(element) > 0) {
				tmp = tmp.getNext();
			}
			return (tmp != null && tmp.getElement().equals(element));
		}
	}

	/**
	 * Renvoie le premier maillon contenant element.
	 * @param element
	 * @return le maillon correspondant à l'élément element.
	 */
	public Link<T> get(T element) {
		if (this.isEmpty())
			return null;
		else {
			Link<T> tmp = head;
			while (tmp != null && tmp.getElement().compareTo(element) < 0) {
				tmp = tmp.getNext();
			}
			if (tmp != null && tmp.getElement().equals(element))
				return tmp;
			return null;
		}
	}

	/**
	 * Indique la taille de la liste chainée..
	 * @return
	 */
	public int size() {
		if (this.isEmpty())
			return 0;
		else {
			Link<T> tmp = head;
			int size = 1;
			while (tmp.getNext() != null) {
				size++;
				tmp = tmp.getNext();
			}
			return size;
		}
	}

	/**
	 * Renvoie une copie de la liste passée en paramètre. (Ne copie pas les T, uniquement les maillons)
	 * @return
	 */
	public LinkedList<T> clone() {
		if (this.isEmpty())
			return new LinkedList<T>();
		LinkedList<T> clonedList = new LinkedList<T>();
		clonedList.head = new Link<T>(head.getElement());
		Link<T> tmp = head;
		Link<T> clonedLink = clonedList.head;
		while (tmp.getNext()!= null){
			Link<T> clonedLinkNext = new Link<T>(tmp.getNext().getElement());
			clonedLink.setNext(clonedLinkNext);
			clonedLink = clonedLink.getNext();
			tmp = tmp.getNext();
		}
		return clonedList;
	}

	/**
	 * Ajoute un élément en tête. (Un élément ne peut être ajouté s'il est déjà dans la liste(supposée triée ordre croissant).
	 * @param element
	 * @return true si l'élément a été ajouté. false sinon
	 */
	public boolean add(T element) {
		if (!this.contains(element)) {
			if (head == null)
				head = new Link<T>(element);
			else {
				Link<T> tmp = head;
				head = new Link<T>(element, tmp);
			}
			return true;
		}
		return false;
	}

	/**
	 * Ajoute un élément en tête. (Un élément ne peut être ajouté s'il est déjà dans la liste(supposée triée ordre décroissant).
	 * @param element
	 * @return true si l'élément a été ajouté. false sinon
	 */
	public boolean addReverse(T element) {
		if (!this.containsReverse(element)) {
			if (head == null)
				head = new Link<T>(element);
			else {
				Link<T> tmp = head;
				head = new Link<T>(element, tmp);
			}
			return true;
		}
		return false;
	}

	/**
	 * Insère un élément à sa place dans la liste(supposée triée, ordre croissant) s'il n'existe pas déjà.
	 * @param element
	 * @return true si l'élément a été ajouté.
	 */
	public boolean put(T element) {
		if (!this.contains(element)) {
			if (this.isEmpty())
				head = new Link<T>(element);
			else {
				Link<T> tmp = head;
				Link<T> tmp2 = tmp;
				while (tmp != null) {
					if (tmp.getElement().compareTo(element) < 0) {
						tmp2 = tmp;
						tmp = tmp.getNext();
						if (tmp == null) {
							tmp2.setNext(new Link<T>(element, null));
						}
					} else if (tmp.getElement().compareTo(element) > 0) {
						if (tmp.equals(head)) {
							head = new Link<T>(element, tmp2);
						} else
							tmp2.setNext(new Link<T>(element, tmp));
						return true;
					}
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * Insère un élément à sa place dans la liste(supposée triée, ordre décroissant) s'il n'existe pas déjà.
	 * @param element
	 * @return true si l'élément a été ajouté.
	 */
	public boolean putReverse(T element) {
		if (!this.containsReverse(element)) {
			if (this.isEmpty())
				head = new Link<T>(element);
			else {
				Link<T> tmp = head;
				Link<T> tmp2 = tmp;
				while (tmp != null) {
					if (tmp.getElement().compareTo(element) > 0) {
						tmp2 = tmp;
						tmp = tmp.getNext();
						if (tmp == null) {
							tmp2.setNext(new Link<T>(element, null));
						}
					} else if (tmp.getElement().compareTo(element) < 0) {
						if (tmp.equals(head)) {
							head = new Link<T>(element, tmp2);
						} else
							tmp2.setNext(new Link<T>(element, tmp));
						return true;
					}
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * Enlève un élément de la liste(supposée triée, ordre croissant).
	 * @param element
	 * @return true si l'élément a été retiré.
	 */
	public boolean remove(T element) {
		if (this.contains(element)) {
			if (head.getElement().equals(element)) {
				head = head.getNext();
			} else {
				Link<T> tmp = head;
				while (tmp != null) {
					Link<T> tmpBefore = tmp;
					if (tmp.getNext() != null)
						tmp = tmp.getNext();
					else
						break;
					Link<T> tmpAfter = null;
					if (tmp.getNext() != null)
						tmpAfter = tmp.getNext();
					if (tmp.getElement().equals(element)) {
						tmpBefore.setNext(tmpAfter);
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Enlève un élément de la liste(supposée triée, ordre décroissant).
	 * @param element
	 * @return
	 */
	public boolean removeReverse(T element) {
		if (this.containsReverse(element)) {
			if (head.getElement().equals(element)) {
				head = head.getNext();
			} else {
				Link<T> tmp = head;
				while (tmp != null) {
					Link<T> tmpBefore = tmp;
					if (tmp.getNext() != null)
						tmp = tmp.getNext();
					else
						break;
					Link<T> tmpAfter = null;
					if (tmp.getNext() != null)
						tmpAfter = tmp.getNext();
					if (tmp.getElement().equals(element)) {
						tmpBefore.setNext(tmpAfter);
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Trie la liste, ordre croissant.
	 * @return une nouvelle liste, triée par ordre croissant.
	 */
	public LinkedList<T> sort() {
		if (this.size() <= 1)
			return this;
		LinkedList<T> res = new LinkedList<T>();
		Link<T> tmp = head;
		while (tmp != null) {
			res.put(tmp.getElement());
			tmp = tmp.getNext();
		}
		return res;
	}

	/**
	 * Trie la liste, ordre décroissant.
	 * @return une nouvelle liste, triée par ordre décroissant.
	 */
	public LinkedList<T> sortReverse() {
		if (this.size() <= 1)
			return this;
		LinkedList<T> res = new LinkedList<T>();
		Link<T> tmp = head;
		while (tmp != null) {
			res.putReverse(tmp.getElement());
			tmp = tmp.getNext();
		}
		return res;
	}

	/**
	 * Renvoie une version affichable de la liste. Invoque les toString de chacun des maillons.
	 * @return elem1=>elem2=>elem3=>/
	 */
	public String toString() {
		String str = "";
		if (head == null)
			str = "La liste chainée est vide.";
		else {
			Link<T> tmp = head;
			while (tmp != null) {
				str += tmp.toString();
				if (tmp.getNext() == null) {
					str += "/";
					break;
				}
				tmp = tmp.getNext();
			}
		}
		return str;
	}

	/**
	 * Renvoie la tête de la liste.
	 * @return
	 */
	public Link<T> getHead() {
		return head;
	}

	/**
	 * Met head a la tête de la liste. La queue est abandonnée si elle n'est pas gérée manuellement.
	 * @param head
	 */
	public void setHead(Link<T> head) {
		this.head = head;
	}

}