
package Archivo;

import Modelo.Producto;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Archivo {
    private String filePath;

    public Archivo(String filePath) {
        this.filePath = filePath;
    }

    public List<Producto> cargarProductos() {
        List<Producto> productos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Producto producto = new Producto(data[0], data[1], Double.parseDouble(data[2]), data[3]);
                productos.add(producto);
            }
        } catch (IOException e) {
        }
        return productos;
    }

    public void guardarProductos(List<Producto> productos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Producto producto : productos) {
                bw.write(producto.toString());
                bw.newLine();
            }
        } catch (IOException e) {
        }
    }

    public List<Producto> consultarPorCategoria(String categoria) {
        List<Producto> productosCategoria = new ArrayList<>();
        for (Producto producto : cargarProductos()) {
            if (producto.getCategoria().equalsIgnoreCase(categoria)) {
                productosCategoria.add(producto);
            }
        }
        return productosCategoria;
    }
}