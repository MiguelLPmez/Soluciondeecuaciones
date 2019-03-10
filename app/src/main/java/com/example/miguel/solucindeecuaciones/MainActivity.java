package com.example.miguel.solucindeecuaciones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText A, B, C, D, E;
    RadioButton biseccion, newton, secante, reglaFalsa;
    EditText tol, p0, p1, aproximacion;
    TextView resultado, error;
    RadioGroup rGroup;
    Button calcular;
    Algoritmo algoritmo;

    public MainActivity(){
        A = findViewById(R.id.A);
        B = findViewById(R.id.B);
        C = findViewById(R.id.C);
        D = findViewById(R.id.D);
        E = findViewById(R.id.E);

        biseccion = findViewById(R.id.bisección_RButton);
        newton = findViewById(R.id.newton_RButton);
        secante = findViewById(R.id.secante_RB);
        reglaFalsa = findViewById(R.id.regla_RB);
        rGroup = findViewById(R.id.métodoSeleccionado);

        tol = findViewById(R.id.tolerancia_EditText);
        p0 = findViewById(R.id.intervalo_a);
        p1 = findViewById(R.id.intervalo_b);
        aproximacion = findViewById(R.id.aproximacion_ET);

        resultado = findViewById(R.id.resultadoFinal);
        error = findViewById(R.id.errorFinal);

        calcular = findViewById(R.id.calcular_button);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new MainActivity();
        calcular.setEnabled(false);
    }

    private double doFunction(double p0, double p1, double tol, int itMax){
        double resultado;
        if(rGroup.getCheckedRadioButtonId() == biseccion.getId())
            resultado = algoritmo.getSoluciónBisección(p0,p1,tol,itMax);
        else if(rGroup.getCheckedRadioButtonId() == newton.getId())
            resultado = algoritmo.getSoluciónNewton(p0,tol,itMax);
        else if(rGroup.getCheckedRadioButtonId() == secante.getId())
            resultado = algoritmo.getSoluciónSecante(p0,p1,tol,itMax);
        else if(rGroup.getCheckedRadioButtonId() == reglaFalsa.getId())
            resultado = algoritmo.getSoluciónBisección(p0,p1,tol,itMax);
        else return 0;

        return resultado;
    }

    public void calculatePerformed(View v){
        int a = Integer.parseInt(A.getText().toString());
        int b = Integer.parseInt(B.getText().toString());
        int c = Integer.parseInt(C.getText().toString());
        int d = Integer.parseInt(D.getText().toString());
        int e = Integer.parseInt(E.getText().toString());

        algoritmo = new Algoritmo(new Ecuación(a, b, c, d, e));

        double p0 = Double.parseDouble(this.p0.getText().toString());
        double p1 = Double.parseDouble(this.p1.getText().toString());
        double tol = Double.parseDouble(this.tol.getText().toString());

        double res = doFunction(p0,p1,tol,4);
        double err = algoritmo.getErrorFinal();

        resultado.setText("Resultado: "+res);
        error.setText("Error: "+error);

        calcular.setEnabled(false);
    }



    public void rGroupPerformed(View v){
        dataVerification();
    }

    private void dataVerification(){
        if(A.getText().length() == 0 || B.getText().length() == 0 || C.getText().length() == 0 ||
                D.getText().length() == 0 || E.getText().length() == 0 ||
                tol.getText().length() == 0 || p0.getText().length() == 0 || p1.getText().length() == 0){
            calcular.setEnabled(false);
            rGroup.clearCheck();
        }else{
            calcular.setEnabled(true);

        }
    }
}
