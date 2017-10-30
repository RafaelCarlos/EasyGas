package com.codigo.rafael.easygas.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codigo.rafael.easygas.MainActivity;
import com.codigo.rafael.easygas.R;
import com.melnykov.fab.FloatingActionButton;


public class PedidoFragment extends Fragment {

    private FloatingActionButton fab;
    private Fragment frag;

    public PedidoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pedido, container, false);

        fab = (FloatingActionButton) view.findViewById(R.id.fab);

        fab.show();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Finalizando Fragment atual.
//                getActivity().getSupportFragmentManager().popBackStack();
                //Limpando toda pilha de Fragments.
                getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        BottomNavigationView navigation = (BottomNavigationView) view.findViewById(R.id.navigation_main);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

        frag = getParentFragment();
        if (frag == null) {

            frag = new PedidoPendenteFragment();
            ft.replace(R.id.rl_fragment_container_pedido, frag);
            ft.addToBackStack(null);
            ft.commit();
        }
        // Inflate the layout for this fragment
        return view;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    frag = new PedidoPendenteFragment();
                    ft.replace(R.id.rl_fragment_container_pedido, frag);
//                    ft.addToBackStack(null);
                    ft.commit();
                    Toast.makeText(getContext(), "Andamento", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_dashboard:
                    Toast.makeText(getContext(), "Concluídos", Toast.LENGTH_SHORT).show();
                    frag = new PedidoConcluidoFragment();
                    ft.replace(R.id.rl_fragment_container_pedido, frag);
//                    ft.addToBackStack(null);
                    ft.commit();
                    return true;
                case R.id.navigation_notifications:
                    frag = new PedidoCanceladoFragment();
                    ft.replace(R.id.rl_fragment_container_pedido, frag);
//                    ft.addToBackStack(null);
                    ft.commit();
                    Toast.makeText(getContext(), "Cancelados", Toast.LENGTH_SHORT).show();
                    return true;

            }
            return false;
        }

    };


}
