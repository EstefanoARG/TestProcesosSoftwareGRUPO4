
package Modelo;

import java.io.*;
import java.io.*;
import java.util.*;

public class ArchivoPersonal {
    private ArrayList<Personal> arregloPersonal = new ArrayList<>();
    private static final String ARCHIVO = "Personal.txt";

    public ArchivoPersonal() {
        cargarDesdeArchivo();
    }
    public boolean verificarCredenciales(String usuario, String contraseña) {
        for (Personal p : arregloPersonal) {
            if (p.getUsuario().equals(usuario) && p.getContrasenia().equals(contraseña)) {
                return true;
            }
        }
        return false;
    }

    public void agregarPersonal(Personal personal) {
        arregloPersonal.add(personal);
        guardarEnArchivo();
    }
    public Personal buscarPersonal(String id) {
        for (Personal p : arregloPersonal) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }
    public Personal buscarPersonalPorCredenciales(String usuario, String contra){
        for(Personal p: arregloPersonal){
            if(p.getUsuario().equals(usuario)&&p.getContrasenia().equals(contra)){
                return p;
            }
        }
        return null;
    }

    public void mostrarTodosLosEmpleados() {
        if (arregloPersonal.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            for (Personal p : arregloPersonal) {
                System.out.println("ID: "+p.getId()+" Nombre: "+p.getNombre()+" Apellido: "+p.getApellido()+" Usuario: "+p.getUsuario()+" Contraseña: "+p.getContrasenia());
            }
        }
    }

    public void eliminarPersonal(String id) {
        Iterator<Personal> iterator = arregloPersonal.iterator();
        while (iterator.hasNext()) {
            Personal p = iterator.next();
            if (p.getId().equals(id)) {
                iterator.remove();
                guardarEnArchivo();
                break;
            }
        }
    }

    public void actualizarPersonal(Personal personalActu) {
        for (int i = 0; i < arregloPersonal.size(); i++) {
            if (arregloPersonal.get(i).getId().equals(personalActu.getId())) {
                arregloPersonal.set(i, personalActu);
                guardarEnArchivo();
                break;
            }
        }
    }

    private void guardarEnArchivo() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVO))) {
            writer.println(Personal.getContador());

            for (Personal p : arregloPersonal) {
                writer.println(p.toString());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar en archivo: " + e.getMessage());
        }
    }

    private void cargarDesdeArchivo() {
        try (Scanner scanner = new Scanner(new File(ARCHIVO))) {
            if (scanner.hasNextLine()) {
                String contadorLine = scanner.nextLine();
                int contador = Integer.parseInt(contadorLine.trim()); // Asumimos que el contador está en la primera línea
                Personal.setContador(contador);

                while (scanner.hasNextLine()) {
                    String[] datos = scanner.nextLine().split(",");
                    if (datos.length == 6) {
                        Personal p = new Personal(datos[1], datos[2], datos[3], datos[4], datos[5], datos[0]);
                        arregloPersonal.add(p);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado. Creando nuevo archivo 'Personal.txt'.");
        }
    }

    public ArrayList<Personal> getArregloPersonal() {
        return arregloPersonal;
    }
    
}
