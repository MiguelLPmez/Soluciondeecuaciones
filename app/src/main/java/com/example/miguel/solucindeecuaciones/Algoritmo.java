package com.example.miguel.solucindeecuaciones;

public class Algoritmo {
    private final Ecuación ec;
    private double solución, errorF;

    public Algoritmo(Ecuación ec){
        this.ec = ec;
    }

    // - Retorna el error final de la última llamada al método que da la solución por un determinado
    //   algoritmo.
    public double getErrorFinal(){
        return errorF;
    }

    // - Aplica el algoritmo de Newton-Raphson y regresa la solución.
    public double getSoluciónNewton(double p0, double tol, int itmax){
        algoritmoNewton(p0, tol, itmax);
        return solución;
    }

    // - Aplica el algoritmo de la secante y regresa la solución.
    public double getSoluciónSecante(double p0, double p1, double tol, int itmax){
        algoritmoSecante(p0, p1, tol, itmax);
        return solución;
    }

    // - Aplica el algoritmo de Bisección y regresa la solución.
    public double getSoluciónBisección(double xi,double xu, double tol, int itmax){
        algoritmoBisección(xi, xu, tol,  itmax);
        return solución;
    }


    /*              - Implementación de los algorimos -             */

    private void algoritmoNewton(double p0, double tol, int itmax){
        for (int i = 0; i < itmax; i++) {
            solución = p0 - ec.evaluarEn(p0) / ec.derivadaEn(p0);
            errorF = Math.abs(solución - p0);
            if (errorF <= tol) break;
            p0 = solución;
        }

    }

    private void algoritmoSecante(double p0, double p1, double tol, int itmax){
        for (int i = 0; i < itmax; i++) {
            solución = p1 - (ec.evaluarEn(p1) * (p1 - p0)) / (ec.evaluarEn(p1) - ec.evaluarEn(p0));
            errorF = Math.abs(solución - p1);
            if (errorF <= tol) break;
            p0 = p1;
            p1 = solución;
        }
    }

    private void algoritmoBisección(double xi, double xu, double tol, int itmax){
        double iu;
        errorF =  ((xi + xu) / 2) - xu;
        for (int i = 0; i < itmax; i++){
            solución = (xi + xu) / 2;
            iu = ec.evaluarEn(xi) * ec.evaluarEn(solución);

            if (ec.evaluarEn(iu) == 0) break;
            else if (errorF <= tol) break;
                else{
                    if (iu < 0) {
                        xu = solución;
                        errorF = solución - xi;
                    }
                    if (iu > 0) {
                        xi = solución;
                        errorF = solución - xu;
                    }
                }
        }
    }

    /*          De versión anterior de la clase:

    // - Crear aqui los metodos bisección, newton, secante y regla falsa.

    public double secante(double p0, double p1, double tol, int itmax){
        int i = 0;
        double fp0 = ec.evaluarEn(p0);
        double fp1 = ec.evaluarEn(p1);
        double fp, p;

        while (i <= itmax){
            p = p1 - fp1 * (p1 - p0) / (fp1 - fp0);
            fp = ec.evaluarEn(p);
            if (Math.abs(fp - p) < tol)
                return p;
            i++;
            p0 = p1;
            p1 = p;
        }

        //if (Math.abs(fp) > tol){
        //    return -1;
        //}

        return -1;
    }

    */
}
