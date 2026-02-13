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
    
    fun hitungDiskon(hargaAwal: Double, persenDiskon: Double): Double{
        var potongan = hargaAwal * persenDiskon / 100
        return hargaAwal - potongan
    }


    fun main() {
        val hargaBarang: Double = 200_000.0
        val persenDiskon: Double = 15.0
        val hargaSetelahDiskon = hitungDiskon(hargaBarang, persenDiskon)
        println("Harga awal: Rp$hargaBarang")
        println("Diskon: $persenDiskon%")
        println("Harga setelah diskon: Rp$hargaSetelahDiskon")


        // Kode ini digunakan untuk mengecek hasil dari kode yang Anda buat
        checkHasil(hargaBarang, persenDiskon, ::hitungDiskon) // Hanya hapus kode ini jika Anda menggunakan Kotlin Playground
    }
    
    fun expectedOutput() {
        for (output in listExpectedOutput) {
            println(output)
        }
    }
    
    fun checkHasil(hargaBarang: Double, persenDiskon: Double, fungsi:(Double, Double) -> Double) {
        if (hargaBarang != 200000.0 || persenDiskon != 15.0) errorLog.add("pastikan variabel hargaBarang dan persenDiskon sesuai dengan nilai yang ditetapkan")
        
        val hargaSetelahDiskon = fungsi(hargaBarang, persenDiskon)
        
        listExpectedOutput.add("Harga awal: Rp$hargaBarang")
        listExpectedOutput.add("Diskon: $persenDiskon%")
        listExpectedOutput.add("Harga setelah diskon: Rp$hargaSetelahDiskon")
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