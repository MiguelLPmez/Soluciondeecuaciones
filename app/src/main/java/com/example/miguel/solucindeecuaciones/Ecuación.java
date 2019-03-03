package com.example.miguel.solucindeecuaciones;

public class Ecuación {
    private int a, b, c, d, e;

    public Ecuación(int a, int b, int c, int d, int e){
        this.setA(a);
        this.setB(b);
        this.setC(c);
        this.setD(d);
        this.setE(e);
    }

    // - Regresa el valor de la función en el punto dado.
    public double evaluarEn(double x){
        return a * Math.pow(x, 4) + b * Math.pow(x, 3) + c * Math.pow(x, 2) + d * x  + e;
    }

    // - Regresa el valor de la derivada de la función en el punto dado.
    public double derivadaEn(double x){
        return 4 * a * Math.pow(x, 3) + 3 * b * Math.pow(x, 2) +  2 * c * x + d;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getE() {
        return e;
    }

    public void setE(int e) {
        this.e = e;
    }
}
