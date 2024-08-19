package quarkus;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Path("/temperaturas")
public class TemperaturaResource {

    private final TemperaturaService temperaturas;

    @Inject
    public TemperaturaResource(TemperaturaService temperaturas) {
        this.temperaturas = temperaturas;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Temperatura nueva(Temperatura temp) {
        temperaturas.addTemperatura(temp);
        return temp;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Temperatura> list() {
        /*return Arrays.asList(
                new Temperatura("Madrid", 18, 28),
                new Temperatura("Málaga", 15, 31),
                new Temperatura("Tenerife", 20, 30),
                new Temperatura("Bogotá", 13, 27)
        );*/
        return Collections.unmodifiableList(temperaturas.obtenerTemperaturas());
    }

    @GET
    @Path("/una")
    //@Produces(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Temperatura medicion() {
        return new Temperatura("Málaga", 18, 20);
    }

    @GET
    @Path("/maxima")
    @Produces(MediaType.APPLICATION_JSON)
    public String maxima() {
        return Integer.toString(temperaturas.maxima());
    }

}
