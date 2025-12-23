# 🎲 Java Emlak Oyunu (Monopoly Clone) - v2.0

Bu proje, Java programlama dilini ve algoritma mantığını öğrenme sürecimde geliştirdiğim, klasik Monopoly oyununun mekaniklerini simüle eden **konsol tabanlı** bir oyundur.

## 📝 Proje Hakkında

**v2.0 Güncellemesi Yayında!** 🚀
Başlangıçta (v1.0) sadece oyunun mantığını oturtmak için geliştirdiğim prototip, artık çok daha gelişmiş, çok oyunculu ve dinamik bir yapıya kavuştu. Kod yapısı optimize edildi, oyun akışı hızlandı ve rekabet unsurları eklendi.

Bu proje; Java'daki **Collections (ArrayList)**, **OOP prensipleri**, **Modüler Metot Yapısı** ve **Algoritma Kurma** yeteneklerimi sergilemektedir.

## 🔥 v2.0 Yenilikleri (What's New?)

Eski sürüme (v1.0) kıyasla yapılan majör değişiklikler:

* **👥 4 Oyuncu Desteği:** Oyun artık 2 kişiyle sınırlı değil! 4 kişiye kadar (Araba, Gemi, Uçak, Tren) destekliyor.
* **🏎️ Karakter Seçimi:** Her oyuncu kendine has bir piyon (String tabanlı sembol) ile temsil ediliyor.
* **💀 Battle Royale İflas Sistemi:**
    * İflas eden oyuncu oyundan atılır.
    * **ÖNEMLİ:** İflas eden kişinin tapuları "sahipsiz" duruma düşer ve tekrar satın alınabilir hale gelir.
    * Oyun, tek bir kişi kalana kadar devam eder.
* **🎲 Düşeş (Double) Sistemi:** Zar çift gelirse (örn: 4-4), oyuncu tekrar oynama hakkı kazanır.
* **⚡ Optimize Kod Yapısı:** Eski "Spaghetti" yapı temizlendi. İşlemler metotlara (Methods) bölünerek daha okunabilir, düzenli ve hızlı bir yapıya geçildi.
* **💸 Bankacılık Sistemi:** Kira ödemeleri artık havaya gitmiyor, doğrudan mülk sahibinin hesabına "EFT" yapılıyor.

## 🚀 Mevcut Özellikler

* **Dinamik Harita:** Farklı fiyat ve getiri oranlarına sahip, seviye seviye artan 30 farklı semt.
* **Sıralama Algoritması:** Oyuna kimin başlayacağı, oyun başında atılan zarlara göre (Bubble Sort mantığıyla) belirlenir.
* **Akıllı Döngü:** Oyun sırası modüler aritmetik ile yönetilir, kimse sırasını kaybetmez.
* **Detaylı Loglama:** Konsol ekranında kimin ne kadar parası kaldı, nereye gitti, kime ödeme yaptı anlık olarak raporlanır.

## 🗺️ Yol Haritası (Roadmap)

Geliştirme süreci devam ediyor. Gelecek sürümler (v3.0+) için planlanan özellikler:

- [ ] **👮 Kodes (Hapis) Sistemi:** Belirli karelerde oyuncunun cezalı duruma düşmesi.
- [ ] **🃏 Şans ve Kamu Kartları:** Oyuncuya rastgele para kazandıran veya kaybettiren kart destesi.
- [ ] **⚖️ 3 Kere Düşeş Kuralı:** Üst üste 3 kere çift atan oyuncunun "hile yaptığı" varsayılıp kodese gönderilmesi.
- [ ] **🔨 Açık Arttırma:** Satın alınmayan mülklerin diğer oyuncular arasında ihaleye çıkması.
- [ ] **💾 Save/Load:** Oyunun durumunu kaydedip sonra devam edebilme.

## 🛠️ Kurulum ve Çalıştırma

1.  Projeyi klonlayın:
    ```bash
    git clone [https://github.com/SuleymanKilincc/Java-Monopoly-Game.git](https://github.com/SuleymanKilincc/Java-Monopoly-Game.git)
    ```
2.  Favori IDE'nizi (IntelliJ IDEA, Eclipse, VS Code) açın.
3.  `Main.java` dosyasını çalıştırın.
4.  Konsol ekranını genişletin ve oyunun keyfini çıkarın!

---
## 👨‍💻 Geliştirici Notu

v1.0 sürümündeki "Make it Work" (Sadece çalışsın) felsefesinden, v2.0 ile "Make it Right" (Doğru ve Temiz olsun) felsefesine geçiş yaptım. Bu proje benim için sadece bir oyun değil, aynı zamanda **Refactoring** (Kod İyileştirme) sürecini öğrendiğim canlı bir laboratuvar.

*Geliştirici: [Süleyman Kılınç]*
*Java Learning Journey 2024-2025*
