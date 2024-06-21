package xima.com.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import xima.com.data.model.Penguin

private const val BASE_URL =  "http://172.0.0.1:8100"

private val penguins = listOf(
    Penguin("Joseph", "He's nice", "$BASE_URL/penguins/penguin1.jpg"),
    Penguin("Nikola", "He's nice two", "$BASE_URL/penguins/penguin2.jpg"),
    Penguin("Helena", "He's nice three", "$BASE_URL/penguins/penguin3.jpg"),
    Penguin("RG10", "He's nice fork", "$BASE_URL/penguins/penguin4.jpg"),
    Penguin("cassio", "He aint nice", "$BASE_URL/penguins/penguin5.jpg"),
)

fun Route.randomPenguin() {
    get("/randomPenguin") {
        call.respond(
            HttpStatusCode.OK,
            penguins.random()
        )
    }
}