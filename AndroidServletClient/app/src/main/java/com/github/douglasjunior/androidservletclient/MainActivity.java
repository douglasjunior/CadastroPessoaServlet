package com.github.douglasjunior.androidservletclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements View.OnClickListener {

    private EditText editText;
    private ImageButton imageButton;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);

        imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(this);

        listView = (ListView) findViewById(R.id.listView);
    }

    @Override
    public void onClick(View view) {
        if (imageButton == view) {
            pesquisarPessoas();
        }
    }

    private void pesquisarPessoas() {
        String nome = editText.getText().toString();
        if (nome != null && !nome.isEmpty()) {
            // cria o cliente HTTP
            HttpClient client = new DefaultHttpClient();

            // cria a requisição
            String url = "http://10.2.1.30:8084/CadastroPessoa/PesquisaPessoaServlet?mobile=true&nome=" + nome;
            HttpGet request = new HttpGet(url);

            try {
                // envia a requisição e recebe a resposta do servidor
                HttpResponse response = client.execute(request);

                // verifica se a requisição retornar código 200 OK
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    // faz a leitura da resposta devolvida pelo servidor
                    HttpEntity entity = response.getEntity();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
                    String linha = null;
                    List<Pessoa> pessoas = new ArrayList<Pessoa>();
                    while ((linha = reader.readLine()) != null) {
                        System.out.println(linha);
                        String partes[] = linha.split("\\|");
                        if (partes.length == 4) {
                            Pessoa pessoa = new Pessoa();
                            pessoa.setCodigo(stringToInt(partes[0]));
                            pessoa.setNome(partes[1]);
                            pessoa.setIdade(stringToInt(partes[2]));
                            pessoa.setTime(partes[3]);
                            pessoas.add(pessoa);
                        }
                    }
                    PessoaAdapter adapter = new PessoaAdapter(this, R.layout.pessoa_item, pessoas);
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    reader.close();
                    entity.consumeContent();
                }
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private int stringToInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception ex) {
            return 0;
        }
    }
}
