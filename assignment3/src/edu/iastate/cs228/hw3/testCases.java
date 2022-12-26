package edu.iastate.cs228.hw3;


import java.util.AbstractSequentialList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

//import org.w3c.dom.Node;

public class testCases {
public static void main(String []args) {
	StoutList<String> n = new StoutList<String>();
	n.add("A");  
	n.add("G");  //[ a B 1 2 c]
	n.add("H");	
	n.add("2"); 
	n.add("I");	
	//n.add("D");	
//	n.add("E");
	n.add("F");	 


	 ListIterator<String> iter = n.listIterator(n.size());
	 iter.previous();
		System.out.println(n.toStringInternal(iter));

	 iter.remove();
		System.out.println(n.toStringInternal(iter));
//iter.remove();
//System.out.println(n.toStringInternal(iter));

	iter.add("V");
	System.out.println(n.toStringInternal(iter));

	iter.previous();
	System.out.println(n.toStringInternal(iter));

	iter.next();
	System.out.println(n.toStringInternal(iter));

	iter.add("W");
	System.out.println(n.toStringInternal(iter));

	iter.previous();
	System.out.println(n.toStringInternal(iter));

	iter.previous();
	iter.previous();
	iter.previous();
	iter.add("X");
	System.out.println(n.toStringInternal(iter));

	/*
	 * n.add("V"); System.out.println(n.toStringInternal());
	 * 
	 * 
	 * 
	 * ListIterator<String> iter = n.listIterator(n.size()); iter.previous();
	 * System.out.println("num 1 "+n.toStringInternal());
	 * 
	 * iter.next(); System.out.println("num 2 "+n.toStringInternal(iter));
	 * 
	 * iter.add("W"); System.out.println("num 3 "+n.toStringInternal(iter));
	 * 
	 * iter.previous(); System.out.println("num 4 "+n.toStringInternal(iter));
	 * 
	 * iter.previous(); System.out.println("num 5 "+n.toStringInternal(iter));
	 * 
	 * iter.previous(); System.out.println("num 6 "+n.toStringInternal(iter));
	 * 
	 * iter.previous(); System.out.println("num 7 "+n.toStringInternal(iter));
	 * 
	 * iter.add("X"); System.out.println(n.toStringInternal(iter));
	 */
	//iter.set("Y");;

//	System.out.println("index 8: "+iter.previous());
	//System.out.println(n.toStringInternal(iter));

	/*
	 * System.out.println(n.toStringInternal()); n.remove(2);
	 * 
	 * System.out.println(n.toStringInternal()); n.remove(5); n.add("V");
	 * n.add("W"); n.add(2,"X"); n.add(2,"Y");
	 * System.out.println(n.toStringInternal()); n.add(2,"Z"); n.remove(9);
	 * n.remove(3); System.out.println(n.toStringInternal()); n.remove(3);
	 * n.remove(5); System.out.println(n.toStringInternal()); n.remove(3);
	 * System.out.println(n.toStringInternal());
	 * 
	 */

}
}