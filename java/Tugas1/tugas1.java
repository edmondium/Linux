import javax.swing.JOptionPane;

public class tugas1 {
    public static void main(String[] args) {
        MenuManager manager = new MenuManager();
        
        // Ambil teks Menu
        String daftarTeks = manager.getDaftarMenuString();

        String[] orderNames = new String[4];
        int[] orderQtys = new int[4];

        // Input Pesanan 1-4 secara manual (Indikator 2)
        orderNames[0] = JOptionPane.showInputDialog(null, daftarTeks + "\nMasukkan Nama/Nomor Menu 1:", "DAFTAR MENU RESTORAN", JOptionPane.QUESTION_MESSAGE);
        orderQtys[0] = ambilQty(daftarTeks, orderNames[0]);

        orderNames[1] = JOptionPane.showInputDialog(null, daftarTeks + "\nMasukkan Nama/Nomor Menu 2:", "DAFTAR MENU RESTORAN", JOptionPane.QUESTION_MESSAGE);
        orderQtys[1] = ambilQty(daftarTeks, orderNames[1]);

        orderNames[2] = JOptionPane.showInputDialog(null, daftarTeks + "\nMasukkan Nama/Nomor Menu 3:", "DAFTAR MENU RESTORAN", JOptionPane.QUESTION_MESSAGE);
        orderQtys[2] = ambilQty(daftarTeks, orderNames[2]);

        orderNames[3] = JOptionPane.showInputDialog(null, daftarTeks + "\nMasukkan Nama/Nomor Menu 4:", "DAFTAR MENU RESTORAN", JOptionPane.QUESTION_MESSAGE);
        orderQtys[3] = ambilQty(daftarTeks, orderNames[3]);

        OrderProcessor.prosesDanCetak(manager, orderNames, orderQtys);
    }

    private static int ambilQty(String daftarTeks, String menuName) {
        if (menuName == null || menuName.isEmpty()) return 0;
        String val = JOptionPane.showInputDialog(null, daftarTeks + "\nJumlah untuk " + menuName + ":", "DAFTAR MENU RESTORAN", JOptionPane.QUESTION_MESSAGE);
        try {
            return Integer.parseInt(val);
        } catch (Exception e) {
            return 0;
        }
    }
}
