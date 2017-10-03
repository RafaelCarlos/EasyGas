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
    //    @Bind(R.id.bt_enviar_tela_feedback)
    private Button btEnviarFeedback;

    //    @Bind(R.id.et_conteudo_tela_feedback)
    private EditText etConteudo;

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
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);
        btEnviarFeedback = (Button) view.findViewById(R.id.bt_enviar_tela_feedback);
        etConteudo = (EditText) view.findViewById(R.id.et_conteudo_tela_feedback);

        btEnviarFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mensagem();
            }
        });


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
                        etConteudo.setText("");
                    }
                })
                .show();


    }
}
