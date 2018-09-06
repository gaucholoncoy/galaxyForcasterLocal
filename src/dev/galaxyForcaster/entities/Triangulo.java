/**
 * 
 */
package dev.galaxyForcaster.entities;

import java.awt.geom.Point2D;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase utilitaria para poder abstraer el calculo de orientacion y perimetro de
 * la clase pronostico
 * 
 * @author richard
 *
 */
public class Triangulo {

	final static Logger log = LoggerFactory.getLogger(Triangulo.class);

	/**
	 * 
	 */

	private Point2D puntoA;
	private Point2D puntoB;
	private Point2D puntoC;
	private Point2D puntoSol;

	public Triangulo(PosicionOrbital pA, PosicionOrbital pB, PosicionOrbital pC, Point2D C) {
		// TODO Auto-generated constructor stub
		puntoA = new Point2D.Double(pA.getX(), pA.getY());
		puntoB = new Point2D.Double(pB.getX(), pB.getY());
		puntoC = new Point2D.Double(pC.getX(), pC.getY());
		puntoSol = C;

	}

	public Triangulo(Point2D pA, Point2D pB, Point2D pC, Point2D sol) {
		// TODO Auto-generated constructor stub
		this.puntoA = pA;
		this.puntoB = pB;
		this.puntoC = pC;
		this.puntoSol = sol;

	}

	public double getPerimetro() {
		return (puntoA.distance(puntoB) + puntoB.distance(puntoC) + puntoC.distance(puntoA));
	}

	/**
	 * para el calculo de orientacion encontre en esta url el codigo para poder
	 * calcularlo
	 * 
	 */
	// http://funes.uniandes.edu.co/8137/1/pag3.html

	// código Java
//float areaT(Point2D A1,Point2D A2,Point2D A3)
//   {//área del triángulo  A1A2A3, es > 0 sii esta orientado positivamente,else < 0
//    return (A1.x - A3.x)*(A2.y - A3.y)-(A1.y - A3.y)*(A2.x - A3.x);
//
//   }//
//
//boolean estaEnTri(Point2D A1,Point2D A2,Point2D A3, Point2D P)
//   {   // Decide si un punto P está dentro del triángulo orientado A1A2A3
//
//       if(areaT(A1,A2,A3)>=0)
//              return areaT(A1, A2, P) >= 0 &&
//                     areaT(A2, A3, P) >= 0 &&
//                     areaT(A3, A1, P) >= 0;
//       else   return areaT(A1, A2, P) <= 0 &&
//                     areaT(A2, A3, P) <= 0 &&
//                     areaT(A3, A1, P) <= 0;
//
//   }//

	private double getArea(Point2D puntoA, Point2D puntoB, Point2D puntoC) {
		return ((puntoA.getX() - puntoC.getX()) * (puntoB.getY() - puntoC.getY())
				- (puntoA.getY() - puntoC.getX()) * (puntoB.getX() - puntoC.getX()));
		// return (A1.x - A3.x)*(A2.y - A3.y)-(A1.y - A3.y)*(A2.x - A3.x);

	}

	public boolean isPuntoInTriangulo() {

		if (getArea(puntoA, puntoB, puntoC) >= 0)
			return getArea(puntoA, puntoB, puntoSol) >= 0 && (getArea(puntoB, puntoC, puntoSol) >= 0)
					&& (getArea(puntoC, puntoA, puntoSol) >= 0);
		else
			return getArea(puntoA, puntoB, puntoSol) <= 0 && getArea(puntoB, puntoC, puntoSol) <= 0
					&& getArea(puntoC, puntoA, puntoSol) <= 0;
		// return (A1.x - A3.x)*(A2.y - A3.y)-(A1.y - A3.y)*(A2.x - A3.x);

	}

}
