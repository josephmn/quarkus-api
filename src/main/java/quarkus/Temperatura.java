package quarkus;

import java.util.Objects;

public class Temperatura {

    private String ciudad;
    private int minima;
    private int maxima;

    public Temperatura() {
    }

    public Temperatura(String ciudad, int minima, int maxima) {
        this.ciudad = ciudad;
        this.minima = minima;
        this.maxima = maxima;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getMinima() {
        return minima;
    }

    public void setMinima(int minima) {
        this.minima = minima;
    }

    public int getMaxima() {
        return maxima;
    }

    public void setMaxima(int maxima) {
        this.maxima = maxima;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Temperatura that = (Temperatura) o;
        return minima == that.minima && maxima == that.maxima && Objects.equals(ciudad, that.ciudad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ciudad, minima, maxima);
    }

    @Override
    public String toString() {
        return "Temperatura{" +
                "ciudad='" + ciudad + '\'' +
                ", minima=" + minima +
                ", maxima=" + maxima +
                '}';
    }
}
