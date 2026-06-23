// Kelas Makanan yang merupakan subclass dari MenuItem
import java.util.Locale;

public class Makanan extends MenuItem {
    private String jenisMakanan;
    private static final Locale IDN = Locale.of("id", "ID");

    public Makanan(String name, double price, String foodType) {
        super(name, price, "Makanan");
        this.jenisMakanan = foodType;
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