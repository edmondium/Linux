import javax.swing.JOptionPane;

class OrderProcessor {
    public static void prosesDanCetak(MenuManager manager, String[] itemNames, int[] quantities) {
        Menu m1 = manager.cari(itemNames[0]);
        Menu m2 = manager.cari(itemNames[1]);
        Menu m3 = manager.cari(itemNames[2]);
        Menu m4 = manager.cari(itemNames[3]);

        double totalPesanan = (m1.harga * quantities[0]) + (m2.harga * quantities[1]) + 
                              (m3.harga * quantities[2]) + (m4.harga * quantities[3]);
        
        double diskon = (totalPesanan > 100_000) ? (totalPesanan * 0.10) : 0;
        String promo = (totalPesanan > 50_000) ? "Beli 1 Gratis 1 (Minuman)" : "Tidak Ada";
        double pajak = (totalPesanan - diskon) * 0.10;
        double pelayanan = 20_000;
        double totalAkhir = (totalPesanan - diskon) + pajak + pelayanan;

        StringBuilder struk = new StringBuilder();
        
        if (quantities[0] > 0) struk.append(m1.nama).append("\t x").append(quantities[0]).append("\t: ").append(CurrencyFormatter.format(m1.harga * quantities[0])).append("\n");
        if (quantities[1] > 0) struk.append(m2.nama).append("\t x").append(quantities[1]).append("\t: ").append(CurrencyFormatter.format(m2.harga * quantities[1])).append("\n");
        if (quantities[2] > 0) struk.append(m3.nama).append("\t x").append(quantities[2]).append("\t: ").append(CurrencyFormatter.format(m3.harga * quantities[2])).append("\n");
        if (quantities[3] > 0) struk.append(m4.nama).append("\t x").append(quantities[3]).append("\t: ").append(CurrencyFormatter.format(m4.harga * quantities[3])).append("\n");
        struk.append("---------------------------------------\n");
        struk.append("Subtotal\t\t: ").append(CurrencyFormatter.format(totalPesanan)).append("\n");
        if (diskon > 0) struk.append("Diskon (10%)\t: -").append(CurrencyFormatter.format(diskon)).append("\n");
        struk.append("Pajak (10%)\t: ").append(CurrencyFormatter.format(pajak)).append("\n");
        struk.append("Pelayanan\t\t: ").append(CurrencyFormatter.format(pelayanan)).append("\n");
        struk.append("Promo\t\t: ").append(promo).append("\n");
        struk.append("---------------------------------------\n");
        struk.append("TOTAL AKHIR\t: ").append(CurrencyFormatter.format(totalAkhir)).append("\n");
        

        JOptionPane.showMessageDialog(null, struk.toString(), "Receipt", JOptionPane.INFORMATION_MESSAGE);
    }
}
