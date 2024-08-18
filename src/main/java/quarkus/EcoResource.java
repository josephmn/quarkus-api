package quarkus;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path(value = "/saludar")
public class EcoResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String saludar() {
        return "Hola";
    }

    @GET
    @Path("/dias")
    @Produces(MediaType.APPLICATION_JSON)
    public String dias() {
        return "Hola, buenos días";
    }

    @GET
    @Path("/tardes")
    @Produces(MediaType.APPLICATION_JSON)
    public String tardes() {
        return "Hola, buenas tardes";
    }

    @GET
    @Path("/noches")
    @Produces(MediaType.APPLICATION_JSON)
    public String noches() {
        return "Hola, buenas noches";
    }

}
