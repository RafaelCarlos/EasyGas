package com.codigo.rafael.easygas.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.codigo.rafael.easygas.PedidoActivity;
import com.codigo.rafael.easygas.R;
import com.codigo.rafael.easygas.adapters.ProdutoDistribuidorAdapter;
import com.codigo.rafael.easygas.entities.Menu;
import com.codigo.rafael.easygas.entities.Produto;
import com.google.gson.Gson;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class DistribuidorHomeFragment extends Fragment {

    private ListView mListView;
    private List<Produto> produtos;
    private List<Produto> produtosCar;
    private Produto produtoCarrinho;
    private FloatingActionButton fabCarrinhoProduto;
    private TextView tvTituloNome, tvBairro, tvDistancia, tvValor;
    private RatingBar rbAvaliacao;
    private Menu menu;
    private MaterialDialog dialog;
    private boolean isEmpty = false;
    private int qtd = 0;


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
        produtoCarrinho = new Produto();
        produtosCar = new ArrayList<>();
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

        fabCarrinhoProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isEmpty) {
                    Toast.makeText(getActivity(), "Carrinho vazio. Por favor, insira itens ao carrinho!", Toast.LENGTH_LONG).show();
                } else {
                    startActivity(new Intent(getActivity(), PedidoActivity.class));
                }
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

                dialog = new MaterialDialog.Builder(getActivity())
                        .content("Deseja adicionar esse produto ao carrinho?")
                        .positiveText("Sim")
                        .negativeText("Não")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                                produtoCarrinho = (Produto) mListView.getItemAtPosition(i);
                                produtosCar.add(produtoCarrinho);
                                isEmpty = true;
                                qtd++;

                                SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                Gson g = new Gson();

                                String json = g.toJson(produtosCar);
                                editor.putString("meuProduto", json);
                                editor.commit();


                            }
                        })
                        .show();

//                startActivity(new Intent(getActivity(), PedidoMapsActivity.class));
            }
        });


        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                dialog = new MaterialDialog.Builder(getActivity())
                        .content("Deseja remover esse produto ao carrinho?")
                        .positiveText("Sim")
                        .negativeText("Não")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                
                            }
                        })
                        .show();

                return false;
            }
        });

        return view;
    }


}
