/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author estef
 */
public class Administrador extends Personal {

    private ArchivoPersonal archivoPersonal;
    
    public Administrador(String nombre, String apellido, ArchivoPersonal archivoPersonal, String rol) {
        super(nombre, apellido, rol);
        this.archivoPersonal = archivoPersonal;
    }

    public void agregarEmpleado(Personal empleado) {
        archivoPersonal.agregarPersonal(empleado);
    }

    public void eliminarEmpleado(String id) {
        archivoPersonal.eliminarPersonal(id);

    }

    public void actualizarEmpleado(Personal empleadoActualizado) {
        archivoPersonal.actualizarPersonal(empleadoActualizado);
    }
}
