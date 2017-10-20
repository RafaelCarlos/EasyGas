package com.codigo.rafael.easygas.adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codigo.rafael.easygas.R;
import com.codigo.rafael.easygas.entities.Cartao;

import java.util.List;

/**
 * Created by Rafael Carlos Oliveira on 20/10/2017.
 */

public class CartaoAdapter extends ArrayAdapter<Cartao> {
    private Context context;
    private List<Cartao> lista;
    private LayoutInflater inflater;
    private float scale;
    private float width;
    private float height;

    public CartaoAdapter(Context context, List<Cartao> lista) {
        super(context, 0, lista);
        this.context = context;
        this.lista = lista;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        scale = context.getResources().getDisplayMetrics().density;
        width = context.getResources().getDisplayMetrics().widthPixels - (int) (14 * scale + 0.5f);
        height = (width / 16) * 9;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_cartoes_fragment, null);
        }

        ImageView vrImageView = convertView.findViewById(R.id.iv_bandeira_cartao);
        vrImageView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), lista.get(position).getBandeiraCartao()));

        TextView tvNomeTitular = (TextView) convertView.findViewById(R.id.tv_nome_titular_cartao_item_cartoes_fragment);
        tvNomeTitular.setText(lista.get(position).getTitularCartao());
        TextView tvNumeroCartao = (TextView) convertView.findViewById(R.id.tv_numero_cartao_item_cartoes_fragment);
        tvNumeroCartao.setText(lista.get(position).getNumeroCartao());

        return convertView;
    }
}
