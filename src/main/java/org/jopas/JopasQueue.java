package org.jopas;

import java.util.LinkedList;

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
 * @author Oscar Lage Serrano - Javier Vicente Sez
 * @version 1.01
 */

public class JopasQueue {
  private LinkedList queue = null;
  static private JopasQueue jopasQueue=null;

  /**
   * Constructor that start the queue
   */
  JopasQueue(){
      queue=new LinkedList();
  }
  /**
   * It Puts a Matrix paquet at the end of FIFO Queue
  */
  public synchronized void push(Matrix packet){
    //Log.trace("[PQ] " + packet);
    queue.add(packet);
    notifyAll();
  }

 /**
  * It picks up a paquet of the FIFO Queue
  *
  * @return Matrix
  */
 public synchronized Matrix pull(){
    try {
      while (queue.isEmpty()) {
        wait();
      }
    } catch (InterruptedException e){
      return null;
    }
    return (Matrix)queue.remove(0);
  }

  /**
   * It Gets the reference of the unique instance of the class
   *
   * @return JopasQueue
   */
  static JopasQueue getJopasQueue(){
        if (jopasQueue==null){
            jopasQueue=new JopasQueue();
        }
        return jopasQueue;
    }

}
