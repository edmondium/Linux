import java.util.Scanner;
public class switchCaseThree {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int panjang, lebar, pilih;
        double luas, keliling;
        System.out.println("Menghitung luas dan keliling persegi panjang");
        System.out.print("Masukkan panjang: ");
        panjang = in.nextInt();

        System.out.print("Masukkan lebar: ");
        lebar = in.nextInt();

        System.out.println("1. Luas\n2. Keliling");
        System.out.print("Masukkan pilihan: ");
        pilih = in.nextInt();
        in.close();

        switch(pilih){
            case 1 -> {
                luas = panjang * lebar;
                System.out.println("Luas persegi panjang: " +luas);
            }
                case 2 -> {
                    keliling = (2*panjang)+(2*lebar); // keliling = 2 * (panjang + lebar)
                    System.out.println("Keliling persegi panjang: " +keliling);
                }
        }
    }
}
