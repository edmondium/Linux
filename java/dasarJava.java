import javax.swing.JOptionPane;
public class dasarJava {
    public static void main(String[] args){
        String input = JOptionPane.showInputDialog("Pilihan:\n1. ikan asin" + "\n2. ikan bandeng" + "\n3. ikan tongkol");
        int angka = Integer.parseInt(input);
        if (angka == 1) {
            System.out.println("memilih ikan asin");
        } else if (angka == 2) {
            System.out.println("memilih ikan bandeng");
        } else if (angka == 3) {
            System.out.println("memilih ikan tongkol");
        } else {
            System.out.println("Pilihan tidak ada");
        }
    }
}
