package com.codigo.rafael.easygas.user;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_user);
        initiAll();

        //Adicionando um TextWatcher do tipo TEL(Telefone) em um EditText.
        etTelefone.addTextChangedListener(Mask.insert(Mask.MaskType.TEL, etTelefone));

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

    private void initiAll() {

        bundle = new Bundle();
        user = new Usuario();
        tvLinkLogin = (TextView) findViewById(R.id.link_login);
        etNome = (EditText) findViewById(R.id.et_nome_cadastrar_activity);
        etEmail = (EditText) findViewById(R.id.et_email_cadastrar_activity);
        etSenha = (EditText) findViewById(R.id.et_senha_cadastrar_activity);
        etTelefone = (EditText) findViewById(R.id.et_telefone_activity_cadastrar_user);

        btCadastrarEndereco = (Button) findViewById(R.id.bt_cadastrar_activity_cadastrar);
    }

}
