package jeuDeLaVieTest;

public class LinkedList<T extends Comparable<Object>> {

	private Link<T> head;

	public LinkedList() {
		head = null;
	}

	public LinkedList(Link<T> head) {
		this.head = head;
	}

	public boolean isEmpty() {
		return (head == null);
	}

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

	public String toString() {
		if (head == null)
			return "La liste chainée est vide.";
		else {
			String str = "";
			Link<T> tmp = head;
			while (tmp != null) {
				str += tmp.toString();
				if (tmp.getNext() == null) {
					str += "/";
					break;
				}
				tmp = tmp.getNext();
			}
			return str;
		}
	}

	public Link<T> getHead() {
		return head;
	}

	public void setHead(Link<T> head) {
		this.head = head;
	}

}
