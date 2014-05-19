package controller;

import model.Dataset;

public class Main {

	private static Dataset sDataSet;
	public Dataset getDataSet(){return sDataSet;} 
	
	public static void main(String[] args) {
		System.out.println("Hello World!!");
		sDataSet = new Dataset();
		
		ControllerLogin login = new ControllerLogin();
		login.start();
	}

}
