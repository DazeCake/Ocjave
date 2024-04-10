package org.jopas;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Dimension;


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
public class Plot extends JFrame {
    private BorderLayout borderLayout1 = new BorderLayout();
    private String varY=null;
    private String varX=null;
    private Plotter plotter = null;
    private JopasLabel jLabel1 = null;
    private String title =null;

    /**
     * It generates a new Frame with a Chart of variable "var"
     *
     * @param var String
     * @param title String
     * @param jopas Jopas
     */
    public Plot(String var,String title, Jopas jopas) {
        this.varY=var;
        this.plotter=plotter;
        this.title=title;
        this.setSize(600,500);
        this.setTitle(title);
        jLabel1 = new JopasLabel(jopas);//,500,600);
        System.out.println(jLabel1.getWidth()+ " x " +jLabel1.getHeight());
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        jLabel1.paintLabel(var,title);
    }

    /**
     * It generates a new Frame with a Chart of variables "varX" and "varY"
     *
     * @param varX String
     * @param varY String
     * @param title String
     * @param jopas Jopas
     */
    public Plot(String varX,String varY, String title, Jopas jopas) {
        this.varY=varY;
        this.varX=varX;
        this.plotter=plotter;
        this.title=title;
        this.setSize(600,500);
        this.setTitle(title);
        jLabel1 = new JopasLabel(jopas);//,500,600);
        System.out.println(jLabel1.getWidth()+ " x " +jLabel1.getHeight());
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        jLabel1.paintLabel(this.varX,this.varY,title);
    }


    /**
     * It draws the Frame
     *
     * @throws Exception
     */
    private void jbInit() throws Exception {
        getContentPane().setLayout(borderLayout1);
        this.getContentPane().add(jLabel1, java.awt.BorderLayout.CENTER);
        this.setVisible(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension frameSize = this.getSize();
      if (frameSize.height > screenSize.height) {
        frameSize.height = screenSize.height;
      }
      if (frameSize.width > screenSize.width) {
        frameSize.width = screenSize.width;
      }
      this.setLocation( (screenSize.width - frameSize.width) / 2,
                       (screenSize.height - frameSize.height) / 2);


    }
}
