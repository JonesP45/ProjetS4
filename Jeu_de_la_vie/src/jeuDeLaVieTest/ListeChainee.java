package jeuDeLaVieTest;

public class ListeChainee {

	private Maillon tete;

	public ListeChainee() {
		tete = null;
	}

	public ListeChainee(Maillon tete) {
		this.tete = tete;
	}

	public boolean contains(Cellule cellule) {
		if (tete == null)
			return false;
		else {
			Maillon tmp = tete;
			while (tmp != null) {
				if (tmp.getC().equals(cellule))
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
			Maillon tmp = tete;
			int size = 1;
			while (tmp.getSuivant() != null) {
				size++;
				tmp = tmp.getSuivant();
			}
			return size;
		}
	}

	public ListeChainee clone(){
		if (this.tete == null)
			return new ListeChainee();
		else {
			ListeChainee lcClone = new ListeChainee ();
			lcClone.tete = new Maillon(tete.getC());
			Maillon tmp = tete;
			Maillon mClone = lcClone.tete;
			while (tmp.getSuivant()!= null){
				Maillon mCloneNext = new Maillon(tmp.getSuivant().getC());
				mClone.setSuivant(mCloneNext);
				mClone = mClone.getSuivant();
				tmp = tmp.getSuivant();
			}
			return lcClone;
		}
	}
	
	public ListeChainee cloneBis(int debut, int fin){
		if (this.tete == null)
			return new ListeChainee();
		else if (debut <= this.size() && fin <= this.size() && debut <= fin){
			ListeChainee lcClone = new ListeChainee ();
			int acc = 1;
			Maillon tmp = tete;
			while (acc < debut) {
				tmp = tmp.getSuivant();
				acc++;
			}
			while (acc < fin){
				lcClone.add(tmp.getC());
				tmp = tmp.getSuivant();
				acc++;
			}
			return lcClone;
		}
		return null;
	}

	public boolean add(Cellule cellule) {
		if (!this.contains(cellule)) {
			if (tete == null)
				tete = new Maillon(cellule);
			else {
				Maillon tmp = tete;
				tete = new Maillon(cellule, tmp);
			}
			return true;
		}
		return false;
	}

	public boolean remove(Cellule cellule) {
		if (this.contains(cellule)) {
			if (tete.getC().equals(cellule)) {
				tete = tete.getSuivant();
			} else {
				Maillon tmp = tete;
				while (tmp != null) {
					Maillon tmpAv = tmp;
					if (tmp.getSuivant() != null)
						tmp = tmp.getSuivant();
					else
						break;
					Maillon tmpAp = null;
					if (tmp.getSuivant() != null)
						tmpAp = tmp.getSuivant();
					if (tmp.getC().equals(cellule)) {
						tmpAv.setSuivant(tmpAp);
						return true;
					}
				}
			}
		}
		return false;
	}

	/*public ListeChainee sort() {
		if (this.size() <= 1)
			return this;
		ListeChainee res = this.clone(); 												System.out.println("res: " + res.toString());
		int tailleInf = res.size();
		int tailleSup = tailleInf;
		if (tailleInf%2 == 0) {
			tailleInf /= 2;
			tailleSup = tailleInf;
		} else {
			tailleInf = (tailleInf / 2) + 1;
			tailleSup /= 2;
		}
		
		ListeChainee li = partitionInf(res, tailleInf); 								System.out.println("li: " + li.toString());
		ListeChainee ls = partitionSup(res, tailleSup); 									System.out.println("ls: " + ls.toString());
		
		
		return res;
	}

	public ListeChainee partitionInf(ListeChainee list, int fin) {
		if (this.size() <= 1)
			return list;
		ListeChainee res = new ListeChainee();
		Maillon tmp = list.tete;
		int acc = 1;
		while (acc <= fin) {
			res.add(tmp.getC());
			acc++;
			tmp = tmp.getSuivant();
		}
		return res;
	}
	
	public ListeChainee partitionSup(ListeChainee list, int taille) {
		if (this.size() <= 1)
			return list;
		else {
			ListeChainee res = new ListeChainee();
			Maillon tmp = list.tete;
			while (tmp != null) {
				if (tmp.getC().compareTo(pivot) > 0)
					res.add(tmp.getC());
				tmp = tmp.getSuivant();
			}
			return res;
		}
	}*/
	
	public ListeChainee sort() {
		if (this.size() <= 1)
			return this;
		ListeChainee res = this.clone(); System.out.println("res: " + res.toString());
		Cellule pivot = tete.getC(); System.out.println("pivot: " + pivot.toString());
		res.tete = res.tete.getSuivant(); System.out.println("res.tete: " + res.tete.toString());
		Maillon tmp = res.tete;
		Cellule fin = null;
		while (tmp != null) {
			if (tmp.getSuivant() == null)
				fin = tmp.getC();
			tmp = tmp.getSuivant();
		}
		
		ListeChainee li = partitionInf(res, pivot, fin); System.out.println("li: " + li.toString());
		ListeChainee ls = partitionSup(res, pivot); System.out.println("ls: " + ls.toString());
		System.out.println();
		ls.add(pivot);
		res = concatene(li, ls);
		res.sort();
//		while (tmp.getSuivant() != null) {
//			
//		}
//		li = li.sort(); System.out.println("li sort: " + li.toString());
//		ls = ls.sort(); System.out.println("ls sort: " + ls.toString());

		//ls.add(pivot);
		//System.out.println("conc: " + concatene(li, ls));
		return res;
		//return concatene(li, ls);
	}

	public ListeChainee partitionInf(ListeChainee list, Cellule pivot, Cellule fin) {
		if (this.size() <= 1)
			return list;
		ListeChainee res = new ListeChainee();
		Maillon tmp = list.tete;
		while (tmp != null && !tmp.getC().equals(fin)) {
			if (tmp.getC().compareTo(pivot) < 0)
				res.add(tmp.getC());
			tmp = tmp.getSuivant();
		}
		return res;
	}
	
//	public ListeChainee partitionInf(ListeChainee list, Cellule pivot) {
//		Maillon tmp = list.tete;
//		Cellule fin = null;
//		while (tmp != null) {
//			if (tmp.getSuivant() == null)
//				fin = tmp.getC();
//			tmp = tmp.getSuivant();
//		}
//		return partitionInfBornee(list, pivot, fin);
//	}
	
//	public ListeChainee partitionInf(ListeChainee list, Cellule pivot) {
//		if (this.size() <= 1)
//			return list;
//		ListeChainee res = new ListeChainee();
//		Maillon tmp = list.tete;
//		while (tmp != null) {
//			if (tmp.getC().compareTo(pivot) < 0)
//				res.add(tmp.getC());
//			tmp = tmp.getSuivant();
//		}
//		return res;
//	}

	public ListeChainee partitionSup(ListeChainee list, Cellule pivot) {
		if (this.size() <= 1)
			return list;
		else {
			ListeChainee res = new ListeChainee();
			Maillon tmp = list.tete;
			while (tmp != null) {
				if (tmp.getC().compareTo(pivot) > 0)
					res.add(tmp.getC());
				tmp = tmp.getSuivant();
			}
			return res;
		}
	}

	public ListeChainee concatene(ListeChainee l1, ListeChainee l2) {
		if (l1.tete == null)
			return l2;
		ListeChainee res = l1;
		Maillon tmp = l1.tete;
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
			Maillon tmp = tete;
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

	public Maillon getTete() {
		return tete;
	}

	public void setTete(Maillon tete) {
		this.tete = tete;
	}

}
