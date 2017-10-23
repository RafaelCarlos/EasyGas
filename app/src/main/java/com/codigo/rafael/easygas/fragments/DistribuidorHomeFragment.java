package com.codigo.rafael.easygas.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.codigo.rafael.easygas.PedidoMapsActivity;
import com.codigo.rafael.easygas.R;
import com.codigo.rafael.easygas.adapters.ProdutoDistribuidorAdapter;
import com.codigo.rafael.easygas.entities.Menu;
import com.codigo.rafael.easygas.entities.Produto;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class DistribuidorHomeFragment extends Fragment {

    private ListView mListView;
    private List<Produto> produtos;
    private FloatingActionButton fabCarrinhoProduto;
    private TextView tvTituloNome, tvBairro, tvDistancia, tvValor;
    private RatingBar rbAvaliacao;
    private Menu menu;


//    private CoordinatorLayout coordinatorLayout;

    public DistribuidorHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_distribuidor_home, container, false);
        tvTituloNome = view.findViewById(R.id.tv_nome_distribuidor_fragment);
        tvDistancia = view.findViewById(R.id.tv_distancia_distribuidor_fragment);
        tvBairro = view.findViewById(R.id.tv_endereco_distribuidor_fragment);
        tvValor = view.findViewById(R.id.tv_valor_distribuidor_fragment);
        mListView = view.findViewById(R.id.lv_produtos_distribuidor_fragment);
        fabCarrinhoProduto = view.findViewById(R.id.fab_carrinho_produto);
        rbAvaliacao = view.findViewById(R.id.rbar_avaliacao_distribuidor_fragment);
        produtos = new ArrayList<>();

        produtos.add(new Produto("Botijão de Gás", "Botijão de gás de 13kg", "R$ 83,00", R.mipmap.ic_arrow_right));
        produtos.add(new Produto("Botijão de Gás", "Botijão de gás de 7kg", "R$ 56,00", R.mipmap.ic_arrow_right));
        produtos.add(new Produto("Botijão de Gás", "Botijão de gás de 3kg", "R$ 35,00", R.mipmap.ic_arrow_right));
        produtos.add(new Produto("Galão de Água Santa Clara", "Galão de água Santa Clara 20 litros", "R$ 10,00", R.mipmap.ic_arrow_right));
        produtos.add(new Produto("Galão de Água Suja", "Botijão de gás de 13kg", "R$ 8,00", R.mipmap.ic_arrow_right));


        ProdutoDistribuidorAdapter produtoDistribuidorAdapter = new ProdutoDistribuidorAdapter(getActivity(), produtos);

        mListView.setAdapter(produtoDistribuidorAdapter);


        menu = (Menu) getActivity().getIntent().getSerializableExtra("menu");

        Log.i("MenuRece", menu.toString());
        tvTituloNome.setText(menu.getTitulo());
        tvBairro.setText(menu.getBairro());
        tvDistancia.setText(String.valueOf(menu.getDistancia()));
        tvValor.setText(menu.getValor());
        rbAvaliacao.setRating(menu.getAvaliacao());

        fabCarrinhoProduto.attachToListView(mListView);
        fabCarrinhoProduto.show();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                startActivity(new Intent(getActivity(), PedidoMapsActivity.class));
            }
        });

        return view;
    }


}
