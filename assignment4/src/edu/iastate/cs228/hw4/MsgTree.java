package edu.iastate.cs228.hw4;

import java.util.Stack;
/**
 * MsgTree that constructs, prints, and decodes 
 * @author Tara Munjuluri
 */


public class MsgTree {
	public char payloadChar;
	public MsgTree left;
	public MsgTree right;
	public Stack<MsgTree> stack;
	public MsgTree parent;
	
	/*
	 * Constructor building the tree from a string
	 */
	public MsgTree(String encodingString) {
		if((encodingString==null)||(encodingString.length()<=1)) {
			return;
		}
			stack = new Stack<>();
			MsgTree node=null;
			MsgTree temp = this; 
			
			 for(int i=encodingString.length()-1;i>=0;i--) {
				 payloadChar=encodingString.charAt(i); //Incrementing the character so rest can be done for each character in the string
				 node=new MsgTree(payloadChar);
				 
				if(payloadChar!='^') {
					stack.push(node);
				}
				else {
					node.left=stack.pop();
					node.right=stack.pop();
				temp.left=node.left;
				temp.right=node.right;
					stack.push(node);

				}
	}
			 }
		
		
		
		
	
	/*Constructor for a single node with null children
	 * 
	 */
	public MsgTree(char payloadChar) {
		this.payloadChar = payloadChar;
		this.left = null;
		this.right = null;
		
		
		
	}
	
	/*
	 * method to print characters and their binary codes
	 */
	public static void printCodes(MsgTree root, String code){

		if(root!=null) {
			if(root.left==null && root.right==null) {
				System.out.println(root.payloadChar+"             "+code);
			}
			else {
				printCodes(root.left,code+"0");
				printCodes(root.right,code+"1");
			}
			
		
	}
	
	}
	/*
	 * Print the decoded message to the console:
	 */
	public void decode(MsgTree codes, String msg)  {
		System.out.println();

		System.out.println("MESSAGE:");

	MsgTree temp=codes;
			while(temp.left!=null && temp.right!=null) {

				for(int i=0;i<msg.length();i++) {
					char current=msg.charAt(i);
					if(current=='0') {
						temp=temp.left;
						
					}
					else if(current=='1') {
						temp=temp.right;
					}
					if(temp.payloadChar!='^') {
						 System.out.print(temp.payloadChar);
						 temp=codes;

					}
					


					}
				break;
					
		}

	
}
}

