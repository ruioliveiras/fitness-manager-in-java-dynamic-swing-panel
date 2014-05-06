    /**
     * Calories burn calculation - based on HR *******ATENTION - NEEDS TESTING*****
     * @param g gender
     * @param w weight [kg]
     * @param h height [cm]
     * @param a age [years]
     * @param hrr heart rate at rest [1/min]
     * @return net calories burn [kcal]
     */
    private int calcCaloriesHR(Genero g, int w, int h, int a, int hrr){
        double ncb, gcb, bmr, rmrcb;
        double hrm = 208.0 - 0.7*((double) a);
        double vo2m = 15.3 * hrm / ((double) hrr);
        if(g == Genero.Masculino){
            gcb = ((-95.7735 + (0.634 * (double) mHearthRate) + (0.404 * vo2m) + (0.394 * (double) w) + (0.271 * (double) a))/4.184) * 60 * timeInHours();
            bmr = (13.75 * (double) w) + (5 * (double) h) - (6.76 * (double) a) + 66;
            rmrcb = bmr * 1.1 / 24 * timeInHours();
            ncb = gcb - rmrcb;
        }
        else if(g == Genero.Feminino) {
            gcb = ((-59.3954 + (0.45 * (double) mHearthRate) + (0.38 * (double) vo2m) + (0.103 * (double) w) + (0.274 * (double) a))/4.184) * 60 * timeInHours();
            bmr = (9.56 * (double) w) + (1.85 * (double) h) - (4.68 * (double) a) + 655;
            rmrcb = bmr * 1.1 / 24 * timeInHours();
            ncb = gcb - rmrcb; 
        }
        else ncb = 0;
        
        return (int) ncb;
    }
    
    /**
     * Calories burn calculation - based on MET *******ATENTION - NEEDS TESTING*****
     * @param g gender
     * @param w weight [kg]
     * @param h height [cm]
     * @param a age [years]
     * @param met Metabolic Equivalent of Task - to be assign by each particular activity
     * @return calories burn [kcal]
     */
    private int calcCaloriesMET(Genero g, int w, int h, int a, double met){
        double cb, cmet, rmr;
        if(g == Genero.Masculino){
            rmr = (66.473 + 5.0033 * (double) h + 13.7516 * (double) w - 6.755 * (double) a) / (1440 * 5 * (double) w) * 1000;
            cmet = met * 3.5 / rmr;
            cb = cmet * (double) w * timeInHours();
        }
        else if(g == Genero.Feminino) {
            rmr = (655.0955 + 1.8496 * (double) h + 9.5634 * (double) w - 4.6756 * (double) a) / (1440 * 5 * (double) w) * 1000;
            cmet = met * 3.5 / rmr;
            cb = cmet * (double) w * timeInHours();
        }
        else cb = 0;
        
        return (int) cb;
    }
    
    public int calcCalories(Genero g, int w, int h, int a, int hrr, double met){
        int cb = 0;
        
        if(g != Genero.Desconhecido && w != 0 && h != 0 && a != 0) {
           if(hrr != 0) cb = calcCaloriesHR(g, w, h, a, hrr); 
           else if(met != 0) cb = calcCaloriesMET(g, w, h, a, met); 
           else cb = 0;
        }
            
        return cb;
    }
