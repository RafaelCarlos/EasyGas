package com.codigo.rafael.easygas.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codigo.rafael.easygas.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AvaliacaoDistribuidorFragment extends Fragment {


    public AvaliacaoDistribuidorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_avaliacao_distribuidor, container, false);
    }

}
