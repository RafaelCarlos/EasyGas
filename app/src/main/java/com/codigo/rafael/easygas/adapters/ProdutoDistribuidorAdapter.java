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
import com.codigo.rafael.easygas.entities.Menu;
import com.codigo.rafael.easygas.entities.Produto;

import java.util.List;

/**
 * Created by Rafael Carlos Oliveira on 18/10/2017.
 */

public class ProdutoDistribuidorAdapter extends ArrayAdapter<Produto> {

    private Context context;
    private List<Produto> lista;
    private LayoutInflater inflater;
    private float scale;
    private float width;
    private float height;

    public ProdutoDistribuidorAdapter(Context context, List<Produto> lista) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_produto_fragment, null);
        }

        TextView tvNome = convertView.findViewById(R.id.tv_nome_item_produto_fragment);
        tvNome.setText(lista.get(position).getNome());

        TextView tvValor = convertView.findViewById(R.id.tv_valor_item_produto_fragment);
        tvValor.setText(lista.get(position).getValor());

        TextView tvDescricao = convertView.findViewById(R.id.tv_descricao_item__produto_fragment);
        tvDescricao.setText(lista.get(position).getDescricao());


        ImageView ivArrowRight = convertView.findViewById(R.id.iv_arrow_right_item_produto_fragment);
        ivArrowRight.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), lista.get(position).getSeta()));

        return convertView;
    }
}
