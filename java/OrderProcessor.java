class OrderProcessor {
    public static void cetakStruk(MenuManager manager, String n1, int q1, String n2, int q2, String n3, int q3, String n4, int q4) {
        Menu m1 = manager.cari(n1);
        Menu m2 = manager.cari(n2);
        Menu m3 = manager.cari(n3);
        Menu m4 = manager.cari(n4);

        double totalPesanan = (m1.harga * q1) + (m2.harga * q2) + (m3.harga * q3) + (m4.harga * q4);
        
        // Aturan Diskon & Promo
        double diskon = (totalPesanan > 100_000) ? (totalPesanan * 0.10) : 0;
        String promo = (totalPesanan > 50_000) ? "Beli 1 Gratis 1 (Kategori Minuman)" : "Tidak Ada";
        
        double pajak = (totalPesanan - diskon) * 0.10;
        double pelayanan = 20_000;
        double totalAkhir = (totalPesanan - diskon) + pajak + pelayanan;

        System.out.println("\n========== STRUK PEMBAYARAN ==========");
        if (q1 > 0) cetakBaris(m1, q1);
        if (q2 > 0) cetakBaris(m2, q2);
        if (q3 > 0) cetakBaris(m3, q3);
        if (q4 > 0) cetakBaris(m4, q4);
        System.out.println("--------------------------------------");
        System.out.println("Subtotal        : " + CurrencyFormatter.format(totalPesanan));
        if (diskon > 0) System.out.println("Diskon (10%)    : -" + CurrencyFormatter.format(diskon));
        System.out.println("Pajak (10%)     : " + CurrencyFormatter.format(pajak));
        System.out.println("Biaya Pelayanan : " + CurrencyFormatter.format(pelayanan));
        System.out.println("Penawaran       : " + promo);
        System.out.println("TOTAL AKHIR     : " + CurrencyFormatter.format(totalAkhir));
        System.out.println("======================================");
    }

    private static void cetakBaris(Menu m, int qty) {
        System.out.println(m.nama + " \t x" + qty + " : " + CurrencyFormatter.format(m.harga * qty));
    }
}
