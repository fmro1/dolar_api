package com.example.felip.app_dolar_api;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TelaPrincipal extends AppCompatActivity {

    EditText edt_dolar;
    TextView tv_real;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        edt_dolar = (EditText)findViewById(R.id.edt_dolar);
        tv_real = (TextView)findViewById(R.id.tv_real);

    }

    public void converter_clique(View v){

        //prepara o retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //obtenho a api
        Api api = retrofit.create(Api.class);

        Call<Moeda> call = api.converter();

        call.enqueue(new Callback<Moeda>() {
            @Override
            public void onResponse(Call<Moeda> call, Response<Moeda> response) {
               //guardo o retorno
                Moeda retorno = response.body();

                //guardo o valor retornado de 1 dolar para real
                double valor_dolar = retorno.getUSD_BRL();

                //guardo o valor digitado de dolares a converter
                double valor_digitado = Double.parseDouble(edt_dolar.getText().toString());

                //fazer a conversao
                double resultado = valor_digitado * valor_dolar;

                //colocar mascara para os decimais
                DecimalFormat decimal = new DecimalFormat("#,##0.00");

                //NumberFormat formatter = new DecimalFormat("$#.###.###,##");
                //txtValor.setText(formatter.format(txtValor.getText()));

                //mostrar os dados na tela
                tv_real.setText("R$ "+decimal.format(resultado));
            }

            @Override
            public void onFailure(Call<Moeda> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
