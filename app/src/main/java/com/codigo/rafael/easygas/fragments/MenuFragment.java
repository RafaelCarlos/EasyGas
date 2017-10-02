package com.codigo.rafael.easygas.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.codigo.rafael.easygas.R;
import com.codigo.rafael.easygas.adapters.MenuAdapter;
import com.codigo.rafael.easygas.entities.Menu;

import java.util.ArrayList;


public class MenuFragment extends Fragment {
    public MenuFragment() {
        // Required empty public constructor
    }

    private ListView mListView;
    int posicaoAtual = 0;
    private TextView tvTitulo, tvValor, tvDistancia, tvBairro;
    private ImageView ivCar;
    private RatingBar rBarAvaliacao;
    private ArrayList<Menu> listaMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        mListView = (ListView) view.findViewById(R.id.lv_menu_fragment);

        listaMenu = new ArrayList<>();

        listaMenu.add(new Menu("Adalberto Gás", "Plano Diretor Norte", 3.5, R.mipmap.ic_car, "R$ 85,00", 5));
        listaMenu.add(new Menu("Distribuidor do José", "Plano Diretor Sul", 1.2, R.mipmap.ic_car, "R$ 82,00", 3));
        listaMenu.add(new Menu("Gás e Água", "Taquaralto", 6.5, R.mipmap.ic_car, "R$ 80,00", 4));

        MenuAdapter menuAdapter = new MenuAdapter(getActivity(), listaMenu);

        mListView.setAdapter(menuAdapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    Toast.makeText(getActivity(), "Você escolhe a distribuidora: " + listaMenu.get(i).getTitulo(), Toast.LENGTH_LONG).show();
                } else if (i == 1) {
                    Toast.makeText(getActivity(), "Você escolhe a distribuidora: " + listaMenu.get(i).getTitulo(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "Você escolhe a distribuidora: " + listaMenu.get(i).getTitulo(), Toast.LENGTH_LONG).show();
                }
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}
