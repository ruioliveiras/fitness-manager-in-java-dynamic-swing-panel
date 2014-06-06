package model.activity;

import java.util.GregorianCalendar;



public class Pilates extends Ludic {
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;

	
    /**
     * Construtor de Copia
     * @param act actividade a copiar
     */
	public Pilates(Pilates act){
        super(act);
    }
    
    /**
     * Construtor vazio
     */
	public Pilates() {
		super();
	}

    /**
     * Contrutor parametrizado
     * @param time duração da actividade em milisegundos
     * @param weather Weather tempo meteorologico da actividade
     * @param hRate pulsasão da actividade
     * @param date data da actividade
     */
	public Pilates(long time, Weather weather, int hRate, GregorianCalendar date) {
		super(time, weather, hRate, date);
	}

	@Override
	public String getName() {
		return "Pilates";
	}

	@Override
	public int getIntensidade() {
		return 210;
	}

	@Override
	public double getMET() {
		return 3.0;
	}

	@Override
	public Pilates clone(){
		return new Pilates(this);
	}

	
}
