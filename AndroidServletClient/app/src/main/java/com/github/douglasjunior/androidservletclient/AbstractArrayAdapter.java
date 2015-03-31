package com.github.douglasjunior.androidservletclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;


/**
 * Created by Douglas on 16/09/2014.
 */
public abstract class AbstractArrayAdapter<T> extends ArrayAdapter<T> {

    private final LayoutInflater inflater;
    private final int resourceId;
    private final List<T> itens;
    private OnNotifyDataSetChangedListener onNotifyDataSetChangedListener;

    public AbstractArrayAdapter(Context context, int resourceId, List<T> itens, OnNotifyDataSetChangedListener onNotifyDataSetChangedListener) {
        this(context, resourceId, itens);
        this.onNotifyDataSetChangedListener = onNotifyDataSetChangedListener;
    }

    public AbstractArrayAdapter(Context context, int resourceId, List<T> itens) {
        super(context, resourceId, itens);
        this.resourceId = resourceId;
        this.itens = itens;

        this.inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public List<T> getItens() {
        return itens;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflateIfNecessary(convertView, parent);

        T item = getItem(position);

        if (item != null) {
            onCustomView(position, convertView, parent, item);
            return convertView;
        }

        System.out.println(getClass() + " [return null]");
        return convertView;
    }

    protected View inflateIfNecessary(View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(resourceId, parent, false);
        }
        return convertView;
    }

    protected abstract void onCustomView(int position, View convertView, ViewGroup parent, T item);

    public void removeAll(List<T> list) {
        for (T t : list)
            remove(t);
    }

    protected LayoutInflater getInflater() {
        return inflater;
    }

    public int getResourceId() {
        return resourceId;
    }

    public abstract long getItemId(int position);

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (onNotifyDataSetChangedListener != null)
            onNotifyDataSetChangedListener.onNotifyDataSetChanged();
    }

    public interface OnNotifyDataSetChangedListener {
        public void onNotifyDataSetChanged();
    }
}
