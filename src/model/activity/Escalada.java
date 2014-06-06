package model.activity;

import java.util.GregorianCalendar;



public class Escalada extends Ludic {
	private static final long serialVersionUID = 1L;

    /**
     * Construtor de Copia
     * @param act actividade a copiar
     */
	public Escalada(Escalada act){
        super(act);
    }
    /**
     * Construtor vazio
     */
	public Escalada() {
		super();
	}

    /**
     * Contrutor parametrizado
     * @param time duração da actividade em milisegundos
     * @param weather Weather tempo meteorologico da actividade
     * @param hRate pulsasão da actividade
     * @param date data da actividade
     */
	public Escalada(long time, Weather weather, int hRate,
			GregorianCalendar date) {
		super(time, weather, hRate, date);
	}

	@Override
	public String getName() {
		return "Escalada";
	}

	@Override
	public int getIntensidade() {
		return 79;
	}

	@Override
	public double getMET() {
		return 8.0;
	}

	@Override
	public Escalada clone() {
		return new Escalada(this);
	}
}
