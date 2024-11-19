/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author rodri
 */
import java.util.ArrayList;
import java.util.List;

public class DetallePedido {
    private Producto producto;
    private int cantidad;
    private List<String> salsas;

    public DetallePedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.salsas = new ArrayList<>();
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<String> getSalsas() {
        return salsas;
    }

    public void agregarSalsa(String salsa) {
        salsas.add(salsa);
    }

    public void eliminarSalsa(String salsa) {
        salsas.remove(salsa);
    }

    public double calcularSubtotal() {
        return producto.getPrecio() * cantidad;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(producto.getNombre()).append(" x").append(cantidad).append(" - ");
        if (!salsas.isEmpty()) {
            sb.append("Salsas: ").append(String.join(", ", salsas));
        } else {
            sb.append("Sin salsas");
        }
        sb.append(" - $").append(calcularSubtotal());
        return sb.toString();
    }
}