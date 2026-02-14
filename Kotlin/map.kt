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
        val dataIbukota : MutableMap<String, String> = mutableMapOf(
            "Indonesia" to "Jakarta",
            "Jepang" to "Tokyo",
            "Perancis" to "Paris",
            "India" to "New Delhi",
            "Brazil" to "Brasilia"
        )

        // Tampilkan daftar negara beserta ibu kotanya
        println("Daftar Negara dan Ibukotanya:")
        for ((negara, ibukota) in dataIbukota) {
            println("- $negara : $ibukota")
        }
        // Cari ibu kota berdasarkan nama negara
        val negaraDicari = "Jepang"
        val hasilIbukota: String? =dataIbukota[negaraDicari]

        if (hasilIbukota != null) {
            println("Ibukota dari $negaraDicari adalah $hasilIbukota.")
        } else {
            println("Negara $negaraDicari tidak ditemukan.")
        }

        // Tambah data ibu kota negara baru
        println("Menambahkan ibu kota negara Thailand")
        dataIbukota["Thailand"] = "Bangkok"

        // Tampilkan kembali daftar negara beserta ibu kotanya
        println("Daftar Negara dan Ibukotanya:")
        for ((negara, ibukota) in dataIbukota) {
            println("- $negara : $ibukota")
        }
        // Kode ini digunakan untuk mengecek hasil dari kode yang Anda buat
        checkHasil(negaraDicari) // Hanya hapus kode ini jika kamu menggunakan Kotlin Playground
    }
    
    fun expectedOutput() {
        for (output in listExpectedOutput) {
            println(output)
        }
    }
    
    fun checkHasil(negaraDicariFromUser: String) {
        val dataIbukota : MutableMap<String, String> = mutableMapOf(
            "Indonesia" to "Jakarta",
            "Jepang" to "Tokyo",
            "Perancis" to "Paris",
            "India" to "New Delhi",
            "Brazil" to "Brasilia"
        )
        val negaraDicari = "Jepang"
        
        if (negaraDicariFromUser != negaraDicari) {
            errorLog.add("pastikan nilai dari variabel negaraDicari sesuai dengan nilai awalnya (tidak dimodifikasi)")
        }
        
        listExpectedOutput.add("Daftar Negara dan Ibukotanya:")
        for ((negara, ibukota) in dataIbukota) {
             listExpectedOutput.add("- $negara : $ibukota")
        }
        
        val hasilIbukota: String? = dataIbukota[negaraDicari]
         listExpectedOutput.add("Ibukota dari $negaraDicari adalah $hasilIbukota.")
        
         dataIbukota["Thailand"] = "Bangkok"
    	 listExpectedOutput.add("Menambahkan ibu kota negara Thailand")
        
         listExpectedOutput.add("Daftar Negara dan Ibukotanya:")
        for ((negara, ibukota) in dataIbukota) {
             listExpectedOutput.add("- $negara : $ibukota")
        }
    }
    
    fun check(){
        kotlin.io.println("Output program Anda:")
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