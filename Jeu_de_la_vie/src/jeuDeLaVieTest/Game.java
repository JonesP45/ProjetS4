package jeuDeLaVieTest;

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
                    neighbors.put(tmp);
            }
        }
        return neighbors;
    }

    public static LinkedList<Cell> nextGameBoard(LinkedList<Cell> gameBoard) {  //calcule l'étape n+1 du jeu de la vie
        LinkedList<Cell> nextGameBoardList = gameBoard.clone();
        LinkedList<Cell> newCellsList = new LinkedList<Cell>();    //On stocke toutes les nouvelles Cells mortes à vivantes pour ne pas les "ajouter deux fois".
        Link<Cell> tmpLink = gameBoard.getHead();
        while (tmpLink != null) {
            int x = tmpLink.getElement().getRow();
            int y = tmpLink.getElement().getColumn();
            int neighbors = neighbors(gameBoard, x, y);
            if (!(neighbors == 3 || neighbors == 2)) {
                nextGameBoardList.remove(new Cell(x, y));
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

    public static void main(String [] args) {
        LinkedList<Cell> list = generateList(500);
        print(list);
        LinkedList<Cell> l2 = generateStep(list, 10);
    }

}
