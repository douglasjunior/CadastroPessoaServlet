package com.github.douglasjunior.androidservletclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PesquisaPessoaActivity extends Activity implements View.OnClickListener, JHttpClient.OnJHttpResultListener {

    private EditText editText;
    private ImageButton imageButton;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pesquisa_pessoa);

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
        Map<String, String> params = new HashMap<>();
        params.put("nome", editText.getText().toString());
        new JHttpClient(this, "/PesquisaPessoaServlet", JHttpClient.GET, params, this).execute();
    }

    @Override
    public void onSucess(HttpResponse response, String content) {
        // faz a leitura da resposta devolvida pelo servidor
        try {
            List<Pessoa> pessoas = new ArrayList<>();
            for (String linha : content.split("\n")) {
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
            final PessoaAdapter adapter = new PessoaAdapter(this, R.layout.pessoa_item, pessoas);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onError(HttpResponse response, String mensagem) {
        Toast.makeText(this, "Não foi possível executar a pesquisa.\n\n" + mensagem, Toast.LENGTH_LONG).show();
    }

    private int stringToInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception ex) {
            return 0;
        }
    }
}
