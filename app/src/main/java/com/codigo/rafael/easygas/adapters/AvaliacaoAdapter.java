package com.codigo.rafael.easygas.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.codigo.rafael.easygas.R;
import com.codigo.rafael.easygas.entities.Avaliacao;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Rafael Carlos Oliveira on 18/10/2017.
 */

public class AvaliacaoAdapter extends ArrayAdapter<Avaliacao> {


    private Context context;
    private List<Avaliacao> lista;
    private LayoutInflater inflater;
    private float scale;
    private float width;
    private float height;

    public AvaliacaoAdapter(Context context, List<Avaliacao> lista) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_avaliacao_fragment, null);
        }


        TextView tvNomeUsuario = convertView.findViewById(R.id.tv_nome_usuario_item_avaliacao_fragment);
        tvNomeUsuario.setText(lista.get(position).getNomeUsuario());

        TextView tvDescricao = convertView.findViewById(R.id.tv_descricao_item_avaliacao_fragment);
        tvDescricao.setText(lista.get(position).getDescricao());


        TextView tvData = convertView.findViewById(R.id.tv_data_item_avaliacao_fragment);
        tvData.setText(String.valueOf(lista.get(position).getDataAvaliacao()));

        RatingBar rBarAvaliacao = (RatingBar) convertView.findViewById(R.id.rbar_avaliacao_usuario_item_avaliacao_fragment);
//        rBarAvaliacao.setNumStars(lista.get(position).getAvaliacao());
        rBarAvaliacao.setRating(lista.get(position).getNota());


        return convertView;
    }
}
