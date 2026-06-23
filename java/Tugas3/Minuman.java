// Kelas Minuman yang merupakan subclass dari MenuItem
import java.util.Locale;

public class Minuman extends MenuItem {
    private String jenisMinuman;
    private static final Locale IDN = Locale.of("id", "ID");

    public Minuman(String name, double price, String drinkType) {
        super(name, price, "Minuman");
        this.jenisMinuman = drinkType;
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