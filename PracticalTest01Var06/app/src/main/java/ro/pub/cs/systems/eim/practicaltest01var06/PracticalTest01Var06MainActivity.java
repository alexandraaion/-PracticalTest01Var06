package ro.pub.cs.systems.eim.practicaltest01var06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class PracticalTest01Var06MainActivity extends AppCompatActivity {

    EditText firstNumber;
    EditText secondNumber;
    EditText thirdNumber;

    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;

    Button playButton;
    int nrCheckboxes = 0;
    int scor = 0;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            switch(view.getId()) {
                case R.id.play_button:
                    if (!checkBox1.isChecked()) {
                        Random random = new Random();
                        firstNumber.setText(Constants.numbers.get(random.nextInt(Constants.numbers.size())));
                    } else {
                        nrCheckboxes++;
                    }
                    if (!checkBox2.isChecked()) {
                        Random random = new Random();
                        secondNumber.setText(Constants.numbers.get(random.nextInt(Constants.numbers.size())));
                    } else {
                        nrCheckboxes++;
                    }
                    if (!checkBox3.isChecked()) {
                        Random random = new Random();
                        thirdNumber.setText(Constants.numbers.get(random.nextInt(Constants.numbers.size())));
                    } else {
                        nrCheckboxes++;
                    }
                    Context context = getApplicationContext();
                    String text = "Numbers: " + firstNumber.getText() + " " + secondNumber.getText() + " " + thirdNumber.getText();

                    Toast.makeText(context, text, Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var06SecondaryActivity.class);
                    intent.putExtra(Constants.NUMBER1, Integer.parseInt(firstNumber.getText().toString()));
                    intent.putExtra(Constants.NUMBER2, Integer.parseInt(secondNumber.getText().toString()));
                    intent.putExtra(Constants.NUMBER3, Integer.parseInt(thirdNumber.getText().toString()));
                    intent.putExtra(Constants.CHECKBOX, nrCheckboxes);
                    startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var06_main);

        firstNumber = (EditText)findViewById(R.id.first_number);
        secondNumber = (EditText)findViewById(R.id.second_number);
        thirdNumber = (EditText)findViewById(R.id.third_number);

        checkBox1 = (CheckBox)findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox)findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox)findViewById(R.id.checkBox3);

        playButton = (Button)findViewById(R.id.play_button);
        playButton.setOnClickListener(buttonClickListener);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(Constants.SCOR)) {
                scor = savedInstanceState.getInt(Constants.SCOR);
            } else {
                scor = 0;
            }
        } else {
            scor = 0;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(Constants.SCOR, scor);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(Constants.SCOR)) {
            scor = savedInstanceState.getInt(Constants.SCOR);
        } else {
            scor = 0;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
            scor += resultCode;
        }
    }

}