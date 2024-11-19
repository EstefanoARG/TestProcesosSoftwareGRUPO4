/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

/**
 *
 * @author Luis Bizarro
 */
import javax.swing.*;
import java.awt.*;

public class SplashScreen {
    private JWindow window;

    // Constructor
    public SplashScreen(String imagePath, int durationMillis) {
        // Crear un JWindow para mostrar el splash screen
        window = new JWindow();

        // Cargar la imagen
        ImageIcon image = new ImageIcon(imagePath);
        JLabel label = new JLabel(image);

        // Agregar la imagen al JWindow
        window.getContentPane().add(label);
        window.setSize(image.getIconWidth(), image.getIconHeight());

        // Centrar el JWindow en la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - window.getSize().width) / 2;
        int y = (screenSize.height - window.getSize().height) / 2;
        window.setLocation(x, y);

        // Mostrar el splash durante la duración especificada
        showSplash(durationMillis);
    }

    // Método para mostrar el splash screen
    private void showSplash(int durationMillis) {
        // Hacer visible el JWindow
        window.setVisible(true);

        // Esperar el tiempo especificado
        try {
            Thread.sleep(durationMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Ocultar el JWindow
        window.setVisible(false);
        window.dispose();
    }
}

