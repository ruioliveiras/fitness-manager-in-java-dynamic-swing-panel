package core;

import model.activity.Activity;
import model.activity.Distance;
import model.activity.Contest;
import model.activity.Weather;
import model.user.User;
import model.events.Event;
import model.user.Estatisticas;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.GregorianCalendar;


public class EventSimulation {
    
    static private long getSimulationDistance(User u, Distance act,
                                                int recordType, int stages) {
        Weather w = act.getWeather();                                           
        double rndFact = 1 + (Math.random() - 0.50);
        double prsBest = (double) (u.getRecordValue(act.getClass(), recordType))/stages; /*tempo por intervalo, Ex:por km*/
        double fitnessFact = u.getForma();
        double weatherFact = (double) Math.min(0.1, (double) w.getLvl()/20);
        long result;
        if(Math.random() < 0.01) /*probabilida de de desistir = 1%*/
            result = Long.MAX_VALUE; /*desistiu => duracao muito elevada*/
        else{
            result = (long) (rndFact * prsBest * fitnessFact * weatherFact);
        }
        return result;
        /**TODO - adicionar automaticamente os resultados como novas actividades do User?*/
    }
    
    static public List<Long> getFullSimulationDistance(User u, Distance act, 
                                                int recordType, int stages) {
        Weather w = act.getWeather(); 
        List<Long> result = new ArrayList<Long>(stages);
        boolean desistiu = false;
        long oneResult;

        for(int i=0; (i < stages) && !desistiu; i++){
            oneResult = getSimulationDistance(u, act, recordType, stages);
            result.add(oneResult);
            if(oneResult == Long.MAX_VALUE) desistiu = true;
        }
        return result;             
    }
    
    public List<List<Long>> executeEventDistance(Set<User> users, Distance act, int recordType) {
        /*PERGUNTAR OLIVEIRA - percorrer Users?, associar Users aos resultados criados?*/
        Weather w = act.getWeather();
        List<ArrayList<Long>> result = new ArrayList<ArrayList<Long>>();
        long eventDistance = act.getRecordTypeValue(recordType);
        int stages = (eventDistance > 1000) ? eventDistance/1000 : 1;
        
        for(User u : users){
            result.add(getFullSimulationDistance(u, act.getClass(), 
                        recordType, w, stages));
        }

        return result;
    }
    
    /**
     * Simula uma disputa entre dois utilizadores
     * @param u1 utilizador 1
     * @param u2 utilizador 2
     * @param category categoria da actividade
     * @return <0 se user 1 vence; =0 se empatam; ou >0 se user 2 vence
     */
    static public int getSimulationContest(User u1, User u2, Class<? extends Contest> category){
        GregorianCalendar agora = new GregorianCalendar();
        int user1Pts = getPtsByYear(u1, agora, category);
        int user2Pts = getPtsByYear(u1, agora, category);
        double user1RndFact = 1 + (Math.random() - 0.50);
        double user2RndFact = 1 + (Math.random() - 0.50);
        return (int) (user2RndFact*user2Pts - user1RndFact*user1Pts);
    }

}