package model.activity;



public class Tenis extends Individual {

	@Override
	public String getName() {
		return "Tenis";
	}

 

 

	@Override
	public int getIntensidade() {
		return 100;
	}

	@Override
	public String getPointName() {
		return "Pontos";
	}
	
	@Override
	public double getMET() {
		return 7.3;
	}

	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
