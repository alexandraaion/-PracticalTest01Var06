package ro.pub.cs.systems.eim.practicaltest01var06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01Var06SecondaryActivity extends AppCompatActivity {

    private TextView textView;
    private Button okButton;
    int scor = 0;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ok_button:
                    setResult(scor, null);
                    break;
            }
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var06_secondary);

        textView = (TextView)findViewById(R.id.textView);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey(Constants.NUMBER1)) {
            int number1 = intent.getIntExtra(Constants.NUMBER1, -1);
            int number2 = intent.getIntExtra(Constants.NUMBER2, -1);
            int number3 = intent.getIntExtra(Constants.NUMBER3, -1);
            int checkBoxes = intent.getIntExtra(Constants.CHECKBOX, -1);

            String text = "";

            if (checkBoxes == 1) {
                textView.setText("50");
                scor = 50;
            } else if (checkBoxes == 2) {
                textView.setText("10");
                scor = 10;
            } else {
                textView.setText("100");
                scor = 100;
            }

        }

        okButton = (Button)findViewById(R.id.ok_button);
        okButton.setOnClickListener(buttonClickListener);
    }
}