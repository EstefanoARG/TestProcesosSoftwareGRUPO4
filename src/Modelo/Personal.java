
package Modelo;

public class Personal {
    private static int contador=0;
    private String rol;
    private String id;
    private String nombre;
    private String apellido;
    private String usuario;
    private String contrasenia;

    public Personal(String nombre, String apellido, String rol) {
        this.id = generarId();
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = generarUsuario(nombre, apellido);
        this.rol=rol;
        this.contrasenia = generarContrasenia();
    }
    public Personal(String id, String nombre, String apellido, String usuario, String contrasenia, String rol) {
        this.rol=rol;
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }
    private String generarId() {
        contador++;
        return String.format("%05d", contador);
    }
    private String generarUsuario(String nombre, String apellido) {
        return nombre.toLowerCase().charAt(0) + apellido.toLowerCase();
    }

    private String generarContrasenia() {
        return "pass" + id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setId(String id){
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getRol() {
        return rol;
    }
    public static int getContador() {
        return contador;
    }


    public static void setContador(int nuevoContador) {
        contador = nuevoContador;
    }
    

    public String toString() {
        return rol+ "," +id + "," + nombre + "," + apellido + "," + usuario + "," + contrasenia;
    }
    
    



}
