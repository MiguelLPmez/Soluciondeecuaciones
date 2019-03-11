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
    EditText tol, p0, p1, itMax,aproxET;
    TextView resultado, error, intervaloTV,aproxTV;
    RadioGroup rGroup;
    Button calcular;
    Algoritmo algoritmo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
        itMax = findViewById(R.id.itmax);

        intervaloTV = findViewById(R.id.entrada_desc);
        aproxTV = findViewById(R.id.aproxInicial_desc);
        aproxET = findViewById(R.id.aproximacion_ET);

        resultado = findViewById(R.id.resultadoFinal);
        error = findViewById(R.id.errorFinal);

        calcular = findViewById(R.id.calcular_button);
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
            resultado = algoritmo.getSoluciónReglaFalsa(p0,p1,tol,itMax);
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
        int itMax = Integer.parseInt(this.itMax.getText().toString());

        double res = doFunction(p0,p1,tol,itMax);
        double err = algoritmo.getErrorFinal();

        resultado.setText("Resultado: "+res);
        error.setText("Error: "+err);

        calcular.setEnabled(false);
    }

    public void rGroupPerformed(View v){
        int id = rGroup.getCheckedRadioButtonId();
        if(id == biseccion.getId() ||  id == secante.getId() || id == reglaFalsa.getId()){
            aproxTV.setVisibility(TextView.INVISIBLE);
            aproxET.setVisibility(EditText.INVISIBLE);

            //Para arreglar de selecciones anteriores
            intervaloTV.setVisibility(TextView.VISIBLE);
            p0.setVisibility(EditText.VISIBLE);
            p1.setVisibility(EditText.VISIBLE);
        }else if(id == newton.getId()){
            intervaloTV.setVisibility(TextView.INVISIBLE);
            p0.setVisibility(EditText.INVISIBLE);
            p1.setVisibility(EditText.INVISIBLE);

            //Para arreglar de selecciones anteriores
            aproxTV.setVisibility(TextView.VISIBLE);
            aproxET.setVisibility(EditText.VISIBLE);
        }
        dataVerification();
    }

    private void dataVerification(){
        //Condiciones necesarias en todos los casos
        if(A.getText().length() == 0 || B.getText().length() == 0 || C.getText().length() == 0 ||
                D.getText().length() == 0 || E.getText().length() == 0 ||
                tol.getText().length() == 0 || p0.getText().length() == 0 || itMax.getText().length() == 0){
            calcular.setEnabled(false);
            rGroup.clearCheck();
        }else{
            //Condicion para Newton
            if(aproxET.getVisibility() != EditText.INVISIBLE && aproxET.getText().length() != 0)
                calcular.setEnabled(true);
            //Condicion para Biseccion, Secante, Regla falsa
            else if(p0.getVisibility() != EditText.INVISIBLE &&
                    p0.getText().length() != 0 && p1.getText().length() != 0)
                calcular.setEnabled(true);
            else {
                calcular.setEnabled(false);
                rGroup.clearCheck();
            }
        }
    }
}
