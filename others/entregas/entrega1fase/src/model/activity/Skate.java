package model.activity;



public class Skate extends Skill {

	@Override
	public String getName() {
		return "Skate";
	}

 

 

	@Override
	public int getIntensidade() {
		return 100;
	}
	
	@Override
	public String getPointName() {
		// TODO Auto-generated method stub
		return null;
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
