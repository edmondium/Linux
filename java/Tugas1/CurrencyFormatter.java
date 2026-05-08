import java.util.Locale;
import java.text.NumberFormat;

// Kelas khusus untuk utilitas pemformatan mata uang
class CurrencyFormatter {
    public static String format(double nominal) {
        // Standar Java 19+: Locale.of("id", "ID")
        Locale localeID = Locale.of("id", "ID");
        NumberFormat formatRP = NumberFormat.getCurrencyInstance(localeID);
        return formatRP.format(nominal);
    }
}