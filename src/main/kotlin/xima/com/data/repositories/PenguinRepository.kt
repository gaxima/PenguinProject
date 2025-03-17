package xima.com.data.repositories

import xima.com.data.model.Penguin

interface PenguinRepository {

    fun getAllPenguins(name:String?): List<Penguin>

    fun getPenguinById(id: String): Penguin?

    fun getRandomPenguin(): Penguin

    fun insertPenguin(penguin: Penguin)

    fun deletePenguinById(id: String): Boolean

    fun updatePenguinById(id: Int, updatedPenguin: Penguin): Boolean
}