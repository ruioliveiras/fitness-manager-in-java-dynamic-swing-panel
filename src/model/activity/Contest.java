package model.activity;

import java.util.GregorianCalendar;

import model.ObjectRecordClass;
import model.ObjectRecordClass.EnumAttr;
import model.ObjectRecordClass.Record;

public abstract class Contest extends Activity {
    private static final long serialVersionUID = 1L;

    /*TODO: criar texto de RESULT e toString*/
    static public enum Result {WIN,LOSE,DRAW};
    
    /*Resultado do contest? */
    private int mPointRival;
    private int mPointTeam;
    private Contest.Result mResult;
    
    
    public Contest() {
        super();
        mPointRival = 0;
        mPointTeam = 0;
    }

    public Contest(long time,Weather weather,int hRate,GregorianCalendar date,int pointRival,int pointTeam,Contest.Result result) {
        super(time, weather, hRate,date);
        mPointRival = pointRival;
        mPointTeam = pointTeam;
        mResult = result;
    }   

    public Contest(Contest act) {
        this(act.getDuration(),act.getWeather(),act.getHeartRate(),act.getDate(),act.getPointRival(),act.getPointTeam(),act.getResult());
    }
    
    public void setPointRival(int pointRival){mPointRival = pointRival;}
    public int getPointRival(){return mPointRival;}
    public void setPointTeam(int pointTeam){mPointTeam = pointTeam;}
    public int getPointTeam(){return mPointTeam;}
    public void setResult(){
        if(this.mPointTeam == this.mPointRival) mResult = Result.DRAW;
        else if(this.mPointRival > this.mPointTeam) mResult = Result.LOSE;
        else mResult = Result.WIN;
    }
    public Result getResult(){return mResult;}
    public abstract String getPointName(); /*Return "Goal" "points" etc..*/ 

    
    
    
    public enum Attr implements ObjectRecordClass.EnumAttr {
        TEMPO("Tempo"),RESULT_DIF("Diferença de resultado"),POINT_TEAM("Pontos da equipa");

        private String eName;
        Attr(String name){eName = name;}
        
        @Override
        public String getName() {return eName;}
        @Override
        public int getAttrType() {return ordinal();}
    }


    public enum MyRecords implements Record{
        BEST_VITORY("Melhor vitoria",Attr.RESULT_DIF),
        MAX_PONTOS("Maior nº pontos",Attr.POINT_TEAM);
        
        private ObjectRecordClass.EnumAttr eFix;
        private ObjectRecordClass.EnumAttr eMov;
        private int eValue;
        private String eName;

        MyRecords(String name,ObjectRecordClass.EnumAttr var,ObjectRecordClass.EnumAttr fixo,int value){
            eName = name;eFix = fixo; eMov = var; eValue = value;
        }
        MyRecords(String name,ObjectRecordClass.EnumAttr var){
            eName = name;eMov = var;eFix = null;    eValue = -1;
        }
        @Override
        public EnumAttr getFixed() {return eFix;}
        @Override
        public boolean similar(long value) 
            {return (Math.abs(value - eValue) < eValue/2);}
        @Override
        public EnumAttr getMov() {return eMov;}
        @Override
        public int getrecordType() {return ordinal();}
        @Override
        public String getName() {return eName;}
        @Override
        public long getValue() {return eValue;}
    }
    
    
	

	@Override
	public long get(EnumAttr att){
	
		if (att.getName().equals(Attr. POINT_TEAM.getName())){
			return getPointTeam();
		}else if (att.getName().equals(Attr.RESULT_DIF.getName())){
			return getPointTeam();
		}else if (att.getName().equals(Attr.TEMPO.getName())){
			return getDuration();
		}else {
			return -1;
		}
	}
	@Override
    public boolean isRecordBiggerBetter(Record recordType){
		EnumAttr att = recordType.getMov();
		
//		if (att.getName().equals(Attr.POINT_TEAM.getName())){
//			return false;
//		}
    	return true;
    }
	
    @Override
    public void correct(Record recordType) {	
    	//don't need
	} 
    
    @Override
	public int getRecordSize() {
		return MyRecords.values().length;
	}
   @Override
    public Record getRecord(int index){
    	return MyRecords.values()[index];
    }
    @Override
    public String getRecordToString(Record recordType){
    	EnumAttr att = recordType.getMov();
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append(getName());sb.append(" |- ");sb.append(recordType.getName());
    	sb.append(" ");
    	if (att.getName().equals(Attr.RESULT_DIF.getName())){
    		sb.append(getPointTeam() - getPointRival());sb.append(" ");sb.append(getPointName());
    	}else if (att.getName().equals(Attr.POINT_TEAM.getName())){
    		sb.append(getPointTeam());sb.append(" ");sb.append(getPointName()); 
    	}
    	
    	return sb.toString();
    }	
    
    

    
    @Override
    public boolean equals (Object obj){
        if(this == obj) return true; 
        if((obj == null) || (this.getClass() != obj.getClass())) return false;
        Contest e = (Contest) obj;
        return super.equals(obj) && mPointRival == e.getPointRival() && mPointTeam == e.getPointRival() && mResult == e.getResult();
    }
    
    @Override
    public int hashCode(){
        return super.hashCode() + Long.valueOf((mPointRival * 31 + mPointTeam) * 31 + mResult.hashCode()).hashCode();
    }

}
