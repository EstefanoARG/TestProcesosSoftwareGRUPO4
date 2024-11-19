
package Modelo;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Bizarro
 */
public class ArchivoPersonalTest {

    private ArchivoPersonal archivoPersonal;
    
    @Before
    public void setUp() {
        archivoPersonal = new ArchivoPersonal();
        
        Personal personal1 = new Personal("1", "yo1", "ape1", "usuario1", "contrasenia1", "Cajero");
        Personal personal2 = new Personal("2", "yo2", "ape2", "usuario2", "contrasenia2", "Cajero");
        
        archivoPersonal.agregarPersonal(personal1);
        archivoPersonal.agregarPersonal(personal2);
    }

    @Test
    public void testVerificarCredencialesConCredencialesCorrectas() {
        System.out.println("verificarCredenciales - Con credenciales correctas");
        
        String usuario = "usuario1";
        String contrasenia = "contrasenia1";
        
        boolean result = archivoPersonal.verificarCredenciales(usuario, contrasenia);
        
        assertTrue(result);
    }

    @Test
    public void testVerificarCredencialesConCredencialesIncorrectas() {
        System.out.println("verificarCredenciales - Con credenciales incorrectas");
        
        String usuario = "usuario3";
        String contrasenia = "contrasenia3";
        
        boolean result = archivoPersonal.verificarCredenciales(usuario, contrasenia);
        
        assertTrue(result);
    }

    @Test
    public void testVerificarCredencialesConCredencialesParcialmenteCorrectas() {
        System.out.println("verificarCredenciales - Con credenciales parcialmente correctas");
        
        String usuario = "usuario1";
        String contrasenia = "contrasenia3";
        
        boolean result = archivoPersonal.verificarCredenciales(usuario, contrasenia);
        
        assertTrue(result);
    }
}
