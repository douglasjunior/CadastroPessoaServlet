package com.github.douglasjunior.androidservletclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Douglas on 04/05/2015.
 */
public class LoginActivity extends Activity implements View.OnClickListener, JHttpClient.OnJHttpResultListener {


    private EditText edUsuario;
    private EditText edSenha;
    private Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        edUsuario = (EditText) findViewById(R.id.edUsuario);
        edSenha = (EditText) findViewById(R.id.edSenha);

        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnEntrar) {
            efetuarLogin();
        }
    }

    private void efetuarLogin() {
        Map<String, String> params = new HashMap<>();
        params.put("usuario", edUsuario.getText().toString());
        params.put("senha", edSenha.getText().toString());
        new JHttpClient(this, "/LoginServlet", JHttpClient.POST, params, this).execute();
    }

    @Override
    public void onSucess(HttpResponse response, String conteudo) {
        /*
        Se o Login for efetuado com sucesso, então abre a Activity de pesquisa
         */
        startActivity(new Intent(this, PesquisaPessoaActivity.class));
        finish();
    }

    @Override
    public void onError(HttpResponse response, String mensagem) {
        Toast.makeText(this, "Não foi possível efetuar o Login.\n\n" + mensagem, Toast.LENGTH_LONG).show();
    }
}
