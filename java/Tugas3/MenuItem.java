// Kelas abstrak untuk item menu
public abstract class MenuItem {
    private String nama;
    private double harga;
    private String kategori;

    public MenuItem(String name, double price, String category) {
        this.nama = name;
        this.harga = price;
        this.kategori = category;
    }

    public String getNama() { return nama; }
    public double getHarga() { return harga; }
    public String getKategori() { return kategori; }

    public abstract void tampilMenu();
    public abstract String toFileString();
}