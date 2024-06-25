package xima.com.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import xima.com.data.model.Penguin

private const val BASE_URL = "http://172.0.0.1:8100"


private var penguins = mutableListOf(
    Penguin(1, "Joseph", "He's nice", "$BASE_URL/penguins/penguin1.jpg"),
    Penguin(2, "Nikola", "He's nice two", "$BASE_URL/penguins/penguin2.jpg"),
    Penguin(3, "Helena", "He's nice three", "$BASE_URL/penguins/penguin3.jpg"),
    Penguin(4, "RG10", "He's nice fork", "$BASE_URL/penguins/penguin4.jpg"),
    Penguin(5, "cassio", "He aint nice", "$BASE_URL/penguins/penguin5.jpg"),
)


fun Route.allPenguins() {
    get("/allPenguins") {
        call.respond(
            HttpStatusCode.OK,
            penguins
        )
    }
}

fun Route.randomPenguin() {
    get("/randomPenguin") {
        call.respond(
            HttpStatusCode.OK,
            penguins.random()
        )
    }
}

fun Route.penguinById() {
    get(path = "/penguinById/{id}") {
        val id = call.parameters["id"]
        val penguin = penguins.find { it.id.toString() == id }

        if (penguin != null) {
            call.respond(HttpStatusCode.OK, penguin)
        } else {
            call.respond(HttpStatusCode.NotFound, "Penguin Not Found!")
        }
    }
}

fun Route.insertPenguin() {
    post("/insertPenguin") {
        try {
            val newPenguin = call.receive<Penguin>()
            penguins.add(newPenguin)
            call.respondText("Penguin was added", status = HttpStatusCode.Created)
        } catch (e: ContentTransformationException) {
            call.respond(HttpStatusCode.BadRequest, "Invalid Request!")
        }

    }
}

fun Route.deletePenguinById() {
    route("/removePenguin") {
        delete("/{id}") {

            val id = call.parameters["id"]

            if (id == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@delete
            }
            if (penguins.removeIf { it.id.toString() == id }) {
                call.respond(HttpStatusCode.NoContent)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }

    }
}
