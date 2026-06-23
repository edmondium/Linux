// Impor spesifik untuk komponen AWT yang benar-benar digunakan
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;

// Impor spesifik untuk komponen Swing
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

// Impor spesifik untuk I/O, Utilities, dan Lokalisasi
import java.io.IOException;
import java.util.Locale;

// ==========================================
// MAIN GUI CLASS APPLICATION
// ==========================================
public class MainRestoranGUI extends JFrame {
    private Menu manajemenMenu = new Menu();
    
    // REVISI: Menggunakan kelas Pesanan mandiri menggantikan raw ArrayList
    private Pesanan pesananPelanggan = new Pesanan();
    
    private static final Locale IDN = Locale.of("id", "ID");

    // Komponen GUI Swing
    private JTable tabelMenu;
    private DefaultTableModel modelTabelMenu;
    private JTextArea areaStruk;
    private JTextField txtNamaItem, txtHargaItem, txtAtributTambahan, txtNamaPesanan;
    private JComboBox<String> cbJenisMenu;
    private JLabel lblHargaAtauDiskon, lblAtribut;

    public MainRestoranGUI() {
        // Init Pengaturan Frame Utama
        setTitle("Sistem Manajemen Restoran Modern");
        setSize(950, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Memuat basis data menu dari file teks secara otomatis
        manajemenMenu.muatMenuDariFile();

        // ------------------ PANEL NORTH: INPUT MENU BARU ------------------
        JPanel panelInputMenu = new JPanel(new GridLayout(2, 5, 5, 5));
        panelInputMenu.setBorder(BorderFactory.createTitledBorder("1. Tambah Menu Baru"));

        cbJenisMenu = new JComboBox<>(new String[]{"Makanan", "Minuman", "Diskon"});
        txtNamaItem = new JTextField();
        txtHargaItem = new JTextField();
        txtAtributTambahan = new JTextField();
        JButton btnTambahMenu = new JButton("Tambah ke Menu");

        lblHargaAtauDiskon = new JLabel("Harga (e.g. 10.000):");
        lblAtribut = new JLabel("Jenis (Cemilan/Kuah/dll):");

        panelInputMenu.add(new JLabel("Kategori:"));
        panelInputMenu.add(new JLabel("Nama Item:"));
        panelInputMenu.add(lblHargaAtauDiskon);
        panelInputMenu.add(lblAtribut);
        panelInputMenu.add(new JLabel("Aksi:"));

        panelInputMenu.add(cbJenisMenu);
        panelInputMenu.add(txtNamaItem);
        panelInputMenu.add(txtHargaItem);
        panelInputMenu.add(txtAtributTambahan);
        panelInputMenu.add(btnTambahMenu);

        // ------------------ PANEL CENTER: TABEL DAFTAR & AREA KASIR ------------------
        JPanel panelTengah = new JPanel(new GridLayout(1, 2, 10, 10));

        // Sisi Kiri: Visualisasi Tabel Menu
        modelTabelMenu = new DefaultTableModel(new String[]{"Kategori", "Nama Menu", "Harga/Potongan", "Keterangan"}, 0);
        tabelMenu = new JTable(modelTabelMenu);
        JScrollPane scrollTabel = new JScrollPane(tabelMenu);
        scrollTabel.setBorder(BorderFactory.createTitledBorder("2. Daftar Menu Restoran"));
        panelTengah.add(scrollTabel);
        refreshTabelMenu();

        // Sisi Kanan: Area Pencatatan Kasir & Struk Belanja
        JPanel panelKasir = new JPanel(new BorderLayout(5, 5));
        panelKasir.setBorder(BorderFactory.createTitledBorder("3. Pesanan Kasir"));

        JPanel panelInputPesanan = new JPanel(new BorderLayout(5, 5));
        txtNamaPesanan = new JTextField();
        JButton btnTambahPesanan = new JButton("Tambah ke Pesanan");
        panelInputPesanan.add(new JLabel("Ketik Nama Menu: "), BorderLayout.WEST);
        panelInputPesanan.add(txtNamaPesanan, BorderLayout.CENTER);
        panelInputPesanan.add(btnTambahPesanan, BorderLayout.EAST);

        areaStruk = new JTextArea();
        areaStruk.setEditable(false);
        areaStruk.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Font khusus struk lurus
        JScrollPane scrollStruk = new JScrollPane(areaStruk);

        JPanel panelAksiPesanan = new JPanel(new GridLayout(1, 2, 5, 5));
        JButton btnCetakStruk = new JButton("Hitung Total & Cetak Struk");
        JButton btnResetPesanan = new JButton("Reset Pesanan");
        panelAksiPesanan.add(btnCetakStruk);
        panelAksiPesanan.add(btnResetPesanan);

        panelKasir.add(panelInputPesanan, BorderLayout.NORTH);
        panelKasir.add(scrollStruk, BorderLayout.CENTER);
        panelKasir.add(panelAksiPesanan, BorderLayout.SOUTH);

        panelTengah.add(panelKasir);

        // Penyusunan komponen ke Layout Jendela Utama
        add(panelInputMenu, BorderLayout.NORTH);
        add(panelTengah, BorderLayout.CENTER);

        // ------------------ INTERACTION LOGIC (LAMBDA EXPRESSIONS) ------------------

        // Listener dinamis untuk mendeteksi perubahan tipe menu yang dipilih
        cbJenisMenu.addActionListener(e -> {
            String dipilih = (String) cbJenisMenu.getSelectedItem();
            if ("Diskon".equals(dipilih)) {
                lblHargaAtauDiskon.setText("Besar Diskon % (e.g. 10):");
                lblAtribut.setText("Tidak Diperlukan:");
                txtAtributTambahan.setEnabled(false);
                txtAtributTambahan.setText("-");
            } else {
                lblHargaAtauDiskon.setText("Harga (e.g. 10.000):");
                lblAtribut.setText("Makanan".equals(dipilih) ? "Jenis (Cemilan/Kuah/dll):" : "Suhu (Panas/Dingin):");
                txtAtributTambahan.setEnabled(true);
                txtAtributTambahan.setText("");
            }
        });

        // Event Tombol Tambah Menu Baru ke Restoran
        btnTambahMenu.addActionListener(e -> {
            try {
                String kategori = (String) cbJenisMenu.getSelectedItem();
                String nama = txtNamaItem.getText().trim();
                
                if (nama.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Nama item tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if ("Diskon".equals(kategori)) {
                    double besarDisc = Double.parseDouble(txtHargaItem.getText().trim());
                    manajemenMenu.tambahItem(new Diskon(nama, besarDisc));
                } else {
                    String hargaBersih = txtHargaItem.getText().trim().replace(".", "");
                    double harga = Double.parseDouble(hargaBersih);
                    String atribut = txtAtributTambahan.getText().trim();

                    if ("Makanan".equals(kategori)) {
                        manajemenMenu.tambahItem(new Makanan(nama, harga, atribut));
                    } else {
                        manajemenMenu.tambahItem(new Minuman(nama, harga, atribut));
                    }
                }

                manajemenMenu.simpanMenuKeFile();
                refreshTabelMenu();
                clearInputMenuFields();
                JOptionPane.showMessageDialog(this, "Menu '" + nama + "' berhasil disimpan!");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Format angka pada kolom harga/diskon salah!", "Error Input", JOptionPane.ERROR_MESSAGE);
            }
        });

        // REVISI: Mengarahkan penambahan data ke objek pesananPelanggan (Kelas Pesanan)
        btnTambahPesanan.addActionListener(e -> {
            String namaPesan = txtNamaPesanan.getText().trim();
            if (namaPesan.isEmpty()) return;

            try {
                MenuItem item = manajemenMenu.cariItem(namaPesan);
                
                // Mendelegasikan pencatatan ke objek Pesanan resmi
                pesananPelanggan.tambahKePesanan(item);
                
                areaStruk.append("✓ Ditambahkan: " + item.getNama() + "\n");
                txtNamaPesanan.setText("");
            } catch (MenuNotFoundException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Menu Tidak Ditemukan", JOptionPane.ERROR_MESSAGE);
            }
        });

        // REVISI: Mengambil output teks struk dan penulisan berkas langsung dari objek Pesanan
        btnCetakStruk.addActionListener(e -> {
            if (pesananPelanggan.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Belum ada pesanan yang dicatat!", "Struk Kosong", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Meminta teks struk final yang sudah dikalkulasi dari objek Pesanan
            String teksStrukFinal = pesananPelanggan.hasilkanStruk();
            
            // Set output teks ke area layar kasir GUI
            areaStruk.setText(teksStrukFinal);

            // Menyerahkan proses simpan berkas I/O ke dalam instans objek Pesanan
            try {
                pesananPelanggan.simpanStrukKeFile(teksStrukFinal);
                JOptionPane.showMessageDialog(this, "Struk berhasil dicetak ke file 'struk_pesanan.txt'");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal menulis file struk: " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // REVISI: Mereset state melalui method internal kelas Pesanan
        btnResetPesanan.addActionListener(e -> {
            pesananPelanggan.resetPesanan(); // Menghapus ArrayList di dalam objek Pesanan
            areaStruk.setText("");
            txtNamaPesanan.setText("");
            JOptionPane.showMessageDialog(this, "Daftar pesanan kasir berhasil dikosongkan.");
        });
    }

    // Sinkronisasi data ArrayList internal ke visualisasi JTable
    private void refreshTabelMenu() {
        modelTabelMenu.setRowCount(0); 
        for (MenuItem item : manajemenMenu.getDaftarMenu()) {
            if (item instanceof Diskon) {
                modelTabelMenu.addRow(new Object[]{
                        item.getKategori(), item.getNama(), 
                        String.format("-%.0f%%", ((Diskon) item).getBesarDiskon()), "Potongan Promo"
                });
            } else {
                modelTabelMenu.addRow(new Object[]{
                        item.getKategori(), item.getNama(), 
                        String.format(IDN, "Rp%,.2f", item.getHarga()), 
                        (item instanceof Makanan) ? "Makanan" : "Minuman"
                });
            }
        }
    }

    private void clearInputMenuFields() {
        txtNamaItem.setText("");
        txtHargaItem.setText("");
        txtAtributTambahan.setText("");
    }

    public static void main(String[] args) {
        // Menjalankan GUI di thread Event Dispatch Thread yang aman
        SwingUtilities.invokeLater(() -> new MainRestoranGUI().setVisible(true));
    }
}