public class Persegi {
    private int panjang;
    public Persegi(int panjang){
        this.panjang = panjang;
    }
    public static void showInfo(){
        System.out.println("Dipanggil dari method static showInfo di class Persegi");
    }
    public void setPanjang(int panjang){
        this.panjang = panjang;
    }
    public int getPanjang(){
        return this.panjang;
    }

    public int getLuas(){
        int luas = this.panjang * this.panjang;
        return luas;
    }

    public int getKeliling(){
        int keliling = 4 * this.panjang;
        return keliling;
    }

    public int getLuasPermukaan(){
        int luasPermukaan = 6 * (this.panjang * this.panjang);
        return luasPermukaan;
    }

    public int getVolumeKubus(){
        int volume = this.panjang * this.panjang * this.panjang;
        return volume;
    }

    public int showException() throws Exception{
        return 1/0;
    }
}
