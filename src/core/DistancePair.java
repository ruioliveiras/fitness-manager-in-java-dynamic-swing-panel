package core;

import java.util.ArrayList;

/**
 * Write a description of class DistancePair here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistancePair{
    private String mUserName;
    private long mUserResult;
    
    public DistancePair(String name, long result){
        mUserName = name; 
        mUserResult = result;
    }
    
    public String getName(){return this.mUserName;}
    public long getResult(){return this.mUserResult;}
    public void setName(String name){this.mUserName = name;}
    public void setResults(long result){this.mUserResult = result;}
    

}
