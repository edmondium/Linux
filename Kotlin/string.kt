fun main(){
    val test1 = Test()
    test1.check()
}

class Test {

    var listOutput = mutableListOf<String>()
	val listExpectedOutput = mutableListOf("Berkas tersebut telah berhasil disimpan di: C:\\Users\\Budi\\Documents")
    
    val errorList = mutableListOf<String>()

    fun println(input: String){
        listOutput.add(input)
        kotlin.io.println(input)
    }
    
    fun main() {
        var fileName = "C:\\Users\\Budi\\Documents"
println("Berkas tersebut telah berhasil disimpan di: $fileName")
    }
    
    fun expectedOutput() {
        for (output in listExpectedOutput) {
            println(output)
        }
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