package org.jopas;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Arrays;


/**
 * <p>Title: JOPAS</p>
 *
 * <p>Description: Java to Octave</p>
 *
 * <p>Copyright: (C) Copyright 2000-2005, by Object Refinery Limited and
 * Contributors.
 * <p>
 * Project Info:  	http://jopas.sourceforge.net
 * http://www.eside.deusto.es/grupos/eside_pas
 * <p>
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 * <p>
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 * <p>
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation,
 * Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA.
 * <p>
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc.
 * in the United States and other countries.]
 * </p>
 *
 * <p>Company: PAS - Universidad de Deusto</p>
 *
 * @author Oscar Lage Serrano - Javier Vicente S�ez
 * @version 1.01
 */
@Slf4j
public class Jopas {
    private PrintWriter pw = null;
    private Parser parser = null;
    private Plotter plotter = null;

    /**
     * The constructor of the class, it connect and run Octave.
     */
    public Jopas(String octavePath) {

        Process p = null;
        Runtime r = Runtime.getRuntime();
        try {
            log.info("Starting Octave with path: {}", octavePath);
//            p = r.exec(octavePath + " --no-gui");
            p = r.exec(octavePath);

        } catch (java.lang.Exception e) {
            System.err.println("Octave's execution ERROR:");
            e.printStackTrace();
        }

        BufferedReader is = new BufferedReader(new InputStreamReader(p.
                getInputStream()));

        try {
            pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(p.
                    getOutputStream())));
        } catch (Exception e) {
            System.out.println("Error writing to output stream " + e);
            e.printStackTrace();
        }

        parser = new Parser(is, this);
        parser.start();

    }

    /**
     * Load the Matrix in Octave
     *
     * @param vector Matrix
     */
    public synchronized void Load(Matrix vector) {
        pw.println(vector + ";");
        pw.flush();
        //System.out.println("Loading: " + vector);
    }

    /**
     * Load the variable "name" with de number "num"
     *
     * @param num  double
     * @param name String
     */
    public synchronized void Load(double num, String name) {
        Matrix matrix = new Matrix(num, name);
        this.Load(matrix);
    }

    /**
     * Return the value of the variable "var" like a Matrix
     *
     * @param var String
     * @return Matrix
     */
    public synchronized Matrix Save(String var) {
        pw.println("save -ascii - " + var + " ");
        pw.flush();
        //System.out.println("Saving: " + var);
        return JopasQueue.getJopasQueue().pull();
    }

    /**
     * Executes the code "code" in Octave
     *
     * @param code String
     */
    public synchronized void Execute(String code) {
        pw.println(code + ";");
        pw.flush();
        //System.out.println("Executing: " + code);
    }

    /**
     * Private function to initializate the parser
     */
    public synchronized void LoadVersion() {
        pw.println("JOPASVERSION = '1.01'");
        pw.flush();
    }

    public synchronized void stop() {
        pw.println("exit");
        pw.flush();
    }

    /**
     * Function to draw a new form that plots the variable "var"
     *
     * @param var String
     */
    public void plot(String var) {
        new Plot(var, "PLOTTER", this);
    }

}
