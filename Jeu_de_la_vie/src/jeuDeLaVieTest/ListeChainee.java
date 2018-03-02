package jeuDeLaVieTest;

public class ListeChainee<T extends Comparable> {

	private Maillon<T> tete;

	public ListeChainee() {
		tete = null;
	}

	public ListeChainee(Maillon<T> tete) {
		this.tete = tete;
	}

	public boolean contains(T element) {
		if (tete == null)
			return false;
		else {
			Maillon<T> tmp = tete;
			while (tmp != null) {
				if (tmp.getElement().equals(element))
					return true;
				tmp = tmp.getSuivant();
			}
			return false;
		}
	}

	public int size() {
		if (tete == null)
			return 0;
		else {
			Maillon<T> tmp = tete;
			int size = 1;
			while (tmp.getSuivant() != null) {
				size++;
				tmp = tmp.getSuivant();
			}
			return size;
		}
	}

	public ListeChainee<T> clone(){
		if (this.tete == null)
			return new ListeChainee<T>();
		else {
			ListeChainee<T> lcClone = new ListeChainee<T>();
			lcClone.tete = new Maillon<T>(tete.getElement());
			Maillon<T> tmp = tete;
			Maillon<T> mClone = lcClone.tete;
			while (tmp.getSuivant()!= null){
				Maillon<T> mCloneNext = new Maillon<T>(tmp.getSuivant().getElement());
				mClone.setSuivant(mCloneNext);
				mClone = mClone.getSuivant();
				tmp = tmp.getSuivant();
			}
			return lcClone;
		}
	}

	public ListeChainee<T> cloneBis(int debut, int fin){
		if (this.tete == null)
			return new ListeChainee<T>();
		else if (debut <= this.size() && fin <= this.size() && debut <= fin && debut >= 1 && fin >= 1){
			ListeChainee<T> lcClone = new ListeChainee<T>();
			int acc = 1;
			Maillon<T> tmp = tete;
			while (acc < debut) {
				tmp = tmp.getSuivant();
				acc++;
			}
			while (acc < fin){
				lcClone.add(tmp.getElement());
				tmp = tmp.getSuivant();
				acc++;
			}
			return lcClone;
		}
		return null;
	}

	public boolean add(T element) {
		if (!this.contains(element)) {
			if (tete == null)
				tete = new Maillon<T>(element);
			else {
				Maillon<T> tmp = tete;
				tete = new Maillon<T>(element, tmp);
			}
			return true;
		}
		return false;
	}

	public boolean put(T element) {
		if (!this.contains(element)) {
			if (tete == null)
				tete = new Maillon<T>(element);
			else {
				Maillon<T> tmp = tete;
				Maillon<T> tmp2 = tmp;
				while (tmp != null) {
					if (tmp.getElement().compareTo(element) < 0) {
						tmp2 = tmp;
						tmp = tmp.getSuivant();
						if (tmp == null) {
							tmp2.setSuivant(new Maillon(element, null));
						}
					} else if (tmp.getElement().compareTo(element) > 0) {
						if (tmp.equals(tete)) {
							tete = new Maillon(element, tmp2);
						} else
							tmp2.setSuivant(new Maillon(element, tmp));
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
			if (tete.getElement().equals(element)) {
				tete = tete.getSuivant();
			} else {
				Maillon<T> tmp = tete;
				while (tmp != null) {
					Maillon<T> tmpAv = tmp;
					if (tmp.getSuivant() != null)
						tmp = tmp.getSuivant();
					else
						break;
					Maillon<T> tmpAp = null;
					if (tmp.getSuivant() != null)
						tmpAp = tmp.getSuivant();
					if (tmp.getElement().equals(element)) {
						tmpAv.setSuivant(tmpAp);
						return true;
					}
				}
			}
		}
		return false;
	}

	public ListeChainee<T> sort() {
		if (this.size() <= 1)
			return this;
		ListeChainee<T> res = new ListeChainee<T>();
		Maillon<T> tmp = tete;
		while (tmp != null) {
			res.put(tmp.getElement());
			tmp = tmp.getSuivant();
		}
		return res;
	}

	public ListeChainee<T> insert(T element, ListeChainee<T> liste) {
		if (liste.getTete() == null)
			return new ListeChainee<T>(new Maillon<T>(element));
		else if (element.compareTo(liste.tete.getElement()) < 0) {
			liste.add(element);
			return liste;
		} else {
			T elementTete = liste.tete.getElement();//tmp de la tete
			liste.setTete(liste.tete.getSuivant());//on vire la tete de la liste
			ListeChainee<T> listRec = insert(element, liste);//.add(liste.tete.getElement());
			listRec.add(elementTete);
			return listRec;
		}
	}

	public ListeChainee<T> merge(ListeChainee<T> l1, ListeChainee<T> l2) {
		T elementTete;
		if (l1.tete == null)
			return l2;
		else if (l2.tete == null)
			return l1;
		else
			elementTete = l1.tete.getElement();
		l1.setTete(l1.tete.getSuivant());//on vire la tete de l1
		return merge(l1, insert(elementTete, l2));
	}

	public ListeChainee<T> mergeSort(ListeChainee<T> liste) {
		if (liste.size() <= 1)
			return liste;
		else {
			int taille = liste.size() / 2;
			ListeChainee<T> l1 = new ListeChainee<T>();
			ListeChainee<T> l2 = new ListeChainee<T>();
			int compteur = 0;
			Maillon<T> tmp = liste.tete;
			while (compteur < taille) {
				l1.add(tmp.getElement());
				tmp = tmp.getSuivant();
				compteur++;
			}
			while (tmp != null) {
				l2.add(tmp.getElement());
				tmp = tmp.getSuivant();
			}
			System.out.println(l1);
			System.out.println(l2);
			System.out.println();
			return merge(mergeSort(l1), mergeSort(l2));
		}
	}

	public boolean bienDanslePlateau(int i, int j, int x, int y) {
		return x < i && y < j;
	}

	public ListeChainee concatene(ListeChainee<T> l1, ListeChainee<T> l2) {
		if (l1.tete == null)
			return l2;
		ListeChainee<T> res = l1;
		Maillon<T> tmp = l1.tete;
		while (tmp.getSuivant() != null)
			tmp = tmp.getSuivant();
		tmp.setSuivant(l2.tete);
		return res;
	}

	public String toString() {
		if (tete == null)
			return "La liste chainée est vide.";
		else {
			String str = "";
			Maillon<T> tmp = tete;
			while (tmp != null) {
				str += tmp.toString();
				if (tmp.getSuivant() == null) {
					str += "/";
					break;
				}
				tmp = tmp.getSuivant();
			}
			return str;
		}
	}

	public Maillon<T> getTete() {
		return tete;
	}

	public void setTete(Maillon<T> tete) {
		this.tete = tete;
	}

}
