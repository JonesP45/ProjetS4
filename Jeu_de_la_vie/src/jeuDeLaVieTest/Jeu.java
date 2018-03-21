package jeuDeLaVieTest;

public class Jeu {

    public static void print(ListeChainee<Cellule> liste) {
        int maxHaut = -1000000, maxBas = 1000000, maxGauche = 1000000, maxDroite = -1000000;
        Maillon<Cellule> tmp = liste.getTete();
        while (tmp != null) {
            if (tmp.getElement().getLigne() < maxBas)
                maxBas = tmp.getElement().getLigne();
            if (tmp.getElement().getLigne() > maxHaut)
                maxHaut = tmp.getElement().getLigne();
            if (tmp.getElement().getColonne() < maxGauche)
                maxGauche = tmp.getElement().getColonne();
            if (tmp.getElement().getColonne() > maxDroite)
                maxDroite = tmp.getElement().getColonne();
            tmp = tmp.getSuivant();
        }
        tmp = liste.getTete();
        int ligne = maxHaut;
        int colonne = maxGauche;
        int largeur;
        if (maxGauche < 0)
            largeur = Math.abs(maxDroite) + Math.abs(maxGauche) + 1;
        else
            largeur = Math.max(maxDroite, maxGauche) - Math.min(maxDroite, maxGauche) + 1;
        int i = 0, j = 0;
        Cellule curseur = new Cellule(ligne, colonne);
        System.out.println();
        while (tmp != null) {//on ne se préocupe pas de la fin
            if (tmp.getElement().equals(curseur)) {//si le maillon est égual au curseur
                System.out.print("*"); //plateau.add(1, i, j);//1 dans la case correspondante
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
                    System.out.println();
                }//finSi
                tmp = tmp.getSuivant();//on déplace le pointeur de la liste
            } else {//sinon (si le maillon n'est pas égual au curseur)
                while (curseur.compareTo(tmp.getElement()) < 0) {//TQ le curseur est plus petit que le maillon
                    System.out.print("."); //plateau.add(0, i, j);//0 dans la case correspondante
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
                        System.out.println();
                    }//finSi
                }//finTQ
            }//FinSi
        }
        while (ligne >= maxBas && colonne <= maxDroite) {
            System.out.print("."); //plateau.add(0, i, j);//0 dans la case correspondante
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
                System.out.println();
            }//finSi
        }
    }

    public static int etatCellule(ListeChainee<Cellule> plateau, int x, int y){    //renvoi l'état d'une cellule sous forme d'entier, 1 si elle est vivante, 0 si elle est morte
        if (plateau.contains(new Cellule(x, y)))
            return 1;
        return 0;
    }

    public static int voisins(ListeChainee<Cellule> plateau, int x, int y){    //Méthode calculant le nombre de vosins vivant d'une cellule morte ou vivante et retourne ce nombre.
        int c = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (!(i == x && j == y))
                    c += etatCellule(plateau, i, j);
            }
        }
        return c;
    }

    public static ListeChainee<Cellule> voisinsLC(ListeChainee<Cellule> plateau, int x, int y){    // Stocke les cellules mortes dans une liste chainee
        ListeChainee<Cellule> voisins = new ListeChainee<Cellule>();
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                Cellule tmp = new Cellule(i, j);
                if (!(plateau.contains(tmp)) && !tmp.equals(new Cellule(x, y)))
                    voisins.put(tmp);
            }
        }
        return voisins;
    }

    public static ListeChainee<Cellule> plateauSuivant(ListeChainee<Cellule> plateau) {  //calcule l'étape n+1 du jeu de la vie
        ListeChainee<Cellule> plateauSuiv = plateau.clone();
        ListeChainee<Cellule> NouvelleCellules = new ListeChainee<Cellule>();    //On stocke toutes les nouvelles cellules mortes à vivantes pour ne pas les "ajouter deux fois".
        Maillon<Cellule> i = plateau.getTete();
        while (i != null) {
            int x = i.getElement().getLigne();
            int y = i.getElement().getColonne();
            int voisins = voisins(plateau, x, y);
            //System.out.println(voisins);
            if (!(voisins == 3 || voisins == 2)) {
                plateauSuiv.remove(new Cellule(x, y));
            }
            //System.out.println(plateauSuiv);

            ListeChainee<Cellule> tmp = voisinsLC(plateau, x, y);
            //System.out.println(tmp);
            Maillon<Cellule> j = tmp.getTete();
            while (j != null) {
                int x1 = j.getElement().getLigne();
                int y1 = j.getElement().getColonne();
                if (voisins(plateau, x1, y1) == 3 && !(NouvelleCellules.contains(new Cellule(x1,y1)))) {
                    plateauSuiv.put(new Cellule(x1, y1));
                    NouvelleCellules.put(new Cellule(x1, y1));
                }
                j = j.getSuivant();
            }
            i = i.getSuivant();
        }
        return plateauSuiv;
    }

    public static void main(String [] args) {
        ListeChainee<Cellule> list = new ListeChainee<Cellule>();
        ListeChainee<Cellule> l1 = new ListeChainee<Cellule>();
        ListeChainee<Cellule> l2 = new ListeChainee<Cellule>();

        Cellule c1 = new Cellule(4,7);
        Cellule c2 = new Cellule(3,8);
        Cellule c3 = new Cellule(4,6);
        Cellule c4 = new Cellule(5,9);
        Cellule c5 = new Cellule(1,2);
        Cellule c6 = new Cellule(8,10);
        Cellule c7 = new Cellule(-3,4);
        Cellule c8 = new Cellule(-1,-2);

        Cellule c10 = new Cellule(1,3);
        Cellule c11 = new Cellule(1,5);
        Cellule c12 = new Cellule(2,5);
        Cellule c13 = new Cellule(2,7);
        Cellule c14 = new Cellule(4,10);
        Cellule c15 = new Cellule(4,15);
        Cellule c16 = new Cellule(10,20);

        list.add(c1);
        list.add(c2);
        list.add(c8);
        list.add(c4);
        list.add(c5);
        list.add(c6);
        list.add(c7);
        list.add(c3);
        list.add(c10);
        list.add(c11);
        list.add(c12);
        list.add(c13);
        list.add(c14);
        list.add(c15);
        list.add(c16);

        System.out.println();
        System.out.println(list.toString());
        System.out.println("taille: " + list.size());

        System.out.println(l1.toString());
        list = list.mergeSort();
        System.out.println(list);

        System.out.println();
        print(list);
        System.out.println();
        l1 = plateauSuivant(list);
        System.out.println(l1);
        print(l1);
    }

}