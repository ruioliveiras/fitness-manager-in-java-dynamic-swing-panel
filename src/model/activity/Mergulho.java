package model.activity;

import java.util.GregorianCalendar;



public class Mergulho extends Ludic {
	private static final long serialVersionUID = 1L;

    /**
     * Construtor de Copia
     * @param act actividade a copiar
     */
	public Mergulho(Mergulho act){
        super(act);
    }
    /**
     * Construtor vazio
     */
	public Mergulho() {
		super();
	}
    /**
     * Contrutor parametrizado
     * @param time duração da actividade em milisegundos
     * @param weather Weather tempo meteorologico da actividade
     * @param hRate pulsasão da actividade
     * @param date data da actividade
     */
	public Mergulho(long time, Weather weather, int hRate,
			GregorianCalendar date) {
		super(time, weather, hRate, date);
	}

	@Override
	public String getName() {
		return "Mergulho";
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
	public Mergulho clone() {
		return new Mergulho(this);
	}
}
