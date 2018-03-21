package jeuDeLaVieTest;

public class LinkedList<T extends Comparable> {

	private Link<T> head;

	public LinkedList() {
		head = null;
	}

	public LinkedList(Link<T> head) {
		this.head = head;
	}

	public boolean contains(T element) {
		if (head == null)
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

	public int size() {
		if (head == null)
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

	public LinkedList<T> clone(){
		if (this.head == null)
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
		if (this.head == null)
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

	public boolean put(T element) {
		if (!this.contains(element)) {
			if (head == null)
				head = new Link<T>(element);
			else {
				Link<T> tmp = head;
				Link<T> tmp2 = tmp;
				while (tmp != null) {
					if (tmp.getElement().compareTo(element) < 0) {
						tmp2 = tmp;
						tmp = tmp.getNext();
						if (tmp == null) {
							tmp2.setNext(new Link(element, null));
						}
					} else if (tmp.getElement().compareTo(element) > 0) {
						if (tmp.equals(head)) {
							head = new Link(element, tmp2);
						} else
							tmp2.setNext(new Link(element, tmp));
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

	public LinkedList<T> insert(T element, LinkedList<T> list) {
		if (list.getHead() == null)
			return new LinkedList<T>(new Link<T>(element));
		else if (element.compareTo(list.head.getElement()) < 0) {
			list.add(element);
			return list;
		} else {
			T elementHead = list.head.getElement();//tmp de la head
			list.setHead(list.head.getNext());//on vire la head de la list
			LinkedList<T> listRec = insert(element, list);//.add(list.head.getElement());
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
		return merge(l1, insert(elementHead, l2));
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

	public LinkedList<T> concatenate(LinkedList<T> l1, LinkedList<T> l2) {
		if (l1.head == null)
			return l2;
		LinkedList<T> res = l1;
		Link<T> tmp = l1.head;
		while (tmp.getNext() != null)
			tmp = tmp.getNext();
		tmp.setNext(l2.head);
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
