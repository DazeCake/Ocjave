package org.jopas;

import org.jfree.data.xy.XYDataset;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import java.awt.image.BufferedImage;
import org.jfree.data.xy.XYSeriesCollection;

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
public class Plotter {
    private Jopas jopas = null;
    /**
     * Constructor of the class
     *
     * @param jopas Jopas
     */
    public Plotter(Jopas jopas) {
        this.jopas = jopas;

    }

    /**
     * It returns a Chart of the variable "var" with the title "title" and "XTitle" and "YTitle"
     * of "width"x"height"
     *
     *
     * @param var String
     * @param title String
     * @param XTitle String
     * @param YTitle String
     * @param width int
     * @param height int
     * @return BufferedImage
     */
    public BufferedImage getImage(String var, String title, String XTitle, String YTitle, int width,
                                  int height) {

        org.jopas.Matrix matrix = jopas.Save(var);
        int rows = matrix.getRows();
        int columns = matrix.getColumns();
        XYSeriesCollection dataCollection = new XYSeriesCollection();
        XYSeries series = null;
        for (int r=1; r<=rows; r++){
            series = new XYSeries(r);
            for (int c = 0; c < columns; c++) {
                series.add(c, matrix.getRealAt(r-1, c));
            }
            dataCollection.addSeries(series);
        }

        XYDataset juegoDatos = new XYSeriesCollection(series);


        JFreeChart chart = org.jfree.chart.ChartFactory.createXYLineChart(title,
                XTitle, YTitle, dataCollection, PlotOrientation.VERTICAL,
                false, //leyenda abajo
                true, //Aceptar valores negativos
                true // Show legend
                           );



        if (width<200) width = 200;
        if (height<150) height = 150;

        BufferedImage image = chart.createBufferedImage(width, height);
        return image;
    }

    /**
     * It returns a Chart of the variable "varX" and "varY" with the title "title" and "XTitle" and "YTitle"
     * of "width"x"height"
     *
     *
     * @param varX String
     * @param varY String
     * @param title String
     * @param XTitle String
     * @param YTitle String
     * @param width int
     * @param height int
     * @return BufferedImage
     */
    public BufferedImage getImage(String varX, String varY, String title, String XTitle, String YTitle, int width,
                                  int height) {

        org.jopas.Matrix matrixY = jopas.Save(varY);
        org.jopas.Matrix matrixX = jopas.Save(varX);
        int rows = matrixY.getRows();
        int columns = matrixY.getColumns();

        XYSeriesCollection dataCollection = new XYSeriesCollection();
        XYSeries series = null;
        for (int r=1; r<=rows; r++){
            series = new XYSeries(r);
            for (int c = 0; c < columns; c++) {
                series.add(matrixX.getRealAt(r-1, c), matrixY.getRealAt(r-1, c));
            }
            dataCollection.addSeries(series);
        }

        JFreeChart chart = org.jfree.chart.ChartFactory.createXYLineChart(title,
                XTitle, YTitle, dataCollection, PlotOrientation.VERTICAL,
                false, //leyenda abajo
                true, //Aceptar valores negativos
                true // Show legend
                           );

        if (width<200) width = 200;
        if (height<150) height = 150;

        BufferedImage image = chart.createBufferedImage(width, height);
        return image;
    }





}
