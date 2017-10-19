package com.codigo.rafael.easygas;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.codigo.rafael.easygas.entities.Menu;
import com.codigo.rafael.easygas.fragments.AvaliacaoDistribuidorFragment;
import com.codigo.rafael.easygas.fragments.DistribuidorHomeFragment;
import com.codigo.rafael.easygas.fragments.InformacaoDistribuidorFragment;
import com.codigo.rafael.easygas.fragments.PedidoConcluidoFragment;

import static java.security.AccessController.getContext;

public class DistribuidorActivity extends AppCompatActivity {

    private TextView tvTituloNome, tvBairro, tvDistancia, tvValor;
    private RatingBar rbAvaliacao;
    private Menu menu;
    //    private CoordinatorLayout coordinatorLayout;
    private BottomNavigationView navigation;
    private Fragment frag;
    private Toolbar tbDistribuidor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distribuidor);

        navigation = (BottomNavigationView) findViewById(R.id.navigation_distribuidor);
        tbDistribuidor = findViewById(R.id.tb_activity_distribuidor);
        setSupportActionBar(tbDistribuidor);
//        tvTituloNome = findViewById(R.id.tv_titulo_nome_distribuidor_activity);
//        tvDistancia = findViewById(R.id.tv_distancia_distribuidor_activity);
//        tvBairro = findViewById(R.id.tv_bairro_distribuidor_activity);
//
////        coordinatorLayout = findViewById(R.id.coordinator_distribuidor);
//        tvValor = findViewById(R.id.tv_valor_distribuidor_fragment);
//
//        rbAvaliacao = findViewById(R.id.rbar_avaliacao_distribuidor_activity);
//
//        menu = (Menu) getIntent().getSerializableExtra("menu");
//
//        Log.i("MenuRece", menu.toString());

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        frag = getSupportFragmentManager().findFragmentByTag("distribuidorFrag");

        if (frag == null) {
            tbDistribuidor.setTitle("Distribuidora");
            frag = new DistribuidorHomeFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.rl_fragment_container_produto, frag, "distribuidorFrag");
            ft.commit();
        }

//        final Snackbar snackbar = Snackbar.make(coordinatorLayout, "Dados Recebidos", Snackbar.LENGTH_INDEFINITE);
//
//        snackbar.setAction("OK", new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                snackbar.dismiss();
//            }
//        });
//
//        snackbar.show();
//        tvTituloNome.setText(menu.getTitulo());
//        tvBairro.setText(menu.getBairro());
//        tvDistancia.setText(String.valueOf(menu.getDistancia()));
//        tvValor.setText(menu.getValor());
//        rbAvaliacao.setRating(menu.getAvaliacao());


    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            Fragment frag = frag = getSupportFragmentManager().findFragmentByTag("mainFrag");
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_distribuidor_home:
                    frag = new DistribuidorHomeFragment();
                    tbDistribuidor.setTitle("Distribuidora");
                    ft.replace(R.id.rl_fragment_container_produto, frag);
                    ft.addToBackStack(null);
                    ft.commit();
                    Toast.makeText(DistribuidorActivity.this, "Distribuidor", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_distribuidor_avaliacao:
                    Toast.makeText(DistribuidorActivity.this, "Avaliação", Toast.LENGTH_SHORT).show();
                    frag = new AvaliacaoDistribuidorFragment();
                    tbDistribuidor.setTitle("Avaliações");
//                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.rl_fragment_container_produto, frag);
                    ft.addToBackStack(null);
                    ft.commit();
                    return true;
                case R.id.navigation_distribuidor_info:
                    frag = new InformacaoDistribuidorFragment();
                    tbDistribuidor.setTitle("Informações");
                    ft.replace(R.id.rl_fragment_container_produto, frag);
                    ft.addToBackStack(null);
                    ft.commit();
                    Toast.makeText(DistribuidorActivity.this, "Informação", Toast.LENGTH_SHORT).show();
                    return true;

            }
            return false;
        }

    };
    /**
     * Exemplo SnackBar
     * @param text

    private void showSnackbar(final String text) {
    View container = findViewById(android.R.id.content);
    if (container != null) {
    Snackbar.make(container, text, Snackbar.LENGTH_LONG).show();
    }
    }
     */
}
