import java.util.Scanner;
public class tugas1 {
    public static void main(String[] args) {
        MenuManager manager = new MenuManager();
        manager.tampilkanDaftar();
        
        Scanner sc = new Scanner(System.in);
        System.out.println("\nMasukkan pesanan (Maks 4 item).");

        System.out.print("Item 1: "); String n1 = sc.nextLine();
        int q1 = n1.isEmpty() ? 0 : ambilAngka(sc);

        System.out.print("Item 2: "); String n2 = sc.nextLine();
        int q2 = n2.isEmpty() ? 0 : ambilAngka(sc);

        System.out.print("Item 3: "); String n3 = sc.nextLine();
        int q3 = n3.isEmpty() ? 0 : ambilAngka(sc);

        System.out.print("Item 4: "); String n4 = sc.nextLine();
        int q4 = n4.isEmpty() ? 0 : ambilAngka(sc);

        // Memanggil Kasir (OrderProcessor)
        OrderProcessor.cetakStruk(manager, n1, q1, n2, q2, n3, q3, n4, q4);
    }

    private static int ambilAngka(Scanner sc) {
        System.out.print("Jumlah: ");
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (Exception e) { return 0; }
    }
}