package com.poker.emun;

//private String[] numbers = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace" };
//"two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "jack", "queen", "king", "ace"
public enum NumberType {
	two(2), three(3), four(4), five(5), six(6), seven(7), eight(8), nine(9), ten(10), jack(11), queen(12), king(13),
	ace(14);

	public final int number;
	NumberType(int number) {
		this.number = number;
	}
	
	public int getNumber() {
		return number;
	}
}
 