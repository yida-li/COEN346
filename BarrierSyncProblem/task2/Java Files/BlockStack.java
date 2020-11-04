/**
 * Class BlockStack Implements character block stack and operations upon it.
 *
 * $Revision: 1.4 $ $Last Revision Date: 2019/07/02 $
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca; Inspired by an earlier
 *         code by Prof. D. Probst
 * 
 */
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
	 * Current stack access counter
	 */
	private int stackCounter = 0;

	/**
	 * stack[0:5] with four defined values
	 */
	public char acStack[] = new char[] { 'a', 'b', 'c', 'd', '$', '$' };

	/**
	 * private stack[0:5] with four defined values
	 * 
	 * private char acStack[] = new char[] { 'a', 'b', 'c', 'd', '$', '$' };
	 * 
	 * 
	 * 
	 * public char getStack(){
	 * 
	 * return acStack;
	 * 
	 * }
	 */

	/**
	 * Default constructor
	 */
	public BlockStack() {
	}

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
		stackCounter++;
		return this.acStack[this.iTop];
	}

	/**
	 * Returns arbitrary value from the stack array
	 * 
	 * @return the element, char
	 */
	public char getAt(final int piPosition) {
		stackCounter++;
		return this.acStack[piPosition];
	}

	/**
	 * Standard push operation
	 */
	public void push(final char pcBlock) {
		stackCounter++;
		if (isEmpty()) {
			System.out.println("Stack is empty, putting a on top");
			this.acStack[++this.iTop] = 'a';
		} else {
			System.out.println("Sucessful push");
			this.acStack[++this.iTop] = pcBlock;
		}

	}

	/**
	 * Standard pop operation
	 * 
	 * @return ex-top element of the stack, char
	 */
	public char pop() {
		stackCounter++;
		System.out.println("Sucessful pop");
		char cBlock = this.acStack[this.iTop];
		this.acStack[this.iTop--] = '*'; // Leave prev. value undefined
		return cBlock;
	}

	/**
	 * The method to get iTop is to called getITop();
	 * 
	 * 
	 */
	public int getITop() {
		return iTop;

	}

	/**
	 * The method to get iSize is called getISize();
	 * 
	 * 
	 */
	public int getISize() {
		return iSize;

	}

	/**
	 * The method to get iSize is called getISize();
	 * 
	 * 
	 */
	public int getAccessCounter() {
		return stackCounter;

	}

	/**
	 * 
	 * 
	 * @return return true if the stack is emp
	 */
	public boolean isEmpty() {
		return (this.iTop == -1);
	}

}

// EOF
