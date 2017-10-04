package com.codigo.rafael.easygas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.codigo.rafael.easygas.fragments.AtualizarCadastro;
import com.codigo.rafael.easygas.fragments.ConfiguracaoFragment;
import com.codigo.rafael.easygas.fragments.EnderecoFragment;
import com.codigo.rafael.easygas.fragments.FeedbackFragment;
import com.codigo.rafael.easygas.fragments.MenuFragment;
import com.codigo.rafael.easygas.fragments.PedidoFragment;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class MainActivity extends AppCompatActivity {

    //    @BindView(R.id.bt_teste)
//    Button btTeste;
    private Button btTe;
    private Drawer drawerMenu;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);

        toolbar = (Toolbar) findViewById(R.id.tb_endereco_add_activity);
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_main);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setSupportActionBar(toolbar);
        toolbar.setTitle("Distribuidoras");

        Fragment frag = frag = getSupportFragmentManager().findFragmentByTag("mainFrag");

        if (frag == null) {
            frag = new MenuFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.rl_fragment_container, frag, "mainFrag");
            ft.commit();
        }


        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.color.colorTema)
                .addProfiles(
                        new ProfileDrawerItem().withName("Rafael Oliveira").withEmail("rafaellcarloss@hotmail.com")
                                .withIcon(getResources().getDrawable(R.mipmap.ic_user))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        drawerMenu = new DrawerBuilder()
                .withActivity(this)
                .withDrawerGravity(Gravity.LEFT)
                .withSelectedItem(0)
                .withSavedInstance(savedInstanceState)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .withAccountHeader(headerResult)
//                .addDrawerItems(item1, new DividerDrawerItem())
                .withFooterDivider(true)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Fragment frag = null;

                        if (position == 1) {
                            frag = new MenuFragment();
                            Toast.makeText(getApplicationContext(), "Posicao: " + position, Toast.LENGTH_SHORT).show();
                            toolbar.setTitle("Distribuidoras");
                            toolbar.setTitleTextColor(Color.WHITE);
                        } else if (position == 2) {
                            frag = new PedidoFragment();
                            toolbar.setTitle("Pedidos");
                            toolbar.setTitleTextColor(Color.WHITE);
                            Toast.makeText(getApplicationContext(), "Posicao: " + position, Toast.LENGTH_SHORT).show();

                        } else if (position == 3) {
                            frag = new EnderecoFragment();
                            toolbar.setTitle("Meus Endereços");
                            toolbar.setTitleTextColor(Color.WHITE);
                            Toast.makeText(getApplicationContext(), "Posicao: " + position, Toast.LENGTH_SHORT).show();

                        } else if (position == 4) {
                            frag = new FeedbackFragment();
                            toolbar.setTitle("Feedback");
                            toolbar.setTitleTextColor(Color.WHITE);
                            Toast.makeText(getApplicationContext(), "Posicao: " + position, Toast.LENGTH_SHORT).show();

                        } else if (position == 6) {
                            frag = new AtualizarCadastro();
                            toolbar.setTitle("Atualizar Cadastro");
                            toolbar.setTitleTextColor(Color.WHITE);
                            Toast.makeText(getApplicationContext(), "Posicao: " + position, Toast.LENGTH_SHORT).show();

                        } else if (position == 7) {
                            frag = new ConfiguracaoFragment();
                            toolbar.setTitle("Configurações");
                            toolbar.setTitleTextColor(Color.WHITE);
                            Toast.makeText(getApplicationContext(), "Posicao: " + position, Toast.LENGTH_SHORT).show();

                        } else if (position == 8) {
                            frag = new MenuFragment();
                            Intent sendIntent = new Intent();
                            sendIntent.setAction(Intent.ACTION_SEND);
                            String texto = "Olá!" + "\n" + "Comece a utilizar o EasyGás! :-)" + "\n" + "Compartilhado do aplicativo.";
                            sendIntent.putExtra(Intent.EXTRA_TEXT, texto);
                            sendIntent.setType("text/plain");
                            startActivity(sendIntent);

                            toolbar.setTitleTextColor(Color.WHITE);
                            Toast.makeText(getApplicationContext(), "Posicao: " + position, Toast.LENGTH_SHORT).show();

                        } else {
                            frag = new MenuFragment();
                        }

                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.rl_fragment_container, frag);
                        ft.addToBackStack(null);
                        ft.commit();

                        return false;
                    }
                })
                .build();

        drawerMenu.addItem(new PrimaryDrawerItem().withName("Distribuidoras").withIcon(getDrawable(R.mipmap.ic_truck)));
        drawerMenu.addItem(new PrimaryDrawerItem().withName("Meus Pedidos").withIcon(getDrawable(R.mipmap.ic_historico)));
        drawerMenu.addItem(new PrimaryDrawerItem().withName("Meus Endereços").withIcon(getDrawable(R.mipmap.ic_local)));
        drawerMenu.addItem(new PrimaryDrawerItem().withName("Feedback").withIcon(getDrawable(R.mipmap.ic_feedback)));
        drawerMenu.addItem(new SectionDrawerItem());
        drawerMenu.addItem(new PrimaryDrawerItem().withName("Atualizar Cadastro").withIcon(getDrawable(R.mipmap.ic_edit)));
        drawerMenu.addItem(new PrimaryDrawerItem().withName("Configurações").withIcon(getDrawable(R.mipmap.ic_setting)));
        drawerMenu.addItem(new PrimaryDrawerItem().withName("Indique-nos").withIcon(getDrawable(R.mipmap.ic_share)));
        drawerMenu.addStickyFooterItem(new PrimaryDrawerItem().withName("EasyGás desenvolvido por Rafael Oliveira"));


    }

//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.navigation_home:
//                    Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
//                    return true;
//                case R.id.navigation_dashboard:
//                    Toast.makeText(MainActivity.this, "Dashboard", Toast.LENGTH_SHORT).show();
//                    return true;
//                case R.id.navigation_notifications:
//                    Toast.makeText(MainActivity.this, "Notication", Toast.LENGTH_SHORT).show();
//                    return true;
//            }
//            return false;
//        }
//
//    };
}
