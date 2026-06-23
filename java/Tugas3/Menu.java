// Kelas Menu untuk mengelola daftar menu restoran, termasuk penyimpanan dan pemuatan dari file
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Menu {
    private ArrayList<MenuItem> daftarMenu = new ArrayList<>();
    private final String FILE_MENU = "/home/edmondium/Linux/java/Tugas3/daftar_menu.txt";

    public void tambahItem(MenuItem item) {
        daftarMenu.add(item);
    }

    public ArrayList<MenuItem> getDaftarMenu() {
        return daftarMenu;
    }

    public MenuItem cariItem(String nama) throws MenuNotFoundException {
        for (MenuItem item : daftarMenu) {
            if (item.getNama().equalsIgnoreCase(nama)) {
                return item;
            }
        }
        throw new MenuNotFoundException("Menu '" + nama + "' tidak ditemukan di restoran ini!");
    }

    public void simpanMenuKeFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_MENU))) {
            for (MenuItem item : daftarMenu) {
                writer.println(item.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Gagal menyimpan menu: " + e.getMessage());
        }
    }

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
        } catch (IOException | NumberFormatException e) {
            System.out.println("Gagal memuat menu: " + e.getMessage());
        }
    }
}