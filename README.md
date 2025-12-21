# 🎲 Java Emlak Oyunu (Monopoly Clone) - v1.0

Bu proje, Java programlama dilini öğrenme sürecimde geliştirdiğim, klasik Monopoly oyununun mantığını simüle eden **konsol tabanlı** bir oyundur.

## 📝 Proje Hakkında

Bu proje şu an **v1.0 (Initial Release)** aşamasındadır. Temel oyun döngüsü, ekonomi yönetimi ve yapay zeka (Bot) mantığı başarıyla çalışmaktadır. Projenin amacı, Java'daki temel veri yapılarını (ArrayList, Class) ve algoritma mantığını (Modüler aritmetik, Döngüler) pekiştirmektir.

## 🚀 Mevcut Özellikler (v1.0)

* **2 Oyuncu Desteği:** 1 Gerçek Oyuncu vs 1 Bot.
* **Dinamik Harita:** Farklı fiyat ve kira bedellerine sahip 30 farklı semt.
* **Ekonomi Sistemi:**
    * Tapu satın alma.
    * Kira ödeme mantığı.
    * Bakiye yönetimi ve İflas kontrolü.
* **Oyun Mekaniği:**
    * Zar atma ve haritada ilerleme.
    * Sonsuz döngü (Harita bitince başa dönme).
    * Sıra tabanlı oyun akışı.

## 🚧 Geliştirme Süreci ve Kod Yapısı

### ⚠️ v1.0 Durumu: "Make it Work"
Şu anki sürüm, oyunun mantığını oturtmak amacıyla **Prototip** olarak geliştirilmiştir. Kod yapısı şu an için **"Monolithic / Procedural"** (Spaghetti Code) yapısındadır.
* Tüm oyun mantığı `Main` sınıfı içerisinde toplanmıştır.
* Amaç öncelikle çalışan bir ürün ortaya koymaktır.

### 🎯 Hedeflenen Yapı (Refactoring)
Bir sonraki güncellemelerde proje, **Nesne Yönelimli Programlama (OOP)** prensiplerine uygun olarak yeniden tasarlanacaktır. "Spaghetti" yapıdan, modüler ve temiz bir yapıya geçiş süreci bu projenin ana öğrenim hedeflerinden biridir.

## 🗺️ Yol Haritası (Roadmap)

Projenin gelecekteki sürümleri için planlanan güncellemeler şunlardır:

### v2.0 - Clean Code & Refactoring
- [ ] `Main` sınıfındaki yükün azaltılması.
- [ ] `OyunYonetici` (GameManager) sınıfının oluşturulması.
- [ ] Kullanıcı girişleri ve Harita işlemlerinin ayrı sınıflara bölünmesi.
- [ ] Kod tekrarının önlenmesi ve optimizasyon.

### v3.0 - Gameplay Update
- [ ] **Oyuncu Sayısı:** 2 kişiden 4 kişiye çıkarılması.
- [ ] **Şans Kartları:** "Kader Çarkı" ve "Kamu Fonu" benzeri kart mekanikleri.
- [ ] **Hapis Cezası:** Belirli karelerde bekleme cezası.
- [ ] **Müzayede:** Satın alınmayan yerlerin açık artırmaya çıkması.

## 🛠️ Kurulum ve Çalıştırma

1.  Projeyi klonlayın:
    ```bash
    git clone [https://github.com/KULLANICI_ADIN/REPO_ADIN.git](https://github.com/KULLANICI_ADIN/REPO_ADIN.git)
    ```
2.  Favori IDE'nizi (IntelliJ IDEA, Eclipse vb.) açın.
3.  `Main.java` dosyasını çalıştırın.
4.  Konsol ekranındaki yönergeleri takip ederek oyunu oynayın.

---
*Geliştirici: [Senin Adın]*
*Java Learning Journey 2024*
