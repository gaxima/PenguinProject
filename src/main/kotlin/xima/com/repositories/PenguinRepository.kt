package xima.com.repositories

import xima.com.data.model.Penguin
import javax.print.DocFlavor.STRING

private const val BASE_URL = "http://0.0.0.0:8080"

class PenguinRepository {
    private val penguins = mutableListOf<Penguin>(
        Penguin(1, "Joseph", "He's nice", "$BASE_URL/penguins/penguin1.jpg"),
        Penguin(2, "Nikola", "He's nice two", "$BASE_URL/penguins/penguin2.jpg"),
        Penguin(3, "Helena", "He's nice three", "$BASE_URL/penguins/penguin3.jpg"),
        Penguin(4, "RG10", "He's nice fork", "$BASE_URL/penguins/penguin4.jpg"),
        Penguin(5, "cassio", "He aint nice", "$BASE_URL/penguins/penguin5.jpg")
    )

    fun getAllPenguins(name: String?): List<Penguin> {
        return if (name != null) {
            penguins.filter { it.name.equals(name, ignoreCase = true)}
        } else {
            penguins
        }
    }

    fun getPenguinById(id: String): Penguin? {
        return penguins.find { it.id.toString() == id }
    }

    fun getRandomPenguin(): Penguin {
        return penguins.random()
    }

    fun insertPenguin(penguin: Penguin) {
        penguins.add(penguin)
    }

    fun deletePenguinById(id: String): Boolean {
        return penguins.removeIf { it.id.toString() == id }
    }

    fun updatePenguinById(id: Int, updatedPenguin: Penguin): Boolean {
        val penguin = penguins.firstOrNull() { it.id == id } ?: return false

        penguin.description = updatedPenguin.description
        penguin.name = updatedPenguin.name
        penguin.id = updatedPenguin.id
        penguin.imageUrl = updatedPenguin.imageUrl
        return true
    }
}