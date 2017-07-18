/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.in;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 */
public class RatingApp {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws Exception {
        List<String> lineList = readFile("Firma.txt");
        final String urunSayi = lineList.get(0);
        Scanner oku = new Scanner(System.in);
        List<String> ilkSatir = getWordList(urunSayi);
        String devam = " ", tip = " ";
        String Name, Surname, City, Country, Occupation;
        int CustomerID, LicensePlateNum;
        int benzerlikDeger = 999;
        int[] benzerlikDegerDizi = null;
        int[] urunMiktarDizi;
        int yenideger = 0;

        int[][] urunMatris = new int[MAX_CUSTOMER_COUNT][Integer.valueOf(ilkSatir.get(0))];//URUNLERİN ATİLDİGİ MATRİS
        int sayac = 0, sayac1 = 0, sayac3 = 0;
        Customer[] customerArray = new Customer[MAX_CUSTOMER_COUNT];// CUSTOMER DİZİMİZ 
        NationalCustomer yeniCust = new NationalCustomer();
        //    musterileri okur.
        int customerIndex = 0;
        for (int i = 1; i < lineList.size(); i += 2) {
            customerArray = musteriDizisiDoldur(lineList, i, customerArray, customerIndex);//BUTUN CUSTOMERLER BURAYA ATILIYO
            urunMatris = urunMatrisDoldur(lineList, i, urunMatris, customerIndex);// YUKLEME İŞLEMİ GERÇEKLEŞTİRİLİYO
            customerIndex++;

        }

        final Integer deger = Integer.valueOf(ilkSatir.get(0)); // NULL YA DA 0 OLABİLME TEKLİKESİNE BUTUN İLKLEMELER 0 OLARAK AYARLANMASI 
        int[] ortalamaList = new int[deger];
        int[] N_ortalamaList = new int[deger];
        int[] I_ortalamaList = new int[deger];
        int[] D_ortalamaList = new int[deger];
        int[] yenidegerler = new int[deger];
        for (int i = 0; i < deger; i++) {

            ortalamaList[i] = 0;// tum urunler icin ilkleme
            N_ortalamaList[i] = 0;//natıonal customerler icin ilkleme
            I_ortalamaList[i] = 0;// international customerler icin ilkleme
            D_ortalamaList[i] = 0;// natıonal ve doktor olanlar icin ilkleme
            yenidegerler[i] = 0;

        }

        for (int[] urunArray : urunMatris) { // URUNLERIN  ORTALAMASINI BULMA

            for (int i = 0; i < urunArray.length; i++) {
                ortalamaList[i] = ortalamaList[i] + urunArray[i];

            }

        }
        System.out.println(" ");
        System.out.println("*************************************************************");
        System.out.println("*************************************************************");
        System.out.println("Butun Musterilerin urun Derecelendirmeleri ....");
        for (Integer integer : ortalamaList) {

            System.out.print(" " + (float) integer / customerIndex);// butun customerler icin listeleme

        }
        System.out.println(" ");

        for (int i = 0; i < customerArray.length; i++) {

            if (!(customerArray[i] instanceof NationalCustomer)) { // ULUSLARARASI BIR MUSTERI ISE...

                for (int x = i; x < deger; x++) {
                    sayac++;
                    for (int y = 0; y < deger; y++) {
                        I_ortalamaList[y] = I_ortalamaList[y] + urunMatris[x][y];
                    }
                    break;
                }

            } else {  // ULUSAL BİR MUSTERI İSE ....

                sayac1++;
                for (int x = i; x < deger; x++) {
                    for (int y = 0; y < deger; y++) {
                        N_ortalamaList[y] = N_ortalamaList[y] + urunMatris[x][y];

                    }
                    break;
                }
                yeniCust = (NationalCustomer) customerArray[i];// ULUSAL VE DOKTOR İCİN
                if (yeniCust.getOccupation().equals("Doktor")) {
                    sayac3++;
                    for (int x = i; x < deger; x++) {
                        for (int y = 0; y < deger; y++) {
                            D_ortalamaList[y] = D_ortalamaList[y] + urunMatris[x][y];

                        }
                        break;
                    }
                }

            }

        }

        System.out.println("");
        System.out.println("*************************************************************");
        System.out.println("*************************************************************");
        System.out.println("  ");
        System.out.println("National olan Musterilerin urun derecelendirmeleri...");
        for (int i = 0; i < deger; i++) {
            System.out.print(" " + (float) N_ortalamaList[i] / sayac1);
        }
        System.out.println("");
        System.out.println("*************************************************************");
        System.out.println("*************************************************************");
        System.out.println("  ");

        System.out.println("Internatıonal olan musterılerın urun derecelendirmeleri...");
        for (int i = 0; i < deger; i++) {
            System.out.print(" " + (float) I_ortalamaList[i] / sayac);
        }
        System.out.println("");
        System.out.println("*************************************************************");
        System.out.println("*************************************************************");
        System.out.println("  ");

        System.out.println("National ve Doktor olan musterılerın urun derecelendırmelerı ");
        for (int i = 0; i < deger; i++) {
            System.out.print(" " + (float) D_ortalamaList[i] / sayac3);
        }
        System.out.println("");
        System.out.println("*************************************************************");
        System.out.println("*************************************************************");
        for (int a = 0; a < deger; a++) { // ortalma altı veren national musterılerı yazdırma
            System.out.println(a + 1 + " . urun için ortalama altı vereren ulusal Musteriler ");
            for (int i = 0; i < customerIndex; i++) {
                if ((customerArray[i] instanceof NationalCustomer) && urunMatris[i][a] < ((float) ortalamaList[a] / customerIndex)) {
                    System.out.println(customerArray[i]);
                }

            }
        }
        System.out.println("*************************************************************");
        System.out.println("*************************************************************");
        for (int a = 0; a < deger; a++) { // ortalma altı veren International musterılerı yazdırma
            System.out.println(a + 1 + " . urun için ortalama altı vereren uluslararasi Musteriler ");
            for (int i = 0; i < customerIndex; i++) {
                if ((customerArray[i] instanceof InternationalCustomer) && urunMatris[i][a] < ((float) ortalamaList[a] / customerIndex)) {
                    System.out.println(customerArray[i]);
                }

            }
        }

        do {
            System.out.println("Lutfen Musterinin Tipini Belirtiniz :"); // tip belirtip ekleme işlemleri yapmak için
            tip = oku.next();
            if (tip.equals("n")) {
                System.out.print("musteri ID sini Giriniz : ");
                CustomerID = oku.nextInt();
                System.out.print("Musteri isim :");
                Name = oku.next();
                System.out.print("Musteri Soyad :");
                Surname = oku.next();
                System.out.print("Musteri Lisence Plate Number giriniz :");
                LicensePlateNum = oku.nextInt();
                System.out.print("Musteri Occupation giriniz : ");
                Occupation = oku.next();
                Customer cst = new NationalCustomer(CustomerID, Name, Surname, LicensePlateNum, Occupation);
                customerArray[customerIndex] = cst;
                customerIndex++;
                System.out.print("Musterinin Degerlendirecegi Urun miktarini Giriniz :");
                int urunmiktar = oku.nextInt();
                urunMiktarDizi = new int[urunmiktar];
                int temp = 0;
                for (int i = 0; i < urunmiktar - 1; i++) {
                    System.out.println((i + 1) + ". inci urunun puanini giriniz   ");

                    urunMiktarDizi[i] = oku.nextInt();
                }
                for (int[] urunMatri : urunMatris) {
                    for (int i = 0; i < urunmiktar - 1; i++) {
                        yenideger = Math.abs(urunMiktarDizi[i] - urunMatri[i]) + yenideger;

                        temp = i;
                    }
                    if (yenideger > benzerlikDeger) {
                        benzerlikDeger = yenideger;
                        urunMiktarDizi[urunmiktar - 1] = urunMatris[temp][urunmiktar - 1];
                    }

                }

            } else { // İNTERNATİONAL MUSTERİ GİRİŞİ İCİN
                System.out.print("musteri ID sini Giriniz : ");
                CustomerID = oku.nextInt();
                System.out.print("Musteri isim :");
                Name = oku.next();
                System.out.print("Musteri Soyad :");
                Surname = oku.next();
                System.out.print("Musteri country giriniz :");
                Country = oku.next();
                System.out.print("Musteri city giriniz : ");
                City = oku.next();
                Customer cst1 = new InternationalCustomer(CustomerID, Name, Surname, Country, City);
                customerArray[customerIndex] = cst1;
                customerIndex++;
                System.out.print("Musterinin Degerlendirecegi Urun miktarini Giriniz :");
                int urunmiktar = oku.nextInt();
                urunMiktarDizi = new int[urunmiktar];
                int temp = 0;
                for (int i = 0; i < urunmiktar - 1; i++) {
                    System.out.println((i + 1) + ". inci urunun puanini giriniz   ");

                    urunMiktarDizi[i] = oku.nextInt();
                }
                for (int[] urunMatri : urunMatris) {
                    for (int i = 0; i < urunmiktar - 1; i++) {
                        yenideger = Math.abs(urunMiktarDizi[i] - urunMatri[i]) + yenideger;

                        temp = i;
                    }
                    if (yenideger < benzerlikDeger) {
                        benzerlikDeger = yenideger;
                        urunMiktarDizi[urunmiktar - 1] = urunMatris[temp][urunmiktar - 1];
                    }

                }
                for (int i = 0; i < urunmiktar; i++) {
                    urunMatris[customerIndex][i] = urunMiktarDizi[i];
                }

            }

            System.out.println("Devam Etmek İster Misiniz? Y/y or N/n");// döngu devamı için
            devam = oku.next();

        } while (devam.equals("Y") || devam.equals("y"));
        for (int i = 0; i < customerIndex; i++) {
            System.out.println(" " + customerArray[i]);
        }

        for (int[] urunArray : urunMatris) {

            for (int i = 0; i < urunArray.length; i++) {
                ortalamaList[i] = ortalamaList[i] + urunArray[i];

            }

        }
        System.out.println("*************************************************************");
        System.out.println("*************************************************************");
        System.out.println("butun urunlerin son durum ortalamalari");
        for (Integer integer : ortalamaList) {

            System.out.print(" " + (float) integer / customerIndex);// butun customerler icin listeleme

        }
        System.out.println(" ");
        System.out.println("*************************************************************");
        System.out.println("*************************************************************");
        System.out.println("*************************************************************");
        System.out.println("*************************************************************");
    }

    private static int[][] urunMatrisDoldur(List<String> lineList, int i, int[][] urunMatris, int customerIndex) throws NumberFormatException {
        List<String> urunList = getWordList(lineList.get(i + 1));

        for (int j = 0; j < urunList.size(); j++) {

            urunMatris[customerIndex][j] = Integer.valueOf(urunList.get(j));

        }
        return urunMatris;
    }

    private static Customer[] musteriDizisiDoldur(List<String> lineList, int i, Customer[] customerArray, int customerIndex) throws NumberFormatException {
        List<String> tokenList = getWordList(lineList.get(i));
        Customer birey;
        if (tokenList.get(0).equals("i")) { // uluslararası musteri ise
            birey = new InternationalCustomer(Integer.valueOf(tokenList.get(1)), tokenList.get(2), tokenList.get(3), tokenList.get(4), tokenList.get(5));

        } else {// ulusal musterı ise
            birey = new NationalCustomer(Integer.valueOf(tokenList.get(1)), tokenList.get(2), tokenList.get(3), Integer.valueOf(tokenList.get(4)), tokenList.get(5));

        }
        customerArray[customerIndex] = birey; // customer sınıfına atama yap

        return customerArray;
    }
    private static final int MAX_CUSTOMER_COUNT = 200;

    private static List<String> getWordList(String line) { //satiri ayrı ayrı list sınıfına atıp ordan split eden metod
        StringTokenizer token = new StringTokenizer(line, ",");
        List<String> tokenList = new ArrayList<>();
        while (token.hasMoreTokens()) {

            tokenList.add(token.nextToken());

        }
        return tokenList;
    }

    // Dosyadan Verileri okumak için her satırı String olarak lineListe atama
    public static List<String> readFile(String file) throws Exception {
        List<String> lineList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                lineList.add(line);
            }
        }
        return lineList;

    } // dosyadan veri okumaak için 
    // public static void cAtama(Customer[] cust, List<String> tokenlist) {
    //     String iCustomer[] = null;
    //   String nCustomer[] = null;
    // int e = 0;
    //if (cust[e] instanceof InternationalCustomer) {
    //  for (int i = 0; i < tokenlist.size(); i++) {
    //
    //              iCustomer[i] = iCustomer[i] + tokenlist.get(i);
    //
    //          }
    //
    //          e++;
    //    } else {
    //      for (int i = 0; i < tokenlist.size(); i++) {
    //        nCustomer[i] = nCustomer[i] + tokenlist.get(i);
    //  }
    //}
    //}
}
