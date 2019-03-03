package com.example.miguel.solucindeecuaciones;

public class Algoritmo {
    Ecuación ec;

    public Algoritmo(int a, int b, int c, int d, int e){
        ec = new Ecuación(a, b, c, d, e);
    }

    // - Crear aqui los metodos bisección, newton, secante y regla falsa.

    public double secante(double p0, double p1, double tol, int itmax){
        int i = 0;
        double fp0 = ec.evaluarEn(p0);
        double fp1 = ec.evaluarEn(p1);
        double p, fp;

        while (i < itmax){
            p = p1 - fp1 * (p1 - p0) / (fp1 - fp0);
            fp = ec.evaluarEn(p);
            if (Math.abs(fp) < tol){
                return p;
            }
            i++;
            p0 = p1;
            p1 = p;
        }

        /*if (Math.abs(fp) > tol){
            return -1;
        }*/
        return -1;
    }
}
