package org.beans;

public class math {
	int number;
	public math(){}
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public void doubleNumber(){
		number *= 2;
	}
}
