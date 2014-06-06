package model.activity;

import java.util.GregorianCalendar;



public class Ioga extends Ludic {
	private static final long serialVersionUID = 1L;

    /**
     * Construtor de Copia
     * @param act actividade a copiar
     */
	public Ioga(Ioga act){
        super(act);
    }
    /**
     * Construtor vazio
     */
	public Ioga() {
		super();
	}
    /**
     * Contrutor parametrizado
     * @param time duração da actividade em milisegundos
     * @param weather Weather tempo meteorologico da actividade
     * @param hRate pulsasão da actividade
     * @param date data da actividade
     */
	public Ioga(long time, Weather weather, int hRate, GregorianCalendar date) {
		super(time, weather, hRate, date);
	}

	@Override
	public String getName() {
		return "Ioga";
	}

	@Override
	public int getIntensidade() {
		return 158;
	}
	
	@Override
	public double getMET() {
		return 4.0;
	}

	@Override
	public Ioga clone() {
		return new Ioga(this);
	}

}
