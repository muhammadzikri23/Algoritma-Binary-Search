import java.util.Arrays;           // Digunakan untuk operasi array seperti sorting dan toString
import java.util.Comparator;       // (Tidak digunakan dalam kode ini, tapi bisa untuk perbandingan objek)
import java.util.Scanner;          // Digunakan untuk input dari pengguna

// Interface yang mendefinisikan metode perbandingan secara generik
interface Searchable<T> {
    int compare(T value);  // Mengembalikan 0 jika sama, negatif jika target < value, positif jika target > value
}

public class GenericBinarySearch {

    // Metode binary search generik
    public static <T> int binarySearch(T[] array, Searchable<T> searchable) {
        int low = 0;
        int high = array.length - 1;

        // Selama masih dalam batas array
        while (low <= high) {
            int mid = low + (high - low) / 2;  // Hindari overflow dengan rumus ini

            // Gunakan metode compare dari interface untuk membandingkan target dengan elemen tengah
            int comparison = searchable.compare(array[mid]);

            if (comparison == 0) {
                return mid;  // Ditemukan
            } else if (comparison < 0) {
                high = mid - 1;  // Cari di sebelah kiri
            } else {
                low = mid + 1;   // Cari di sebelah kanan
            }
        }

        return -1;  // Jika tidak ditemukan
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Untuk menerima input dari pengguna

        System.out.println("=== SISTEM PENCARIAN DATASET ===");
        System.out.println("Pilih jenis data yang ingin dicari:");
        System.out.println("1. Integer");
        System.out.println("2. Double");
        System.out.println("3. String");
        System.out.print("Pilihan Anda (1-3): ");

        int pilihan = scanner.nextInt();  // Menerima pilihan pengguna
        scanner.nextLine();               // Membersihkan newline di buffer setelah nextInt()

        switch (pilihan) {
            case 1:
                // Data bertipe Integer
                Integer[] dataInteger = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};

                System.out.println("\nData Integer: " + Arrays.toString(dataInteger));
                System.out.print("Masukkan nilai integer yang dicari: ");
                int targetInt = scanner.nextInt();  // Input nilai yang dicari

                // Melakukan pencarian menggunakan binarySearch
                int indexInt = binarySearch(dataInteger, new Searchable<Integer>() {
                    @Override
                    public int compare(Integer value) {
                        return targetInt - value;  // Cara membandingkan integer
                    }
                });

                // Tampilkan hasil pencarian
                if (indexInt != -1) {
                    System.out.println("Nilai " + targetInt + " ditemukan pada indeks " + indexInt);
                } else {
                    System.out.println("Nilai " + targetInt + " tidak ditemukan dalam dataset");
                }
                break;

            case 2:
                // Data bertipe Double
                Double[] dataDouble = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7, 8.8, 9.9};

                System.out.println("\nData Double: " + Arrays.toString(dataDouble));
                System.out.print("Masukkan nilai double yang dicari: ");
                double targetDouble = scanner.nextDouble();  // Input nilai yang dicari

                int indexDouble = binarySearch(dataDouble, new Searchable<Double>() {
                    @Override
                    public int compare(Double value) {
                        return Double.compare(targetDouble, value);  // Perbandingan double
                    }
                });

                // Tampilkan hasil pencarian
                if (indexDouble != -1) {
                    System.out.println("Nilai " + targetDouble + " ditemukan pada indeks " + indexDouble);
                } else {
                    System.out.println("Nilai " + targetDouble + " tidak ditemukan dalam dataset");
                }
                break;

            case 3:
                // Data bertipe String
                String[] dataString = {"alpha", "beta", "delta", "gamma", "omega", "sigma", "theta", "zeta"};
                Arrays.sort(dataString);  // Harus diurutkan dulu untuk binary search

                System.out.println("\nData String: " + Arrays.toString(dataString));
                System.out.print("Masukkan string yang dicari: ");
                String targetString = scanner.nextLine();  // Input string target

                int indexString = binarySearch(dataString, new Searchable<String>() {
                    @Override
                    public int compare(String value) {
                        return targetString.compareTo(value);  // Perbandingan string (lexicographical)
                    }
                });

                // Tampilkan hasil pencarian
                if (indexString != -1) {
                    System.out.println("String \"" + targetString + "\" ditemukan pada indeks " + indexString);
                } else {
                    System.out.println("String \"" + targetString + "\" tidak ditemukan dalam dataset");
                }
                break;

            default:
                System.out.println("Pilihan tidak valid!");
        }

        scanner.close();  // Tutup scanner untuk menghindari memory leak
    }
}