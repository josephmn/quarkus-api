package quarkus;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class TemperaturaService {

    private List<Temperatura> valores = new ArrayList<>();

    public void addTemperatura(Temperatura t) {
        valores.add(t);
    }

    public List<Temperatura> obtenerTemperaturas() {
        return Collections.unmodifiableList(valores);
    }

    public int maxima() {
        return valores.stream()
                .mapToInt(Temperatura::getMaxima)
                .max()
                .orElse(0);
    }
}
