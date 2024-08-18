package quarkus;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.Optional;

@Path(value = "/saludar")
public class EcoResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String saludar(@QueryParam("mensaje") String mensaje) {
        // using traditional if - else
        /*if (mensaje == null) {
            return "No sé muy bien que decir";
        } else {
            return "> " + mensaje;
        }*/

        // using Optional
        return Optional.ofNullable(mensaje)
                .map(n -> "> " + n)
                .orElse("No sé muy bien que decir");
    }

    @GET
    @Path("/{nombre}")
    public String saludo(@PathParam("nombre") String nombre) {
        return "Hola, " + nombre;
    }

    @GET
    @Path("/{nombre}/mayusculas")
    public String gritar(@PathParam("nombre") String nombre) {
        return "HOLA, " + nombre.toUpperCase();
    }

}
