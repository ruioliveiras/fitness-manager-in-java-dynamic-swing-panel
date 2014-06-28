package model.activity;




public class Caiaque extends Distance{
    @Override
	public String getName() {
		return "Caiaque";
	}

 

 

	@Override
	public int getIntensidade() {
		return 100;
	}
	
	@Override
	public double getMET() {
		return 5.0;
	}

	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return null;
	}
}
