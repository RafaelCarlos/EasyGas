package com.codigo.rafael.easygas.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codigo.rafael.easygas.R;
import com.codigo.rafael.easygas.entities.Usuario;

public class AtualizarCadastro extends Fragment {
    private EditText etNome, etEmail, etSenha, etTelefone;
    private Button btAtualizarCadastro;
    private Bundle bundle;
    private Usuario user;

    public AtualizarCadastro() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_atualizar_cadastro, container, false);
        bundle = new Bundle();
        user = new Usuario();

        etNome = (EditText) view.findViewById(R.id.et_nome_atualizar_cadastro);
        etEmail = (EditText) view.findViewById(R.id.et_email_atualizar_cadastro);
        etSenha = (EditText) view.findViewById(R.id.et_senha_atualizar_cadastro);
        etTelefone = (EditText) view.findViewById(R.id.et_telefone_atualizar_cadastro);
        btAtualizarCadastro = (Button) view.findViewById(R.id.bt_atualizar_atualizar_cadastro);


        Intent intent = getActivity().getIntent();
        Bundle recebe = getActivity().getIntent().getExtras();

        try {
            user = (Usuario) getActivity().getIntent().getSerializableExtra("userRecebido");


            etNome.setText(user.getNome());
            etEmail.setText(user.getEmail());
            etSenha.setText(user.getSenha());
            etTelefone.setText(user.getTelefone());

        } catch (Exception e) {
            Log.i("Erro Atualizar", e.toString());
            Toast.makeText(getContext(), "Não possível obter os dados do usuário", Toast.LENGTH_SHORT).show();
        }


        // Inflate the layout for this fragment
        return view;
    }

    private void initiAll() {


    }

}
