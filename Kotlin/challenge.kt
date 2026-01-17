fun main(){
    val test1 = Test()
    test1.check()
}

class Test {

    var listOutput = mutableListOf<String>()
	val listExpectedOutput = mutableListOf("Halo Dicoding!", "Halo Dunia!")

    fun println(input: String){
        listOutput.add(input)
        kotlin.io.println(input)
    }
    
    fun main() {
        println("Halo Dicoding!")
        println("Halo Dunia!")
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