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
        val emailPendaftar = listOf(
            "alice@gmail.com",
            "bob@gmail.com",
            "charlie@gmail.com",
            "alice@gmail.com",
            "diana@gmail.com"
        )

        val emailTerdaftar = mutableSetOf<String>()
        for (email in emailPendaftar) {
            if (email in emailTerdaftar) {
                println("Email '$email' sudah terdaftar. Pendaftaran ditolak.")
            } else {
                emailTerdaftar.add(email)
                println("Email '$email' berhasil didaftarkan.")
            }
        }
        println("Daftar email yang sudah terdaftar:")
        for (email in emailTerdaftar) {
            println(email)
        }
        // Kode ini digunakan untuk mengecek hasil dari kode yang Anda buat
        checkHasil(emailPendaftar) // Hanya hapus kode ini jika kamu menggunakan Kotlin Playground
    }
    
    fun expectedOutput() {
        for (output in listExpectedOutput) {
            println(output)
        }
    }
    
    fun checkHasil(emailPendaftarFromUser: List<String>) {
        val emailPendaftar = listOf(
            "alice@gmail.com",
            "bob@gmail.com",
            "charlie@gmail.com",
            "alice@gmail.com",
            "diana@gmail.com"
        )
        
        if (emailPendaftar != emailPendaftarFromUser) {
            errorLog.add("pastikan nilai dari variabel emailPendaftar sesuai dengan nilai awalnya (tidak dimodifikasi)")
        }
        
        val emailTerdaftar = mutableSetOf<String>()

        for (email in emailPendaftar) {
            if (email in emailTerdaftar) {
                listExpectedOutput.add("Email '$email' sudah terdaftar. Pendaftaran ditolak.")
            } else {
                emailTerdaftar.add(email)
                listExpectedOutput.add("Email '$email' berhasil didaftarkan.")
            }
        }

        listExpectedOutput.add("Daftar email yang sudah terdaftar:")
        
        for (email in emailTerdaftar) {
            listExpectedOutput.add(email)
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