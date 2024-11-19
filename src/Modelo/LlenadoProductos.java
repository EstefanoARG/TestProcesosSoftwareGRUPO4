package Modelo;
import Archivo.Archivo;
import java.util.ArrayList;
import java.util.List;
public class LlenadoProductos {
    public static void main(String[] args) {
        String filePath = "DatosProductos.txt";
        Archivo archivo = new Archivo(filePath);

        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto("1", "Filete de Pollo", 8.00, "Broasters"));
        productos.add(new Producto("2", "Filete de Pollo Royal", 9.00, "Broasters"));
        productos.add(new Producto("3", "Filete de Pollo Hawaino", 10.00, "Broasters"));
        productos.add(new Producto("4", "Filete de Pollo a lo pobre", 11.00, "Broasters"));

        productos.add(new Producto("5", "Hamburguesa de casa", 9.50, "Hamburguesas"));
        productos.add(new Producto("6", "Hamburguesa de casa Royal", 11, "Hamburguesas"));
        productos.add(new Producto("7", "Hamburguesa de casa Hawaina", 11, "Hamburguesas"));
        productos.add(new Producto("8", "Hamburguesa de casa Hawaina + platano", 11.5, "Hamburguesas"));
        productos.add(new Producto("9", "Hamburguesa de casa a lo pobre", 11, "Hamburguesas"));
        productos.add(new Producto("10", "Hamburguesa de casa + tocino", 13, "Hamburguesas"));

        productos.add(new Producto("11", "Salchichapa Simple", 11, "Salchipapas"));
        productos.add(new Producto("12", "Salchichapa con desilachado", 14, "Salchichapas"));
        productos.add(new Producto("13", "Salchichapa con chorizo", 14, "Salchichapas"));
        productos.add(new Producto("14", "Salchichapa Royal", 14, "Salchichapas"));
        productos.add(new Producto("15", "Salchichapa a lo pobre", 13, "Salchichapas"));

        productos.add(new Producto("16", "SalchiAlita", 14, "Alitas"));
        productos.add(new Producto("17", "Alita Broaster", 11, "Alitas"));

        productos.add(new Producto("18", "Combo 1", 13, "Combos"));
        productos.add(new Producto("19", "Combo 2", 13, "Combos"));
        productos.add(new Producto("20", "Combo 3", 14, "Combos"));
        productos.add(new Producto("21", "Combo 4", 15, "Combos"));


        productos.add(new Producto("22", "Coca Cola 1L", 5, "Bebidas"));
        productos.add(new Producto("22", "Inca Cola 1L", 5, "Bebidas"));
        productos.add(new Producto("22", "Coca Cola 2L", 8, "Bebidas"));
        productos.add(new Producto("22", "Inca Cola 2L", 8, "Bebidas"));
        archivo.guardarProductos(productos);

        System.out.println("Productos guardados en " + filePath);
    }
}
