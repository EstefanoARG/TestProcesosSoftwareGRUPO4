
import vista.LoginV;
import vista.SplashScreen;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author GRUPO 4 PSFW
 */
public class main {
    public static void main(String[] args) {
        new SplashScreen("src/vista/imagenes/ImagenesInicio/cargandoGIF.gif", 1000); // 1000 ms = 1 segundo
        LoginV vistaInicio = new LoginV();
        vistaInicio.setVisible(true);
    }
        
}
