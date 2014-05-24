package core;
import model.activity.Activity;
import model.user.User;

import java.lang.Math;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Write a description of class FormaCalculation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FormaCalculation{
    static int DIAS_RELEVANTES = 24;
    static double TAXA = 0.05;
    static double MAX_FORMA = 1.0;
    static double MIN_FORMA = 0.1;
    
    private double calculaPesoPonderadoInicial(){
        int i=0;
        double coef = 0.0;
        
        for(i=0;i<DIAS_RELEVANTES;i++){
            coef += Math.pow((1+TAXA),i);
        }
        
        return MAX_FORMA/coef;
    }
    
    public double getForma(User u){
        double formaFinal = 0.0;
        GregorianCalendar hoje = new GregorianCalendar();
        GregorianCalendar diaMaisAfastado = new GregorianCalendar();
        diaMaisAfastado.add(GregorianCalendar.DAY_OF_MONTH, -DIAS_RELEVANTES);
        
        TreeSet<Activity> actividades = u.actividadesEntre(diaMiasAfastado, hoje);
        Iterator<Activity> it = actividades.iterator();
        
        double pesoPonderado = calculaPesoPonderadoInicial();
        
        while(it.hasNext()){
            Activity actActual = it.next();
            formaFinal += (act.Actual.getDuration_inMinutes()/actActual.getIntensidade()) * pesoPonderado;
            pesoPonderado *= (1+TAXA);
        }
        
        if(formaFinal > MAX_FORMA){formaFinal = MAX_FORMA;}
        if(formaFinal < MIN_FORMA){formaFinal = MIN_FORMA;}
        
        return formaFinal;
    }
    
}
