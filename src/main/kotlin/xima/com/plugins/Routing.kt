package xima.com.plugins

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import xima.com.routes.*
import java.io.File

fun Application.configureRouting() {
    routing {
        initial()
        randomPenguin()
        allPenguins()
        penguinById()
        insertPenguin()
        deletePenguinById()
        updatePenguinById()
        //staticResources("/resources", "static")
        // Static plugin. Try to access `/static/index.html`
        static{
            resources("static")
        }
    }
}
