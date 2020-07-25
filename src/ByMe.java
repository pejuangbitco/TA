import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CodeForLife
 */
public class ByMe {
    public static void main(String[] args)
    {
        Random rgen = new Random();        
        String msg_digest = checkSum("a.txt");
        char msg[] = msg_digest.toCharArray();
        for(int i=0; i<msg.length ;i++) {
            int P       = getPrime();
            int g       = rgen.nextInt(P-2);
            int a       = rgen.nextInt(P-2);
            int r[] = new int[3];
            for(int j=0; j<r.length; j++) {
                r[j] = rgen.nextInt(P-2);
            }
            
            hitung(msg[i], P, g, a, r);
        }
    }
    
    public static void hitung(int m, int P, int g, int a, int r[])
    {        
        BigInteger Pb = BigInteger.valueOf(P);
        BigInteger gb = BigInteger.valueOf(g);
        BigInteger ab = BigInteger.valueOf(a);
        BigInteger b  = gb.modPow(ab, Pb);
        System.out.println("P:"+Pb+" g:"+gb+" a:"+BigInteger.valueOf(a)+" b:"+b);
        
        BigInteger c1[] = new BigInteger[3];
        for(int i=0; i<r.length; i++) {
            c1[i] = gb.modPow(BigInteger.valueOf(r[i]), Pb);
            System.out.println("c1"+(i+1)+":"+c1[i]);
        }
        
        //c2
        BigInteger tmp = new BigInteger("1");
        
        tmp = tmp.multiply(BigInteger.valueOf(m));
        for(int i=0; i<r.length; i++) {
            tmp = tmp.multiply(b.pow(r[i]));            
        }
        
        BigInteger c2 = tmp.mod(Pb);
        System.out.println("c2:"+c2);
        
        //dekripsi
        tmp = new BigInteger("1");
        tmp = tmp.multiply(c2);
        for(int i=0; i<r.length; i++) {
            tmp = tmp.multiply(c1[i].pow(P-1-a));            
        }
        
        BigInteger M = tmp.mod(Pb);
        System.out.println("msg:"+m+" "+"dekripsi:"+M);
        System.out.println("----------------------------------");
    }
    
    public static int getPrime()
    {
        Random rand = new Random();
        int num;
        int counter;
        
        num     = rand.nextInt(50000)+500;
        counter = 0;
        
        for(int i=2; i<num; i++) {
            if(num%i==0) {
                i = 1;
                num = rand.nextInt(50000)+500;
            }
        }
        
        return num;
    }

    public static String checkSum(String fileName){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] dataBytes = new byte[1024];

        int nread = 0;
        
        try {
            while ((nread = fis.read(dataBytes)) != -1) {
                md.update(dataBytes, 0, nread);
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;

        byte[] mdbytes = md.digest();

        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < mdbytes.length; i++) {
            sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        System.out.println("MD5 " + sb.toString() + " for " + fileName);
        return sb.toString();
    }
}
