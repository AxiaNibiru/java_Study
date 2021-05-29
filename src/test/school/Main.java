package test.school;

/**
 * @version 2021-4-28
 * @author 201916170415甘帅
 */
public class Main {
    public static void main(String args[]){
        Pair pair1 = new Pair(11, 13);
        pair1.setE(7);
        pair1.setD(103);
        Pair pair2 = new Pair(5, 7);
        pair2.setD(29);
        pair2.setE(3);
        System.out.println("(e,n): (" + pair2.getE() + ", " + pair2.getN() + ")");
        System.out.println("(d,n): (" + pair2.getD() + ", " + pair2.getN() + ")");
        System.out.println("明文为85加密后的密文为： " + Rsa.setRsa(5, pair2));
        System.out.println(Rsa.getRsa(20, pair2));
    }
}
