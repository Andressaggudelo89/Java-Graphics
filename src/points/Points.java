/*
 * Ejemplo básico en Java2D
 * 
 * Tomado de el Tutorial de Java2D de ZetTutorial: http://zetcode.com/tutorials/java2dtutorial/
 * 
 * Java tiene un tutorial oficial para Java2D: http://docs.oracle.com/javase/tutorial/2d/index.html
 */
package points;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Point;

public class Points extends JPanel {

    /*
   * En esta función se dibuja.
   * La función es llamada por Java2D.
   * Se recibe una variable Graphics, que contiene la información del contexto
   * gráfico.
   * Es necesario hacerle un cast a Graphics2D para trabajar en Java2D.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // size es el tamaño de la ventana.
        Dimension size = getSize();

        // Insets son los bordes y los títulos de la ventana.
        Insets insets = getInsets();

        int w = size.width - insets.left - insets.right;
        int h = size.height - insets.top - insets.bottom;

        g2d.setColor(Color.red);

        g2d.drawLine(0, h / 2, w, h / 2);
        g2d.drawLine(w / 2, 0, w / 2, h);
        
        for (int i = 15; i <= 90; i+=15) {
            coordinates(g2d, i, w, h);
        }
    }
    
    public void coordinates(Graphics2D g2d, int angle, int w, int h){
        g2d.setColor(Color.blue);
        Point points[] = new Point[5];
        //g2d.setStroke(new BasicStroke(2));
        for (int i = 0; i < 4; i++) {
            int x_pos = (int)Math.round(200*Math.cos(Math.toRadians(angle)));
            int y_pos  = (int)Math.round(200*Math.sin(Math.toRadians(angle)));
            points[i] = new Point(x_pos, y_pos);
            g2d.drawLine(w/2+x_pos, h/2+y_pos, w/2+x_pos, h/2+y_pos);
            angle += 90;
        }
        points[4] = new Point(points[0].x, points[0].y);
        
        g2d.drawLine(w/2-points[0].x, h/2-points[0].y, w/2-points[1].x, h/2-points[1].y);
        g2d.drawLine(w/2+points[1].x, h/2+points[1].y, w/2+points[2].x, h/2+points[2].y);
        g2d.drawLine(w/2-points[2].x, h/2-points[2].y, w/2-points[3].x, h/2-points[3].y);
        g2d.drawLine(w/2+points[3].x, h/2+points[3].y, w/2+points[4].x, h/2+points[4].y);
        
    }

    public void bresenham(int w, int h, Graphics2D g2d, int x1, int y1, int x2, int y2, int sign) {

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        // Transformaion
        x1 += w / 2;
        y1 += h / 2;
        x2 += w / 2;
        int incE = 2 * dy;
        int incNE = 2 * dy - 2 * dx;
        int d = 2 * dy - dx;
        int y = y1;
        for (int x = x1; x <= x2; x++) {
            g2d.drawLine(x, y, x, y);
            if (d <= 0) {
                d += incE;
            } else {
                d += incNE;
                y += 1 * sign;
            }
        }

    }

    public static void main(String[] args) {

        // Crear un nuevo Frame
        JFrame frame = new JFrame("Points");

        // Al cerrar el frame, termina la ejecución de este programa
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Agregar un JPanel que se llama Points (esta clase)
        frame.add(new Points());

        // Asignarle tamaño
        frame.setSize(500, 500);

        // Poner el frame en el centro de la pantalla
        frame.setLocationRelativeTo(null);

        // Mostrar el frame
        frame.setVisible(true);
    }
}
