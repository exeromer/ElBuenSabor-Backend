package com.powerRanger.ElBuenSabor.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "El nombre de la empresa no puede estar vacío")
    @Size(max = 255, message = "El nombre de la empresa no puede exceder los 255 caracteres")
    private String nombre;

    @Column(nullable = false)
    @NotEmpty(message = "La razón social no puede estar vacía")
    @Size(max = 255, message = "La razón social no puede exceder los 255 caracteres")
    private String razonSocial;

    @Column(nullable = false, unique = true, length = 13)
    @NotNull(message = "El CUIL/CUIT es obligatorio")
    @Pattern(regexp = "^(20|23|24|27|30|33|34)-[0-9]{8}-[0-9]{1}$", message = "El formato del CUIL/CUIT no es válido. Ejemplo: 20-12345678-9")
    private String cuil; // Cambiado a String para CUIT/CUIL con guiones

    // Una Empresa tiene Sucursales. mappedBy indica que Sucursal es dueña de la relación.
    // CascadeType.ALL: Si borras la empresa, se borran sus sucursales. ¡Cuidado con esto!
    // OrphanRemoval=true: Si quitas una sucursal de esta lista y guardas la empresa, se borra la sucursal.
    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Sucursal> sucursales = new ArrayList<>();

    public Empresa() {
        this.sucursales = new ArrayList<>();
    }

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getRazonSocial() { return razonSocial; }
    public void setRazonSocial(String razonSocial) { this.razonSocial = razonSocial; }
    public String getCuil() { return cuil; } // Devuelve String
    public void setCuil(String cuil) { this.cuil = cuil; } // Acepta String
    public List<Sucursal> getSucursales() { return sucursales; }
    public void setSucursales(List<Sucursal> sucursales) { this.sucursales = sucursales; }

    // Métodos Helper para la relación con Sucursal
    public void addSucursal(Sucursal sucursal) {
        if (this.sucursales == null) {
            this.sucursales = new ArrayList<>();
        }
        this.sucursales.add(sucursal);
        sucursal.setEmpresa(this); // Establece la relación bidireccional
    }

    public void removeSucursal(Sucursal sucursal) {
        if (this.sucursales != null) {
            this.sucursales.remove(sucursal);
            sucursal.setEmpresa(null); // Rompe la relación
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empresa empresa = (Empresa) o;
        return Objects.equals(id, empresa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", razonSocial='" + razonSocial + '\'' +
                ", cuil='" + cuil + '\'' +
                '}';
    }
}