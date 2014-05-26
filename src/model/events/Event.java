package model.events;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;

import core.util.Manager;
import core.util.ManagerSet;
import model.ObjectClonable;
import model.activity.Activity;
import model.user.User;


//-Classe Evento: {Conjuto Users, Actividade, Categoria, Data evento, Data inscricao, Nome, pre-requisito inscricao (long tempo/distancia), numero maximo de participantes}

public abstract class Event implements ObjectClonable  {
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
        return mActivity;
    }
    public void setActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }
    
    public GregorianCalendar getEventDate() {
        return mEventDate;
    }
    public void setEventDate(GregorianCalendar eventDate) {
        this.mEventDate = eventDate;
    }
    public GregorianCalendar getEndDate() {
        return mEndDate;
    }
    public void setEndDate(GregorianCalendar endDate) {
        this.mEndDate = endDate;
    }
    public long getPreRequisite() {
        return mPreRequisite;
    }
    public void setPreRequisite(int preRequisite) {
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
        return mUsers;
    }
    
    public void addUser(User u) {
        String userEmail = u.getEmail();
        GregorianCalendar agora = new GregorianCalendar();
        if(this.mEndDate.compareTo(new GregorianCalendar()) > 0) return;/*data posterior ao limite de inscricao*/
        if(this.mMaxNumUsers <= this.mUsers.getSize()) return; /*excedeu limite de users*/
        if(this.mPreRequisite > u.getRecordValue(this.mActivity.getClass(), this.mRecordType)) return; /*nao tem tempo minimo*/
        mUsers.add(userEmail);
    }
    
    public boolean equals(Object obj){
      if(this == obj) return true; 
      if((obj == null) || (this.getClass() != obj.getClass())) return false;
      Event e = (Event) obj;
      return this.mName.equals(e.getName()) && this.mEventDate.equals(e.getEventDate());
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
    
    public abstract List<?> realizaEvento();
}
