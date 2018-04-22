package jeuDeLaVieTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Game {

    private static boolean circular = true;
    private static int topTerminal = 10;
    private static int lowTerminal = -11;
    private static int leftTerminal = -10;
    private static int rightTerminal = 11;

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
        System.out.println();
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

    private static boolean arrayIsEmpty(LinkedList<Cell>[] tab) {
        for (int i = 1; i < tab.length; i++) {
            if (!tab[i].isEmpty())
                return false;
        }
        return true;
    }

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

    private static LinkedList<Cell> generateStep(LinkedList<Cell> gameBoard, int nbStep) {
        LinkedList<Cell> list = gameBoard.clone();
        int time = 0;
        int i;
        for (i = 0; i < nbStep; i++) {
            System.out.println(i);
            long start = System.currentTimeMillis();
            list = nextGameBoard(list);
            long end = System.currentTimeMillis();
            print(list);
            time += (end - start);
        }
        System.out.println("time = " + time);
        return list;
    }

    //retourne la taille de la queue.
    private static int calculAsymptotique(LinkedList<Cell> liste, int max) {
        LinkedList<Cell> c1 = liste.clone();
        LinkedList<Cell> c2 = liste.clone();
        LinkedList<Cell> c3;
        int i = 0;
        boolean first = true;

        while (i<=max) {
            if (c1.isEmpty()) {
                return -i;//mort
            } else if (c2.isEmpty()) {
                return -2*i;//mort
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

    private static int queue(LinkedList<Cell> liste, int periode, boolean dec){
        LinkedList<Cell> c1 = liste.clone();
        LinkedList<Cell> c2 = liste.clone();
        for (int i = 0; i<periode; i++){
            c2 = nextGameBoard(c2);
        }
        int i =0;
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
//                        System.out.println(lo);
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

    public static void main(String [] args) {
        long start, end;
        start = System.currentTimeMillis();
        LinkedList<Cell> list = loader("C:\\Users\\Bonjour\\Documents\\Cours\\Jeu_de_la_vie\\src\\jeuDeLaVieTest\\vaisseau.lif");
        end = System.currentTimeMillis();
        System.out.println("load: " + (end - start));
        start = System.currentTimeMillis();
        print(list);
        end = System.currentTimeMillis();
        System.out.println("print list: " + (end - start));
        System.out.println(list);

        list = generateStep(list, 20);
        System.out.println(list);
//        print(list);

        int max = 10;
        int ca = calculAsymptotique(list, max);
        if (ca<0) {
            System.out.println("Mort au bout de " + (-ca) + " cycles.");
        } else if (ca == 2*max+1){
            System.out.println("Etat inconnu de la mort");
        } else if (ca <=max){
            System.out.println("Etat périodique au bout de " + ca + " cycles au maximum.");
            int[] p = periode(list, ca, false);
            System.out.println("La période est de " + p[0] + ".");
            if(p[0]!=1) {
                int qqueue = queue(list, p[0], false);
                System.out.println("Il y a " + qqueue + " cycles avant la structure périodique.");
            }
        } else if (ca <= 2* max){
            ca = ca-max;
            System.out.println("Etat de decalage au bout de " + ca + " cycles au maximum.");
            int[] p = periode(list, ca, true);
            System.out.println("La période est de " + p[0] + " avec un décalage de " + p[1] + " lignes et " + p[2] + " colonnes.");
            if(p[0]!=1) {
                int qqueue = queue(list, p[0], true);
                System.out.println("Il y a  "+ qqueue + " cycles avant la structure périodique (en décalage).");
            }
        } else {
            System.out.println("wut !!!");
        }
    }

}
