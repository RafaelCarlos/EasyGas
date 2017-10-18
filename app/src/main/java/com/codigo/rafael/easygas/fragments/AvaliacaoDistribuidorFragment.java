package com.codigo.rafael.easygas.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.codigo.rafael.easygas.R;
import com.codigo.rafael.easygas.adapters.AvaliacaoAdapter;
import com.codigo.rafael.easygas.entities.Avaliacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class AvaliacaoDistribuidorFragment extends Fragment {

    private ListView mListView;
    private List<Avaliacao> avaliacoes;

    public AvaliacaoDistribuidorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_avaliacao_distribuidor, container, false);


        mListView = view.findViewById(R.id.lv_avaliacao_usuario_avaliacao_fragment);

        avaliacoes = new ArrayList<>();
        Date dataComentario = new Date();


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm", new Locale("pt","BR"));


        String dataAtualizada = sdf.format(dataComentario);


        Date dataConvertida = null;
        try {
            dataConvertida = sdf.parse(dataAtualizada);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        avaliacoes.add(new Avaliacao("Rafael Carlos", "Serviço muito bom!", 5, dataConvertida));
        avaliacoes.add(new Avaliacao("Radharane", "A entrega demorou muito", 2.5f, dataConvertida));
        avaliacoes.add(new Avaliacao("Carlos Henrique", "Gostei!", 4, dataComentario));
        avaliacoes.add(new Avaliacao("João", "Serviço muito ruim!", 0, dataComentario));
        avaliacoes.add(new Avaliacao("Silvano", "Mais ou menos", 2.5f, dataComentario));


        AvaliacaoAdapter avaliacaoAdapter = new AvaliacaoAdapter(getActivity(), avaliacoes);

        mListView.setAdapter(avaliacaoAdapter);

        return view;
    }

}
