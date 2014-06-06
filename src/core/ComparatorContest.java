package core;

import java.io.Serializable;
import java.util.Comparator;

public class ComparatorContest  implements Comparator<SimulationPair>, Serializable{
    private static final long serialVersionUID = 1L;

	public int compare(SimulationPair p1, SimulationPair p2){
	    long dif = p2.getResult() - p1.getResult() ;
	    if(dif == 0) return p1.getName().compareTo(p2.getName());
	    if(dif > 0) return 1;
	    return -1;
    }
	
}
 