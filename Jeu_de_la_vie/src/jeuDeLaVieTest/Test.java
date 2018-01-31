package jeuDeLaVieTest;

public class Test {

	public static void main(String[] args) {
		ListeChainee list = new ListeChainee();
		Cellule c1 = new Cellule(4,7);
		Cellule c2 = new Cellule(3,8);
		Cellule c3 = new Cellule(4,6);
		Cellule c4 = new Cellule(5,9);
		Cellule c5 = new Cellule(1,2);
		Cellule c6 = new Cellule(8,10);
		Cellule c7 = new Cellule(-3,4);
		Cellule c8 = new Cellule(-1,-2);
		list.add(c1);
		list.add(c2);
		list.add(c8);
		list.add(c4);
		list.add(c5);
		list.add(c6);
		list.add(c7);
		list.add(c3);
		System.out.println(list.toString());
		System.out.println("taille: " + list.size());
		System.out.println();
		System.out.println(list.cloneBis(2,5));
		//list = list.sort();
		System.out.println("list triée: " + list.toString());
	}
	
}
