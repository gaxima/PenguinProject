package xima.com.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import xima.com.data.model.Penguin
import xima.com.repositories.PenguinRepository


val repository = PenguinRepository()

fun Route.initial() {
    get("/") {
        call.respond(
            HttpStatusCode.OK,
            "Hello World"
        )
    }
}


fun Route.allPenguins() {
    get("/penguins") {
        val name = call.request.queryParameters["name"]

        val filteredPenguin = repository.getAllPenguins(name)
        if (filteredPenguin.isNotEmpty()) {
            call.respond(HttpStatusCode.OK, filteredPenguin)
        }else{
            call.respond(HttpStatusCode.NotFound, "No penguin found!")
        }
    }
}

fun Route.penguinById() {
    get(path = "/penguins/{id}") {
        val id = call.parameters["id"]
        val penguin = id?.let { repository.getPenguinById(it) }

        if (penguin != null) {
            call.respond(HttpStatusCode.OK, penguin)
        } else {
            call.respond(HttpStatusCode.NotFound, "Penguin Not Found!")
        }
    }
}

fun Route.randomPenguin() {
    get("/randomPenguin") {
        call.respond(
            HttpStatusCode.OK,
            repository.getRandomPenguin()
        )
    }
}


fun Route.insertPenguin() {
    post("/insertPenguin") {
        try {
            val newPenguin = call.receive<Penguin>()
            repository.insertPenguin(newPenguin)
            call.respondText("Penguin was added", status = HttpStatusCode.Created)
        } catch (e: ContentTransformationException) {
            call.respond(HttpStatusCode.BadRequest, "Invalid Request!")
        }

    }
}

fun Route.deletePenguinById() {
    delete("/removePenguinById/{id}") {

        val id = call.parameters["id"]

        if (id == null) {
            call.respond(HttpStatusCode.BadRequest)
            return@delete
        }
        if (repository.deletePenguinById(id)) {
            call.respond(HttpStatusCode.NoContent, "penguin deleted")
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }
}

fun Route.updatePenguinById() {
    put("/updatePenguinById/{id}") {
        val penguin = call.receive<Penguin>()
        val penguinId = call.parameters["id"]?.toIntOrNull()

        if (penguinId == null) {
            call.respond(HttpStatusCode.BadRequest, "It has to be a number!")
            return@put
        }
        val updated = repository.updatePenguinById(penguinId, penguin)
        if (updated) {
            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(
                HttpStatusCode.NotFound,
                "Penguin not found! $penguinId"
            )
        }

    }
}
