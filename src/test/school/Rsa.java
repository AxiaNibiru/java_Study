package test.school;

/**
 * @version 2021-4-28
 * @author 201916170415甘帅
 */
public abstract class Rsa {
    public static int setRsa(int m, Pair pair){
        return (int)(Math.pow(m, pair.getE()) % pair.getN());
    }

    public static int getRsa(int key, Pair pair){
        return (int)(Math.pow(key, pair.getD()) % pair.getN());
    }
}
