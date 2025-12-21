import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Main {

    static ArrayList<YerBilgisi> yer = new ArrayList<>();
    static ArrayList<Oyuncular> oyuncu = new ArrayList<>();
    static Scanner input = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {

        yer.add(new YerBilgisi("Esenler", 0.5, 0.1, 1));
        yer.add(new YerBilgisi("Bağcılar", 0.5, 0.1, 2));
        yer.add(new YerBilgisi("Sultanbeyli", 0.6, 0.15, 3));
        yer.add(new YerBilgisi("Güngören", 0.6, 0.15, 4));
        yer.add(new YerBilgisi("Zeytinburnu", 0.7, 0.2, 5));
        yer.add(new YerBilgisi("Gaziosmanpaşa", 0.7, 0.2, 6));

        yer.add(new YerBilgisi("Üsküdar", 0.8, 0.25, 7));
        yer.add(new YerBilgisi("Ümraniye", 0.8, 0.25, 8));
        yer.add(new YerBilgisi("Kartal", 0.9, 0.3, 9));
        yer.add(new YerBilgisi("Pendik", 0.9, 0.3, 10));
        yer.add(new YerBilgisi("Maltepe", 1.0, 0.35, 11));
        yer.add(new YerBilgisi("Kağıthane", 1.1, 0.35, 12));

        yer.add(new YerBilgisi("Fatih", 1.3, 0.4, 13));
        yer.add(new YerBilgisi("Beyoğlu", 1.4, 0.45, 14));
        yer.add(new YerBilgisi("Şişli", 1.5, 0.5, 15));
        yer.add(new YerBilgisi("Mecidiyeköy", 1.6, 0.55, 16));
        yer.add(new YerBilgisi("Beşiktaş", 1.8, 0.6, 17));
        yer.add(new YerBilgisi("Bakırköy", 2.0, 0.65, 18));

        // --- 4. SEVİYE: NEZİH VE PAHALI SEMTLER (2.2M - 3.2M) ---
        yer.add(new YerBilgisi("Ataköy", 2.2, 0.7, 19));
        yer.add(new YerBilgisi("Florya", 2.4, 0.8, 20));
        yer.add(new YerBilgisi("Ataşehir", 2.5, 0.85, 21));
        yer.add(new YerBilgisi("Kadıköy", 2.7, 0.9, 22));
        yer.add(new YerBilgisi("Bostancı", 2.9, 1.0, 23));
        yer.add(new YerBilgisi("Caddebostan", 3.2, 1.1, 24));

        // --- 5. SEVİYE: LÜKS VE OYUN BİTİRENLER (3.5M - 5.0M) ---
        yer.add(new YerBilgisi("Suadiye", 3.5, 1.2, 25));
        yer.add(new YerBilgisi("Kalamış", 3.8, 1.3, 26));
        yer.add(new YerBilgisi("Levent", 4.0, 1.4, 27));
        yer.add(new YerBilgisi("Etiler", 4.2, 1.5, 28));
        yer.add(new YerBilgisi("Nişantaşı", 4.5, 1.7, 29));
        yer.add(new YerBilgisi("Bebek", 5.0, 2.0, 30));

        oyuncu.add(new Oyuncular("Oyuncu 1", 15.0, 0));
        oyuncu.add(new Oyuncular("Oyuncu 2", 15.0, 0));

        boolean oyunBitti = false;
        int siraNo = 0;

        while (!oyunBitti) {
            Oyuncular aktifOyuncu = oyuncu.get(siraNo % oyuncu.size());
            System.out.println("------------------------");


            System.out.printf("Sırası gelen oyuncu : %s Mevcut para : %.2f M $%n", aktifOyuncu.isim, aktifOyuncu.para);

            YerBilgisi konum;
            while (true) {
                System.out.println("Lütfen zar atmak için space e basınız");
                String zarAt = input.nextLine();

                if (zarAt.equals(" ")) {
                    int zar = zarVer();
                    System.out.println("Zar atıldı , ilerlenecek kare sayısı : " + zar);
                    aktifOyuncu.eskiYer = (aktifOyuncu.eskiYer + zar) % yer.size();
                    konum = yer.get(aktifOyuncu.eskiYer);
                    System.out.println("Konumunuz : " + konum.yerAdi + "    Sahibi : " + konum.sahibi + "   " + konum.yerSirasi + ". karedesiniz");
                    break;
                } else {
                    System.out.println("Yanlış tuşsa bastınız tekrar deneyin");
                }
            }

            if (konum.sahibi == null) {
                System.out.printf("Tapu bedeli : %.2f Kira bedeli : %.2f%n", konum.tapuFiyati, konum.kiraBedeli);

                System.out.println("Almak için b ye pas geçmek için p tuşuna basınız");
                String satinAlma = input.nextLine();
                if (satinAlma.equals("b") && aktifOyuncu.para >= konum.tapuFiyati) {
                    aktifOyuncu.para -= konum.tapuFiyati;
                    System.out.println(konum.yerAdi + " Tapusunu aldınız");
                    System.out.printf("Kalan paranız : %.2f%n", aktifOyuncu.para);
                    konum.sahibi = aktifOyuncu.isim;
                    System.out.println("Sıradaki oyuncuya geçiliyor");
                } else if (satinAlma.equals("b") && aktifOyuncu.para < konum.tapuFiyati) {
                    System.out.println("Maalesef tapuya yetecek paranız yok sıradaki oyuncuya geçiliyor");
                } else {
                    System.out.println("Pas geçtiniz sıradaki oyuncuya geçiliyor");
                }
            } else if (konum.sahibi.equals(aktifOyuncu.isim)) {
                System.out.println("Tapu zaten sizin sıradaki oyuncuya geçiliyor");
            } else {
                System.out.printf("Tapu diğer oyuncunun ödemeniz gereken miktar : %.2f%n", konum.kiraBedeli);

                aktifOyuncu.para -= konum.kiraBedeli;
                if (aktifOyuncu.para >= 0) {
                    System.out.printf("%s kalan paranız : %.2f%n", aktifOyuncu.isim, aktifOyuncu.para);
                } else {
                    System.out.println(aktifOyuncu.isim + " iflas etti");
                    System.out.println("Diğer oyuncu kazandı");
                    oyunBitti = true;
                }
            }
            siraNo++;
        }
    }

    public static int zarVer() {
        int zar1 = random.nextInt(6) + 1;
        int zar2 = random.nextInt(6) + 1;
        return (zar1 + zar2);
    }
}