package com.codigo.rafael.easygas.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.codigo.rafael.easygas.EnderecoAddActivity;
import com.codigo.rafael.easygas.PrincipalActivity;
import com.codigo.rafael.easygas.R;
import com.codigo.rafael.easygas.TesteLocationActivity;
import com.melnykov.fab.FloatingActionButton;


public class EnderecoFragment extends Fragment {
    private FloatingActionButton fabAddEndereco;
    private Button btPageTeste, btPageTeste2;

    public EnderecoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_endereco, container, false);
        btPageTeste = view.findViewById(R.id.bt_geocoder_teste);
        btPageTeste2 = view.findViewById(R.id.bt_teste_geolocation);

        fabAddEndereco = (FloatingActionButton) view.findViewById(R.id.fab_endereco);

        btPageTeste2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), TesteLocationActivity.class));

            }
        });

        btPageTeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), PrincipalActivity.class));
            }
        });

        fabAddEndereco.show();

        fabAddEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getContext(), EnderecoAddActivity.class));
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
