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

import pdms5.at1.sharedjobs.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Ligando (binding) objetos com as Views
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        //Seta masculino como selecionado desde o começo
        activityMainBinding.radioButtonMasc.setChecked(true);

        activityMainBinding.switchCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Celular
                if(activityMainBinding.switchCell.isChecked()){
                    activityMainBinding.editTextCell.setVisibility(View.VISIBLE);
                }   else{
                    activityMainBinding.editTextCell.setVisibility(View.GONE);
                }
            }
        });

        // Listener de item selecionado
        activityMainBinding.spinnerFormacao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (((TextView) view).getText().equals("Fundamental") || ((TextView) view).getText().equals("Ensino Medio")){
                    //Retira os outros campos
                    activityMainBinding.editTextAnoConclusao.setVisibility(View.GONE);
                    activityMainBinding.editTextAnoFormatura.setVisibility(View.GONE);
                    activityMainBinding.editTextMonografia.setVisibility(View.GONE);
                    activityMainBinding.editTextOrientador.setVisibility(View.GONE);

                    //Ativa o campo
                    activityMainBinding.editTextAnoFormatura.setVisibility(View.VISIBLE);
                }   else if(((TextView) view).getText().equals("Graduação") || ((TextView) view).getText().equals("Especialização")){
                    //Retira os outros campos
                    activityMainBinding.editTextAnoFormatura.setVisibility(View.GONE);
                    activityMainBinding.editTextMonografia.setVisibility(View.GONE);
                    activityMainBinding.editTextOrientador.setVisibility(View.GONE);

                    //Ativa o campo
                    activityMainBinding.editTextAnoConclusao.setVisibility(View.VISIBLE);
                    activityMainBinding.editTextInstituicao.setVisibility(View.VISIBLE);
                }   else if(((TextView) view).getText().equals("Mestrado") || ((TextView) view).getText().equals("Doutorado")){
                    //Retira os outros campos
                    activityMainBinding.editTextAnoFormatura.setVisibility(View.GONE);

                    //Ativa o campo
                    activityMainBinding.editTextAnoConclusao.setVisibility(View.VISIBLE);
                    activityMainBinding.editTextInstituicao.setVisibility(View.VISIBLE);
                    activityMainBinding.editTextMonografia.setVisibility(View.VISIBLE);
                    activityMainBinding.editTextOrientador.setVisibility(View.VISIBLE);
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
        sumarioSb.append("Nome completo: ").append(activityMainBinding.editTextTextPersonName.getText().toString()).append("\n");
        sumarioSb.append("E-mail: ").append(activityMainBinding.editTextTextEmailAddress.getText().toString()).append("\n");

        if(activityMainBinding.checkBoxAttsEmail.isChecked()){
            sumarioSb.append("Receber Emails: ").append("true").append("\n");
        }   else{
            sumarioSb.append("Receber Emails: ").append("false").append("\n");
        }

        sumarioSb.append("Telefone: ").append(activityMainBinding.editTextPhone.getText().toString()).append("\n");

        if(activityMainBinding.checkBoxTelComercial.isChecked()){
            sumarioSb.append("Telefone comercial: ").append("true").append("\n");
        }   else{
            sumarioSb.append("Telefone comercial: ").append("false").append("\n");
        }

        if(activityMainBinding.switchCell.isChecked()){
            sumarioSb.append("Celular: ").append(activityMainBinding.editTextCell.getText().toString()).append("\n");
        }

        sumarioSb.append("Sexo: ");
        switch (activityMainBinding.radioGroupSexo.getCheckedRadioButtonId()) {
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

        sumarioSb.append("Data de Nascimento: ").append(activityMainBinding.editTextDate.getText().toString()).append("\n");

        sumarioSb.append("Formacao: ").append(((TextView) activityMainBinding.spinnerFormacao.getSelectedView()).getText()).append("\n");

        if (activityMainBinding.spinnerFormacao.getSelectedItemPosition() == 0 || activityMainBinding.spinnerFormacao.getSelectedItemPosition() == 1){
            sumarioSb.append("Ano de Formatura: ").append(activityMainBinding.editTextAnoFormatura.getText().toString()).append("\n");
        }else if (activityMainBinding.spinnerFormacao.getSelectedItemPosition() == 2 || activityMainBinding.spinnerFormacao.getSelectedItemPosition() == 3){
            sumarioSb.append("Ano de Conclusão: ").append(activityMainBinding.editTextAnoConclusao.getText().toString()).append("\n");
            sumarioSb.append("Instituição: ").append(activityMainBinding.editTextInstituicao.getText().toString()).append("\n");
        }else if (activityMainBinding.spinnerFormacao.getSelectedItemPosition() == 4 || activityMainBinding.spinnerFormacao.getSelectedItemPosition() == 5){
            sumarioSb.append("Ano de Conclusão: ").append(activityMainBinding.editTextAnoConclusao.getText().toString()).append("\n");
            sumarioSb.append("Instituição: ").append(activityMainBinding.editTextInstituicao.getText().toString()).append("\n");
            sumarioSb.append("Título da Monografia: ").append(activityMainBinding.editTextMonografia.getText().toString()).append("\n");
            sumarioSb.append("Orientador: ").append(activityMainBinding.editTextOrientador.getText().toString()).append("\n");
        }

        sumarioSb.append("Vagas de interesse: ").append(activityMainBinding.editTextVagasInteresse.getText().toString()).append("\n");

        Toast.makeText(this, sumarioSb.toString(), Toast.LENGTH_SHORT).show();
    }

    private void cleanForm() {
        activityMainBinding.editTextTextPersonName.setText("");
        activityMainBinding.editTextTextEmailAddress.setText("");
        activityMainBinding.editTextPhone.setText("");
        activityMainBinding.editTextAnoFormatura.setText("");
        activityMainBinding.editTextAnoConclusao.setText("");
        activityMainBinding.editTextMonografia.setText("");
        activityMainBinding.editTextOrientador.setText("");
        activityMainBinding.editTextInstituicao.setText("");
        activityMainBinding.editTextCell.setText("");
        activityMainBinding.editTextDate.setText("");
        activityMainBinding.editTextVagasInteresse.setText("");
        activityMainBinding.radioButtonMasc.setChecked(true);
        activityMainBinding.spinnerFormacao.setSelection(0);
        activityMainBinding.checkBoxAttsEmail.setChecked(false);
        activityMainBinding.checkBoxTelComercial.setChecked(false);
        activityMainBinding.switchCell.setChecked(false);
    }
}