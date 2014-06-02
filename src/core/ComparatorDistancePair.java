package core;

import java.io.Serializable;
import java.util.Comparator;

public class ComparatorDistancePair implements Comparator<DistancePair>, Serializable{
    private static final long serialVersionUID = 1L;

	public int compare(DistancePair p1, DistancePair p2){
	    long dif = p1.getResult() - p2.getResult();
	    if(dif == 0) return 0;
	    if(dif > 0) return 1;
	    return -1;
    }
 
}
