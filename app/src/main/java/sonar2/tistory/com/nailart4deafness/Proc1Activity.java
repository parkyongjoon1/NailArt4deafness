package sonar2.tistory.com.nailart4deafness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Proc1Activity extends AppCompatActivity {

    String caretype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proc1);

        Intent intent = getIntent();
        caretype = intent.getStringExtra("care");
        //Toast.makeText(this, data + " 선택하셨습니다.",Toast.LENGTH_SHORT).show();

        Button button = findViewById(R.id.button_next);
        button.setText(String.format("%s 진행", caretype));
    }

    public void onClick(View view) {
        Intent intent = new Intent(this,Step1Activity.class);
        intent.putExtra("care", caretype);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2) {
            setResult(2);
            finish();
        }
    }
}