/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControladorPedido {
    private static Pedido pedidoActual;

    public static Pedido getPedidoActual() {
        if (pedidoActual == null) {
            int idAleatorio = (int) (Math.random() * 90000) + 10000;
            pedidoActual = new Pedido(String.valueOf(idAleatorio));
        }
        return pedidoActual;
    }

    public static void guardarPedido() {
        List<Pedido> pedidos = cargarPedidosDesdeArchivo();
        Pedido pedidoActual = ControladorPedido.getPedidoActual();
        pedidoActual.actualizarTotal();

        boolean pedidoExistente = false;
        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getId().equals(pedidoActual.getId())) {
                pedidos.set(i, pedidoActual);
                pedidoExistente = true;
                break;
            }
        }

        if (!pedidoExistente) {
            pedidos.add(pedidoActual);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("pedidos.csv"))) {
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
            System.err.println("Error al guardar el pedido: " + e.getMessage());
        }
    }

    public static List<Pedido> cargarPedidosDesdeArchivo() {
        List<Pedido> pedidos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("pedidos.csv"))) {
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
                    pedidoActual = new Pedido(partes[0]);
                    pedidoActual.setTotal(Double.parseDouble(partes[1]));
                    pedidoActual.setEstado(partes[2]);
                } else if (partes.length == 3) {
                    String nombreProducto = partes[0];
                    int cantidad = Integer.parseInt(partes[1]);
                    double precio = Double.parseDouble(partes[2]);

                    Producto producto = new Producto(nombreProducto, precio);
                    DetallePedido detalle = new DetallePedido(producto, cantidad);
                    detalles.add(detalle);
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
}
