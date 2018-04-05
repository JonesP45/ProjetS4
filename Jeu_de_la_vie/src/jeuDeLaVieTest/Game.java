package jeuDeLaVieTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Game {

    public static void print(LinkedList<Cell> list) {
        int maxTop = -1000000, maxLow = 1000000, maxLeft = 1000000, maxRight = -1000000;
        Link<Cell> tmp = list.getHead();
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

    public static int stateCell(LinkedList<Cell> gameBoard, int x, int y){    //renvoi l'état d'une Cellule sous forme d'entier, 1 si elle est vivante, 0 si elle est morte
        if (gameBoard.contains(new Cell(x, y)))
            return 1;
        return 0;
    }

    public static int neighbors(LinkedList<Cell> gameBoard, int x, int y){    //Méthode calculant le nombre de vosins vivant d'une Cellule morte ou vivante et retourne ce nombre.
        int nb = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (!(i == x && j == y))
                    nb += stateCell(gameBoard, i, j);
            }
        }
        return nb;
    }

    public static LinkedList<Cell> neighborsLL(LinkedList<Cell> gameBoard, int x, int y){    // Stocke les Cells mortes dans une liste chainee
        LinkedList<Cell> neighbors = new LinkedList<Cell>();
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                Cell tmp = new Cell(i, j);
                if (!(gameBoard.contains(tmp)) && !tmp.equals(new Cell(x, y)))
                    neighbors.add(tmp);
            }
        }
        return neighbors.mergeSort();
    }

    public static LinkedList<Cell> nextGameBoard(LinkedList<Cell> gameBoard) {  //calcule l'étape n+1 du jeu de la vie
        LinkedList<Cell> nextGameBoardList = gameBoard.clone();
        LinkedList<Cell> newCellsList = new LinkedList<Cell>(); //On stocke toutes les nouvelles Cells mortes à vivantes pour ne pas les "ajouter deux fois".
        Link<Cell> tmpLink = gameBoard.getHead();
        while (tmpLink != null) {
            int x = tmpLink.getElement().getRow();
            int y = tmpLink.getElement().getColumn();
            int neighbors = neighbors(gameBoard, x, y);
            if (!(neighbors == 3 || neighbors == 2)) {
                nextGameBoardList.remove(new Cell(x, y)); //On supprime les cell vivantes qui meurent
            }
            LinkedList<Cell> neighborsList = neighborsLL(gameBoard, x, y);
            Link<Cell> tmp = neighborsList.getHead();
            while (tmp != null) {
                int x1 = tmp.getElement().getRow();
                int y1 = tmp.getElement().getColumn();
                if (neighbors(gameBoard, x1, y1) == 3 && !(newCellsList.contains(new Cell(x1,y1)))) {
                    nextGameBoardList.put(new Cell(x1, y1));
                    newCellsList.put(new Cell(x1, y1));
                }
                tmp = tmp.getNext();
            }
            tmpLink = tmpLink.getNext();
        }
        return nextGameBoardList;
    }

    public static LinkedList<Cell> generateStep(LinkedList<Cell> gameBoard, int nbStep) {
        LinkedList<Cell> nextGameBoardList = gameBoard.clone();
        while (nbStep > 0) {
            nextGameBoardList = nextGameBoard(nextGameBoardList);
            print(nextGameBoardList);
            nbStep--;
        }
        return nextGameBoardList;
    }

    public static LinkedList<Cell> generateList(int nbLink) {
        LinkedList<Cell> gameBoard = new LinkedList<Cell>();
        while (nbLink > 0) {
            gameBoard.add(new Cell((int) (Math.random() * 100), (int) (Math.random() * 100)));
            nbLink--;
        }
        return gameBoard.mergeSort();
    }

    //retourne la taille de la queue.
    public static int calculAsymptotique(LinkedList<Cell> liste, int max) {
        LinkedList<Cell> c1 = liste.clone();
        LinkedList<Cell> c2 = liste.clone();
        LinkedList<Cell> c3 = liste.clone();
        int i = 0;

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
                if (c1.equals(c2) || c1.equals(c3)) {
                    return i;//periodique (possiblement stable)
                } else {
                    int[] d = decale(c1,c2);
                    if (!(d[0] == 0 && d[1] == 0)) {
                        return max + i;//decale
                    }
                }
            }
        }
        return 2 * max + 1;//inconnu
    }

    public static int[] decale(LinkedList<Cell> c1, LinkedList<Cell> c2) {
        int[] tab = {0, 0};
        Link<Cell> m1 = c1.getHead();
        Link<Cell> m2 = c2.getHead();
        int decaleX = m1.getElement().getRow() - m2.getElement().getRow();
        int decaleY = m1.getElement().getColumn() - m2.getElement().getColumn();
        while (m1 != null && m2 != null) {
            if (m1.getElement().getRow() - m2.getElement().getRow() != decaleX || m1.getElement().getColumn() - m2.getElement().getColumn() != decaleY) {
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

    public static int[] periode(LinkedList<Cell> liste, int queue, boolean dec) {
        int[] tab = {0, 0, 0};
        LinkedList<Cell> c1 = liste.clone();
        for (int i = 0; i < queue; i++) {
            c1 = nextGameBoard(c1);
        }
        LinkedList<Cell> c2 = nextGameBoard(c1.clone());
        int i = 0;
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

    public static  LinkedList<Cell> loader(String filename) {
        LinkedList<Cell> liste = new LinkedList<Cell>();
        boolean lfl = false; //stand for look for letter
        boolean lfbol = false; //stand for look for block origin ligne
        boolean lfboc = false; //stand for look for block origin colonne
        boolean lfe = false; // stand for look for element
        boolean arc = false; // stand for allow return carriage (permet d'eviter le saut de ligne si 2 \n d'affilÃ©)
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
                    if((char)n == '0' ||(char)n == '1' ||(char)n == '2' ||(char)n == '3' ||(char)n == '4' ||(char)n == '5' ||(char)n == '6' ||(char)n == '7' ||(char)n == '8' ||(char)n == '9'){
                        lo=10*lo+Integer.parseInt(String.valueOf((char)n));
                    }else if((char)n == ' '){
                        lfbol = false;
                        lfboc = true;
                        l = lo;
                    }
                } else if(lfboc) {
                    if((char)n == '0' ||(char)n == '1' ||(char)n == '2' ||(char)n == '3' ||(char)n == '4' ||(char)n == '5' ||(char)n == '6' ||(char)n == '7' ||(char)n == '8' ||(char)n == '9'){
                        co=10*co+Integer.parseInt(String.valueOf((char)n));
                    }else if((char)n == '\n'){
                        lfboc = false;
                        lfe = true;
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
                            l=l+1;
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
        return liste.mergeSort();
    }

    public static void main(String [] args) {
//        long start = System.currentTimeMillis();
//        LinkedList<Cell> list = generateList(3000);
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
        //print(list);
        LinkedList<Cell> list = loader("C:\\Users\\Bonjour\\Documents\\Cours\\Jeu_de_la_vie\\src\\jeuDeLaVieTest\\vaisseau.lif");
        long start = System.currentTimeMillis();
        LinkedList<Cell> l2 = generateStep(list, 1);
        long end = System.currentTimeMillis();
        System.out.println("time: " + (end - start));
        int max = 10;
        int ca = calculAsymptotique(list, max);
        if (ca<0) {
            System.out.println("Mort au bout de " + ca + " cycles.");
        } else if (ca == 2*max+1){
            System.out.println("Etat inconnu de la mort");
        } else if (ca <=max){
            System.out.println("Etat périodique au bout de " + ca + "cycles.");
            int[] p = periode(list, ca, false);
            System.out.println("La période est de " + p[0] + ".");
        } else if (ca <= 2* max){
            System.out.println("Etat de decalage au bout de " + ca + "cycles.");
            int[] p = periode(list, ca, false);
            System.out.println("La période est de " + p[0] + " avec un décalage de " + p[1] + " " + p[2] + ".");
        } else {
            System.out.println("wut !!!");
        }
    }

}
