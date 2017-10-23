package com.codigo.rafael.easygas.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.codigo.rafael.easygas.ItensDialogAddCartaoActivity;
import com.codigo.rafael.easygas.R;
import com.codigo.rafael.easygas.adapters.CartaoAdapter;
import com.codigo.rafael.easygas.entities.Cartao;
import com.codigo.rafael.easygas.util.CreditCardFormatTextWatcher;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartaoFragment extends Fragment {

    private FloatingActionButton fabAddCartao;
    private ListView mListView;
    private ArrayList<Cartao> cartoes;
    private MaterialDialog dialog;


    public CartaoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cartao, container, false);

        fabAddCartao = view.findViewById(R.id.fab_cartoes);

        mListView = view.findViewById(R.id.lv_cartoes_fragment);


        cartoes = new ArrayList<>();

        cartoes.add(new Cartao(R.mipmap.ic_visa, "Rafael C Q Oliveira", "**** 0514"));
        cartoes.add(new Cartao(R.mipmap.ic_master_card, "Rafael C Q Oliveira", "**** 4532"));
        cartoes.add(new Cartao(R.mipmap.ic_visa, "Rafael C Q Oliveira", "**** 5890"));


        CartaoAdapter cartaoAdapter = new CartaoAdapter(getActivity(), cartoes);

        mListView.setAdapter(cartaoAdapter);
        fabAddCartao.attachToListView(mListView);
        fabAddCartao.show();


        fabAddCartao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //TODO
                /**
                 * Terminar essa parte
                 */

                startActivity(new Intent(getActivity(), ItensDialogAddCartaoActivity.class));


            }
        });


        return view;
    }

    public void confimar(View view) {
        Toast.makeText(getActivity(), "Funcionou", Toast.LENGTH_LONG).show();
    }

}
