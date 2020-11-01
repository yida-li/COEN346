/**
 * Class BlockStack Implements character block stack and operations upon it.
 *
 * $Revision: 1.4 $ $Last Revision Date: 2019/07/02 $
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca; Inspired by an earlier
 *         code by Prof. D. Probst
 * 
 */

package task2;

import task2.common.BaseThread;
import task2.common.Semaphore;

class BlockStack {
	/**
	 * # of letters in the English alphabet + 2
	 */
	public static final int MAX_SIZE = 28;

	/**
	 * Default stack size
	 */
	public static final int DEFAULT_SIZE = 6;

	/**
	 * Current size of the stack
	 */
	private int iSize = DEFAULT_SIZE;

	/**
	 * Current top of the stack
	 */
	private int iTop = 3;

	/**
	 * stack[0:5] with four defined values
	 */
	public char acStack[] = new char[] { 'a', 'b', 'c', 'd', '$', '$' };

	/**
	 * Default constructor
	 */
	public BlockStack() {
	}

	/**
	 * Stack access counter
	 */
	private int counter = 0;

	/**
	 * Supplied size
	 */
	public BlockStack(final int piSize) {

		if (piSize != DEFAULT_SIZE) {
			this.acStack = new char[piSize];

			// Fill in with letters of the alphabet and keep
			// 2 free blocks
			for (int i = 0; i < piSize - 2; i++)
				this.acStack[i] = (char) ('a' + i);

			this.acStack[piSize - 2] = this.acStack[piSize - 1] = '$';

			this.iTop = piSize - 3;
			this.iSize = piSize;
		}
	}

	/**
	 * Picks a value from the top without modifying the stack
	 * 
	 * @return top element of the stack, char
	 */
	public char pick() {
		counter++;
		return this.acStack[this.iTop];
	}

	/**
	 * Returns arbitrary value from the stack array
	 * 
	 * @return the element, char
	 */
	public char getAt(final int piPosition) {
		counter++;
		return this.acStack[piPosition];
	}

	/**
	 * Standard push operation
	 */
	public void push(final char pcBlock) {

		if (isEmpty()) { // when the stack is empty

			this.acStack[++this.iTop] = 'a'; // Calling push() on empty stack should place an ‘a’ on top
		} else {

			this.acStack[++this.iTop] = pcBlock;
		}
		counter++;
		System.out.println("Successful push!");
	}

	/**
	 * Standard pop operation
	 * 
	 * @return ex-top element of the stack, char
	 */
	public char pop() {
		counter++;
		System.out.println("Successful pop!");
		char cBlock = this.acStack[this.iTop];
		this.acStack[this.iTop--] = '*'; // Print ‘*’ instead of ‘$’ for each empty position in the stack
		return cBlock;
	}

	/**
	 * Standard check operation
	 * 
	 * @return true if stack is empty
	 */
	public boolean isEmpty() {

		return (this.iTop == -1);
	}

	/**
	 * getter method to fectch private variable iSize
	 * 
	 * @return Current size of the stack
	 */
	public int getISize() {

		return iSize;
	}

	/**
	 * getter method to fectch private variable iTop
	 * 
	 * @return Current top of the stack
	 */
	public int getITop() {

		return iTop;
	}

	/**
	 * getter method to fectch private stack access counter
	 * 
	 * @return the stack access counter
	 */
	public int getAccessCounter() {

		return counter;
	}

}

// EOF
