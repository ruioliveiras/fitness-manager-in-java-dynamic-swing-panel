package model.activity;

import java.util.GregorianCalendar;



public class Danca extends Ludic {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;
    /**
     * Construtor de Copia
     * @param act actividade a copiar
     */
	public Danca(Danca act){
        super(act);
    }
	
    /**
     * Construtor vazio
     */
	public Danca() {
		super();
	}

    /**
     * Contrutor parametrizado
     * @param time duração da actividade em milisegundos
     * @param weather Weather tempo meteorologico da actividade
     * @param hRate pulsasão da actividade
     * @param date data da actividade
     */
	public Danca(long time, Weather weather, int hRate, GregorianCalendar date) {
		super(time, weather, hRate, date);
	}

	@Override
	public String getName() {
		return "Dança";
	}

	@Override
	public int getIntensidade() {
		return 81;
	}
	
	@Override
	public double getMET() {
		return 7.8;
	}

	@Override
	public Danca clone() {
		return new Danca(this);
	}

}
