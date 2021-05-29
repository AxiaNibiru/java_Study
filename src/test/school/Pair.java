package test.school;

/**
 * @version 2021-4-28
 * @author 201916170415甘帅
 */
public class Pair {
    private final  int first ;
    private final int second;
    private final int n;
    private int e;
    private int d;

    public Pair(int first, int second){
        this.first = first;
        this.second = second;
        n = first * second;
    }

    public int getFirst(){
        return first;
    }

    public int getSecond(){
        return second;
    }

    public int getE(){
        return e;
    }

    public void setE(int e){
        this.e = e;
    }

    public int getD(){
        return d;
    }

    public void setD(int d){
        this.d = d;
    }

    public int getN(){
        return n;
    }

    public Pair getPublicM(){
        return new Pair(e, n);
    }
    public Pair getPrivateM(){
        return new Pair(d, n);
    }
}
