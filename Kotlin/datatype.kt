fun main(){
    val test1 = Test()
    test1.check()
}

class Test {

    var listOutput = mutableListOf<String>()
	val listExpectedOutput = mutableListOf("Nilai rata-rata siswa: 73.63636363636364", "73.63636363636364")

    fun println(input: String){
        listOutput.add(input)
        kotlin.io.println(input)
    }
    
    fun main() {
        // Tambahkan kode setelah baris ini
val nilaiSiswa = listOf(80, 70, 65, 90, 75, 60, 70, 85, 55, 88, 72)

        val nilaiRataRataSiswa = nilaiSiswa.average()
        println("Nilai rata-rata siswa: $nilaiRataRataSiswa")

        // Kode ini digunakan untuk mengecek hasil dari kode yang Anda buat
        checkHasil(nilaiRataRataSiswa) // Hanya hapus kode ini jika kamu menggunakan Kotlin Playground
    }
    
    fun expectedOutput() {
        for (output in listExpectedOutput) {
            println(output)
        }
    }
    
    fun checkHasil(nilai: Int){
        listOutput.add(nilai.toString())
    }
    
    fun checkHasil(nilai: Double){
        listOutput.add(nilai.toString())
    }
    
    fun check(){
        kotlin.io.println("Output program kamu:")
        main()
        
        val status = if (listOutput == listExpectedOutput)  "Berhasil ✅"  else "Belum tepat ❌"
        
        kotlin.io.println("\nStatus: $status\n")
        
        if (status == "Belum tepat ❌") {
            kotlin.io.println("Expected Output:")
            expectedOutput()
        }
    }    
}