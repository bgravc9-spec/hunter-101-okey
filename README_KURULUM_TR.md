# HUNTER GAMES – 101 Okey APK (GitHub Actions)

Bu depo **yalnızca 101 Okey** oyununu Android APK’ye dönüştürür. Oyun HTML’si APK içinde bulunur.

## GitHub üzerinden APK alma

1. ZIP dosyasını bilgisayarda **çıkar** (GitHub’a ZIP’i tek dosya olarak yükleme).
2. GitHub’da yeni **Public** veya **Private** depo oluştur. Örn. `hunter-101-okey`.
3. Depoda **Add file → Upload files** ile ZIP’ten çıkan **dosya ve klasörlerin tamamını** yükle; `app`, `.github`, `settings.gradle`, `build.gradle`, `gradle.properties` depo kökünde görünmeli.
4. Eğer `.github` klasörü görünmediyse GitHub **Add file → Create new file** ile dosya yolunu **`.github/workflows/build-apk.yml`** yaz ve yanında verilen YAML dosyasının tamamını yapıştır.
5. **Commit changes** de. GitHub **Actions → HUNTER 101 Okey APK → Run workflow** aç. Bazı depolarda Actions ilk önce etkinleştirilmelidir.
6. Yeşil tik çıkınca çalıştırma detayındaki **Artifacts → HUNTER_GAMES_101_OKEY_APK** dosyasını indir ve ZIP içindeki APK’yi telefonuna kur.

> Not: Bu bir **debug APK**’dir. Android üzerinden doğrudan kurmak/test etmek için uygundur. Google Play yayını ve kalıcı güncelleme için kendi imza anahtarınla *release* derleme gerekir.

## Ne var?

- En son HUNTER GAMES V45 101 Okey oynanışı, V44 masa tasarımı, Seri/Çift skorları.
- Botlarla antrenman, aynı telefonda 4 oyunculu yerel mod, online oda kullanıcı arayüzü.
- Android WebView üzerinde HTTPS varlık kökeni (WebViewAssetLoader); HTML çevrimdışı olarak açılır.
- Android uygulama etiketi: **HUNTER 101 Okey**; paket adı `com.huntergames.okey101`.
- Android en az API 24 (Android 7.0); yatay kullanım.

## Online mod hakkında önemli ayrım

**APK üretmek online sunucuyu kurmaz.** Gerçek 4 telefonla oynama için Firebase projesi, Auth/Firestore ve V45 `functions` sunucusunun yayınlanması gerekir. V45 oyun kuralları sunucuda ayrıca canlı sınanmalıdır. APK'deki Firebase ayarları HUNTER GAMES içindeki ayarlar ekranından girilir; kendi Firebase projenin bilgilerini kullan.

Google hesabıyla tarayıcı penceresi açan OAuth akışları Android WebView içinde sınırlanabilir. Firebase e-posta/şifre girişi ve ağ istekleri gerçek cihazda ayrıca doğrulanmalıdır. Firebase veritabanı erişim izinlerini sıkı tut.

## Proje düzeni

```
.github/workflows/build-apk.yml
app/src/main/assets/index.html            ← SADECE 101 OKEY HTML
app/src/main/java/com/huntergames/okey101/MainActivity.java
app/src/main/AndroidManifest.xml
app/src/main/res/mipmap-*/ic_launcher.png
app/build.gradle
build.gradle
settings.gradle
gradle.properties
```

## Sorun olursa

- Actions görünmüyorsa depoda Actions'ı etkinleştir, `.github/workflows/build-apk.yml` yolunu kontrol et.
- `No matching SDK` hatasında Actions loglarındaki Android SDK kurulum adımını kontrol et.
- `Could not find com.android.tools.build:gradle` hatasında ağ veya `google()` deposu bağlantısını kontrol et.
- Oyun açılıyor ama online giriş başarısızsa bu APK derleme hatası değil; Firebase sunucusunu ve Auth ayarlarını kontrol et.

**Uyarı:** Burada yalnızca proje dosyalarının bütünlüğü ve HTML/JS doğrulaması yapıldı. APK derlemesi GitHub Actions üzerinde gerçekleşecek; canlı APK henüz bu ortamda üretilmedi.
