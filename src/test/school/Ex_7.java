package test.school;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * @author 甘帅201916170415
 * @version 2021-4-28
 * */
public class Ex_7 extends Encryption{
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        System.out.print("请输入需要加密的字符串： ");
        String string = scanner.nextLine();
        char[] input = string.toCharArray();
        System.out.print("输入密钥： ");
        String string2 = scanner.nextLine();
        char[] key = string2.toCharArray();
        System.out.println("根据密钥得出的密文为： " + new String(Encryption.afterEncryption(input, key)));
    }

}

abstract class Encryption{
    static Map<String, Integer> map = new HashMap<>();
    //静态初始化map表
    static {
        map.put("a", 0);
        map.put("b", 1);
        map.put("c", 2);
        map.put("d", 3);
        map.put("e", 4);
        map.put("f", 5);
        map.put("g", 6);
        map.put("h", 7);
        map.put("i", 8);
        map.put("j", 9);
        map.put("k", 10);
        map.put("l", 11);
        map.put("m", 12);
        map.put("n", 13);
        map.put("o", 14);
        map.put("p", 15);
        map.put("q", 16);
        map.put("r", 17);
        map.put("s", 18);
        map.put("t", 19);
        map.put("u", 20);
        map.put("v", 21);
        map.put("w", 22);
        map.put("x", 23);
        map.put("y", 24);
        map.put("z", 25);
    }
    /**
     * @param target 输入的明文
     * @param key 用于加密的密钥
     * */
    public static char[] afterEncryption(char[] target, char[] key){
        //创建用于保存密文的数组
        char[] encryption = new char[target.length];
        int index1 = 0;
        for (int i = 0; i < target.length; i++) {
            if (target[i] == ' '){
                encryption[i] = ' ';
                continue;
            }
            //分别取得明文第i位字符， 和密钥index位置在字母表中的位置
            int index2 = map.get(String.valueOf(target[i]));
            int index3 = map.get(String.valueOf(key[index1++ % key.length]));
            index2 = (index2 + index3) % 26;
            String temp = "";
            //遍历map表查找index2（偏移后所对应的位置）的字母
            for (Map.Entry<String, Integer> entry: map.entrySet()){
                if (entry.getValue().equals(index2)){
                    temp = entry.getKey();
                    break;
                }
            }
            encryption[i] = temp.charAt(0);
        }
        return encryption;
    }
}