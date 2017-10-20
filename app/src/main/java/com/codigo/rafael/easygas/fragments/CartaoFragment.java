package com.codigo.rafael.easygas.fragments;


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

    private EditText etNumeroCartao, etValidade, etCodigoSeguranca;
    private Button btConfirmar;
    private View viewDialog;

    private CreditCardFormatTextWatcher tv;

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


//        viewDialog = view.findViewById(R.layout.itens_dialog_add_cartao);
//
//        etNumeroCartao = viewDialog.findViewById(R.id.et_numero_cartao_itens_add_cartao);
//        etValidade = viewDialog.findViewById(R.id.et_data_validade_itens_dialog_add_cartao);
//        etCodigoSeguranca = viewDialog.findViewById(R.id.et_cvc_itens_dialog_add_cartao);
//        btConfirmar = viewDialog.findViewById(R.id.bt_confirmar_itens_add_cartao);


        // the EditText here is used to compute the padding (1 EM) which depends
        // on the actual size of the Text rendered on screen taking into account:
        // the font, the text size, the user scaling on text
//        tv = new CreditCardFormatTextWatcher(etNumeroCartao);
//        etNumeroCartao.addTextChangedListener(tv);

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

                boolean wrapInScrollView = true;

                //TODO
                /**
                 * Terminar essa parte
                 */
                btConfirmar = getActivity().findViewById(R.id.bt_confirmar_itens_add_cartao);
                dialog = new MaterialDialog.Builder(getActivity())
                        .title("Add Novo Cartão")
//                        .customView(viewDialog, wrapInScrollView)
//                        .onPositive(confimar())
                        .positiveText("Continuar")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                            }
                        })
                        .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_PHONE)
                        .input("Número Cartão", "Numero", new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {

                            }
                        })
                        .show();

//                        .title("Add Novo Cartão")
//                        .content("Nome Titular")
//                        .content("Número Cartão")
//                        .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)
//                        .input("Nome Titular", "Número Cartão", new MaterialDialog.InputCallback() {
//                            @Override
//                            public void onInput(MaterialDialog dialog, CharSequence input) {
//                                // Do something
//                            }
//                        }).show();
            }
        });


        return view;
    }

    public void confimar(View view) {
        Toast.makeText(getActivity(), "Funcionou", Toast.LENGTH_LONG).show();
    }

}
