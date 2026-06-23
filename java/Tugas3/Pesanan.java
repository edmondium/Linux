// Kelas Pesanan untuk mengelola pesanan dan menghasilkan struk
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class Pesanan {
    // Atribut ArrayList sesuai spesifikasi soal (Encapsulation)
    private ArrayList<MenuItem> itemDipesan = new ArrayList<>();
    private final String FILE_STRUK = "/home/edmondium/Linux/java/Tugas3/struk_pesanan.txt";
    private static final Locale IDN = Locale.of("id", "ID");

    public void tambahKePesanan(MenuItem item) {
        itemDipesan.add(item);
    }

    public void resetPesanan() {
        itemDipesan.clear();
    }

    public boolean isEmpty() {
        return itemDipesan.isEmpty();
    }

    public ArrayList<MenuItem> getItemDipesan() {
        return itemDipesan;
    }

    // Mengembalikan string struk yang sudah diformat untuk GUI dan File
    public String hasilkanStruk() {
        StringBuilder struk = new StringBuilder();
        struk.append("==================================\n");
        struk.append("          STRUK RESTORAN          \n");
        struk.append("==================================\n");

        double subtotal = 0;
        double totalDiskonPersen = 0;

        for (MenuItem item : itemDipesan) {
            if (item instanceof Diskon) {
                struk.append(String.format(IDN, "%-18s : -%.0f%%\n", item.getNama(), ((Diskon) item).getBesarDiskon()));
                totalDiskonPersen += ((Diskon) item).getBesarDiskon();
            } else {
                struk.append(String.format(IDN, "%-18s : Rp%,.2f\n", item.getNama(), item.getHarga()));
                subtotal += item.getHarga();
            }
        }

        double potongan = subtotal * (totalDiskonPersen / 100);
        double totalAkhir = subtotal - potongan;

        struk.append("----------------------------------\n");
        struk.append(String.format(IDN, "%-18s : Rp%,.2f\n", "Subtotal", subtotal));
        struk.append(String.format(IDN, "%-18s : Rp%,.2f\n", "Total Potongan", potongan));
        struk.append("----------------------------------\n");
        struk.append(String.format(IDN, "%-18s : Rp%,.2f\n", "TOTAL BAYAR", totalAkhir));
        struk.append("==================================\n");

        return struk.toString();
    }

    // Operasi File I/O untuk menyimpan struk
    public void simpanStrukKeFile(String isiStruk) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_STRUK))) {
            writer.print(isiStruk);
        }
    }
}