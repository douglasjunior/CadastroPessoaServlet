package com.github.douglasjunior.androidservletclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Douglas on 30/03/2015.
 */
public class PessoaAdapter extends AbstractArrayAdapter<Pessoa> {


    public PessoaAdapter(Context context, int resourceId, List<Pessoa> itens) {
        super(context, resourceId, itens);
    }

    @Override
    protected void onCustomView(int position, View convertView, ViewGroup parent, Pessoa item) {
        TextView codigo = (TextView) convertView.findViewById(R.id.tvCodigo);
        TextView nome = (TextView) convertView.findViewById(R.id.tvNome);
        TextView time = (TextView) convertView.findViewById(R.id.tvTime);
        TextView idade = (TextView) convertView.findViewById(R.id.tvIdade);

        codigo.setText(item.getCodigo() + "");
        nome.setText(item.getNome());
        time.setText(item.getTime());
        idade.setText(item.getIdade() + "");
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getCodigo();
    }
}
