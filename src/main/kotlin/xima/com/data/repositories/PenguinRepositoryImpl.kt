package xima.com.data.repositories

import xima.com.data.model.Penguin

class PenguinRepositoryImpl: PenguinRepository {
    private val penguins = mutableListOf<Penguin>(
        Penguin(1, "Joseph", "He's nice", ""),
        Penguin(2, "Nikola", "He's nice two", ""),
        Penguin(3, "Helena", "He's nice three", ""),
        Penguin(4, "RG10", "He's nice fork", ""),
        Penguin(5, "cassio", "He aint nice", "")
    )

    override fun getAllPenguins(name: String?): List<Penguin> {
        return if (name != null) {
            penguins.filter { it.name.equals(name, ignoreCase = true)}
        } else {
            penguins
        }
    }

    override fun getPenguinById(id: String): Penguin? {
        return penguins.find { it.id.toString() == id }
    }

    override fun getRandomPenguin(): Penguin {
        return penguins.random()
    }

    override fun insertPenguin(penguin: Penguin) {
        penguins.add(penguin)
    }

    override fun deletePenguinById(id: String): Boolean {
        return penguins.removeIf { it.id.toString() == id }
    }

    override fun updatePenguinById(id: Int, updatedPenguin: Penguin): Boolean {
        val penguin = penguins.firstOrNull() { it.id == id } ?: return false

        penguin.description = updatedPenguin.description
        penguin.name = updatedPenguin.name
        penguin.id = updatedPenguin.id
        penguin.imageUrl = updatedPenguin.imageUrl
        return true
    }
}