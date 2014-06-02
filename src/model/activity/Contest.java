package model.activity;

import java.util.GregorianCalendar;

import model.ObjectRecord;

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
        if(this.mPointRival == this.mPointTeam) mResult = Result.DRAW;
        else if(this.mPointRival > this.mPointTeam) mResult = Result.LOSE;
        else mResult = Result.WIN;
    }
    public Result getResult(){return mResult;}
    public abstract String getPointName(); /*Return "Goal" "points" etc..*/ 

    
    
    
    public enum Attr implements ObjectRecord.enumAttr {
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
        
        private ObjectRecord.enumAttr eFix;
        private ObjectRecord.enumAttr eMov;
        private int eValue;
        private String eName;

        MyRecords(String name,ObjectRecord.enumAttr var,ObjectRecord.enumAttr fixo,int value){
            eName = name;eFix = fixo; eMov = var; eValue = value;
        }
        MyRecords(String name,ObjectRecord.enumAttr var){
            eName = name;eMov = var;eFix = null;    eValue = -1;
        }
        @Override
        public enumAttr getFixed() {return eFix;}
        @Override
        public boolean similar(long value) 
            {return (Math.abs(value - eValue) < eValue/2);}
        @Override
        public enumAttr getMov() {return eMov;}
        @Override
        public int getrecordType() {return ordinal();}
        @Override
        public String getName() {return eName;}
        @Override
        public long getValue() {return eValue;}
    }
    
    public long getStat(int recordType) {
        MyRecords a = MyRecords.values()[recordType];
        return super.getStat(a);
    }
    public long get(int iAttr) {
    	Attr a = Attr.values()[iAttr];
    	switch (a) {
		case POINT_TEAM: return getPointTeam();
		case RESULT_DIF: return getPointTeam() - getPointRival();
		case TEMPO: 	 return (int) (-1) * (getDuration() / (1000) ); //* seconds
		default:
			return -1;
    	}
	} 
    
    public String getRecordToString(int recordType){
    	Record r = MyRecords.values()[recordType];
    	Attr a = Attr.values()[r.getMov().getAttrType()];
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append(getName());sb.append(" |- ");sb.append(a.getName());
    	sb.append(" ");
    	
    	switch (a) {
		case RESULT_DIF: sb.append(getPointTeam() - getPointRival());sb.append(" ");sb.append(getPointName()); break;
		case POINT_TEAM: 	sb.append(getPointTeam());sb.append(" ");sb.append(getPointName()); break;
		default:
			break;
    	}
    	
    	return sb.toString();
    }
    

    @Override
    public void correct(int recordType) {
        //Don't need to correct nothing
    } 
    @Override
    public int getRecordSize() {
        return MyRecords.values().length;
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
