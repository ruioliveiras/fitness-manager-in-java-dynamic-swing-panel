package model.activity.altimerty;

import model.activity.Altimetry;



public class SkiTT extends Altimetry {
	private static final long serialVersionUID = 1L;

	public SkiTT(SkiTT act){
        super(act);
    }
	
	@Override
	public String getName() {
		return "Ski de fundo";
	}

	@Override
	public int getIntensidade() {
		return 90;
	}
	
	@Override
	public double getMET() {
		return 7.0;
	}

	@Override
	public SkiTT clone() {
		return new SkiTT(this);
	}

}
