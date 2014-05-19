package model.activity;

import model.user.*;


/**
 * Write a description of class Teste here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Teste
{
    public static void main(String[] args) {
        Activity a = new WindSurf();
        User u = new User();
        
        a.setDuration(1*1000*3600);//1h
        a.setHeartRate(160);
        
        u.setAltura(180);
        u.setPeso(95);
        u.setDataNascimento(1982, 9, 3);
        u.setGenero(Genero.Masculino);
        u.setFreqCardio(60);
        
        System.out.println(a.calculateCalories(u));
        
       
    }
}
