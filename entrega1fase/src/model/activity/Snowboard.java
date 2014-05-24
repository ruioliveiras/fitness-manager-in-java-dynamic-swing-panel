package model.activity;



public class Snowboard extends Skill {

	@Override
	public String getName() {
		return "Snowboard";
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
		return 5.3;
	}

	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
