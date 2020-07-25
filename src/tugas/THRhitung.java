/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas;

import java.util.Scanner;

/**
 *
 * @author CodeForLife
 */
public class THRhitung {
    public static void main(String[] args)
    {
        Scanner myScann = new Scanner(System.in);
        System.out.println("====== Program Menghitung THR Gaji Pegawai =====");
        System.out.println("================================================");
        System.out.print("Input gaji pokok: ");
        float gajiPokok = myScann.nextFloat();
        System.out.print("Input jumlah bulan kerja: ");        
        float bulanKerja = myScann.nextFloat();
        System.out.println("================================================");
        float THR = 0;
        if(bulanKerja >= 12) {
            THR = gajiPokok;            
        } else {
            THR = (gajiPokok/12)*bulanKerja;
        }
        System.out.println("================= HASIL ========================");
        System.out.printf("THR: %.2f Rupiah\n",THR);    
        System.out.println("================================================");
    }
}
