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
				if (tmp.getCellule().equals(cellule))
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
			lcClone.tete = new Maillon(tete.getCellule());
			Maillon tmp = tete;
			Maillon mClone = lcClone.tete;
			while (tmp.getSuivant()!= null){
				Maillon mCloneNext = new Maillon(tmp.getSuivant().getCellule());
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
		else if (debut <= this.size() && fin <= this.size() && debut <= fin && debut >= 1 && fin >= 1){
			ListeChainee lcClone = new ListeChainee ();
			int acc = 1;
			Maillon tmp = tete;
			while (acc < debut) {
				tmp = tmp.getSuivant();
				acc++;
			}
			while (acc < fin){
				lcClone.add(tmp.getCellule());
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

	public boolean put(Cellule cellule) {
		if (!this.contains(cellule)) {
			if (tete == null)
				tete = new Maillon(cellule);
			else {
				Maillon tmp = tete;
				Maillon tmp2 = tmp;
				while (tmp != null) {
					if (tmp.getCellule().compareTo(cellule) < 0) {
						tmp2 = tmp;
						tmp = tmp.getSuivant();
						if (tmp == null) {
							tmp2.setSuivant(new Maillon(cellule, null));
						}
					} else if (tmp.getCellule().compareTo(cellule) > 0) {
						if (tmp.equals(tete)) {
							tete = new Maillon(cellule, tmp2);
						} else
							tmp2.setSuivant(new Maillon(cellule, tmp));
						return true;
					}
				}
			}
			return true;
		}
		return false;
	}

	public boolean remove(Cellule cellule) {
		if (this.contains(cellule)) {
			if (tete.getCellule().equals(cellule)) {
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
					if (tmp.getCellule().equals(cellule)) {
						tmpAv.setSuivant(tmpAp);
						return true;
					}
				}
			}
		}
		return false;
	}

	public ListeChainee sort() {
		if (this.size() <= 1)
			return this;
		ListeChainee res = new ListeChainee();
		Maillon tmp = tete;
		while (tmp != null) {
			res.put(tmp.getCellule());
			tmp = tmp.getSuivant();
		}
		return res;
	}

    /*public String plateau() {
        int maxHaut = 0, maxBas = 0, maxGauche = 0, maxDroite = 0;
        Maillon tmp = tete;
        while (tmp != null) {
            if (tmp.getCellule().getLigne() < maxBas)
                maxBas = tmp.getCellule().getLigne();
            if (tmp.getCellule().getLigne() > maxHaut)
                maxHaut = tmp.getCellule().getLigne();
            if (tmp.getCellule().getColonne() < maxGauche)
                maxGauche = tmp.getCellule().getColonne();
            if (tmp.getCellule().getColonne() > maxDroite)
                maxDroite = tmp.getCellule().getColonne();
            tmp = tmp.getSuivant();
        }
        tmp = tete;

    }*/

	/*public Plateau plateau() {//int[][]
		int maxHaut = 0, maxBas = 0, maxGauche = 0, maxDroite = 0;
		Maillon tmp = tete;
		while (tmp != null) {
			if (tmp.getCellule().getLigne() < maxBas)
				maxBas = tmp.getCellule().getLigne();
			if (tmp.getCellule().getLigne() > maxHaut)
				maxHaut = tmp.getCellule().getLigne();
			if (tmp.getCellule().getColonne() < maxGauche)
				maxGauche = tmp.getCellule().getColonne();
			if (tmp.getCellule().getColonne() > maxDroite)
				maxDroite = tmp.getCellule().getColonne();
			tmp = tmp.getSuivant();
		}
		tmp = tete;
		int ligne = maxHaut;
		int colonne = maxGauche;//
		int i = 0, j = 0;//
		Plateau plateau = new Plateau(Math.abs(maxBas) + Math.abs(maxHaut) +1, Math.abs(maxDroite) + Math.abs(maxGauche) + 1);//
		Cellule curseur = new Cellule(ligne, colonne);//
		while (tmp != null) {//on ne se préocupe pas de la fin
			if (tmp.getCellule().equals(curseur)) {//si le maillon est égual au curseur
				plateau.add(1, i, j);//1 dans la case correspondante
				j++;//on déplace la colonne du plateau
				if (j < plateau.getNombreColonne()) {//si la colonne du plateau est tjs dans le plateau
					colonne++;//on met a jour la colonne
					curseur.setColonne(colonne);//on met a jour le curseur
				} else {//sinon (si la colonne du plateau n'est pas dans le plateau)
					i++;//on déplace la ligne du plateau
					j = 0;//on déplace la colonne du plateau
					ligne--;//on met a jour la ligne
					colonne = maxGauche;//on met a jour la colonne
					curseur = new Cellule(ligne, colonne);//on met à jour le curseur
				}//finSi
				tmp = tmp.getSuivant();//on déplace le pointeur de la liste
			} else {//sinon (si le maillon n'est pas égual au curseur)
				while (curseur.compareTo(tmp.getCellule()) < 0) {//TQ le curseur est plus petit que le maillon
					plateau.add(0, i, j);//0 dans la case correspondante
					j++;//on déplace la colonne du plateau
					if (j < plateau.getNombreColonne()) {//si la colonne du plateau est tjs dans le plateau
						colonne++;//on met a jour la colonne
						curseur.setColonne(colonne);//on met a jour le curseur
					} else {//sinon (si la colonne du plateau n'est pas dans le plateau)
						i++;//on déplace la ligne du plateau
						j = 0;//on déplace la colonne du plateau
						ligne--;//on met a jour la ligne
						colonne = maxGauche;//on met a jour la colonne
						curseur = new Cellule(ligne, colonne);//on met à jour le curseur
					}//finSi
				}//finTQ
			}//FinSi
		}//finTQ
		return plateau;
	}

	public void afficher() {
		plateau().afficher();
	}*/

	public boolean bienDanslePlateau(int i, int j, int x, int y) {
		return x < i && y < j;
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





/*	public ListeChainee insert(Cellule cellule, ListeChainee liste) {
		if (liste.tete == null)
			return new ListeChainee(new Maillon(cellule));
		else if (cellule.compareTo(liste.tete) < 0) {
			liste.put(cellule);
			return liste;
		} else {
			ListeChainee list = insert(cellule, liste);//.add(liste.tete.getCellule());
			list.put(liste.tete.getCellule());
			return list;
		}
	}

	public ListeChainee merge(ListeChainee l1, ListeChainee l2) {
		if (l1.tete == null)
			return l2;
		else if (l2.tete == null)
			return l1;
		else
			return merge(l1, insert(l1.tete.getCellule(), l2));
	}

	public ListeChainee mergeSort(ListeChainee liste) {
		if (liste.size() <= 1)
			return liste;
		else {
			int taille = 15;
			int taille1 = taille/2;
			int taille2 = taille1;
			if (taille % 2 != 0)
				taille1 += 1;
			ListeChainee l1 = new ListeChainee();
			ListeChainee l2 = new ListeChainee();
			int compteur = 0;
			Maillon tmp = liste.tete;
			while (compteur < taille1) {
				l1.put(tmp.getCellule());
				tmp = tmp.getSuivant();
				compteur++;
			}
			while (tmp != null) {
				l2.put(tmp.getCellule());
				tmp = tmp.getSuivant();
			}
			System.out.println(l1);
			System.out.println(l2);
			System.out.println();
			return merge(mergeSort(l1), mergeSort(l2));
		}
	}*/

/*	public ListeChainee sort() {
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

/*	public ListeChainee sort() {
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
		ListeChainee li = res.partitionInf(fin); System.out.println("li: " + li.toString());
		ListeChainee ls = res.partitionSup(pivot); System.out.println("ls: " + ls.toString());
		System.out.println();
		ls.add(pivot);
		res = concatene(li, ls);
		res = res.quickSort(pivot, fin);
		return res;
	}

	public ListeChainee quickSort(Cellule pivot, Cellule debut ,Cellule fin) {
        if (this.size() <= 1)
            return this;
        ListeChainee res = new ListeChainee();
        ListeChainee li = this.partitionInf(pivot);
        ListeChainee ls = this.partitionSup(pivot);
        ls.add(pivot);
        res = res.concatene(li, ls);
        return quickSort(pivot, res.tete.getC(), fin);
    }

	public ListeChainee partitionInf(Cellule pivot) {
		if (this.size() <= 1)
			return this;
		ListeChainee res = new ListeChainee();
		Maillon tmp = tete;
		while (tmp != null && !tmp.getC().equals(pivot)) { //jusqua la fin pas plus
			if (tmp.getC().compareTo(tete) < 0)
				res.add(tmp.getC());
			tmp = tmp.getSuivant();
		}
		return res;
	}

	public ListeChainee partitionSup(Cellule pivot) {
		if (this.size() <= 1)
			return this;
		else {
			ListeChainee res = new ListeChainee();
			Maillon tmp = tete;
            while (tmp != null && !tmp.getC().equals(pivot)) //tq le pivot n'est pas atteint
                tmp = tmp.getSuivant();
			while (tmp != null) {
				if (tmp.getC().compareTo(pivot) > 0)
					res.add(tmp.getC());
				tmp = tmp.getSuivant();
			}
			return res;
		}
	}*/



/*	public void afficher(int[][]plateau, int minLigne, int maxLigne, int minColonne, int maxColonne) {
        System.out.print("  ");
        for (int j = minColonne; j <= maxColonne; j++) {
            if (j < 10)
                System.out.print("  " + j + " ");
            else
                System.out.print(" " + j + " ");
        }
        System.out.println();
        System.out.print("  ");
        for (int j = 0; j < plateau[0].length; j++) {
            System.out.print(" ___");
        }
        int k = maxLigne;
        for (int i = 0; i < plateau.length; i++) {
            System.out.println();
            if (k < 10 && k >= 0)
                System.out.print(k + " |");
            else
                System.out.print(k + "|");
            k--;
            for (int j = 0; j < plateau[0].length; j++) {
                if (plateau[i][j] == 0)
                    System.out.print("   |");
                else
                    System.out.print(" X |");
            }
            System.out.println();
            System.out.print("  |");
            for (int j = 0; j < plateau[0].length; j++) {
                System.out.print("___|");
            }
        }
	}*/


