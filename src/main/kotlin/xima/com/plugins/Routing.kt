package xima.com.plugins

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import xima.com.routes.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        randomPenguin()
        allPenguins()
        penguinById()
        insertPenguin()
        deletePenguinById()
        updatePenguinById()
        // Static plugin. Try to access `/static/index.html`
        static {
            resources("static")
        }
    }
}
