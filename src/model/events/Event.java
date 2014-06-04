package model.events;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashSet;

import model.ObjectClonable;
import model.ObjectKey;
import model.activity.Activity;
import model.user.RecordDontExitExeception;
import model.user.User;
import core.util.Manager;
import core.util.ManagerSet;


//-Classe Evento: {Conjuto Users, Actividade, Categoria, Data evento, Data inscricao, Nome, pre-requisito inscricao (long tempo/distancia), numero maximo de participantes}

public abstract class Event implements ObjectClonable,ObjectKey,Serializable {
    private String mName;
    private Activity mActivity;/**ATENCAO: weather do Evento dentro da activity?*/
    private int mRecordType;
    private GregorianCalendar mEventDate;
    private GregorianCalendar mEndDate;
    private long mPreRequisite;
    private int mMaxNumUsers;
    private ManagerSet<String> mUsers;

    public Event() {
        mName = "";
        mActivity = null;
        mEventDate = new GregorianCalendar();
        mEndDate = new GregorianCalendar();
        mPreRequisite = -1;
        mMaxNumUsers = -1;
        mRecordType = -1;
        mUsers = new ManagerSet<String>(mListenerBeforeAdd, new HashSet<String>());
    }
    public Event(String name, Activity activity, GregorianCalendar eventDate,
                    GregorianCalendar endDate, long preRequisite, int maxNumUsers, int recordType) {
        mName = name;
        mActivity = activity;
        mEventDate = eventDate;
        mEndDate = endDate;
        mPreRequisite = preRequisite;
        mMaxNumUsers = maxNumUsers;
        mRecordType = recordType;
        mUsers = new ManagerSet<String>(mListenerBeforeAdd, new HashSet<String>());
    }

    public Event(Event e){
        this(e.getName(),e.getActivity(),e.getEventDate(),e.getEndDate(),e.getPreRequisite(),e.getMaxNumUsers(), e.getRecordType());
    }
    
    public String getName() {
        return mName;
    }
    public void setName(String name) {
        this.mName = name;
    }
    public Activity getActivity() {
        return mActivity.clone();
    }
    public void setActivity(Activity activity) {
        this.mActivity = activity.clone();
    }
    
    public GregorianCalendar getEventDate() {
        return (GregorianCalendar) mEventDate.clone();
    }
    public void setEventDate(GregorianCalendar eventDate) {
        this.mEventDate = (GregorianCalendar) eventDate.clone();
    }
    public GregorianCalendar getEndDate() {
        return (GregorianCalendar) mEndDate.clone();
    }
    public void setEndDate(GregorianCalendar endDate) {
        this.mEndDate = (GregorianCalendar) endDate.clone();
    }
    public long getPreRequisite() {
        return mPreRequisite;
    }
    public void setPreRequisite(long preRequisite) {
        this.mPreRequisite = preRequisite;
    }
    public int getMaxNumUsers() {
        return mMaxNumUsers;
    }
    public void setMaxNumUsers(int maxNumUsers) {
        this.mMaxNumUsers = maxNumUsers;
    }
    public int getRecordType() {
        return mRecordType;
    }
    public void setRecordType(int recordType) {
        this.mRecordType = recordType;
    }
    public Manager<String> getUserManager(){
        return mUsers;/*TODO: PERGUNTAR AO OLIVEIRA*/
    }
    
    public void addUser(User u) throws AddEventException{
        String userEmail = u.getEmail();
        GregorianCalendar agora = new GregorianCalendar();
        if(this.mEndDate.compareTo(new GregorianCalendar()) < 0) throw new AddEventException("Data limite de inscrição!");/*data posterior ao limite de inscricao*/
        if(this.mMaxNumUsers <= this.mUsers.size()) throw new AddEventException("Número máximo de inscrições!"); /*excedeu limite de users*/
       
        try {
        	long aux = u.getRecordValue(this.mActivity.getClass(), this.mRecordType);
        	if (mActivity.isRecordBiggerBetter(mActivity.getRecord(mRecordType))){
        		if(this.mPreRequisite > aux) throw new AddEventException("Não cumpre o pré-requisito!");
        	}else{
        		if(this.mPreRequisite < aux) throw new AddEventException("Não cumpre o pré-requisito!");
        	}
			
		} catch (RecordDontExitExeception e) {
			throw new AddEventException();
		} /*nao tem tempo minimo*/
        mUsers.add(userEmail);
    }
    
    
    public boolean equals(Object obj){
      if(this == obj) return true; 
      if((obj == null) || (this.getClass() != obj.getClass())) return false;
      Event e = (Event) obj;
      return this.mName.equals(e.getName()) && this.mEventDate.equals(e.getEventDate());
    }
  
    
    public Object getKey(){
    	return getName();
    }
    
    public abstract int hashCode();
    
    public abstract Object clone();
    
    public abstract String toString();
  
    private Manager.OnManagerAdd<String> mListenerBeforeAdd = new Manager.OnManagerAdd<String>() {
        @Override
        public boolean beforeAdd(String obj) {
            
            return true;
        }
    };
}
