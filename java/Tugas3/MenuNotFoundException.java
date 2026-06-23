// Kelas untuk menangani kasus ketika menu tidak ditemukan
public class MenuNotFoundException extends Exception {
    public MenuNotFoundException(String message) {
        super(message);
    }
}