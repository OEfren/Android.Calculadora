package mx.com.connecta.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public double numero;
    public String operador;
    public boolean borrar;

    TextView lblOperador;
    EditText txtNumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lblOperador = findViewById(R.id.lblOperador);
        txtNumero = findViewById(R.id.txtNumero);

        findViewById(R.id.button1).setOnClickListener(this::onClick);
        findViewById(R.id.button2).setOnClickListener(this::onClick);
        findViewById(R.id.button3).setOnClickListener(this::onClick);
        findViewById(R.id.button4).setOnClickListener(this::onClick);
        findViewById(R.id.button5).setOnClickListener(this::onClick);
        findViewById(R.id.button6).setOnClickListener(this::onClick);
        findViewById(R.id.button7).setOnClickListener(this::onClick);
        findViewById(R.id.button8).setOnClickListener(this::onClick);
        findViewById(R.id.button9).setOnClickListener(this::onClick);
        findViewById(R.id.button0).setOnClickListener(this::onClick);
        findViewById(R.id.buttonPunto).setOnClickListener(this::onClick);

        findViewById(R.id.buttonDivide).setOnClickListener(this::onClickOperador);
        findViewById(R.id.buttonMultiplica).setOnClickListener(this::onClickOperador);
        findViewById(R.id.buttonResta).setOnClickListener(this::onClickOperador);
        findViewById(R.id.buttonSuma).setOnClickListener(this::onClickOperador);
        findViewById(R.id.buttonBorrar).setOnClickListener(view -> {
            lblOperador.setText("");
            txtNumero.setText("");
            numero = 0;
        });

        findViewById(R.id.buttonIgual).setOnClickListener(view -> {
            String operadorTexT = lblOperador.getText().toString();
            if (!operadorTexT.equals("")) {
                lblOperador.setText("");
                borrar = true;
                String numeroText = txtNumero.getText().toString();
                double numeroDos =  Double.parseDouble(numeroText);
                switch (operadorTexT) {
                    case "+":
                        changeValue(String.valueOf(numero + numeroDos));
                        break;
                    case "-":
                        changeValue(String.valueOf(numero - numeroDos));
                        break;
                    case "*":
                        changeValue(String.valueOf(numero * numeroDos));
                        break;
                    case "/":
                        changeValue(String.valueOf(numero / numeroDos));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void onClick(View view) {
        Button button = (Button)view;
        String text = button.getText().toString();
        changeValue(text);
    }

    public void onClickOperador(View view) {
        Button button = (Button)view;
        operador = button.getText().toString();
        lblOperador.setText(operador);
    }

    private void changeValue(String number) {
        if (operador == null || operador.equals("")) {
            if (borrar) {
                borrar = false;
                txtNumero.setText("");
            }
            String box = txtNumero.getText().toString();
            if (number.equals(".") && box.contains(".")) {
                return;
            }
            String newValue = box + number;
            txtNumero.setText(newValue);
        }
        else {
            operador = "";
            borrar = true;
            String numeroText = txtNumero.getText().toString();
            if (numeroText.equals("")) {
                numeroText = "0.0";
            }
            numero = Double.parseDouble( numeroText );
            changeValue(number);
        }
    }
}