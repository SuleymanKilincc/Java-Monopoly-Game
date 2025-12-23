import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Main {

    static String[] karakterler = {"Araba", "Gemi", "Uçak", "Tren"};
    static int[] numaralar = new int[4];
    static ArrayList<YerBilgisi> yer = new ArrayList<>();
    static ArrayList<Oyuncular> oyuncu = new ArrayList<>();
    static Scanner input = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {

        yerleriYukle();

        // Giris ekrani
        System.out.println("========================================");
        System.out.println("      MONOPOLY OYUNUNA HOS GELDINIZ     ");
        System.out.println("========================================\n");

        System.out.println("Karakterler:");
        System.out.println("1. Araba \t 2. Gemi");
        System.out.println("3. Ucak  \t 4. Tren\n");

        System.out.println(">>> Baslangic sirasi icin [ENTER] tusuna basin...");

        // Ilk siralama islemleri
        baslamaZari();
        baslangicSirasi();
        oyunculariYukle();

        System.out.println("\n----------- OYUN SIRALAMASI -----------");
        for (int i = 0; i < oyuncu.size(); i++) {
            System.out.println((i + 1) + ". " + oyuncu.get(i).isim);
        }
        System.out.println("---------------------------------------");

        System.out.println("\n>>> OYUN BASLIYOR! Lider: " + karakterler[0].toUpperCase());

        int siraNo = 0;

        // Oyun dongusu - tek kisi kalana kadar doner
        while (oyuncu.size() > 1) {

            // Siradaki oyuncuyu buluyoruz
            Oyuncular aktifOyuncu = oyuncu.get(siraNo % oyuncu.size());

            System.out.println("\n============================================================");
            System.out.printf("SIRA: %-10s | BAKIYE: %.2f M $ | KONUM: %d%n",
                    aktifOyuncu.isim.toUpperCase(), aktifOyuncu.para, aktifOyuncu.eskiYer);
            System.out.println("============================================================");

            System.out.println(">>> Zar atmak icin [ENTER] yapin...");
            input.nextLine();

            // Zar atma kismi
            int[] zar1 = zarVerDizi();
            int toplamZar1 = zar1[0] + zar1[1];
            System.out.println("[ZAR] Atilan: " + zar1[0] + " - " + zar1[1] + " (Toplam: " + toplamZar1 + ")");

            int ilerlenecekKare;

            // Duses kontrolu (cift gelirse tekrar atar)
            if (zar1[0] == zar1[1]) {
                System.out.println("!!! DUSES GELDI! Ekstra zar atiliyor...");
                int[] zar2 = zarVerDizi();
                int toplamZar2 = zar2[0] + zar2[1];
                System.out.println("[ZAR] 2. Atis: " + zar2[0] + " - " + zar2[1] + " (Toplam: " + toplamZar2 + ")");

                ilerlenecekKare = toplamZar1 + toplamZar2;
                System.out.println(">>> Toplam Ilerleme: " + ilerlenecekKare + " kare");
            } else {
                ilerlenecekKare = toplamZar1;
            }

            // Oyuncuyu ilerletme (mod kullanarak harita disina cikmasini engelliyoruz)
            aktifOyuncu.eskiYer = (aktifOyuncu.eskiYer + ilerlenecekKare) % yer.size();
            YerBilgisi konum = yer.get(aktifOyuncu.eskiYer);

            String sahipBilgisi = (konum.sahibi == null) ? "SATILIK" : konum.sahibi;
            System.out.println("\n[KONUM]: " + konum.yerAdi.toUpperCase());
            System.out.println("(Kare: " + konum.yerSirasi + " | Durum: " + sahipBilgisi + ")");

            // Konum islemleri
            if (konum.sahibi == null) {
                // Konum bossa satin alma
                System.out.println("---------------------------------------");
                System.out.printf("Tapu Fiyati: %.2f M $ \nKira Getirisi: %.2f M $%n", konum.tapuFiyati, konum.kiraBedeli);
                System.out.println("---------------------------------------");
                System.out.println("[B] Satin Al  |  [P] Pas Gec");
                System.out.print("Secim: ");

                String satinAlma = input.nextLine();

                if (satinAlma.equalsIgnoreCase("b")) {
                    if (aktifOyuncu.para >= konum.tapuFiyati) {
                        aktifOyuncu.para -= konum.tapuFiyati;
                        konum.sahibi = aktifOyuncu.isim;
                        System.out.println("\n+++ Satin alma basarili! " + konum.yerAdi + " artik sizin.");
                        System.out.printf("Kalan Bakiye: %.2f M $%n", aktifOyuncu.para);
                    } else {
                        System.out.println("\n[!] Yetersiz bakiye! Islem iptal.");
                    }
                } else {
                    System.out.println("\n>>> Pas gecildi.");
                }
                siraNo++;

            } else if (konum.sahibi.equals(aktifOyuncu.isim)) {
                System.out.println("\n[INFO] Burasi zaten sizin mulkunuz.");
                siraNo++;
            } else {
                // Kira odeme zamani
                System.out.println("\n$$$ KIRA VAKTI! $$$");
                System.out.printf("Mulk Sahibi: %s | Odenecek: %.2f M $%n", konum.sahibi, konum.kiraBedeli);

                aktifOyuncu.para -= konum.kiraBedeli;

                // Parayi mulk sahibine aktarma
                for (Oyuncular mulkSahibi : oyuncu) {
                    if (mulkSahibi.isim.equals(konum.sahibi)) {
                        mulkSahibi.para += konum.kiraBedeli;
                        System.out.printf(">>> TRANSFER: %s hesabina +%.2f M $ eklendi.%n", mulkSahibi.isim, konum.kiraBedeli);
                        break;
                    }
                }

                // Iflas kontrolu
                if (aktifOyuncu.para >= 0) {
                    System.out.printf("\n[OK] Odeme Basarili. Kalan: %.2f M $%n", aktifOyuncu.para);
                    siraNo++;
                } else {
                    System.out.println("\n!!! IFLAS !!!");
                    System.out.println("OYUNCU ELENDI: " + aktifOyuncu.isim);

                    // Tapulari bosa cikarma
                    for (YerBilgisi y : yer) {
                        if (y.sahibi != null && y.sahibi.equals(aktifOyuncu.isim)) {
                            y.sahibi = null;
                            System.out.println(">>> Iade: " + y.yerAdi + " artik sahipsiz.");
                        }
                    }
                    // Oyuncuyu sil (Sira numarasini artirmiyoruz cunku liste kaydi)
                    oyuncu.remove(aktifOyuncu);

                }
            }
        }

        // Oyun sonu ekrani
        System.out.println("\n############################################");
        System.out.println("#              OYUN SONA ERDI              #");
        System.out.println("############################################");
        System.out.println("KAZANAN: " + oyuncu.get(0).isim.toUpperCase());
        System.out.printf("KASA: %.2f M $%n", oyuncu.get(0).para);
        System.out.println("############################################");
    }

    // Semtleri ve fiyatlari ekliyoruz
    public static void yerleriYukle() {
        // 1. Seviye
        yer.add(new YerBilgisi("Esenler", 0.5, 0.1, 1));
        yer.add(new YerBilgisi("Bagcilar", 0.5, 0.1, 2));
        yer.add(new YerBilgisi("Sultanbeyli", 0.6, 0.15, 3));
        yer.add(new YerBilgisi("Gungoren", 0.6, 0.15, 4));
        yer.add(new YerBilgisi("Zeytinburnu", 0.7, 0.2, 5));
        yer.add(new YerBilgisi("Gaziosmanpasa", 0.7, 0.2, 6));

        // 2. Seviye
        yer.add(new YerBilgisi("Uskudar", 0.8, 0.25, 7));
        yer.add(new YerBilgisi("Umraniye", 0.8, 0.25, 8));
        yer.add(new YerBilgisi("Kartal", 0.9, 0.3, 9));
        yer.add(new YerBilgisi("Pendik", 0.9, 0.3, 10));
        yer.add(new YerBilgisi("Maltepe", 1.0, 0.35, 11));
        yer.add(new YerBilgisi("Kagithane", 1.1, 0.35, 12));

        // 3. Seviye
        yer.add(new YerBilgisi("Fatih", 1.3, 0.4, 13));
        yer.add(new YerBilgisi("Beyoglu", 1.4, 0.45, 14));
        yer.add(new YerBilgisi("Sisli", 1.5, 0.5, 15));
        yer.add(new YerBilgisi("Mecidiyekoy", 1.6, 0.55, 16));
        yer.add(new YerBilgisi("Besiktas", 1.8, 0.6, 17));
        yer.add(new YerBilgisi("Bakirkoy", 2.0, 0.65, 18));

        // 4. Seviye
        yer.add(new YerBilgisi("Atakoy", 2.2, 0.7, 19));
        yer.add(new YerBilgisi("Florya", 2.4, 0.8, 20));
        yer.add(new YerBilgisi("Atasehir", 2.5, 0.85, 21));
        yer.add(new YerBilgisi("Kadikoy", 2.7, 0.9, 22));
        yer.add(new YerBilgisi("Bostanci", 2.9, 1.0, 23));
        yer.add(new YerBilgisi("Caddebostan", 3.2, 1.1, 24));

        // 5. Seviye
        yer.add(new YerBilgisi("Suadiye", 3.5, 1.2, 25));
        yer.add(new YerBilgisi("Kalamis", 3.8, 1.3, 26));
        yer.add(new YerBilgisi("Levent", 4.0, 1.4, 27));
        yer.add(new YerBilgisi("Etiler", 4.2, 1.5, 28));
        yer.add(new YerBilgisi("Nisantasi", 4.5, 1.7, 29));
        yer.add(new YerBilgisi("Bebek", 5.0, 2.0, 30));

    }

    public static void oyunculariYukle() {

        for (String isim : karakterler) {
            oyuncu.add(new Oyuncular(isim, 15.00, 0));

        }
    }

    public static int[] zarVerDizi() {

        int zar1 = random.nextInt(6) + 1;
        int zar2 = random.nextInt(6) + 1;
        return new int[]{zar1, zar2};

    }

    // Baslangic zar atisi
    public static void baslamaZari() {

        while (true) {
            String zarAt = input.nextLine();
            if (zarAt.isEmpty()) {
                for (int i = 0; i < 4; i++) {
                    int[] zar = zarVerDizi();
                    numaralar[i] = zar[0] + zar[1];
                    System.out.println(karakterler[i] + " : " + numaralar[i] + " atti");
                }
                break;
            } else {
                System.out.println("Yanlis tus! Devam etmek icin [ENTER] tusuna basiniz.");
            }
        }

    }

    // Bubble sort ile siralama
    public static void baslangicSirasi() {

        for (int i = 0; i < numaralar.length - 1; i++) {
            for (int j = 0; j < numaralar.length - i - 1; j++) {
                if (numaralar[j] < numaralar[j + 1]) {
                    int tempNo = numaralar[j];
                    numaralar[j] = numaralar[j + 1];
                    numaralar[j + 1] = tempNo;

                    String tempName = karakterler[j];
                    karakterler[j] = karakterler[j + 1];
                    karakterler[j + 1] = tempName;

                }
            }
        }
    }
}