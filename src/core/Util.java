package core;

public class Util {

	
    public static int CalcHashCode(int a,int b,int c){
        return Long.valueOf((a * 31 + b) * 31 + c).hashCode();
    }
}
