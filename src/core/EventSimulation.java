package core;

import model.activity.Activity;
import model.activity.Distance;
import model.activity.Contest;
import model.activity.Weather;
import model.user.RecordDontExitExeception;
import model.user.User;
import core.UserStats;

import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.GregorianCalendar;

import model.events.ContestPair;

import java.util.Iterator;


public class EventSimulation {
    
    /**a simulation of a stage for one User, Ex: 1km*/
    static private long getSimulationDistance(User u, Distance act,
                                                int recordType, int stages) {
        Weather w = act.getWeather();                                           
        double rndFact = 1 + (Math.random() - 0.50);
        double prsBest=0;
        try {
            prsBest = (double) ((u.getRecordValue(act.getClass(), recordType))/stages);
        } catch (RecordDontExitExeception e) {
            e.printStackTrace();
        } /*tempo por intervalo, Ex:por km*/
        double fitnessFact = 1/u.getForma();/*pior forma => mais tempo (pior)*/
        double weatherFact = 1/Math.max(0.1, (double) w.getLvl()/20);/*pior tempo => mais tempo (pior), no limite duplica o tempo*/
        long result;
        if(Math.random()*20 < 0.01*u.getIdade()) /*probabilida de de desistir proporcional a faixa etaria, 1% para 20 anos*/
            result = Long.MAX_VALUE/2; /*desistiu => duracao muito elevada*/
        else{
            result = (long) (rndFact * prsBest * fitnessFact * weatherFact);
        }
        return result;
        /**TODO - adicionar automaticamente os resultados como novas actividades do User?*/
    }
         
    /**simulation of the all race (several stages) for a User*/
    static private ArrayList<Long> getFullSimulationDistance(User u, Distance act, 
                                                int recordType, int stages) {
        ArrayList<Long> result = new ArrayList<>(stages);
        boolean desistiu = false;
        long oneResult;
        
        for(int i=0; (i < stages) && !desistiu; i++){
            oneResult = getSimulationDistance(u, act, recordType, stages);
            result.add(oneResult);
            if(oneResult == Long.MAX_VALUE/2) desistiu = true;
        }
        return result;             
    }
    
    static public Map<String,ArrayList<Long>> getAllResults(List<User> users, Distance act, 
                                                int recordType, int stages){
        Map<String,ArrayList<Long>> result = new HashMap<>();
        for(User u : users){
            ArrayList<Long> aux = getFullSimulationDistance(u,act,recordType,stages);
            result.put(u.getNome(),aux);
        }
        return result;
    }
    
    static private long getStageResult(ArrayList<Long> results, int stage){
        long stageResult = 0;
        int i = 1;
        for(Long r : results){
            if(i > stage) break;
            stageResult = stageResult + r;
            i++;
        }
        return stageResult;
    }
    
    /*treeSet para resultados ordenados por etapa(km)*/
    static public TreeSet<DistancePair> getStageClassification(Map<String,ArrayList<Long>> results, int stage){
        TreeSet<DistancePair> r = new TreeSet<>(new ComparatorDistancePair());
        
        
        Iterator<String> it = results.keySet().iterator();
        for(ArrayList<Long> lst : results.values()){
            long auxResult = getStageResult(lst, stage);
            r.add(new DistancePair(it.next(),auxResult));
        }
            
        return r;
    }   
    
    /**
     * Simula uma disputa entre dois utilizadores
     * @param u1 utilizador 1
     * @param u2 utilizador 2
     * @param category categoria da actividade
     * @return <0 se user 1 vence; =0 se empatam; ou >0 se user 2 vence
     */
    static public int getSimulationContest(User u1, User u2, Class<? extends Activity> category){
        int user1Pts = UserStats.getPtsFromLastYear(u1, category);
        int user2Pts = UserStats.getPtsFromLastYear(u2, category);
        double rnd1 = 1 + (Math.random() - 0.50);
        double rnd2 = 1 + (Math.random() - 0.50);
        return (int) (rnd2*user2Pts - rnd1*user1Pts);
    }      

}