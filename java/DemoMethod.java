public class DemoMethod {
    public static void main(String[] args){
        Persegi.showInfo();
        Persegi persegi = new Persegi(10);
        System.out.println(persegi.getPanjang());
        System.out.println(persegi.getLuas());
        System.out.println(persegi.getKeliling());
        System.out.println(persegi.getLuasPermukaan());
        System.out.println(persegi.getVolumeKubus());

        persegi.setPanjang(20);
        System.out.println(persegi.getPanjang());
        System.out.println(persegi.getLuas());
        System.out.println(persegi.getKeliling());
        System.out.println(persegi.getLuasPermukaan());
        System.out.println(persegi.getVolumeKubus());

        try {
            System.out.println(persegi.showException());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
