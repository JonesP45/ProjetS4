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
		if (head == null)
			return true;
		return false;
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

	public boolean containsAll(T element) {
		if (this.isEmpty())
			return false;
		else {
			Link<T> tmp = head;
			while (tmp != null) {
				if (tmp.getElement().equals(element))
					return true;
				tmp = tmp.getNext();
			}
			return false;
		}
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
		else {
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
	}

	public LinkedList<T> cloneBis(int start, int end){
		if (this.isEmpty())
			return new LinkedList<T>();
		else if (start <= this.size() && end <= this.size() && start <= end && start >= 1 && end >= 1){
			LinkedList<T> clonedList = new LinkedList<T>();
			int acc = 1;
			Link<T> tmp = head;
			while (acc < start) {
				tmp = tmp.getNext();
				acc++;
			}
			while (acc < end){
				clonedList.add(tmp.getElement());
				tmp = tmp.getNext();
				acc++;
			}
			return clonedList;
		}
		return null;
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

	public LinkedList<T> reverse() {
		if (this.isEmpty())
			return this;
		Link<T> tmp = head;
		LinkedList<T> aux = new LinkedList<T>();
		while (tmp != null) {
			aux.add(tmp.getElement());
			tmp = tmp.getNext();
		}
		return aux;
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

	public LinkedList<T> insert(T element) {
		if (this.isEmpty())
			return new LinkedList<T>(new Link<T>(element));
		else if (element.compareTo(head.getElement()) < 0) {
			this.add(element);
			return this;
		} else {
			T elementHead = head.getElement();//tmp de la head
			this.setHead(head.getNext());//on vire la head de la list
			LinkedList<T> listRec = this.insert(element);//.add(list.head.getElement());
			listRec.add(elementHead);
			return listRec;
		}
	}

	public LinkedList<T> merge(LinkedList<T> l1, LinkedList<T> l2) {
		T elementHead;
		if (l1.head == null)
			return l2;
		else if (l2.head == null)
			return l1;
		else
			elementHead = l1.head.getElement();
		l1.setHead(l1.head.getNext());//on vire la head de l1
		return merge(l1, l2.insert(elementHead));
	}

	public LinkedList<T> mergeSort() {
		if (this.size() <= 1)
			return this;
		else {
			int size = this.size() / 2;
			LinkedList<T> l1 = new LinkedList<T>();
			LinkedList<T> l2 = new LinkedList<T>();
			int acc = 0;
			Link<T> tmp = this.head;
			while (acc < size) {
				l1.add(tmp.getElement());
				tmp = tmp.getNext();
				acc++;
			}
			while (tmp != null) {
				l2.add(tmp.getElement());
				tmp = tmp.getNext();
			}
			return merge(l1.mergeSort(), l2.mergeSort());
		}
	}

	public boolean wellInGameBoard(int i, int j, int x, int y) {
		return x < i && y < j;
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
