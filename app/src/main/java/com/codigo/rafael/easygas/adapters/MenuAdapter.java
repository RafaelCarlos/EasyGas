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
import android.widget.RatingBar;
import android.widget.TextView;

import com.codigo.rafael.easygas.R;
import com.codigo.rafael.easygas.entities.Menu;

import java.util.List;

/**
 * Created by Rafael Carlos Oliveira on 02/10/2017.
 */

public class MenuAdapter extends ArrayAdapter<Menu> {
    private Context context;
    private List<Menu> lista;
    private LayoutInflater inflater;
    private float scale;
    private float width;
    private float height;

    public MenuAdapter(Context context, List<Menu> lista) {
        super(context, 0, lista);
        this.context = context;
        this.lista = lista;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        scale = context.getResources().getDisplayMetrics().density;
        width = context.getResources().getDisplayMetrics().widthPixels - (int) (14 * scale + 0.5f);
        height = (width / 16) * 9;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_menu_fragment, null);
        }


        TextView tvTitulo = (TextView) convertView.findViewById(R.id.tv_titulo_item_menu_fragment);
        tvTitulo.setText(lista.get(position).getTitulo());

        TextView tvBairro = (TextView) convertView.findViewById(R.id.tv_bairro_item_menu_fragment);
        tvBairro.setText(lista.get(position).getBairro());

        TextView tvDistancia = (TextView) convertView.findViewById(R.id.tv_distancia_item_menu_fragment);
        tvDistancia.setText(String.valueOf(lista.get(position).getDistancia()) + " km");


        ImageView vrImageView = (ImageView) convertView.findViewById(R.id.iv_ic_car_item_menu_fragment);
        vrImageView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), lista.get(position).getFoto()));

        TextView tvValor = (TextView) convertView.findViewById(R.id.tv_valor_item_menu_fragment);
        tvValor.setText(lista.get(position).getValor());


        RatingBar rBarAvaliacao = (RatingBar) convertView.findViewById(R.id.rbar_avaliacao_item_menu_fragment);
//        rBarAvaliacao.setNumStars(lista.get(position).getAvaliacao());
        rBarAvaliacao.setRating(lista.get(position).getAvaliacao());
        return convertView;
    }
}
