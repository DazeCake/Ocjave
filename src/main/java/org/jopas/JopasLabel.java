package org.jopas;

import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


/**
 *
 * <p>Title: JOPAS</p>
 *
 * <p>Description: Java to Octave</p>
 *
 * <p>Copyright: (C) Copyright 2000-2005, by Object Refinery Limited and
 * Contributors.
 *
 * Project Info:  	http://jopas.sourceforge.net
 *                      http://www.eside.deusto.es/grupos/eside_pas
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation,
 * Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA.
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc.
 * in the United States and other countries.]
 * </p>
 *
 * <p>Company: PAS - Universidad de Deusto</p>
 *
 * @author Oscar Lage Serrano - Javier Vicente
 * @version 1.01
 */
public class JopasLabel extends javax.swing.JLabel {
    private String varY = null;
    private String varX = null;
    private String title = null;
    private String XTitle = null;
    private String YTitle = null;
    private Plotter plotter = null;
    private BufferedImage image = null;
    private boolean painted = false;

    /**
     * Constructor of de class
     *
     * @param jopas Jopas
     */
    public JopasLabel(Jopas jopas) { //, int width, int height) {
        super();
        this.setSize(400, 400);
        this.addComponentListener(new JopasLabel_componentAdapter(this));
        plotter = new Plotter(jopas);

    }
    /**
     * Internal function to repaint the label when it is resized
     *
     * @param e ComponentEvent
     */
    public void this_componentResized(ComponentEvent e) {
        if (painted) {
            if (varX==null){
                image = plotter.getImage(varY, title, this.XTitle,this.YTitle, this.getWidth(),
                                         this.getHeight());
            }else image = plotter.getImage(varX, varY, title, this.XTitle,this.YTitle, this.getWidth(),
                                         this.getHeight());
            ImageIcon icon = new ImageIcon(image);
            this.setIcon(icon);

        }

    }

    /**
     * Function to draw the variable "var" in the label showing the title "title"
     *
     * @param var String
     * @param title String
     */
    public void paintLabel(String var, String title) {
        paintLabel(var,title,"","");
    }

    /**
     * Function to draw the variable "var" in the label showing the title "title"
     * and the titles of XYaxis
     *
     * @param var String
     * @param title String
     * @param XTitle String
     * @param YTitle String
     */
    public void paintLabel(String var, String title,String XTitle, String YTitle) {
        this.varY = var;
        this.varX = null;
        this.title = title;
        this.XTitle = XTitle;
        this.YTitle = YTitle;
        image = plotter.getImage(var, title, XTitle, YTitle, this.getWidth(), this.getHeight());

        ImageIcon icon = new ImageIcon(image); //.getScaledInstance(width,height,Image.SCALE_DEFAULT));
        this.setIcon(icon);
        painted = true;

    }

    /**
     * Function to draw the variableX "varX" and variableY "varY" in the label showing the title "title"
     *
     * @param varX String
     * @param varY String
     * @param title String
     */
    public void paintLabel(String varX, String varY, String title) {
        paintLabel(varX,varY,title,"","");
    }
    /**
     * Function to draw the variableX "varX" and variableY "varY" in the label showing the title "title"
     * and the titles of XYaxis
     *
     * @param varX String
     * @param varY String
     * @param title String
     * @param XTitle String
     * @param YTitle String
     */

    public void paintLabel(String varX,String varY, String title,String XTitle, String YTitle) {
        this.varY = varY;
        this.varX = varX;
        this.title = title;
        this.XTitle = XTitle;
        this.YTitle = YTitle;
        image = plotter.getImage(this.varX,this.varY, title, XTitle, YTitle, this.getWidth(), this.getHeight());

        ImageIcon icon = new ImageIcon(image); //.getScaledInstance(width,height,Image.SCALE_DEFAULT));
        this.setIcon(icon);
        painted = true;

    }



}


class JopasLabel_componentAdapter extends ComponentAdapter {
    private JopasLabel adaptee;
    JopasLabel_componentAdapter(JopasLabel adaptee) {
        this.adaptee = adaptee;
    }

    public void componentResized(ComponentEvent e) {
        adaptee.this_componentResized(e);
    }
}
