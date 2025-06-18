package com.powerRanger.ElBuenSabor.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "detalle_pedido")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class DetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private Integer cantidad;

    @NotNull(message = "El subtotal es obligatorio")
    @DecimalMin(value = "0.0", message = "El subtotal no puede ser negativo")
    private Double subTotal;

    @NotNull(message = "El artículo es obligatorio para el detalle")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "articulo_id", nullable = false)
    private Articulo articulo;

    @NotNull(message = "El pedido es obligatorio para el detalle")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promocion_aplicada_id", nullable = true) // Puede ser nulo si no hay promoción
    private Promocion promocionAplicada;

    @Column(name = "descuento_aplicado_por_promocion", nullable = true) // Puede ser nulo o 0.0
    private Double descuentoAplicadoPorPromocion = 0.0; // Inicializar a 0.0 por defecto

    public DetallePedido() {}

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public Double getSubTotal() { return subTotal; }
    public void setSubTotal(Double subTotal) { this.subTotal = subTotal; }
    public Articulo getArticulo() { return articulo; }
    public void setArticulo(Articulo articulo) { this.articulo = articulo; }
    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }
    public Promocion getPromocionAplicada() { return promocionAplicada; }
    public void setPromocionAplicada(Promocion promocionAplicada) { this.promocionAplicada = promocionAplicada; }
    public Double getDescuentoAplicadoPorPromocion() { return descuentoAplicadoPorPromocion;}
    public void setDescuentoAplicadoPorPromocion(Double descuentoAplicadoPorPromocion) { this.descuentoAplicadoPorPromocion = descuentoAplicadoPorPromocion; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetallePedido that = (DetallePedido) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "DetallePedido{" + "id=" + id + ", cantidad=" + cantidad + ", subTotal=" + subTotal +
                ", articuloId=" + (articulo != null ? articulo.getId() : "null") +
                ", pedidoId=" + (pedido != null ? pedido.getId() : "null") +
                ", promocionAplicadaId=" + (promocionAplicada != null ? promocionAplicada.getId() : "null") +
                ", descuentoAplicadoPorPromocion=" + descuentoAplicadoPorPromocion + '}';
    }
}