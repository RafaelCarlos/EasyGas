package com.codigo.rafael.easygas.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codigo.rafael.easygas.MainActivity;
import com.codigo.rafael.easygas.R;


public class PedidoFragment extends Fragment {


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

        BottomNavigationView navigation = (BottomNavigationView) view.findViewById(R.id.navigation_main);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Inflate the layout for this fragment
        return view;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Toast.makeText(getContext(), "Andamento", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_dashboard:
                    Toast.makeText(getContext(), "Concluídos", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_notifications:
                    Toast.makeText(getContext(), "Cancelados", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }

    };


}
