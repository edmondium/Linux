class MenuManager {
    // Array dipindahkan ke sini sebagai atribut private (Encapsulation)
    private Menu[] daftarMenu;

    public MenuManager() {
        // Inisialisasi data di dalam konstruktor
        this.daftarMenu = new Menu[]{
            new Menu("Nasi Goreng", 25_000, "makanan"),
            new Menu("Roti Bakar", 30_000, "makanan"),
            new Menu("Bubur Ayam", 20_000, "makanan"),
            new Menu("Lontong", 35_000, "makanan"),
            new Menu("Teh Tarik", 5_000, "minuman"),
            new Menu("Sirup", 7_000, "minuman"),
            new Menu("Susu", 15_000, "minuman"),
            new Menu("Kopi", 10_000, "minuman")
        };
    }

    public void tampilkanDaftar() {
        System.out.println("=== DAFTAR MENU RESTORAN ===");
        System.out.println("--- MAKANAN ---");
        System.out.println("1. " + daftarMenu[0].nama + " \t- " + CurrencyFormatter.format(daftarMenu[0].harga));
        System.out.println("2. " + daftarMenu[1].nama + " \t- " + CurrencyFormatter.format(daftarMenu[1].harga));
        System.out.println("3. " + daftarMenu[2].nama + " \t- " + CurrencyFormatter.format(daftarMenu[2].harga));
        System.out.println("4. " + daftarMenu[3].nama + " \t- " + CurrencyFormatter.format(daftarMenu[3].harga));
        System.out.println("--- MINUMAN ---");
        System.out.println("5. " + daftarMenu[4].nama + " \t- " + CurrencyFormatter.format(daftarMenu[4].harga));
        System.out.println("6. " + daftarMenu[5].nama + " \t- " + CurrencyFormatter.format(daftarMenu[5].harga));
        System.out.println("7. " + daftarMenu[6].nama + " \t- " + CurrencyFormatter.format(daftarMenu[6].harga));
        System.out.println("8. " + daftarMenu[7].nama + " \t- " + CurrencyFormatter.format(daftarMenu[7].harga));
        System.out.println("----------------------------");
    }

    public Menu cari(String input) {
        if (input == null || input.isEmpty()) return new Menu("Kosong", 0, "none");
        if (input.equals("1") || input.equalsIgnoreCase(daftarMenu[0].nama)) return daftarMenu[0];
        if (input.equals("2") || input.equalsIgnoreCase(daftarMenu[1].nama)) return daftarMenu[1];
        if (input.equals("3") || input.equalsIgnoreCase(daftarMenu[2].nama)) return daftarMenu[2];
        if (input.equals("4") || input.equalsIgnoreCase(daftarMenu[3].nama)) return daftarMenu[3];
        if (input.equals("5") || input.equalsIgnoreCase(daftarMenu[4].nama)) return daftarMenu[4];
        if (input.equals("6") || input.equalsIgnoreCase(daftarMenu[5].nama)) return daftarMenu[5];
        if (input.equals("7") || input.equalsIgnoreCase(daftarMenu[6].nama)) return daftarMenu[6];
        if (input.equals("8") || input.equalsIgnoreCase(daftarMenu[7].nama)) return daftarMenu[7];
        return new Menu("Tidak Ada", 0, "none");
    }
}