package com.codigo.rafael.easygas.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.codigo.rafael.easygas.R;

import butterknife.Bind;

public class FeedbackFragment extends Fragment {
    @Bind(R.id.bt_enviar_tela_feedback)
    Button btEnviarFeedback;

    @Bind(R.id.et_conteudo_tela_feedback)
    EditText etConteu;

    public FeedbackFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        btEnviarFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mensagem();
            }
        });

        View view = inflater.inflate(R.layout.fragment_feedback, container, false);


        return view;
    }

    private void mensagem() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.MaterialDrawerTheme_TranslucentStatus);

//        dialog.setTitle("Atençao");
        dialog.setMessage("Obrigado!")
                .setCancelable(false)
//                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(getActivity(), "Usuario desativado!", Toast.LENGTH_SHORT).show();
//                        //Limpando a pilha de activities
//                        Intent intent = new Intent(getContext(), LoginActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
//                    }
//                })
                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();


    }
}
