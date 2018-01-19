package com.hacker.rank.practice.session;

public class FizzBuzz {
	public static void main(String[] args) {
		new FizzBuzz().print();

	}

	/**
	 * This function prints number from 1 to 100 on new line
	 * If number is divisible by 3 then prints "Fizz" instead of number
	 * If number is divisible by 5 then prints "Buzz" instead of number
	 * If number is divisible by 3 and 5 then prints "FizzBuzz" instead of number 
	 */
	public void print() {
		String str = "";
		for (int i = 1; i <= 100; i++) {
			str = "" + i;
			//if number is divisible by 15 then it must be divisible by 3 and 5
			if (i % 15 == 0) {
				str = "FizzBuzz";
			} else if (i % 3 == 0) {
				str = "Fizz";
			} else if (i % 5 == 0) {
				str = "Buzz";
			}
			System.out.println(str);
		}
	}// end of function
}// end of class
