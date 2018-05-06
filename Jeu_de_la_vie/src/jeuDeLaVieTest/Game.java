package jeuDeLaVieTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.Timer;
import java.awt.event.*;

public class Test {

    private static boolean circular;
    private static int topTerminal;
    private static int lowTerminal;
    private static int leftTerminal;
    private static int rightTerminal;


    /**
     * Affiche en console la grille correspondant à la liste list passée en paramètre.
     * Les coordonnées sont gérées dans un maximum de -1 million/+1million dans le cas des grilles infinies.
     * @param list liste a afficher.
     */
    private static void print(LinkedList<Cell> list) {
        int maxTop = topTerminal, maxLow = lowTerminal, maxLeft = leftTerminal, maxRight = rightTerminal;
        Link<Cell> tmp = list.getHead();
        if (topTerminal == 0 && lowTerminal == 0 && leftTerminal == 0 && rightTerminal == 0) {
            maxTop = -1000000;
            maxLow = 1000000;
            maxLeft = 1000000;
            maxRight = -1000000;
            while (tmp != null) {
                if (tmp.getElement().getRow() < maxLow)
                    maxLow = tmp.getElement().getRow();
                if (tmp.getElement().getRow() > maxTop)
                    maxTop = tmp.getElement().getRow();
                if (tmp.getElement().getColumn() < maxLeft)
                    maxLeft = tmp.getElement().getColumn();
                if (tmp.getElement().getColumn() > maxRight)
                    maxRight = tmp.getElement().getColumn();
                tmp = tmp.getNext();
            }
            tmp = list.getHead();
        }
        int row = maxTop;
        int column = maxLeft;
        Cell pointer = new Cell(row, column);
        while (tmp != null) {//on ne se préocupe pas de la fin
            if (tmp.getElement().equals(pointer)) {//si le Link est égual au pointer
                System.out.print("*"); //gameBoard.add(1, i, j);//1 dans la case correspondante
                column++;//on déplace la column du gameBoard
                if (column <= maxRight) {//si la column du gameBoard est tjs dans le gameBoard
                    pointer.setColumn(column);//on met a jour le pointer
                } else {//sinon (si la column du gameBoard n'est pas dans le gameBoard)
                    row--;//on met a jour la row
                    column = maxLeft;//on met a jour la column
                    pointer = new Cell(row, column);//on met à jour le pointer
                    System.out.println();
                }//finSi
                tmp = tmp.getNext();//on déplace le pointeur de la liste
            } else {//sinon (si le Link n'est pas égual au pointer)
                while (pointer.compareTo(tmp.getElement()) < 0) {//TQ le pointer est plus petit que le Link
                    System.out.print("."); //gameBoard.add(0, i, j);//0 dans la case correspondante
                    column++;//on déplace la column du gameBoard
                    if (column <= maxRight) {//si la column du gameBoard est tjs dans le gameBoard
                        pointer.setColumn(column);//on met a jour le pointer
                    } else {//sinon (si la column du gameBoard n'est pas dans le gameBoard)
                        row--;//on met a jour la row
                        column = maxLeft;//on met a jour la column
                        pointer = new Cell(row, column);//on met à jour le pointer
                        System.out.println();
                    }//finSi
                }//finTQ
            }//FinSi
        }
        while (row >= maxLow && column <= maxRight) {
            System.out.print("."); //gameBoard.add(0, i, j);//0 dans la case correspondante
            column++;//on déplace la column du gameBoard
            if (column <= maxRight) {//si la column du gameBoard est tjs dans le gameBoard
                pointer.setColumn(column);//on met a jour le pointer
            } else {//sinon (si la column du gameBoard n'est pas dans le gameBoard)
                row--;//on met a jour la row
                column = maxLeft;//on met a jour la column
                pointer = new Cell(row, column);//on met à jour le pointer
                System.out.println();
            }//finSi
        }
    }

    /**
     * Prends un tableau de listes chainées de Cell en paramètre. Vérifie s'il est vide.
     * @param tab
     * @return true si le tableau est vide, false sinon.
     */
    private static boolean arrayIsEmpty(LinkedList<Cell>[] tab) {
        for (int i = 1; i < tab.length; i++) {
            if (!tab[i].isEmpty())
                return false;
        }
        return true;
    }

    /**
     * Crée un tableau de 8 listes chainées en décalant les coordonées depuis la liste passée en paramètre de 1 dans chaque direction possible.
     * @param gameBoard liste chainiée de cellule dont on veux les listes décalées.
     * @return le tableau de 8 liste chainées
     */
    private static LinkedList<Cell>[] mode(LinkedList<Cell> gameBoard) {
        Link<Cell> tmp = gameBoard.getHead();
        int x;
        int y;
        LinkedList<Cell> N = new LinkedList<Cell>();
        LinkedList<Cell> NE = new LinkedList<Cell>();
        LinkedList<Cell> E = new LinkedList<Cell>();
        LinkedList<Cell> SE = new LinkedList<Cell>();
        LinkedList<Cell> S = new LinkedList<Cell>();
        LinkedList<Cell> SO = new LinkedList<Cell>();
        LinkedList<Cell> O = new LinkedList<Cell>();
        LinkedList<Cell> NO = new LinkedList<Cell>();
        //infini
        if (topTerminal == 0 && lowTerminal == 0 && leftTerminal == 0 && rightTerminal == 0) {
            while (tmp != null) {
                x = tmp.getElement().getRow();
                y = tmp.getElement().getColumn();
                N.addReverse(new Cell(x + 1, y));
                NE.addReverse(new Cell(x + 1, y + 1));
                E.addReverse(new Cell(x, y + 1));
                SE.addReverse(new Cell(x - 1, y + 1));
                S.addReverse(new Cell(x - 1, y));
                SO.addReverse(new Cell(x - 1, y - 1));
                O.addReverse(new Cell(x, y - 1));
                NO.addReverse(new Cell(x + 1, y - 1));
                tmp = tmp.getNext();
            }
            //fini
        } else {
            while (tmp != null) {
                x = tmp.getElement().getRow();
                y = tmp.getElement().getColumn();
                //si pas dans les bornes
                if (tmp.getElement().getRow() > topTerminal || tmp.getElement().getRow() < lowTerminal
                        || tmp.getElement().getColumn() < leftTerminal || tmp.getElement().getColumn() > rightTerminal) {
                    gameBoard.remove(tmp.getElement());
                    //sinon circulaire et tmp sur borne
                } else if (circular && (x == topTerminal || x == lowTerminal || y == leftTerminal || y == rightTerminal)) {
                    if (x == topTerminal) { //N -> S
                        N.addReverse(new Cell(lowTerminal, y));
                        NE.addReverse(new Cell(lowTerminal, y + 1));
                        E.addReverse(new Cell(x, y + 1));
                        SE.addReverse(new Cell(x - 1, y + 1));
                        S.addReverse(new Cell(x - 1, y));
                        SO.addReverse(new Cell(x - 1, y - 1));
                        O.addReverse(new Cell(x, y - 1));
                        NO.addReverse(new Cell(lowTerminal, y - 1));
                    }
                    if (x == lowTerminal) { //S -> N
                        N.addReverse(new Cell(x + 1, y));
                        NE.addReverse(new Cell(x + 1, y + 1));
                        E.addReverse(new Cell(x, y + 1));
                        SE.addReverse(new Cell(topTerminal, y + 1));
                        S.addReverse(new Cell(topTerminal, y));
                        SO.addReverse(new Cell(topTerminal, y - 1));
                        O.addReverse(new Cell(x, y - 1));
                        NO.addReverse(new Cell(x + 1, y - 1));
                    }
                    if (y == leftTerminal) { //O -> E*
                        N.addReverse(new Cell(x + 1, y));
                        NE.addReverse(new Cell(x + 1, y + 1));
                        E.addReverse(new Cell(x, y + 1));
                        SE.addReverse(new Cell(x - 1, y + 1));
                        S.addReverse(new Cell(x - 1, y));
                        SO.addReverse(new Cell(x - 1, rightTerminal));
                        O.addReverse(new Cell(x, rightTerminal));
                        NO.addReverse(new Cell(x + 1, rightTerminal));
                    }
                    if (y == rightTerminal) { //E -> O
                        N.addReverse(new Cell(x + 1, y));
                        NE.addReverse(new Cell(x + 1, leftTerminal));
                        E.addReverse(new Cell(x, leftTerminal));
                        SE.addReverse(new Cell(x - 1, leftTerminal));
                        S.addReverse(new Cell(x - 1, y));
                        SO.addReverse(new Cell(x - 1, y - 1));
                        O.addReverse(new Cell(x, y - 1));
                        NO.addReverse(new Cell(x + 1, y - 1));
                    }
                    //sinon non circulaire sur borne
                } else if (!circular && (x == topTerminal || x == lowTerminal || y == leftTerminal || y == rightTerminal)) {
                    if (x == topTerminal) { //pas N
                        E.addReverse(new Cell(x, y + 1));
                        SE.addReverse(new Cell(x - 1, y + 1));
                        S.addReverse(new Cell(x - 1, y));
                        SO.addReverse(new Cell(x - 1, y - 1));
                        O.addReverse(new Cell(x, y - 1));
                    }
                    if (x == lowTerminal) { //pas S
                        N.addReverse(new Cell(x + 1, y));
                        NE.addReverse(new Cell(x + 1, y + 1));
                        E.addReverse(new Cell(x, y + 1));
                        O.addReverse(new Cell(x, y - 1));
                        NO.addReverse(new Cell(x + 1, y - 1));
                    }
                    if (y == leftTerminal) { //pas O
                        N.addReverse(new Cell(x + 1, y));
                        NE.addReverse(new Cell(x + 1, y + 1));
                        E.addReverse(new Cell(x, y + 1));
                        SE.addReverse(new Cell(x - 1, y + 1));
                        S.addReverse(new Cell(x - 1, y));
                    }
                    if (y == rightTerminal) { //pas E
                        N.addReverse(new Cell(x + 1, y));
                        S.addReverse(new Cell(x - 1, y));
                        SO.addReverse(new Cell(x - 1, y - 1));
                        O.addReverse(new Cell(x, y - 1));
                        NO.addReverse(new Cell(x + 1, y - 1));
                    }
                } else {
                    N.addReverse(new Cell(x + 1, y));
                    NE.addReverse(new Cell(x + 1, y + 1));
                    E.addReverse(new Cell(x, y + 1));
                    SE.addReverse(new Cell(x - 1, y + 1));
                    S.addReverse(new Cell(x - 1, y));
                    SO.addReverse(new Cell(x - 1, y - 1));
                    O.addReverse(new Cell(x, y - 1));
                    NO.addReverse(new Cell(x + 1, y - 1));
                }
                tmp = tmp.getNext();
            }
            N = N.sortReverse();
            NE = NE.sortReverse();
            E = E.sortReverse();
            SE = SE.sortReverse();
            S = S.sortReverse();
            SO = SO.sortReverse();
            O = O.sortReverse();
            NO = NO.sortReverse();
        }
        LinkedList[] array = {N, NE, E, SE, S, SO, O, NO};
        return (LinkedList<Cell>[]) array; // cast
    }

    /**
     * A partir d'une liste chainée de cell, calcule la génération suivante. La liste originelle est modifiée.
     * @param gameBoard La liste dont on veux calculer la génération suivante.
     * @return gameBoard Liste modifié. Il s'agit du même objet que celui donné en entrée.
     */
    private static LinkedList<Cell> nextGameBoard(LinkedList<Cell> gameBoard) {
        LinkedList<Cell>[] arrayList = mode(gameBoard);
        LinkedList<Cell> neighborsList = new LinkedList<Cell>();
        Cell min = arrayList[0].getHead().getElement();
        int indexOfMinList = 0;
        while (!arrayIsEmpty(arrayList)) { //TQ le tableau de liste n'est pas vide
            if (min == null) {
                for (int i = 0; i < arrayList.length; i++) {
                    if (!arrayList[i].isEmpty()) {
                        min = arrayList[i].getHead().getElement();
                        indexOfMinList = i;
                        break;
                    }
                }
                for (int i = 1; i < arrayList.length; i++) {
                    if (!arrayList[i].isEmpty() && min.compareTo(arrayList[i].getHead().getElement()) < 0) { //pas de pb de NullPointerException
                        min = arrayList[i].getHead().getElement();
                        indexOfMinList = i;
                    }
                }
            } else {
                for (int i = 0; i < arrayList.length; i++) {
                    if (!arrayList[i].isEmpty() && min.compareTo(arrayList[i].getHead().getElement()) < 0) {
                        min = arrayList[i].getHead().getElement();
                        indexOfMinList = i;
                    }
                }
            }
            Link<Cell> link = gameBoard.get(min);
            if (link != null)
                link.getElement().addNeighbors(1);
            else {
                link = neighborsList.get(min);
                if (link == null) {
                    neighborsList.put(min);
                    link = neighborsList.get(min);
                    link.getElement().addNeighbors(1);
                } else
                    link.getElement().addNeighbors(1);
            }
            arrayList[indexOfMinList].removeReverse(min);
            if (!arrayList[indexOfMinList].isEmpty())
                min = arrayList[indexOfMinList].getHead().getElement();
            else
                min = null;
        }
        Link<Cell> tmp = gameBoard.getHead();
        while (tmp != null) {
            if (!(tmp.getElement().getNbNeighbors() == 1003 || tmp.getElement().getNbNeighbors() == 1002))
                gameBoard.remove(tmp.getElement());
            tmp.getElement().setNbNeighbors(1000);
            tmp = tmp.getNext();
        }
        tmp = neighborsList.getHead();
        while (tmp != null) {
            if (tmp.getElement().getNbNeighbors() == 1003) {
                tmp.getElement().setNbNeighbors(1000);
                gameBoard.put(tmp.getElement());
            }
            tmp = tmp.getNext();
        }
        return gameBoard;
    }


    private static Timer t = new Timer(0, null);
    /**
     * Calcule et affiche les nbStep générations suivantes de gameBoard.
     * @param gameBoard Liste initiale dont on veux calculé les générations. Sa valeur est modifiée par l'éxécution.
     * @param nbStep Nombre de générations a calculer.
     */
    private static void generateStep(LinkedList<Cell> gameBoard, int nbStep) {
        LinkedList<Cell> list = gameBoard.clone();
        ActionListener taskPerformer = new ActionListener() {
            int count = 0;
            long totalTime = 0;
            long printTime = 0;
            long time;
            long start;
            long end;
            public void actionPerformed(ActionEvent e) {
                if (count < nbStep) {
                    System.out.println();
                    System.out.println("Génération " + count + ": ");
                    start = System.currentTimeMillis();
                    nextGameBoard(list);
                    end = System.currentTimeMillis();
                    time = (end - start);
                    System.out.println("Temps pour calculer la génération " + count + ": " + time + " milisecondes.");
                    totalTime += time;
                    start = System.currentTimeMillis();
                    print(list);
                    end = System.currentTimeMillis();
                    time = (end - start);
                    printTime += time;
                    System.out.println("Temps pour calculer la génération " + count + ": " + time + " milisecondes.");
                    count++;
                } else { //if (i == nbStep){
                    System.out.println();
                    System.out.println("Exécution terminé.");
                    System.out.println("Temps pour calculer " + nbStep + " générations: " + totalTime + " milisecondes.");
                    System.out.println("Temps pour calculer " + nbStep + " affichages: " + printTime + " milisecondes.");
                    System.out.println("Temps total: " + (printTime + totalTime) + " milisecondes.");
                    t.stop();
                }
            }
        };
        t = new Timer(1000, taskPerformer);
        t.start();
        while (t.isRunning());
    }

    /**
     * Renvoie une valeur indiquant le type d'évolution d'un jeu donné.
     * @param liste Jeu dont l'on veux calculer le type d'évolution.
     * @param max Limite de générations a calculer.
     * @return Un entier dont la valeur est :
     *      négative en cas de mort (-x indique une mort au bout de x générations)
     *      comprise entre 0 et max si le jeu est périodique
     *      comprise entre max+1 et 2*max si le jeu est périodique dont la figure a été déplacé.
     *      égale a 2*max +1 si le type d'évolution n'a pas pu être donné dans nombre de générations imparties.
     *      peut être utilisée comme taille maximum de la queue (en valeur absolue, et divisée par 2 dans le cas de décalage).
     */
    private static int calculAsymptotique(LinkedList<Cell> liste, int max) {
        LinkedList<Cell> c1 = liste.clone();
        LinkedList<Cell> c2 = liste.clone();
        LinkedList<Cell> c3;
        int i = 0;
        boolean first = true;

        while (i <= max) {
            if (c1.isEmpty()) {
                return -i;//mort
            } else if (c2.isEmpty()) {
                return -2 * i;//mort
            } else {
                c1 = nextGameBoard(c1);
                c3 = nextGameBoard(c2);
                c2 = nextGameBoard(c3);
                i++;
                if (c1.equals(c2) || (c1.equals(c3) && !first)) {
                    return i;//periodique (possiblement stable)
                } else {
                    first = false;
                    int[] d = decale(c1,c2);
                    if (!(d[0] == 0 && d[1] == 0)) {
                        return max + i;//decale
                    }
                }
            }
        }
        return 2 * max + 1;//inconnu
    }

    /**
     * Évalue si les listes passées en paramètre sont les mêmes a une translation prêt.
     * @param c1 Liste a comparer à c2
     * @param c2 Liste a comparer à c1
     * @return un tableau contenant deux entiers représentant le vecteur de translation. Renvoie {0, 0} si les listes ne sont pas identiques.
     */
    private static int[] decale(LinkedList<Cell> c1, LinkedList<Cell> c2) {
        int[] tab = {0, 0};
        Link<Cell> m1 = c1.getHead();
        Link<Cell> m2 = c2.getHead();
        int decaleX = m2.getElement().getRow() - m1.getElement().getRow();
        int decaleY = m2.getElement().getColumn() - m1.getElement().getColumn();
        while (m1 != null && m2 != null) {
            if (m2.getElement().getRow() - m1.getElement().getRow() != decaleX || m2.getElement().getColumn() - m1.getElement().getColumn() != decaleY) {
                return tab;
            }
            m1 = m1.getNext();
            m2 = m2.getNext();
        }
        if (m1 == null && m2 == null) {
            tab[0] = decaleX;
            tab[1] = decaleY;
            return tab;
        } else {
            return tab;
        }
    }

    /**
     * Calcul la période entre deux générations de jeu identiques. Cette fonction ne termine que si la liste passée en paramètre est réellement périodique.
     * @param liste Liste à partir de laquelle on veux calculer la durée de la période.
     * @param ca Permet de sauter un certain nombre de générations afin d'entre directement dans la structure périodique.
     * @param dec Doit valoir true si la génération sera décalée, false sinon.
     * @return un tableau contenant en première position, la période, et en deux et troisième le vecteur de décalage.
     */
    private static int[] periode(LinkedList<Cell> liste, int ca, boolean dec) {
        int[] tab = {0, 0, 0};
        LinkedList<Cell> c1 = liste.clone();
        for (int i = 0; i < ca; i++) {
            c1 = nextGameBoard(c1);
        }
        LinkedList<Cell> c2 = nextGameBoard(c1.clone());
        int i = 1;
        if (!dec) {
            while (!c1.equals(c2)){
                c2 = nextGameBoard(c2);
                i++;
            }
            tab[0] = i;
            return tab;
        } else {
            int[] d = decale(c1,c2);
            while (d[0] == 0 && d[1] == 0) {
                c2 = nextGameBoard(c2);
                i++;
                d = decale(c1, c2);
            }
            tab[0] = i;
            tab[1] = d[0];
            tab[2] = d[1];
            return tab;
        }
    }

    /**
     * Calcul le nombre de générations avant de rentrer dans une structure périodique.
     * @param liste Liste a partir de laquelle calculer la queue.
     * @param periode Nombre de générations entre deux listes identiques.
     * @param dec Doit valoir true si les générations identiques diffèrent d'une translation.
     * @return La taille de la queue.
     */
    private static int queue(LinkedList<Cell> liste, int periode, boolean dec) {
        LinkedList<Cell> c1 = liste.clone();
        LinkedList<Cell> c2 = liste.clone();
        for (int i = 0; i<periode; i++){
            c2 = nextGameBoard(c2);
        }
        int i = 0;
        if (dec){
            int[] d = decale(c1,c2);
            while (d[0] == 0 && d[1] == 0){
                i++;
            }
            return i;
        } else {
            while (!c1.equals(c2)){
                i++;
            }
            return i;
        }
    }

    /**
     * Lis un fichier .lif de version 1.05 pour utilisation par le programme.
     * @param filename Chemin d'accès du fichier .lif.
     * @return La liste chainée de Cell correspondant au fichier passé en paramètre.
     */
    private static  LinkedList<Cell> loader(String filename) {
        LinkedList<Cell> liste = new LinkedList<Cell>();
        boolean lfl = false; //stand for look for letter
        boolean lfbol = false; //stand for look for block origin ligne
        boolean lfboc = false; //stand for look for block origin colonne
        boolean lfe = false; // stand for look for element
        boolean arc = false; // stand for allow return carriage (permet d'eviter le saut de ligne si 2 \n d'affilÃ©)
        boolean minus = false;
        boolean nfound = false;
        int l = 0; // num de la ligne lors de la recherche d'Ã©lÃ©ment
        int c = 0; // num de la colone lors de la recherche d'Ã©lÃ©ment
        int lo = 0; // ligne d'origine d'un bloc
        int co = 0; // colonne d'origine d'un bloc
        File f = new File(filename);
        try {
            FileInputStream fis = new FileInputStream(f);
            int n = 0;
            while ((n = fis.read()) >= 0) {
                if((char)n == '#'){
                    lfl = true;
                    lfbol = false;
                    lfboc = false;
                    lfe = false;
                    arc = false;
                    l = 0;
                    c = 0;
                    lo = 0;
                    co = 0;
                } else if(lfl){
                    if((char)n == 'P'){
                        lfl = false;
                        lfbol = true;
                    }else{
                        lfl = false;
                    }
                } else if(lfbol){
                    if((char)n == '-'){
                        minus = true;
                    }
                    if((char)n == '0' ||(char)n == '1' ||(char)n == '2' ||(char)n == '3' ||(char)n == '4' ||(char)n == '5' ||(char)n == '6' ||(char)n == '7' ||(char)n == '8' ||(char)n == '9'){
                        lo=10*lo+Integer.parseInt(String.valueOf((char)n));
                        nfound = true;
                    }else if((char)n == ' ' && nfound){
                        nfound = false;
                        lfbol = false;
                        lfboc = true;
                        if (minus) {
                            lo = -lo;
                            minus = false;
                        }
                        l = lo;
                    }
                } else if(lfboc) {
                    if((char)n == '-'){
                        minus = true;
                    }
                    if((char)n == '0' ||(char)n == '1' ||(char)n == '2' ||(char)n == '3' ||(char)n == '4' ||(char)n == '5' ||(char)n == '6' ||(char)n == '7' ||(char)n == '8' ||(char)n == '9'){
                        co=10*co+Integer.parseInt(String.valueOf((char)n));
                    }else if((char)n == '\n'){
                        lfboc = false;
                        lfe = true;
                        if (minus) {
                            co = -co;
                            minus = false;
                        }
                        c = co;
                    }
                } else if (lfe) {
                    if((char)n=='.'){
                        arc = true;
                        c=c+1;
                    }else if((char)n =='*'){
                        arc = true;
                        liste.add(new Cell(l,c));
                        c=c+1;
                    }else if((char)n == '\n'){
                        if (arc){
                            arc=false;
                            c=co;
                            l=l-1;
                        }
                    }
                }
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return liste.sort();
    }

    /**
     * Programme principal.
     * @param Args Lancer le programme avec l'option -h afin d'avoir tout les détails.
     *             Options disponibles : -name/-h/-f/-t/-c/-cf/-ct.
     */
    public static void main(String [] Args) {

        if (Args[0].equals("-name")) {
            System.out.println("Jean-Pacôme Delmas et Romain Villebonnet");
        }

        else if (Args[0].equals("-h")) {
            System.out.println("-h : Affiche cette aide.");
            System.out.println("-name : Affiche les noms et prénoms des auteurs.");
            System.out.println("-s d fichier.lif : Simule des générations du jeu avec leur numéro de génération pendant d.");
            System.out.println("-f d fichier.lif haut bas gauche droite : Comme -s, mais dans un monde fini.");
            System.out.println("-t d fichier.lif haut bas gauche droite : Comme -f, mais dans un monde circulaire (tore).");
            System.out.println("-c max fichier.lif : Calcule le type d'évolution du jeu sans dépasser la duréée max.");
            System.out.println("-cf max fichier.lif haut bas gauche droite : Calcule le type d'évolution du jeu sans dépasser la durée max dans un monde fini.");
            System.out.println("-ct max fichier.lif haut bas gauche droite : Calcule le type d'évolution du jeu sans dépasser la durée max dans un tore.");
            System.out.println("(Pas implémenté) -w max dossier : Comme -c, mais pour tout les fichiers du dossier, les résultats sont sortis sous la forme d'un fichier HTML.");
        }

        else if (Args[0].equals("-s") || Args[0].equals("-f") || Args[0].equals("-t")) {
            if (Args[0].equals("-t")) {
                circular = true;
            } else {
                circular = false;
            }
            if (Args[0].equals("-s")) {
                topTerminal = 0;
                lowTerminal = 0;
                leftTerminal = 0;
                rightTerminal = 0;
            } else {
                topTerminal = Integer.parseInt(Args[3]);
                lowTerminal = Integer.parseInt(Args[4]);
                leftTerminal = Integer.parseInt(Args[5]);
                rightTerminal = Integer.parseInt(Args[6]);
            }
            int d = Integer.parseInt(Args[1]);
            LinkedList<Cell> list = loader(Args[2]);
            generateStep(list, d);
        }

        else if (Args[0].equals("-c") || Args[0].equals("-cf") || Args[0].equals("-ct")) {
            if (Args[0].equals("-ct")) {
                circular = true;
            } else {
                circular = false;
            }
            if (Args[0].equals("-c")) {
                topTerminal = 0;
                lowTerminal = 0;
                leftTerminal = 0;
                rightTerminal = 0;
            } else {
                topTerminal = Integer.parseInt(Args[3]);
                lowTerminal = Integer.parseInt(Args[4]);
                leftTerminal = Integer.parseInt(Args[5]);
                rightTerminal = Integer.parseInt(Args[6]);
            }
            int max = Integer.parseInt(Args[1]);
            LinkedList<Cell> list = loader(Args[2]);
            int ca = calculAsymptotique(list.clone(), max);
            if (ca < 0) {
                System.out.println("Mort au bout de " + (-ca) + " cycles.");
            } else if (ca == 2 * max + 1) {
                System.out.println("Etat inconnu.");
            } else if (ca <= max) {
                System.out.println("Etat périodique au bout de " + ca + " cycles au maximum. (re-spécifié si différent)");
                int[] p = periode(list.clone(), ca, false);
                System.out.println("La période est de " + p[0] + ".");
                if(p[0] != 1) {
                    int qqueue = queue(list.clone(), p[0], false);
                    System.out.println("Il y a " + qqueue + " cycles avant la structure périodique.");
                }
            } else if (ca <= 2 * max) {
                ca = ca - max;
                System.out.println("Etat de decalage au bout de " + ca + " cycles au maximum.");
                int[] p = periode(list.clone(), ca, true);
                System.out.println("La période est de " + p[0] + " avec un décalage de " + p[1] + " lignes et " + p[2] + " colonnes.");
                if(p[0] != 1) {
                    int qqueue = queue(list.clone(), p[0], true);
                    System.out.println("Il y a  "+ qqueue + " cycles avant la structure périodique (en décalage).");
                }
            } else {
                System.out.println("Une erreur c'est produite.");
            }
        }

    }

}