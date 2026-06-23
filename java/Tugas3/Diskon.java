// Kelas Diskon merupakan subclass dari MenuItem
public class Diskon extends MenuItem {
    private double besarDiskon;

    public Diskon(String name, double discount) {
        super(name, 0, "Diskon");
        this.besarDiskon = discount;
    }

    public double getBesarDiskon() { return besarDiskon; }

    @Override
    public void tampilMenu() {
        System.out.printf("[%s] %s - Potongan: %.0f%%\n", getKategori(), getNama(), besarDiskon);
    }

    @Override
    public String toFileString() {
        return "DISKON;" + getNama() + ";" + besarDiskon;
    }
}