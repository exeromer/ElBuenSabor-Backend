package com.powerRanger.ElBuenSabor.dtos;

import jakarta.validation.constraints.*;
import java.util.ArrayList;

public class ArticuloInsumoRequestDTO {

    @NotEmpty(message = "La denominación no puede estar vacía")
    @Size(max = 255)
    private String denominacion;

    @NotNull(message = "El precio de venta es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio de venta debe ser mayor que cero")
    private Double precioVenta;

    @NotNull(message = "El ID de la unidad de medida es obligatorio")
    private Integer unidadMedidaId;

    @NotNull(message = "El ID de la categoría es obligatorio")
    private Integer categoriaId;

    @NotNull(message = "El estado activo es obligatorio")
    private Boolean estadoActivo;

    @DecimalMin(value = "0.0", message = "El precio de compra no puede ser negativo")
    private Double precioCompra;

    @NotNull(message = "Debe especificarse si es para elaborar")
    private Boolean esParaElaborar;

    // Getters y Setters actualizados
    public String getDenominacion() { return denominacion; }
    public void setDenominacion(String denominacion) { this.denominacion = denominacion; }
    public Double getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(Double precioVenta) { this.precioVenta = precioVenta; }
    public Integer getUnidadMedidaId() { return unidadMedidaId; }
    public void setUnidadMedidaId(Integer unidadMedidaId) { this.unidadMedidaId = unidadMedidaId; }
    public Integer getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Integer categoriaId) { this.categoriaId = categoriaId; }
    public Boolean getEstadoActivo() { return estadoActivo; }
    public void setEstadoActivo(Boolean estadoActivo) { this.estadoActivo = estadoActivo; }
    public Double getPrecioCompra() { return precioCompra; }
    public void setPrecioCompra(Double precioCompra) { this.precioCompra = precioCompra; }
    public Boolean getEsParaElaborar() { return esParaElaborar; }
    public void setEsParaElaborar(Boolean esParaElaborar) { this.esParaElaborar = esParaElaborar; }

}