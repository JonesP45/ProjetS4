package jeuDeLaVieTest;

public class Test {

	public static void main(String[] args) {
		LinkedList<Cell> list = new LinkedList<Cell>();

		Cell c1 = new Cell(4,7);
		Cell c2 = new Cell(3,8);
		Cell c3 = new Cell(4,6);
		Cell c4 = new Cell(5,9);
		Cell c5 = new Cell(1,2);
		Cell c6 = new Cell(8,10);
		Cell c7 = new Cell(-3,4);
		Cell c8 = new Cell(-1,-2);

		Cell c10 = new Cell(1,3);
		Cell c11 = new Cell(1,5);
		Cell c12 = new Cell(2,5);
		Cell c13 = new Cell(2,7);
		Cell c14 = new Cell(4,10);
		Cell c15 = new Cell(4,15);
		Cell c16 = new Cell(10,20);

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
		System.out.println();
		list = list.mergeSort();
		System.out.println(list);
		Game.print(list);
		System.out.println();
	}

}
