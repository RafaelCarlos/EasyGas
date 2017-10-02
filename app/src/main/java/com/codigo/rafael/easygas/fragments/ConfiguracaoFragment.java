package com.codigo.rafael.easygas.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.codigo.rafael.easygas.R;
import com.codigo.rafael.easygas.user.LoginActivity;

import butterknife.Bind;


public class ConfiguracaoFragment extends Fragment {

    @Bind(R.id.bt_desativar_usuario_tela_configuracao)
    Button btDesativarUsuario;

    public ConfiguracaoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_configuracao, container, false);

        btDesativarUsuario = (Button) view.findViewById(R.id.bt_desativar_usuario_tela_configuracao);

        btDesativarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensagem();
            }
        });


        return view;
    }

    private void mensagem() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.MaterialDrawerTheme_TranslucentStatus);

        dialog.setTitle("Atençao");
        dialog.setMessage("Deseja realmente desativar seu usuario?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Usuario desativado!", Toast.LENGTH_SHORT).show();
                        //Limpando a pilha de activities
                        Intent intent = new Intent(getContext(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Nao", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}
