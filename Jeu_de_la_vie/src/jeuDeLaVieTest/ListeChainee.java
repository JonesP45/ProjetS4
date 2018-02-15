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

	/*A mettre dans la classe jeu*/
    public String plateau() {
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
		int colonne = maxGauche;
		int hauteur = Math.abs(maxBas) + Math.abs(maxHaut) +1;
		int largeur = Math.abs(maxDroite) + Math.abs(maxGauche) + 1;
		int i = 0, j = 0;
		Cellule curseur = new Cellule(ligne, colonne);
		String newLine = System.getProperty("line.separator");
		String str = "";
		str += newLine;
		while (tmp != null) {//on ne se préocupe pas de la fin
			if (tmp.getCellule().equals(curseur)) {//si le maillon est égual au curseur
				str += "*"; //plateau.add(1, i, j);//1 dans la case correspondante
				j++;//on déplace la colonne du plateau
				if (j < largeur) {//si la colonne du plateau est tjs dans le plateau
					colonne++;//on met a jour la colonne
					curseur.setColonne(colonne);//on met a jour le curseur
				} else {//sinon (si la colonne du plateau n'est pas dans le plateau)
					i++;//on déplace la ligne du plateau
					j = 0;//on déplace la colonne du plateau
					ligne--;//on met a jour la ligne
					colonne = maxGauche;//on met a jour la colonne
					curseur = new Cellule(ligne, colonne);//on met à jour le curseur
					str += newLine;
				}//finSi
				tmp = tmp.getSuivant();//on déplace le pointeur de la liste
			} else {//sinon (si le maillon n'est pas égual au curseur)
				while (curseur.compareTo(tmp.getCellule()) < 0) {//TQ le curseur est plus petit que le maillon
					str += "."; //plateau.add(0, i, j);//0 dans la case correspondante
					j++;//on déplace la colonne du plateau
					if (j < largeur) {//si la colonne du plateau est tjs dans le plateau
						colonne++;//on met a jour la colonne
						curseur.setColonne(colonne);//on met a jour le curseur
					} else {//sinon (si la colonne du plateau n'est pas dans le plateau)
						i++;//on déplace la ligne du plateau
						j = 0;//on déplace la colonne du plateau
						ligne--;//on met a jour la ligne
						colonne = maxGauche;//on met a jour la colonne
						curseur = new Cellule(ligne, colonne);//on met à jour le curseur
						str += newLine;
					}//finSi
				}//finTQ
			}//FinSi
		}
		while (ligne >= maxBas && colonne <= maxDroite) {
			str += "."; //plateau.add(0, i, j);//0 dans la case correspondante
			j++;//on déplace la colonne du plateau
			if (j < largeur) {//si la colonne du plateau est tjs dans le plateau
				colonne++;//on met a jour la colonne
				curseur.setColonne(colonne);//on met a jour le curseur
			} else {//sinon (si la colonne du plateau n'est pas dans le plateau)
				i++;//on déplace la ligne du plateau
				j = 0;//on déplace la colonne du plateau
				ligne--;//on met a jour la ligne
				colonne = maxGauche;//on met a jour la colonne
				curseur = new Cellule(ligne, colonne);//on met à jour le curseur
				str += newLine;
			}//finSi
		}
		return str;
    }

	public void afficher() {
		System.out.println(plateau());
	}
    /**/

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
