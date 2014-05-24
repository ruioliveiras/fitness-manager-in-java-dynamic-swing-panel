package model.activity;




public class Remo extends Distance{
    @Override
	public String getName() {
		return "Remo";
	}

 

 

	@Override
	public int getIntensidade() {
		return 100;
	}
	
	@Override
	public double getMET() {
		return 5.8;
	}

	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return null;
	}
}
