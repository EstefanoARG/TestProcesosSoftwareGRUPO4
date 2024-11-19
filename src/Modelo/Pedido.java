/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author rodri
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private String id;
    private List<DetallePedido> detalles;
    private double total;
    private String estado;

    public Pedido(String id) {
        this.id = id;
        this.detalles = new ArrayList<>();
        this.total = 0.0;
        this.estado = "Preparacion";
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public double getTotal() {
        return total;
    }

    public void agregarDetalle(DetallePedido detalle) {
        boolean existe = false;
        for (DetallePedido d : detalles) {
            if (d.getProducto().getNombre().equals(detalle.getProducto().getNombre())) {
                d.setCantidad(d.getCantidad() + detalle.getCantidad());
                existe = true;
                break;
            }
        }

        if (!existe) {
            detalles.add(detalle);
        }
        actualizarTotal();
    }

    public void actualizarTotal() {
        total = 0.0;
        for (DetallePedido detalle : detalles) {
            total += detalle.calcularSubtotal();
        }
    }

    public void guardarEnArchivo(String filePath) {
    List<Pedido> pedidos = cargarPedidosDesdeArchivo(filePath);
    boolean pedidoActualizado = false;

    for (int i = 0; i < pedidos.size(); i++) {
        if (pedidos.get(i).getId().equals(this.id)) {
            pedidos.set(i, this);
            pedidoActualizado = true;
            break;
        }
    }

    if (!pedidoActualizado) {
        pedidos.add(this);
    }

    File file = new File(filePath);
    if (!file.exists()) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.err.println("Error al crear el archivo: " + e.getMessage());
            return;
        }
    }

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
        for (Pedido pedido : pedidos) {
            writer.write(pedido.getId() + "," + pedido.getTotal() + "," + pedido.getEstado());
            writer.newLine();
            for (DetallePedido detalle : pedido.getDetalles()) {
                writer.write(detalle.getProducto().getNombre() + "," +
                        detalle.getCantidad() + "," +
                        detalle.getProducto().getPrecio());
                writer.newLine();
            }
            writer.newLine();
        }
    } catch (IOException e) {
        System.err.println("Error al escribir en el archivo: " + e.getMessage());
    }
}


    public static List<Pedido> cargarPedidosDesdeArchivo(String filePath) {
    List<Pedido> pedidos = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String linea;
        Pedido pedidoActual = null;
        List<DetallePedido> detalles = new ArrayList<>();

        while ((linea = reader.readLine()) != null) {
            if (linea.isEmpty()) {
                if (pedidoActual != null) {
                    pedidoActual.getDetalles().addAll(detalles);
                    pedidos.add(pedidoActual);
                    detalles.clear();
                    pedidoActual = null;
                }
                continue;
            }

            String[] partes = linea.split(",");
            if (partes.length == 3 && pedidoActual == null) {
                try {
                    pedidoActual = new Pedido(partes[0]);
                    pedidoActual.setTotal(Double.parseDouble(partes[1]));
                    pedidoActual.setEstado(partes[2]);
                } catch (NumberFormatException e) {
                    System.err.println("Error en el formato del pedido: " + e.getMessage());
                    continue;
                }
            } else if (partes.length == 3 && pedidoActual != null) {
                try {
                    String nombreProducto = partes[0];
                    int cantidad = Integer.parseInt(partes[1]);
                    double precio = Double.parseDouble(partes[2]);

                    Producto producto = new Producto(nombreProducto, precio);
                    DetallePedido detalle = new DetallePedido(producto, cantidad);
                    detalles.add(detalle);
                } catch (NumberFormatException e) {
                    System.err.println("Error en el formato de los detalles: " + e.getMessage());
                }
            }
        }

        if (pedidoActual != null) {
            pedidoActual.getDetalles().addAll(detalles);
            pedidos.add(pedidoActual);
        }
    } catch (IOException e) {
        System.err.println("Error al leer el archivo: " + e.getMessage());
    }

    return pedidos;
}

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Pedido ID: " + id + "\n");
        for (DetallePedido detalle : detalles) {
            sb.append(detalle.toString()).append("\n");
        }
        sb.append("Total: ").append(total);
        return sb.toString();
    }
}