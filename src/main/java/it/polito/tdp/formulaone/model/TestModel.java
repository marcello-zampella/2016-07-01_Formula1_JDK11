package it.polito.tdp.formulaone.model;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		for(int i=0;i<5;i++) {
			for(int j=0;j<7;j++) {
				if(j==6) {
					break;
				}
				System.out.println(i+" "+j);
			}
		}
	}

}
