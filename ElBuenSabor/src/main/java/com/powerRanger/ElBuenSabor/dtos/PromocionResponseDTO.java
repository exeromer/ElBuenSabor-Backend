package com.powerRanger.ElBuenSabor.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.powerRanger.ElBuenSabor.entities.enums.TipoPromocion; // Importar el enum TipoPromocion

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;

public class PromocionResponseDTO {
    private Integer id;
    private String denominacion;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaDesde;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaHasta;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horaDesde;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horaHasta;
    private String descripcionDescuento;
    private Double precioPromocional;
    private TipoPromocion tipoPromocion;
    private Double porcentajeDescuento;
    private Boolean estadoActivo;

    private List<ImagenResponseDTO> imagenes = new ArrayList<>();
    private List<PromocionDetalleResponseDTO> detallesPromocion = new ArrayList<>();

    private List<SucursalSimpleResponseDTO> sucursales = new ArrayList<>();

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getDenominacion() { return denominacion; }
    public void setDenominacion(String denominacion) { this.denominacion = denominacion; }
    public LocalDate getFechaDesde() { return fechaDesde; }
    public void setFechaDesde(LocalDate fechaDesde) { this.fechaDesde = fechaDesde; }
    public LocalDate getFechaHasta() { return fechaHasta; }
    public void setFechaHasta(LocalDate fechaHasta) { this.fechaHasta = fechaHasta; }
    public LocalTime getHoraDesde() { return horaDesde; }
    public void setHoraDesde(LocalTime horaDesde) { this.horaDesde = horaDesde; }
    public LocalTime getHoraHasta() { return horaHasta; }
    public void setHoraHasta(LocalTime horaHasta) { this.horaHasta = horaHasta; }
    public String getDescripcionDescuento() { return descripcionDescuento; }
    public void setDescripcionDescuento(String descripcionDescuento) { this.descripcionDescuento = descripcionDescuento; }
    public Double getPrecioPromocional() { return precioPromocional; }
    public void setPrecioPromocional(Double precioPromocional) { this.precioPromocional = precioPromocional; }

    public TipoPromocion getTipoPromocion() { return tipoPromocion; }
    public void setTipoPromocion(TipoPromocion tipoPromocion) { this.tipoPromocion = tipoPromocion; }
    public Double getPorcentajeDescuento() { return porcentajeDescuento; }
    public void setPorcentajeDescuento(Double porcentajeDescuento) { this.porcentajeDescuento = porcentajeDescuento; }
    public Boolean getEstadoActivo() { return estadoActivo; }
    public void setEstadoActivo(Boolean estadoActivo) { this.estadoActivo = estadoActivo; }
    public List<ImagenResponseDTO> getImagenes() { return imagenes; }
    public void setImagenes(List<ImagenResponseDTO> imagenes) { this.imagenes = imagenes; }
    public List<PromocionDetalleResponseDTO> getDetallesPromocion() { return detallesPromocion; }
    public void setDetallesPromocion(List<PromocionDetalleResponseDTO> detallesPromocion) { this.detallesPromocion = detallesPromocion; }
    public List<SucursalSimpleResponseDTO> getSucursales() { return sucursales; }
    public void setSucursales(List<SucursalSimpleResponseDTO> sucursales) { this.sucursales = sucursales; }
}