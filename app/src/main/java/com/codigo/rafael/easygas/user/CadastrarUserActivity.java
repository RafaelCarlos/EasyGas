package com.codigo.rafael.easygas.user;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.codigo.rafael.easygas.R;
import com.codigo.rafael.easygas.entities.Usuario;
import com.codigo.rafael.easygas.util.Mask;

public class CadastrarUserActivity extends AppCompatActivity {

    private EditText etNome, etEmail, etSenha, etTelefone;
    private Button btCadastrarEndereco;
    //    @Bind(R.id.link_login)
    private TextView tvLinkLogin;
    private Bundle bundle;
    private Usuario user;
    private Toolbar tbCadastroUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_user);
        initiAll();

        //Adicionando um TextWatcher do tipo TEL(Telefone) em um EditText.
        etTelefone.addTextChangedListener(Mask.insert(Mask.MaskType.TEL, etTelefone));
        setSupportActionBar(tbCadastroUser);
        tbCadastroUser.setTitle("Cadastro");
        tbCadastroUser.setTitleTextColor(Color.WHITE);

        etTelefone.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {

                if (keyEvent != null && KeyEvent.KEYCODE_ENTER == keyEvent.getKeyCode() || actionId == EditorInfo.IME_ACTION_DONE) {
                    startActivity(new Intent(CadastrarUserActivity.this, EnderecoCadastroActivity.class));

                }
                return false;
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        btCadastrarEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setNome(etNome.getText().toString());
                user.setEmail(etEmail.getText().toString());
                user.setTelefone(etTelefone.getText().toString());
                user.setSenha(etSenha.getText().toString());

                bundle.putSerializable("user", user);
                Intent intent = new Intent(CadastrarUserActivity.this, EnderecoCadastroActivity.class);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        SpannableString spanString2 = new SpannableString(tvLinkLogin.getText().toString());
        spanString2.setSpan(new UnderlineSpan(), 0, spanString2.length(), 0);
        spanString2.setSpan(new StyleSpan(Typeface.ITALIC), 0, spanString2.length(), 0);
        tvLinkLogin.setText(spanString2);


        tvLinkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Limpando a pilha de activities
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initiAll() {

        bundle = new Bundle();
        user = new Usuario();
        tvLinkLogin = findViewById(R.id.link_login);
        etNome = findViewById(R.id.et_nome_cadastrar_activity);
        etEmail = findViewById(R.id.et_email_cadastrar_activity);
        etSenha = findViewById(R.id.et_senha_cadastrar_activity);
        etTelefone = findViewById(R.id.et_telefone_activity_cadastrar_user);

        btCadastrarEndereco = findViewById(R.id.bt_cadastrar_activity_cadastrar);
        tbCadastroUser = findViewById(R.id.tb_cadastro_user);
    }

}
