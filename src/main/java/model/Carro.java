package model;

import services.domain.persistence.Entity;

import java.util.Objects;

public class Carro extends Entity {
    private String placa;
    private String modelo;
    private Integer anoFabricacao;
    private Integer km;

    private Carro(String placa, String modelo, Integer anoFabricacao, Integer km) {
        this.placa = placa;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.km = km;
    }

    public static Carro of(String placa, String modelo, Integer anoFabricacao, Integer km) {
        return new Carro(placa, modelo, anoFabricacao, km);
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }

    public Integer getKm() {
        return km;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Carro carro = (Carro) o;
        return Objects.equals(placa, carro.placa);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(placa);
    }
}
