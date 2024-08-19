package quarkus;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.*;

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
    public Response maxima() {
        if (temperaturas.isEmpty()) {
            return Response.status(404).entity("No hay temperaturas").build();
        } else {
            int temperaturaMaxima = temperaturas.maxima();
            return Response.ok(Integer.toString(temperaturaMaxima))
                    .header("X-Hola", "Buenos días")
                    .build();
        }
    }

    @GET
    @Path("{ciudad}")
    @Produces(MediaType.APPLICATION_JSON)
    public Temperatura sacar(@PathParam("ciudad") String ciudad) {
        return temperaturas.sacarTemperatura(ciudad)
                .orElseThrow(() -> new NoSuchElementException("No hay registro para la ciudad " + ciudad));
    }

}
