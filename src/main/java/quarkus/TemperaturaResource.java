package quarkus;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Arrays;
import java.util.List;

@Path("/temperaturas")
public class TemperaturaResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Temperatura> list() {
        return Arrays.asList(
                new Temperatura("Madrid", 18, 28),
                new Temperatura("Málaga", 15, 31),
                new Temperatura("Tenerife", 20, 30),
                new Temperatura("Bogotá", 13, 27)
        );
    }

    @GET
    @Path("/una")
    //@Produces(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Temperatura medicion() {
        return new Temperatura("Málaga", 18, 20);
    }

}
