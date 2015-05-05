package com.github.douglasjunior.androidservletclient;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Classe responsável por efetuar requisições HTTP a um servidor Java EE.
 * Nesta implementação já possui a funcionalidade de armazenamento do Cookie de sessão.
 * <p/>
 * Created by Douglas on 05/05/2015.
 */
public class JHttpClient extends AsyncTask<Void, Void, String> {

    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String DELETE = "DELETE";

    public static final String SERVER = "http://192.168.10.105:8084/CadastroPessoa";

    public static String JSESSIONID = "";
    private final Context context;
    private final OnJHttpResultListener onJHttpResultListener;
    private final String methodName;
    private final Map<String, String> params;
    private final String url;
    private HttpResponse response;
    private String conteudo = "";

    private ProgressDialog dialog;

    public JHttpClient(Context context, String url, String methodName, Map<String, String> params, OnJHttpResultListener onJHttpResultListener) {
        this.context = context;
        this.url = SERVER + url;
        this.methodName = methodName;
        this.params = params != null ? params : new HashMap<String, String>();
        this.onJHttpResultListener = onJHttpResultListener;
    }

    @Override
    protected void onPreExecute() {
        response = null;
        dialog = new ProgressDialog(context);
        dialog.setMessage("Processando...");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        // cria um parâmetro para informar ao Servidor que é uma requisição mobile
        params.put("mobile", "true");

        // cria o cliente HTTP
        HttpClient client = new DefaultHttpClient();

        System.out.println(url);

        try {
            HttpRequestBase request = criarHttpRequest(url, params);

            // envia de volta o Cookie da sessão para o servidor
            if (JSESSIONID != null && !JSESSIONID.isEmpty())
                request.setHeader("Cookie", JSESSIONID);

            // envia a requisição e recebe a resposta do servidor
            response = client.execute(request);

            // recupera o JSESSIONID para manter a seção
            for (Header h : response.getHeaders("Set-Cookie")) {
                System.out.println(h.getName() + " => " + h.getValue());
                String jsessionid = recuperaJSessionId(h);
                if (jsessionid != null && !jsessionid.isEmpty())
                    JSESSIONID = jsessionid;
            }

            // faz a leitura do corpo da requisição e armazena na String conteudo
            HttpEntity entity = response.getEntity();
            BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
            String linha = null;
            while ((linha = reader.readLine()) != null) {
                conteudo += linha + "\n";
            }
            reader.close();
            entity.consumeContent();

            // verifica se a requisição retornar código 200 OK
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                return response.getStatusLine().getReasonPhrase() + " (" + response.getStatusLine().getStatusCode() + ")";
            }
        } catch (final IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return null;
    }

    /**
     * Cria o HttpRequest de acordo como o método HTTP escolhido
     *
     * @param url
     * @param params
     * @return
     * @throws UnsupportedEncodingException
     */
    private HttpRequestBase criarHttpRequest(String url, Map<String, String> params) throws UnsupportedEncodingException {
        HttpRequestBase request;
        // verifica qual o método HTTP selecionado
        switch (methodName) {
            case POST:
                HttpPost post = new HttpPost(url);
                post.setEntity(convertParamToEntity(params));
                request = post;
                break;
            case PUT:
                HttpPut put = new HttpPut(url);
                put.setEntity(convertParamToEntity(params));
                request = put;
                break;
            case DELETE:
                HttpDelete delete = new HttpDelete(convertParamToUrl(url, params));
                request = delete;
                break;
            case GET:
            default:
                HttpGet get = new HttpGet(convertParamToUrl(url, params));
                request = get;
                break;
        }
        return request;
    }

    /**
     * Monta a URL para GET ou DELETE com os parâmetros neste formato:<br />
     * http://exemplo.com?parametro=valor&outro=valor
     *
     * @param url
     * @param params
     * @return
     */
    private String convertParamToUrl(String url, Map<String, String> params) {
        url += "?";
        for (String name : params.keySet()) {
            String value = params.get(name);
            url += name + "=" + value + "&";
        }
        return url;
    }

    /**
     * Adiciona os parâmetros ao corpo da requisição POST ou PUT
     *
     * @param params
     * @return
     * @throws UnsupportedEncodingException
     */
    private HttpEntity convertParamToEntity(Map<String, String> params) throws UnsupportedEncodingException {
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        for (String name : params.keySet()) {
            String value = params.get(name);
            nameValuePairs.add(new BasicNameValuePair(name, value));
        }
        return new UrlEncodedFormEntity(nameValuePairs);
    }

    @Override
    protected void onPostExecute(String erro) {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
        if (erro != null) {
            if (onJHttpResultListener != null)
                onJHttpResultListener.onError(response, erro);
        } else {
            if (onJHttpResultListener != null)
                onJHttpResultListener.onSucess(response, conteudo);
        }
    }

    /**
     * Método responsável por ler o cabeçalho de Cookies e recuperar o ID da sessão do Servidor Java EE (Tomcat). <br />
     * Lembrando que a sessão é controlada no servidor através de um Cookie que é armazanado no cliente.
     * O Browser faz isso automaticamente, então temos que fazer também no Android.
     * Pois sem a sessão é impossível manter o usuário logado.
     *
     * @param h
     * @return
     */
    private String recuperaJSessionId(Header h) {
        // exemplo: JSESSIONID=809BFBF2164D99F9E6FD60B5492E4F2D; Path=/CadastroPessoa/; HttpOnly
        try {

            int startIndex = h.getValue().indexOf("JSESSIONID="); // pega a posição inicial do JSESSIONID=
            int endIndex = h.getValue().indexOf(';', startIndex); // pega a posição final
            System.out.println(startIndex + " ... " + endIndex);
            String jsessionid = h.getValue().substring(startIndex, endIndex); // recorda só o pedaço que nos interessa
            if (jsessionid != null && !jsessionid.isEmpty()) {
                System.out.println(jsessionid);
                return jsessionid;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * Listener utilizado para devolver a resposta da requisição para a Activity.
     */
    public interface OnJHttpResultListener {
        public void onSucess(HttpResponse response, String conteudo);

        public void onError(HttpResponse response, String mensagem);
    }
}
