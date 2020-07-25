import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

public class md5github {
    public static void main(String[] args) throws UnsupportedEncodingException {
        //String message_digest = checkSum("a.txt");
        Random randomNumber = new Random();
        
        int P               = getPrime();
        int b               = randomNumber.nextInt(P);
        BigInteger g     = BigInteger.valueOf(randomNumber.nextInt(P));        
        BigInteger Prime    = new BigInteger(Integer.toString(P));
                
        int[]  a  = new int[5];
        BigInteger[] c1 = new BigInteger[5];
                
        //c1 section
        for(int i=0; i<5; i++) {
            a[i]    = randomNumber.nextInt(P);            
            BigInteger tmp_a = new BigInteger(Integer.toString(a[i]));
            c1[i] = g.modPow(tmp_a, Prime);          
        }
        
        //c2 section
//        paper section
        BigInteger tmpAtas     = new BigInteger("1");
        BigInteger tmpBawah    = new BigInteger("1");
        BigInteger tmpb        = new BigInteger("22");
        long tmpP        = 97;
        int[] tmpa      = {30,25,12,42,10};
        for(int i=0; i<5; i++) {
            if(i%2==0) {                
                tmpBawah = tmpBawah.multiply(tmpb.pow(tmpa[i]));
                //System.out.println("ganjil");
            } else {                                
                tmpAtas = tmpAtas.multiply(tmpb.pow(tmpa[i]));
                //System.out.println("genap");
            }
            System.out.println(i+". a: "+tmpa[i]);;             
        }
        long m = 47;        
        BigInteger c2 = tmpAtas.multiply(BigInteger.valueOf(m)).divide(tmpBawah).mod(BigInteger.valueOf(tmpP));
        System.out.println("tmpAtas: "+tmpAtas);
        System.out.println("tmpBawah: "+tmpBawah);                
        System.out.println("tmpb: "+tmpb+" tmpP: "+tmpP+" c2: "+c2);
        
//        decrypt section
        int tmp_a    = 89;
        tmpP = 101;
        BigInteger tmpAtasi     = new BigInteger("1");
        BigInteger tmpBawahi    = new BigInteger("1");
        BigInteger[] tmpc = new BigInteger[6];
        tmpc[0] = new BigInteger("79");
        tmpc[1] = new BigInteger("78");
        tmpc[2] = new BigInteger("25");
        tmpc[3] = new BigInteger("58");
        tmpc[4] = new BigInteger("87");
        tmpc[5] = new BigInteger("92");
        
        System.out.println("tmpAtas: "+tmpAtasi);
        System.out.println("tmpBawah: "+tmpBawahi);
        for(int i=0; i<6; i++) {
            if(i%2!=0) {                
                tmpBawahi = tmpBawahi.multiply(tmpc[i].pow(tmp_a));
                System.out.println("ganjil:"+tmpc[i]+" a:"+tmp_a+" bwh:"+tmpBawahi);
            } else {                                
                tmpAtasi = tmpAtasi.multiply(tmpc[i].pow(tmp_a));
                System.out.println("genap:"+tmpc[i]+" a:"+tmp_a+" atas:"+tmpAtasi);
            }
        }
        System.out.println("tmpAtas: "+tmpAtasi);
        System.out.println("tmpBawah: "+tmpBawahi);
        c2 = new BigInteger("51");
        BigInteger om = tmpBawahi.multiply(c2).divide(tmpAtasi).mod(BigInteger.valueOf(tmpP));
        System.out.println("om: "+om);
        System.out.println("tes50:"+tmpc[0].pow(tmp_a));
        System.out.println("tes49:"+tmpc[1].pow(tmp_a));
        //byte[] bytes = message_digest.getBytes("US-ASCII");
        //System.out.println("ascii: "+bytes[0]);
                
    }
    
    public static void hitung(int P, int g, int a, BigInteger b)
    {
        
    }
    
    public static int getPrime()
    {
        Random rand = new Random();
        int num;
        int counter;
        
        num     = rand.nextInt(500)+1;
        counter = 0;
        
        for(int i=2; i<num; i++) {
            if(num%i==0) {
                i = 1;
                num = rand.nextInt(500)+1;
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



