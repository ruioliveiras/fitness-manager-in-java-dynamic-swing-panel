package core;

import model.Activity;
import model.User;
import model.objectEnum.Genero;

public class CaloriesCalculation {
    /**
     * Calories burn calculation - based on HR *******ATENTION - NEEDS TESTING*****
     * @param u user from User classe, namely {gender, weight, height, age, at rest heart rate}
     * @return net calories burn [kcal]
     */
    static private int calcCaloriesHR(Activity a, User u){
        double ncb, gcb, bmr, rmrcb;
        double hrm = 208.0 - 0.7*((double) u.getIdade());
        double vo2m = 15.3 * hrm / ((double) u.getFreqCardio());
        if(u.getGenero() == Genero.Masculino){
            gcb = ((-95.7735 + (0.634 * (double) a.getHeartRate()) + (0.404 * vo2m) + (0.394 * (double) u.getPeso()) + (0.271 * (double) u.getIdade()))/4.184) * 60 * a.getDuration_inHours();
            bmr = (13.75 * (double) u.getPeso()) + (5 * (double) u.getAltura()) - (6.76 * (double) u.getIdade()) + 66;
            rmrcb = bmr * 1.1 / 24 * a.getDuration_inHours();
            ncb = gcb - rmrcb;
        }
        else if(u.getGenero() == Genero.Feminino) {
            gcb = ((-59.3954 + (0.45 * (double) a.getHeartRate()) + (0.38 * (double) vo2m) + (0.103 * (double) u.getPeso()) + (0.274 * (double) u.getIdade()))/4.184) * 60 * a.getDuration_inHours();
            bmr = (9.56 * (double) u.getPeso()) + (1.85 * (double) u.getAltura()) - (4.68 * (double) u.getIdade()) + 655;
            rmrcb = bmr * 1.1 / 24 * a.getDuration_inHours();
            ncb = gcb - rmrcb; 
        }
        else ncb = 0;
        
        return (int) ncb;
    }
    
    /**
     * Calories burn calculation - based on MET *******ATENTION - NEEDS TESTING*****
     * @param u user from User classe, namely {gender, weight, height, age}
     * @return calories burn [kcal]
     */
    static private int calcCaloriesMET(Activity a, User u){
        double cb, cmet, rmr;
        if(u.getGenero() == Genero.Masculino){
            rmr = (66.473 + 5.0033 * (double) u.getAltura() + 13.7516 * (double) u.getPeso() - 6.755 * (double) u.getIdade()) / (1440 * 5 * (double) u.getPeso()) * 1000;
            cmet = a.getMET() * 3.5 / rmr;
            cb = cmet * (double) u.getPeso() * a.getDuration_inHours();
        }
        else if(u.getGenero() == Genero.Feminino) {
            rmr = (655.0955 + 1.8496 * (double) u.getAltura() + 9.5634 * (double) u.getPeso() - 4.6756 * (double) u.getIdade()) / (1440 * 5 * (double) u.getPeso()) * 1000;
            cmet = a.getMET() * 3.5 / rmr;
            cb = cmet * (double) u.getPeso() * a.getDuration_inHours();
        }
        else cb = 0;
        
        return (int) cb;
    }
    
    /**
     * Calories burn calculation - based on MET or HR *******ATENTION - NEEDS TESTING*****
     * @param u user from User classe, namely {gender, weight, height, age, hrr}
     * @return calories burn [kcal]
     */
    static public int calcCalories(Activity a, User u){
        int cb = 0;
        
        if(u.getGenero() != Genero.Desconhecido && u.getPeso() != 0 && u.getAltura() != 0 && u.getIdade() != 0) {
           if(u.getFreqCardio() != 0) cb = calcCaloriesHR(a,u); 
           else if(a.getMET() != 0) cb = calcCaloriesMET(a,u); 
           else cb = 0;
        }
            
        return cb;
    }
}
