import java.time.LocalDate; // Untuk merepresentasikan tanggal
import java.time.format.DateTimeFormatter; // Untuk format tampilan tanggal
import java.util.Scanner; // Untuk menerima input dari user

class Acara {
    LocalDate tanggal;       // Tanggal acara
    String nama;             // Nama acara
    String lokasi;           // Lokasi acara
    String deskripsi;        // Deskripsi acara

        // Konstruktor untuk inisialisasi data acara
        public Acara(LocalDate tanggal, String nama, String lokasi, String deskripsi) {
            this.tanggal = tanggal;
            this.nama = nama;
            this.lokasi = lokasi;
            this.deskripsi = deskripsi;
        }

            // Method untuk mencetak informasi acara dalam format rapi
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy"); // Format: 20 Mei 2025
        return String.format("Tanggal: %s\nNama Acara: %s\nLokasi: %s\nDeskripsi: %s",
                             tanggal.format(formatter), nama, lokasi, deskripsi);
    }
}

public class PencarianAcara {
    public static void main(String[] args) {
                // Array data acara yang sudah terurut berdasarkan tanggal
                Acara[] jadwalAcara = {
                    new Acara(LocalDate.of(2025, 5, 10), "Workshop Java", "Ruang Pelatihan A", "Workshop dasar pemrograman Java"),
                    new Acara(LocalDate.of(2025, 5, 15), "Seminar AI", "Aula Utama", "Seminar tentang perkembangan Artificial Intelligence"),
                    new Acara(LocalDate.of(2025, 5, 20), "Kompetisi Coding", "Lab Komputer", "Kompetisi coding untuk mahasiswa"),
                    new Acara(LocalDate.of(2025, 5, 25), "Tech Talk", "Auditorium", "Diskusi tentang teknologi terbaru"),
                    new Acara(LocalDate.of(2025, 6, 1), "Career Fair", "Gedung Serbaguna", "Pameran karir bidang IT"),
                    new Acara(LocalDate.of(2025, 6, 5), "Webinar Cloud Computing", "Online", "Webinar tentang teknologi cloud"),
                    new Acara(LocalDate.of(2025, 6, 10), "Hackathon", "Co-Working Space", "Hackathon 24 jam"),
                    new Acara(LocalDate.of(2025, 6, 15), "Workshop Database", "Ruang Pelatihan B", "Workshop database SQL dan NoSQL"),
                    new Acara(LocalDate.of(2025, 6, 20), "Game Development Talk", "Ruang Multimedia", "Diskusi tentang pengembangan game")
                };
        
                Scanner scanner = new Scanner(System.in); // Buat Scanner untuk menerima input dari user

                System.out.println("=== SISTEM PENCARIAN ACARA ===");
                System.out.println("Format tanggal: yyyy-MM-dd (contoh: 2025-05-20)");
                System.out.print("Masukkan tanggal yang ingin dicari: ");
                String tanggalInput = scanner.nextLine(); // Ambil input tanggal dari user
        
                try {
                    // Konversi input string menjadi objek LocalDate
                    LocalDate tanggalCari = LocalDate.parse(tanggalInput);

                              // Lakukan pencarian binary search berdasarkan tanggal
            int index = cariAcaraByTanggal(jadwalAcara, tanggalCari);

            System.out.println("\nHASIL PENCARIAN:");
            if (index != -1) {
                System.out.println("Acara ditemukan pada tanggal " + tanggalInput + "!");
                System.out.println(jadwalAcara[index]); // Tampilkan detail acara
            } else {
                System.out.println("Tidak ada acara yang terjadwal pada tanggal " + tanggalInput + ".");
            }
        } catch (Exception e) {
            // Jika input salah format atau gagal di-parse
            System.out.println("Format tanggal tidak valid. Gunakan format yyyy-MM-dd.");
        }

        scanner.close(); // Tutup scanner
    }

        // Fungsi pencarian binary search berdasarkan tanggal
        public static int cariAcaraByTanggal(Acara[] jadwalAcara, LocalDate tanggal) {
            int low = 0; // Indeks awal
            int high = jadwalAcara.length - 1; // Indeks akhir
    
            while (low <= high) {
                int mid = low + (high - low) / 2; // Hitung indeks tengah
    
                         // Jika tanggal di tengah lebih besar (lebih lambat), cari di kiri
            if (jadwalAcara[mid].tanggal.isAfter(tanggal)) {
                high = mid - 1;
            }
            // Jika tanggal di tengah lebih kecil (lebih awal), cari di kanan
            else {
                low = mid + 1;
            }
        }

        // Jika tidak ditemukan
        return -1;
    }
}
   