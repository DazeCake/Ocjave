package org.jopas;

import com.dazecake.ocjave.channel.WebSocketServer;
import lombok.extern.slf4j.Slf4j;

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
 * @author Oscar Lage Serrano - Javier Vicente Sez
 * @version 1.01
 */
@Slf4j
class Parser extends java.lang.Thread {
    private java.io.BufferedReader br;
    private String line;
    private Jopas jopas;

    Parser(java.io.BufferedReader is, Jopas jopas) {
        br = is;
        this.jopas = jopas;
    }

    /**
     * It parser the incomming code of Octave
     * <p>
     * in ocjave, we olny use parser as output printer
     *
     * @todo parse the Infinite "Inf" and "NaN" numbers
     * @ignore by DazeCake
     */
//    public void run() {
//        int len;
//        byte buf[] = new byte[128];
//        try {
//            jopas.LoadVersion();
//            /**
//             for (int i = 0; i <= 11; i++) {
//             line = br.readLine();
//             //System.out.println(line);
//             }
//             }**/
//            line = br.readLine();
//            while (!line.contains("JOPASVERSION")) {
//                line = br.readLine();
//            }
//            line = br.readLine();
//            String var = null;
//            int rows = 0;
//            int columns = 0;
//            while (true){//line != null) {
//                if (line==null){
//                    System.exit(0);
//                }
//                line = br.readLine();
//                line = line.substring(8);
//                //System.out.println("**************************");
//                //System.out.println("Var:" + line);
//                var = line;
//                line = br.readLine();
//                if (line.contains("complex")) {
//
//                    if (line.contains("scalar")) {
//                        //System.out.println("Complex scalar type");
//                        line = br.readLine();
//                        //System.out.println("valor:" + line);
//                        int divisor = line.indexOf(",");
//                        String real = line.substring(1, divisor);
//                        String img = line.substring(divisor + 1,
//                                line.length() - 1);
//                        double[][] realArray = { {Integer.parseInt(real)}
//                        };
//                        double[][] imgArray = { {Integer.parseInt(img)}
//                        };
//                        JopasQueue.getJopasQueue().push(new Matrix(realArray,
//                                imgArray, var));
//                    } else if (line.contains("matrix")){
//                        //System.out.println("Complex matrix type");
//                        line = br.readLine();
//                        line = line.substring(8);
//                        rows = Integer.parseInt(line);
//                        //System.out.println("Rows:" + rows);
//
//                        line = br.readLine();
//                        line = line.substring(11);
//                        columns = Integer.parseInt(line);
//                        //System.out.println("Columns:" + columns);
//                        double[][] realArray = new double[rows][columns];
//                        double[][] imgArray = new double[rows][columns];
//                        for (int i = 0; i < rows; i++) { //lee de fila en fila
//                            //por cada fila
//                            line = br.readLine();
//                            //System.out.println(line);
//                            StringTokenizer st = new StringTokenizer(line, " ");
//
//                            for (int c = 0; c < columns; c++) {
//
//                                line = st.nextToken();
//                                int divisor = line.indexOf(",");
//                                String real = line.substring(1, divisor);
//                                String img = line.substring(divisor + 1,
//                                        line.length() - 1);
//
//                                realArray[i][c] = Double.valueOf(Double.
//                                        parseDouble(real));
//                                imgArray[i][c] = Double.valueOf(Double.
//                                        parseDouble(img));
//                            }
//
//                        }
//                        JopasQueue.getJopasQueue().push(new Matrix(realArray,
//                                var));
//
//                    }
//
//                } else {
//                    if (line.contains("scalar")) {
//                        //System.out.println("Scalar type");
//                        line = br.readLine();
//                        //System.out.println("valor:" + line);
//                        double[][] v1 = { {Double.parseDouble(line)}
//                        };
//                        JopasQueue.getJopasQueue().push(new Matrix(v1, var));
//                    } else if (line.contains("matrix")){
//                        //System.out.println("Matrix type");
//                        line = br.readLine();
//                        line = line.substring(8);
//                        rows = Integer.parseInt(line);
//                        //System.out.println("Rows:" + rows);
//
//                        line = br.readLine();
//                        line = line.substring(11);
//                        columns = Integer.parseInt(line);
//                        //System.out.println("Columns:" + columns);
//                        double[][] v1 = new double[rows][columns];
//                        for (int i = 0; i < rows; i++) { //lee de fila en fila
//                            //por cada fila
//                            line = br.readLine();
//                            //System.out.println(line);
//                            StringTokenizer st = new StringTokenizer(line, " ");
//
//                            for (int c = 0; c < columns; c++) {
//                                v1[i][c] = Double.valueOf(Double.parseDouble(st.
//                                        nextToken()));
//                            }
//
//                        }
//                        JopasQueue.getJopasQueue().push(new Matrix(v1, var));
//
//                    }
//
//                }
//                //System.out.println();
//                //System.out.println();
//                line = br.readLine();
//
//            }
//            //System.out.println("Parser finalizado...");
//
//        } catch (java.io.IOException ex) {
//            ex.printStackTrace();
//        }
//    }
    public void run() {
        try {
            while ((line = br.readLine()) != null) {
                log.info(line);
                WebSocketServer.sendInfo(line, "1");
            }
        } catch (java.io.IOException ex) {
            ex.printStackTrace();
        }
    }


}

