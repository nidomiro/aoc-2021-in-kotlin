import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInputFromRoot(name: String) = File("src", name).readLines()
fun readInputLines(folder: String, name: String) = File("src/main/kotlin/${folder}", name).readLines()
fun bindReadInputLines(folder: String): (String) -> List<String> = { name -> readInputLines(folder, name) }

fun readInput(folder: String, name: String) = File("src/main/kotlin/${folder}", name).readText()
fun bindReadInput(folder: String): (String) -> String = { name -> readInput(folder, name) }


/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

