package model.activity;



public class SkiTT extends Altimetry {

	@Override
	public String getName() {
		return "Ski de fundo";
	}

 

 

	@Override
	public int getIntensidade() {
		return 100;
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
