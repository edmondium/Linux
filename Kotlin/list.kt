fun main(){
    val test1 = Test()
    test1.check()
}

class Test {

    var listOutput = mutableListOf<String>()
	val listExpectedOutput = mutableListOf<String>()
    val errorLog = mutableListOf<String>()
    
    fun println(input: String){
        listOutput.add(input)
        kotlin.io.println(input)
    }
    
    fun main() {
        val daftarHadir: List<String> = listOf("Aulia", "Bayu", "Citra", "Doni", "Eka")
        val namaDicari1 = "Bayu"
        val namaDicari2 = "Bima"
        println("Daftar Mahasiswa yang Hadir Hari Ini:")
        for (nama in daftarHadir) {
            println("- $nama")
        }
        if (namaDicari1 in daftarHadir) {
            println("$namaDicari1 hadir di kelas hari ini.")
        } else {
            println("$namaDicari1 tidak hadir.")
        }
        if (namaDicari2 in daftarHadir) {
            println("$namaDicari2 hadir di kelas hari ini.")
        } else {
            println("$namaDicari2 tidak hadir.")
        }
        // Kode ini digunakan untuk mengecek hasil dari kode yang Anda buat
        checkHasil(daftarHadir, namaDicari1, namaDicari2) // Hanya hapus kode ini jika kamu menggunakan Kotlin Playground
    }
    
    fun expectedOutput() {
        for (output in listExpectedOutput) {
            println(output)
        }
    }
    
    fun checkHasil(daftarHadir: List<String>, namaDicari1: String, namaDicari2:String) {
        if (daftarHadir != listOf("Aulia", "Bayu", "Citra", "Doni", "Eka") || namaDicari1 != "Bayu" && namaDicari2 != "Bima") errorLog.add("pastikan variabel daftarHadir, namaDicari1, dan namaDicari2 sesuai dengan nilai yang ditetapkan")
        
        listExpectedOutput.add("Daftar Mahasiswa yang Hadir Hari Ini:")
        listExpectedOutput.add("- Aulia")
        listExpectedOutput.add("- Bayu")
        listExpectedOutput.add("- Citra")
        listExpectedOutput.add("- Doni")
        listExpectedOutput.add("- Eka")
        listExpectedOutput.add("Bayu hadir di kelas hari ini.")
        listExpectedOutput.add("Bima tidak hadir.")
    }
    
    fun check(){
        kotlin.io.println("Output program kamu:")
        main()
        
        if (errorLog.isNotEmpty()) {
            kotlin.io.println("\nStatus: Belum tepat ❌\n")
            kotlin.io.println("Alasan: ${errorLog.first()}")
            return
        }
        
        val status = if (listOutput == listExpectedOutput)  "Berhasil ✅"  else "Belum tepat ❌"
        
        kotlin.io.println("\nStatus: $status\n")
        
        if (status == "Belum tepat ❌") {
            kotlin.io.println("Expected Output:")
            expectedOutput()
        }
    }    
}