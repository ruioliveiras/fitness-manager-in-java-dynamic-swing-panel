package model.activity.ludic;

import model.activity.Skill;



public class Ginastica extends Skill {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Ginastica(Ginastica act){
        super(act);
    }
	
	public Ginastica() {
		super();
	}

	public Ginastica(Skill act) {
		super(act);
	}

	@Override
	public String getName() {
		return "Ginástica Olímpica";
	}

 
	@Override
	public int getIntensidade() {
		return 166;
	}
	
	@Override
	public String getPointName() {
		return "Pontos";
	}
	
	@Override
	public double getMET() {
		return 3.8;
	}

	@Override
	public Ginastica clone() {
		return new Ginastica(this);
	}

}
