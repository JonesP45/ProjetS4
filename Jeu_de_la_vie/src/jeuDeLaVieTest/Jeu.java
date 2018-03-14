package jeuDeLaVieTest;

public class Jeu {

    public static String plateau(ListeChainee<Cellule> liste) {
        int maxHaut = 0, maxBas = 0, maxGauche = 0, maxDroite = 0;
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
        int hauteur = Math.abs(maxBas) + Math.abs(maxHaut) +1;
        int largeur = Math.abs(maxDroite) + Math.abs(maxGauche) + 1;
        int i = 0, j = 0;
        Cellule curseur = new Cellule(ligne, colonne);
        String newLine = System.getProperty("line.separator");
        String str = "";
        str += newLine;
        while (tmp != null) {//on ne se préocupe pas de la fin
            if (tmp.getElement().equals(curseur)) {//si le maillon est égual au curseur
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
                while (curseur.compareTo(tmp.getElement()) < 0) {//TQ le curseur est plus petit que le maillon
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

    public static void afficher(ListeChainee<Cellule> liste) {
        System.out.println(plateau(liste));
    }

}
