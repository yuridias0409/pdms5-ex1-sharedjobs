package pdms5.at1.sharedjobs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editTextTextPersonName;
    private EditText editTextTextEmailAddress;
    private EditText editTextPhone;
    private EditText editTextAnoFormatura;
    private EditText editTextAnoConclusao;
    private EditText editTextInstituicao;
    private EditText editTextMonografia;
    private EditText editTextOrientador;
    private EditText editTextCell;
    private EditText editTextDate;
    private EditText editTextVagasInteresse;
    private CheckBox checkBoxAttsEmail;
    private CheckBox checkBoxTelComercial;
    private RadioGroup radioGroupSexo;
    private RadioButton radioButtonMasc;
    private RadioButton radioButtonFem;
    private RadioButton radioButtonOutro;
    private Switch switchCell;
    private Spinner spinnerFormacao;
    private Button buttonSalvar;
    private Button buttonLimpar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ligando (binding) objetos com as Views
        bindViews();

        //Seta masculino como selecionado desde o começo
        radioButtonMasc.setChecked(true);

        switchCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Celular
                if(switchCell.isChecked()){
                    editTextCell.setVisibility(View.VISIBLE);
                }   else{
                    editTextCell.setVisibility(View.GONE);
                }
            }
        });

        // Listener de item selecionado
        spinnerFormacao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (((TextView) view).getText().equals("Fundamental") || ((TextView) view).getText().equals("Ensino Medio")){
                    //Retira os outros campos
                    editTextAnoConclusao.setVisibility(View.GONE);
                    editTextAnoFormatura.setVisibility(View.GONE);
                    editTextMonografia.setVisibility(View.GONE);
                    editTextOrientador.setVisibility(View.GONE);

                    //Ativa o campo
                    editTextAnoFormatura.setVisibility(View.VISIBLE);
                }   else if(((TextView) view).getText().equals("Graduação") || ((TextView) view).getText().equals("Especialização")){
                    //Retira os outros campos
                    editTextAnoFormatura.setVisibility(View.GONE);
                    editTextMonografia.setVisibility(View.GONE);
                    editTextOrientador.setVisibility(View.GONE);

                    //Ativa o campo
                    editTextAnoConclusao.setVisibility(View.VISIBLE);
                    editTextInstituicao.setVisibility(View.VISIBLE);
                }   else if(((TextView) view).getText().equals("Mestrado") || ((TextView) view).getText().equals("Doutorado")){
                    //Retira os outros campos
                    editTextAnoFormatura.setVisibility(View.GONE);

                    //Ativa o campo
                    editTextAnoConclusao.setVisibility(View.VISIBLE);
                    editTextInstituicao.setVisibility(View.VISIBLE);
                    editTextMonografia.setVisibility(View.VISIBLE);
                    editTextOrientador.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void onClickButton(View view){
        switch(view.getId()) {
            case R.id.buttonSalvar:
                saveForm();
                break;
            case R.id.buttonLimpar:
                cleanForm();
                break;
            default:
                break;
        }
    }

    private void saveForm() {
        StringBuffer sumarioSb = new StringBuffer();
        sumarioSb.append("Nome completo: ").append(editTextTextPersonName.getText().toString()).append("\n");
        sumarioSb.append("E-mail: ").append(editTextTextEmailAddress.getText().toString()).append("\n");

        if(checkBoxAttsEmail.isChecked()){
            sumarioSb.append("Receber Emails: ").append("true").append("\n");
        }   else{
            sumarioSb.append("Receber Emails: ").append("false").append("\n");
        }

        sumarioSb.append("Telefone: ").append(editTextPhone.getText().toString()).append("\n");

        if(checkBoxTelComercial.isChecked()){
            sumarioSb.append("Telefone comercial: ").append("true").append("\n");
        }   else{
            sumarioSb.append("Telefone comercial: ").append("false").append("\n");
        }

        if(switchCell.isChecked()){
            sumarioSb.append("Celular: ").append(editTextCell.getText().toString()).append("\n");
        }

        sumarioSb.append("Sexo: ");
        switch (radioGroupSexo.getCheckedRadioButtonId()) {
            case R.id.radioButtonMasc:
                sumarioSb.append("masculino");
                break;
            case R.id.radioButtonFem:
                sumarioSb.append("feminino");
                break;
            case R.id.radioButtonOutro:
                sumarioSb.append("outro");
                break;
        }
        sumarioSb.append("\n");

        sumarioSb.append("Data de Nascimento: ").append(editTextDate.getText().toString()).append("\n");

        sumarioSb.append("Formacao: ").append(((TextView) spinnerFormacao.getSelectedView()).getText()).append("\n");

        if (spinnerFormacao.getSelectedItemPosition() == 0 || spinnerFormacao.getSelectedItemPosition() == 1){
            sumarioSb.append("Ano de Formatura: ").append(editTextAnoFormatura.getText().toString()).append("\n");
        }else if (spinnerFormacao.getSelectedItemPosition() == 2 || spinnerFormacao.getSelectedItemPosition() == 3){
            sumarioSb.append("Ano de Conclusão: ").append(editTextAnoConclusao.getText().toString()).append("\n");
            sumarioSb.append("Instituição: ").append(editTextInstituicao.getText().toString()).append("\n");
        }else if (spinnerFormacao.getSelectedItemPosition() == 4 || spinnerFormacao.getSelectedItemPosition() == 5){
            sumarioSb.append("Ano de Conclusão: ").append(editTextAnoConclusao.getText().toString()).append("\n");
            sumarioSb.append("Instituição: ").append(editTextInstituicao.getText().toString()).append("\n");
            sumarioSb.append("Título da Monografia: ").append(editTextMonografia.getText().toString()).append("\n");
            sumarioSb.append("Orientador: ").append(editTextOrientador.getText().toString()).append("\n");
        }

        sumarioSb.append("Vagas de interesse: ").append(editTextVagasInteresse.getText().toString()).append("\n");

        Toast.makeText(this, sumarioSb.toString(), Toast.LENGTH_SHORT).show();
    }

    private void cleanForm() {
        editTextTextPersonName.setText("");
        editTextTextEmailAddress.setText("");
        editTextPhone.setText("");
        editTextAnoFormatura.setText("");
        editTextAnoConclusao.setText("");
        editTextMonografia.setText("");
        editTextOrientador.setText("");
        editTextInstituicao.setText("");
        editTextCell.setText("");
        editTextDate.setText("");
        editTextVagasInteresse.setText("");
        radioButtonMasc.setChecked(true);
        spinnerFormacao.setSelection(0);
        checkBoxAttsEmail.setChecked(false);
        checkBoxTelComercial.setChecked(false);
        switchCell.setChecked(false);
    }

    private void bindViews() {
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextAnoFormatura = findViewById(R.id.editTextAnoFormatura);
        editTextAnoConclusao = findViewById(R.id.editTextAnoConclusao);
        editTextInstituicao = findViewById(R.id.editTextInstituicao);
        editTextMonografia = findViewById(R.id.editTextMonografia);
        editTextOrientador = findViewById(R.id.editTextOrientador);
        editTextCell = findViewById(R.id.editTextCell);
        editTextDate = findViewById(R.id.editTextDate);
        editTextVagasInteresse = findViewById(R.id.editTextVagasInteresse);
        checkBoxAttsEmail = findViewById(R.id.checkBoxAttsEmail);
        checkBoxTelComercial = findViewById(R.id.checkBoxTelComercial);
        radioGroupSexo = findViewById(R.id.radioGroupSexo);
        switchCell = findViewById(R.id.switchCell);
        spinnerFormacao = findViewById(R.id.spinnerFormacao);
        radioButtonMasc = findViewById(R.id.radioButtonMasc);
        radioButtonOutro = findViewById(R.id.radioButtonOutro);
        radioButtonFem = findViewById(R.id.radioButtonFem);
        buttonSalvar = findViewById(R.id.buttonSalvar);
        buttonLimpar = findViewById(R.id.buttonLimpar);
    }

}