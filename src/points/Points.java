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
        
        int angle = 30;
        int ang = angle;
        Point points[] = new Point[5];
        for (int i = 0; i < 4; i++) {
            int x_pos = (int)Math.round(200*Math.cos(Math.toRadians(angle)));
            int y_pos  = (int)Math.round(200*Math.sin(Math.toRadians(angle)));
            points[i] = new Point(w/2+x_pos, h/2+y_pos);
            angle += 90;
        }
        points[4] = new Point(points[0].x, points[0].y);
        
        coordinates(g2d, w, h, points);
        bresenham(g2d, points, w, h, ang);
    }
    
    
    public void bresenham(Graphics g, Point points[], int w, int h, int angle){
        g.setColor(Color.blue);
        for (int i = 0; i < 4; i++) {
            
            int x1 = points[i].x;
            int y1 = points[i].y;
            int x2 = points[i+1].x;
            int y2 = points[i+1].y;
            int dx = Math.abs(x2-x1);
            int dy = Math.abs(y2-y1);

            if(Math.abs((float)dy/dx)<=1 && (i == 0 || i == 2) ){ //Solo funciona para 2 de las 4 líneas
                System.out.println("Move on 'x'");

                int incE = 2*dy;
                int incNE = incE - 2*dx;
                int d = incE - dx;


                if(x1<x2){
                    int y = y1;
                    if(angle<=45){
                        System.out.println("el ciclo va de x1 a x2, increasing. 'Y' decrementa.");
                        for (int x = x1; x <= x2; x++) {
                            g.drawLine(x, y, x, y);
                            if(d<=0){
                                d += incE;
                            }else{
                                d += incNE;
                                y -= 1;
                            }
                        }
                    }else{
                        System.out.println("el ciclo va de x1 a x2, increasing. 'Y' aumenta.");
                        for (int x = x1; x <= x2; x++) {
                            g.drawLine(x, y, x, y);
                            if(d<=0){
                                d += incE;
                            }else{
                                d += incNE;
                                y += 1;
                            }
                        }
                    }
                }else{
                    int y = y2;
                    if(angle<=45){
                        System.out.println("el ciclo va de x2 a x1, decreasing. 'Y' decrementa.");
                        for (int x = x2; x <= x1; x++) {
                            g.drawLine(x, y, x, y);
                            if(d<=0){
                                d += incE;
                            }else{
                                d += incNE;
                                y -= 1;
                            }
                        }
                    }else{
                        System.out.println("el ciclo va de x2 a x1, decreasing. 'Y' incrementa.");
                        for (int x = x2; x <= x1; x++) {
                            g.drawLine(x, y, x, y);
                            if(d<=0){
                                d += incE;
                            }else{
                                d += incNE;
                                y += 1;
                            }
                        }
                    }
                    // el ciclo va de x2 a x1, decreasing, Y aumenta
                }
            }
        }
        
        
        
        
        
//        if(Math.abs((float)dy/dx)>1 /* && (i == 1 || i == 3) */){
//            System.out.println("Move on 'y'");
//        }
    
    }
    
    
    public void coordinates(Graphics2D g2d, int w, int h, Point points[]){
        g2d.setColor(Color.blue);        
        for (int i = 0; i < 1; i++) {
            //g2d.drawLine(points[i].x, points[i].y, points[i+1].x, points[i+1].y);
            //System.out.println("x1: "+points[i].x+"  y1: "+points[i].y+"    x2: "+points[i+1].x+"  y2: "+points[i+1].y);
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
