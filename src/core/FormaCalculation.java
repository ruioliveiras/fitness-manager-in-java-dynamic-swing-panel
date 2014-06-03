package core;
import model.activity.Activity;
import model.user.User;
import java.lang.Math;
import java.util.*;

/**
 * Classe de calculo da forma de um utilizador.
 */
public class FormaCalculation{
    /*Dias relevantes para calculo da forma*/
    static int DIAS_RELEVANTES = 24;
    /*Taxa de decrescimo da influencia na forma ao se andar 1 dia para tras.
       TAXA = 0.05 significa que o dia de ontem vale menos 5% que o de hoje para a forma e assim sucessivamente.*/
    static double TAXA = 0.05;
    /*Valores maximos e minimos que a forma pode tomar*/
    static double MAX_FORMA = 1.0;
    static double MIN_FORMA = 0.1;
    
    /*Calcula a forma de um user.*/
    public static double calculaForma(User u){
        double formaFinal = 0.0;
        GregorianCalendar hoje = new GregorianCalendar();
        GregorianCalendar diaMaisAfastado = new GregorianCalendar();
        diaMaisAfastado.add(GregorianCalendar.DAY_OF_MONTH, -DIAS_RELEVANTES);/*"-" antes de DIAS_RELEVANTES indica andar DIAS_RELEVANTES para tras*/
        
        /*Iterador para percorrer as actividades feitas entre os dias relevantes para a forma*/
        Set<Activity> actividades = u.actividadesEntre(diaMaisAfastado, hoje);
        Iterator<Activity> it = actividades.iterator();
        
        double pesoPonderado = calculaPesoPonderadoInicial();
        
        /*Para cada actividade feita nos dias que interessam para a forma, calcular o seu peso para a forma total e somar.*/
        while(it.hasNext()){
            Activity actActual = it.next();
            formaFinal += (actActual.getDuration_inMinutes()/actActual.getIntensidade()) * pesoPonderado;
            pesoPonderado *= (1+TAXA);
        }
        
        /*Artefacto para garantir que o valor dado para a forma se encontra dentro dos limites estabelecidos.*/
        if(formaFinal > MAX_FORMA){formaFinal = MAX_FORMA;}
        if(formaFinal < MIN_FORMA){formaFinal = MIN_FORMA;}
        
        return formaFinal;
    }
    
    /*Calcula a influencia para a forma do dia mais afastado.
       Se DIAS_RELEVANTES = 24 calcula o peso para a forma do dia que fica a 24 dias atras.*/
    private static double calculaPesoPonderadoInicial(){
        int i=0;
        double coef = 0.0;
        
        for(i=0;i<DIAS_RELEVANTES;i++){
            coef += Math.pow((1+TAXA),i);
        }
        
        return MAX_FORMA/coef;
    }
    
}
