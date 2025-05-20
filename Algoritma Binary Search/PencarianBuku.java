import java.util.Scanner; // Mengimpor class Scanner untuk membaca input dari keyboard

class Buku { // Membuat class Buku sebagai blueprint data buku
    String isbn;        // Nomor ISBN buku
    String judul;       // Judul buku
    String penulis;     // Nama penulis buku
    int tahunTerbit;    // Tahun terbit buku

    // Konstruktor untuk menginisialisasi objek Buku
    public Buku(String isbn, String judul, String penulis, int tahunTerbit) {
        this.isbn = isbn;
        this.judul = judul;
        this.penulis = penulis;
        this.tahunTerbit = tahunTerbit;
    }

    // Override method toString untuk menampilkan informasi buku dengan rapi
    @Override
    public String toString() {
        return String.format("ISBN: %s\nJudul: %s\nPenulis: %s\nTahun Terbit: %d",
                             isbn, judul, penulis, tahunTerbit);
    }
}

public class PencarianBuku {
    public static void main(String[] args) {
        // Data array buku yang sudah diurutkan berdasarkan ISBN (ascending)
        Buku[] daftarBuku = {
            new Buku("9780071606301", "Java: The Complete Reference", "Herbert Schildt", 2007),
            new Buku("9780132222204", "Effective Java", "Joshua Bloch", 2008),
            new Buku("9780132778046", "Head First Java", "Kathy Sierra & Bert Bates", 2005),
            new Buku("9780134685991", "Effective Python", "Brett Slatkin", 2019),
            new Buku("9780135957059", "Clean Code", "Robert C. Martin", 2008),
            new Buku("9780137081073", "The Clean Coder", "Robert C. Martin", 2011),
            new Buku("9780262033848", "Introduction to Algorithms", "Cormen, Leiserson, Rivest & Stein", 2009),
            new Buku("9780321356680", "Effective Java", "Joshua Bloch", 2008),
            new Buku("9780596009205", "Head First Design Patterns", "Eric Freeman & Elisabeth Robson", 2004)
        };

        Scanner scanner = new Scanner(System.in); // Membuat objek Scanner untuk input

        System.out.println("=== SISTEM PENCARIAN BUKU PERPUSTAKAAN ===");
        System.out.print("Masukkan nomor ISBN buku yang dicari: ");
        String isbnCari = scanner.nextLine(); // Menerima input ISBN dari user

        // Melakukan pencarian dengan binary search
        int index = cariBukuByISBN(daftarBuku, isbnCari);

        // Menampilkan hasil pencarian
        System.out.println("\nHASIL PENCARIAN:");
        if (index != -1) {
            System.out.println("Buku ditemukan!");
            System.out.println(daftarBuku[index]); // Menampilkan informasi buku yang ditemukan
        } else {
            System.out.println("Buku dengan ISBN " + isbnCari + " tidak ditemukan.");
        }

        scanner.close(); // Menutup scanner
    }

      // Fungsi binary search untuk mencari buku berdasarkan ISBN
      public static int cariBukuByISBN(Buku[] daftarBuku, String isbn) {
        int low = 0; // Awal indeks
        int high = daftarBuku.length - 1; // Akhir indeks

        while (low <= high) {
            int mid = low + (high - low) / 2; // Hitung indeks tengah

            // Bandingkan ISBN di tengah dengan ISBN yang dicari
            int comparison = daftarBuku[mid].isbn.compareTo(isbn);

            if (comparison == 0) {
                return mid; // Jika ISBN cocok, kembalikan indeks
            }

            if (comparison > 0) {
                high = mid - 1; // Jika ISBN tengah lebih besar, cari di sebelah kiri
            } else {
                low = mid + 1; // Jika lebih kecil, cari di sebelah kanan
            }
        }

        return -1; // Jika tidak ditemukan
    }
}
  