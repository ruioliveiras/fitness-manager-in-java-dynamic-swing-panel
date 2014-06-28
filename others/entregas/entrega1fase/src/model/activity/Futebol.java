package model.activity;



public class Futebol extends Collective {

	@Override
	public String getName() {
		return "Futebol";
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
		return 7.0;
	}

	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
