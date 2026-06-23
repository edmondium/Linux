import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

// ==========================================
// 1. ABSTRACT CLASS & ENCAPSULATION
// ==========================================
abstract class MenuItem {
    private String nama;
    private double harga;
    private String kategori;

    public MenuItem(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }

    // Getter and Setter (Encapsulation)
    public String getNama() { return nama; }
    public double getHarga() { return harga; }
    public String getKategori() { return kategori; }

    // Abstract Method untuk Polymorphism
    public abstract void tampilMenu();
    
    // Format untuk penyimpanan data ke file teks
    public abstract String toFileString();
}

// ==========================================
// 2. INHERITANCE & POLYMORPHISM (SUBCLASSES)
// ==========================================
class Makanan extends MenuItem {
    private String jenisMakanan;
    private static final Locale IDN = Locale.of("id", "ID");

    public Makanan(String nama, double harga, String jenisMakanan) {
        super(nama, harga, "Makanan");
        this.jenisMakanan = jenisMakanan;
    }

    @Override
    public void tampilMenu() {
        System.out.printf(IDN, "[%s] %s - Rp%,.2f (%s)\n", getKategori(), getNama(), getHarga(), jenisMakanan);
    }

    @Override
    public String toFileString() {
        return "MAKANAN;" + getNama() + ";" + getHarga() + ";" + jenisMakanan;
    }
}

class Minuman extends MenuItem {
    private String jenisMinuman;
    private static final Locale IDN = Locale.of("id", "ID");

    public Minuman(String nama, double harga, String jenisMinuman) {
        super(nama, harga, "Minuman");
        this.jenisMinuman = jenisMinuman;
    }

    @Override
    public void tampilMenu() {
        System.out.printf(IDN, "[%s] %s - Rp%,.2f (%s)\n", getKategori(), getNama(), getHarga(), jenisMinuman);
    }

    @Override
    public String toFileString() {
        return "MINUMAN;" + getNama() + ";" + getHarga() + ";" + jenisMinuman;
    }
}

class Diskon extends MenuItem {
    private double besarDiskon; // Contoh: 10 untuk 10%

    public Diskon(String nama, double besarDiskon) {
        super(nama, 0, "Diskon"); // Harga dasar diskon diatur 0
        this.besarDiskon = besarDiskon;
    }

    public double getBesarDiskon() { return besarDiskon; }

    @Override
    public void tampilMenu() {
        System.out.printf("[%s] %s - Potongan: %.0f%%\n", getKategori(), getNama(), besarDiskon);
    }

    @Override
    public String toFileString() {
        return "DISKON;" + getNama() + ";" + besarDiskon;
    }
}

// ==========================================
// 3. CUSTOM EXCEPTION
// ==========================================
class MenuNotFoundException extends Exception {
    public MenuNotFoundException(String message) {
        super(message);
    }
}

// ==========================================
// 4. MANAGEMENT CLASSES (Menu & Pesanan)
// ==========================================
class Menu {
    private ArrayList<MenuItem> daftarMenu = new ArrayList<>();
    private final String FILE_MENU = "daftar_menu.txt";

    public void tambahItem(MenuItem item) {
        daftarMenu.add(item);
    }

    public ArrayList<MenuItem> getDaftarMenu() {
        return daftarMenu;
    }

    public void tampilkanSemuaMenu() {
        if (daftarMenu.isEmpty()) {
            System.out.println("Menu restoran masih kosong.");
            return;
        }
        System.out.println("\n=== DAFTAR MENU RESTORAN ===");
        for (int i = 0; i < daftarMenu.size(); i++) {
            System.out.print((i + 1) + ". ");
            daftarMenu.get(i).tampilMenu();
        }
    }

    public MenuItem cariItem(String nama) throws MenuNotFoundException {
        for (MenuItem item : daftarMenu) {
            if (item.getNama().equalsIgnoreCase(nama)) {
                return item;
            }
        }
        throw new MenuNotFoundException("Error: Menu dengan nama '" + nama + "' tidak ditemukan!");
    }

    // Operasi File: Simpan Menu
    public void simpanMenuKeFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_MENU))) {
            for (MenuItem item : daftarMenu) {
                writer.println(item.toFileString());
            }
            System.out.println("Daftar menu berhasil diperbarui di file.");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan menu: " + e.getMessage());
        }
    }

    // Operasi File: Muat Menu saat Program Dibuka
    public void muatMenuDariFile() {
        File file = new File(FILE_MENU);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            daftarMenu.clear();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(";");
                String tipe = tokens[0];
                if (tipe.equals("MAKANAN")) {
                    daftarMenu.add(new Makanan(tokens[1], Double.parseDouble(tokens[2]), tokens[3]));
                } else if (tipe.equals("MINUMAN")) {
                    daftarMenu.add(new Minuman(tokens[1], Double.parseDouble(tokens[2]), tokens[3]));
                } else if (tipe.equals("DISKON")) {
                    daftarMenu.add(new Diskon(tokens[1], Double.parseDouble(tokens[2])));
                }
            }
            System.out.println("Daftar menu berhasil dimuat dari file.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Gagal memuat menu dari file: " + e.getMessage());
        }
    }
}

class Pesanan {
    private ArrayList<MenuItem> itemDipesan = new ArrayList<>();
    private final String FILE_STRUK = "struk_pesanan.txt";
    private static final Locale IDN = Locale.of("id", "ID");

    public void tambahKePesanan(MenuItem item) {
        itemDipesan.add(item);
    }

    public void cetakDanSimpanStruk() {
        if (itemDipesan.isEmpty()) {
            System.out.println("Belum ada pesanan yang dicatat untuk pelanggan ini.");
            return;
        }

        StringBuilder struk = new StringBuilder();
        struk.append("\n==================================\n");
        struk.append("          STRUK RESTORAN          \n");
        struk.append("==================================\n");

        double subtotal = 0;
        double totalDiskonPersen = 0;

        for (MenuItem item : itemDipesan) {
            if (item instanceof Diskon) {
                struk.append(String.format(IDN, "%-20s : -%.0f%%\n", item.getNama(), ((Diskon) item).getBesarDiskon()));
                totalDiskonPersen += ((Diskon) item).getBesarDiskon();
            } else {
                struk.append(String.format(IDN, "%-20s : Rp%,.2f\n", item.getNama(), item.getHarga()));
                subtotal += item.getHarga();
            }
        }

        double potongan = subtotal * (totalDiskonPersen / 100);
        double totalAkhir = subtotal - potongan;

        struk.append("----------------------------------\n");
        struk.append(String.format(IDN, "%-20s : Rp%,.2f\n", "Subtotal", subtotal));
        struk.append(String.format(IDN, "%-20s : Rp%,.2f\n", "Total Potongan", potongan));
        struk.append("----------------------------------\n");
        struk.append(String.format(IDN, "%-20s : Rp%,.2f\n", "TOTAL BAYAR", totalAkhir));
        struk.append("==================================\n");

        // Tampilkan ke Konsol
        System.out.print(struk.toString());

        // Operasi File: Simpan Struk ke File Teks
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_STRUK))) {
            writer.print(struk.toString());
            System.out.println("Struk belanja berhasil dicetak ke file '" + FILE_STRUK + "'");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan struk ke file: " + e.getMessage());
        }
    }
}

// ==========================================
// 5. MAIN PROGRAM & INTERFAZ USER
// ==========================================
public class MainRestoran {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu manajemenMenu = new Menu();
        Pesanan pesananPelanggan = new Pesanan();

        // Otomatis memuat data dari file saat aplikasi dijalankan
        manajemenMenu.muatMenuDariFile();

        int pilihan = 0;
        do {
            System.out.println("\n===== APLIKASI MANAJEMEN RESTORAN =====");
            System.out.println("1. Tambah Item ke Menu");
            System.out.println("2. Tampilkan Menu Restoran");
            System.out.println("3. Terima/Tambah Pesanan Pelanggan");
            System.out.println("4. Tampilkan Struk & Simpan Transaksi");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi (1-5): ");
            
            try {
                pilihan = Integer.parseInt(scanner.nextLine());
                switch (pilihan) {
                    case 1:
                        System.out.println("\n--- Tambah Menu Baru ---");
                        System.out.print("Pilih Jenis (1. Makanan, 2. Minuman, 3. Diskon): ");
                        int jenis = Integer.parseInt(scanner.nextLine());
                        
                        System.out.print("Masukkan Nama Item: ");
                        String nama = scanner.nextLine();

                        if (jenis == 1) {
                            System.out.print("Masukkan Harga (Boleh pakai titik ribuan, misal 10.000): ");
                            String inputHarga = scanner.nextLine();
                            double harga = Double.parseDouble(inputHarga.replace(".", "")); // Sanitasi Teks ke Angka

                            System.out.print("Masukkan Jenis Makanan (Cemilan/Kuah/Goreng): ");
                            String jm = scanner.nextLine();
                            manajemenMenu.tambahItem(new Makanan(nama, harga, jm));
                        } else if (jenis == 2) {
                            System.out.print("Masukkan Harga (Boleh pakai titik ribuan, misal 5.500): ");
                            String inputHarga = scanner.nextLine();
                            double harga = Double.parseDouble(inputHarga.replace(".", "")); // Sanitasi Teks ke Angka

                            System.out.print("Masukkan Jenis Minuman (Dingin/Panas): ");
                            String jm = scanner.nextLine();
                            manajemenMenu.tambahItem(new Minuman(nama, harga, jm));
                        } else if (jenis == 3) {
                            System.out.print("Masukkan Besar Diskon Persen (Contoh: 10 untuk 10%): ");
                            double disc = Double.parseDouble(scanner.nextLine());
                            manajemenMenu.tambahItem(new Diskon(nama, disc));
                        } else {
                            System.out.println("Pilihan jenis tidak valid.");
                        }
                        manajemenMenu.simpanMenuKeFile(); // Simpan otomatis setiap perubahan menu
                        break;

                    case 2:
                        manajemenMenu.tampilkanSemuaMenu();
                        break;

                    case 3:
                        manajemenMenu.tampilkanSemuaMenu();
                        if (manajemenMenu.getDaftarMenu().isEmpty()) break;
                        
                        System.out.println("\n--- Input Pesanan (Ketik 'selesai' untuk mengakhiri) ---");
                        while (true) {
                            System.out.print("Masukkan nama menu yang dipesan: ");
                            String namaPesan = scanner.nextLine();
                            if (namaPesan.equalsIgnoreCase("selesai")) {
                                break;
                            }
                            
                            // Menangani Exception jika nama pesanan tidak sesuai/tidak terdaftar
                            try {
                                MenuItem item = manajemenMenu.cariItem(namaPesan);
                                pesananPelanggan.tambahKePesanan(item);
                                System.out.println("✓ " + item.getNama() + " berhasil ditambahkan ke daftar pesanan.");
                            } catch (MenuNotFoundException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        break;

                    case 4:
                        pesananPelanggan.cetakDanSimpanStruk();
                        break;

                    case 5:
                        System.out.println("Terima kasih! Menutup sistem manajemen restoran.");
                        break;

                    default:
                        System.out.println("Pilihan salah. Silakan masukkan angka antara 1 sampai 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Masukan menu utama harus berupa karakter angka!");
            }
        } while (pilihan != 5);

        scanner.close();
    }
}