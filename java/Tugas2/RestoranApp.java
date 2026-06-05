import java.util.ArrayList;
import java.util.Locale;
import java.text.NumberFormat;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

public class RestoranApp {
    private ArrayList<Menu> daftarMenu = new ArrayList<>();
    private final String PIN_MANAJEMEN = "1234";
    private final NumberFormat rupiahFormat = NumberFormat.getCurrencyInstance(Locale.of("id", "ID"));

    public RestoranApp() {
        // Mengisi data awal saat objek RestoranApp dibuat
        daftarMenu.add(new Menu("Nasi Goreng", 25_000, "makanan"));
        daftarMenu.add(new Menu("Roti Bakar", 30_000, "makanan"));
        daftarMenu.add(new Menu("Lontong", 20_000, "makanan"));
        daftarMenu.add(new Menu("Bubur Ayam", 28_000, "makanan"));
        
        daftarMenu.add(new Menu("Teh", 5_000, "minuman"));
        daftarMenu.add(new Menu("Sirup", 7_000, "minuman"));
        daftarMenu.add(new Menu("Susu", 15_000, "minuman"));
        daftarMenu.add(new Menu("Kopi", 12_000, "minuman"));
    }

    public void mainMenu() {
        while (true) {
            String[] opsi = {"Menu Pelanggan (Pemesanan)", "Menu Manajemen (Pemilik)", "Keluar Aplikasi"};
            int pilihan = JOptionPane.showOptionDialog(null, 
                    "Selamat Datang di Aplikasi Restoran\nSilakan pilih menu utama:", 
                    "Aplikasi Restoran v1.0", 
                    JOptionPane.DEFAULT_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, 
                    null, opsi, opsi[0]);

            if (pilihan == 0) {
                prosesPemesanan();
            } else if (pilihan == 1) {
                if (otentikasiPemilik()) {
                    menuManajemen();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Terima kasih telah menggunakan aplikasi ini!");
                System.exit(0);
            }
        }
    }

    private boolean otentikasiPemilik() {
        JPasswordField passwordField = new JPasswordField();
        Object[] komponen = {"Masukkan PIN Manajemen Pemilik:", passwordField};
        
        int pilihan = JOptionPane.showConfirmDialog(null, komponen, "Verifikasi Keamanan", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        
        if (pilihan == JOptionPane.OK_OPTION) {
            String inputPIN = new String(passwordField.getPassword());
            return inputPIN.equals(PIN_MANAJEMEN);
        }
        return false;
    }

    private String formatDaftarMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- DAFTAR MENU RESTORAN ---\n\n[ MAKANAN ]\n");
        int nomor = 1;
        for (Menu m : daftarMenu) {
            if (m.getKategori().equalsIgnoreCase("makanan")) {
                sb.append(String.format("%d. %s - %s\n", nomor, m.getNama(), rupiahFormat.format(m.getHarga())));
            }
            nomor++;
        }
        sb.append("\n[ MINUMAN ]\n");
        nomor = 1;
        for (Menu m : daftarMenu) {
            if (m.getKategori().equalsIgnoreCase("minuman")) {
                sb.append(String.format("%d. %s - %s\n", nomor, m.getNama(), rupiahFormat.format(m.getHarga())));
            }
            nomor++;
        }
        return sb.toString();
    }

    private void prosesPemesanan() {
        ArrayList<Menu> pesananMenu = new ArrayList<>();
        ArrayList<Integer> pesananJumlah = new ArrayList<>();

        String[] menuItems = new String[daftarMenu.size()];
        for (int i = 0; i < daftarMenu.size(); i++) {
            Menu m = daftarMenu.get(i);
            menuItems[i] = String.format("%d. %s (%s) - %s", (i + 1), m.getNama(), m.getKategori(), rupiahFormat.format(m.getHarga()));
        }

        String[] opsiTombol = {"Tambah ke Keranjang", "Selesai"};

        while (true) {
            StringBuilder isiKeranjang = new StringBuilder();
            isiKeranjang.append("--- KERANJANG BELANJA ANDA ---\n");
            
            if (pesananMenu.isEmpty()) {
                isiKeranjang.append("[ Keranjang Belanja Kosong ]\n");
            } else {
                for (int i = 0; i < pesananMenu.size(); i++) {
                    double totalItem = pesananMenu.get(i).getHarga() * pesananJumlah.get(i);
                    isiKeranjang.append(String.format("- %s x%d (%s)\n", 
                            pesananMenu.get(i).getNama(), pesananJumlah.get(i), rupiahFormat.format(totalItem)));
                }
            }

            JComboBox<String> menuDropdown = new JComboBox<>(menuItems);
            JTextField fieldJumlah = new JTextField("1");

            Object[] komponen = {
                isiKeranjang.toString() + "\nSilakan Pilih Menu:", menuDropdown,
                "Masukkan Jumlah Pesanan:", fieldJumlah
            };

            int pilihan = JOptionPane.showOptionDialog(
                    null, komponen, "Proses Pemesanan", 
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
                    null, opsiTombol, opsiTombol[0]
            );

            if (pilihan == 1 || pilihan == JOptionPane.CLOSED_OPTION) {
                if (!pesananMenu.isEmpty()) {
                    cetakStruk(pesananMenu, pesananJumlah);
                }
                return;
            }

            int indeksTerpilih = menuDropdown.getSelectedIndex();

            try {
                int jumlah = Integer.parseInt(fieldJumlah.getText());
                if (jumlah <= 0) {
                    JOptionPane.showMessageDialog(null, "Jumlah harus lebih dari 0!");
                    continue;
                }

                Menu pilihanMenu = daftarMenu.get(indeksTerpilih);
                pesananMenu.add(pilihanMenu);
                pesananJumlah.add(jumlah);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Input jumlah tidak valid! Harus berupa angka positif.");
            }
        }
    }

    private void cetakStruk(ArrayList<Menu> pesanan, ArrayList<Integer> jumlah) {
        double subtotal = 0;
        StringBuilder struk = new StringBuilder();
        struk.append("==========================================\n");
        struk.append("              STRUK PESANAN               \n");
        struk.append("==========================================\n");

        for (int i = 0; i < pesanan.size(); i++) {
            double totalItem = pesanan.get(i).getHarga() * jumlah.get(i);
            subtotal += totalItem;
            struk.append(String.format("%s x%d\n  @%s -> %s\n", 
                    pesanan.get(i).getNama(), jumlah.get(i), rupiahFormat.format(pesanan.get(i).getHarga()), rupiahFormat.format(totalItem)));
        }

        struk.append("------------------------------------------\n");
        struk.append(String.format("Subtotal:                     %s\n", rupiahFormat.format(subtotal)));

        double diskon = 0;
        if (subtotal > 100_000) {
            diskon = 0.10 * subtotal;
            struk.append(String.format("Diskon 10%% (Subtotal > 100rb): -%s\n", rupiahFormat.format(diskon)));
        }

        if (subtotal > 50_000) {
            struk.append("[ PENAWARAN KHUSUS ]\n");
            struk.append("-> Anda mendapatkan penawaran Beli 1 Gratis 1 untuk salah satu kategori minuman!\n");
        }

        double totalSetelahDiskon = subtotal - diskon;
        double pajak = 0.10 * totalSetelahDiskon;
        double biayaPelayanan = 20_000;
        double totalAkhir = totalSetelahDiskon + pajak + biayaPelayanan;

        struk.append(String.format("Pajak (10%%):                   %s\n", rupiahFormat.format(pajak)));
        struk.append(String.format("Biaya Pelayanan:              %s\n", rupiahFormat.format(biayaPelayanan)));
        struk.append("------------------------------------------\n");
        struk.append(String.format("TOTAL BAYAR:                  %s\n", rupiahFormat.format(totalAkhir)));
        struk.append("==========================================\n");

        JOptionPane.showMessageDialog(null, struk.toString(), "Struk Pembayaran Pembelian", JOptionPane.INFORMATION_MESSAGE);
    }

    private void menuManajemen() {
        while (true) {
            String[] opsi = {"Tambah Menu Baru", "Ubah Harga Menu", "Hapus Menu", "Kembali"};
            int pilihan = JOptionPane.showOptionDialog(null, 
                    "--- MENU PENGELOLAAN (PEMILIK) ---\nSilakan pilih tindakan:", 
                    "Menu Manajemen", 
                    JOptionPane.DEFAULT_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, 
                    null, opsi, opsi[0]);

            if (pilihan == 0) {
                tambahMenuBaru();
            } else if (pilihan == 1) {
                ubahHargaMenu();
            } else if (pilihan == 2) {
                hapusMenu();
            } else {
                return;
            }
        }
    }

    private void tambahMenuBaru() {
        JTextField fieldNama = new JTextField();
        JTextField fieldHarga = new JTextField();
        String[] opsiKategori = {"makanan", "minuman"};
        JComboBox<String> comboKategori = new JComboBox<>(opsiKategori);

        Object[] komponen = {
            "Masukkan Nama Menu Baru:", fieldNama,
            "Masukkan Harga (bisa pakai titik):", fieldHarga,
            "Pilih Kategori Menu:", comboKategori
        };

        String[] tombol = {"Tambah Menu", "Batal"};

        int pilihan = JOptionPane.showOptionDialog(
                null, komponen, "Tambah Menu", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
                null, tombol, tombol[0]
        );

        if (pilihan == JOptionPane.YES_OPTION) {
            String nama = fieldNama.getText().trim();
            String inputHarga = fieldHarga.getText().trim();
            String kategori = (String) comboKategori.getSelectedItem();

            if (nama.isEmpty() || inputHarga.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nama dan Harga tidak boleh kosong!");
                return;
            }

            try {
                String hargaBersih = inputHarga.replace(".", "");
                double harga = Double.parseDouble(hargaBersih);

                daftarMenu.add(new Menu(nama, harga, kategori));
                JOptionPane.showMessageDialog(null, "Menu baru '" + nama + "' berhasil ditambahkan!");

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Input harga tidak valid! Gunakan format angka.");
            }
        }
    }

    private void ubahHargaMenu() {
        if (daftarMenu.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Daftar menu kosong!");
            return;
        }

        String[] opsiMenu = new String[daftarMenu.size()];
        for (int i = 0; i < daftarMenu.size(); i++) {
            Menu m = daftarMenu.get(i);
            opsiMenu[i] = String.format("%s (%s)", m.getNama(), rupiahFormat.format(m.getHarga()));
        }

        JComboBox<String> menuDropdown = new JComboBox<>(opsiMenu);
        JTextField fieldHargaBaru = new JTextField();

        Object[] komponen = {
            "Pilih Menu yang Ingin Diubah:", menuDropdown,
            "Masukkan Harga Baru (bisa pakai titik):", fieldHargaBaru
        };

        String[] tombol = {"Simpan Perubahan", "Batal"};

        int pilihan = JOptionPane.showOptionDialog(
                null, komponen, "Ubah Harga Menu", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
                null, tombol, tombol[0]
        );

        if (pilihan == JOptionPane.YES_OPTION) {
            int indeksTerpilih = menuDropdown.getSelectedIndex();
            String inputHarga = fieldHargaBaru.getText().trim();

            if (inputHarga.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Harga baru tidak boleh kosong!");
                return;
            }

            try {
                String hargaBersih = inputHarga.replace(".", "");
                double hargaBaru = Double.parseDouble(hargaBersih);

                Menu m = daftarMenu.get(indeksTerpilih);
                m.setHarga(hargaBaru);
                JOptionPane.showMessageDialog(null, "Harga untuk '" + m.getNama() + "' berhasil diperbarui menjadi " + rupiahFormat.format(hargaBaru));

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Input harga tidak valid! Gunakan format angka.");
            }
        }
    }

    private void hapusMenu() {
        while (true) {
            String menuTeks = formatDaftarMenu() + "\nMasukkan nomor menu yang ingin dihapus:";
            String input = JOptionPane.showInputDialog(null, menuTeks, "Hapus Menu Restoran", JOptionPane.QUESTION_MESSAGE);

            if (input == null) return;

            try {
                int indeks = Integer.parseInt(input) - 1;
                if (indeks >= 0 && indeks < daftarMenu.size()) {
                    Menu m = daftarMenu.get(indeks);
                    int konfirmasi = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin menghapus menu '" + m.getNama() + "'?", "Konfirmasi Tindakan", JOptionPane.YES_NO_OPTION);
                    
                    if (konfirmasi == JOptionPane.YES_OPTION) {
                        daftarMenu.remove(indeks);
                        JOptionPane.showMessageDialog(null, "Menu berhasil dihapus!");
                    }
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Nomor menu tidak ditemukan!");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Masukkan input angka yang valid!");
            }
        }
    }
}