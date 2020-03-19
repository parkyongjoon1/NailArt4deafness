package sonar2.tistory.com.nailart4deafness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Step1Activity extends AppCompatActivity {

    String caretype ="";
    String shape="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step1);

        Intent intent = getIntent();
        caretype = intent.getStringExtra("care");
    }

    public void onClick(View view) {
        //Toast.makeText(this, ((String) view.getId()),Toast.LENGTH_SHORT).show();
        ImageView imageView1 = findViewById(R.id.round);
        ImageView imageView2 = findViewById(R.id.oval);
        ImageView imageView3 = findViewById(R.id.square);
        //imageView1.setColorFilter(0xff878787, PorterDuff.Mode.MULTIPLY);
        switch (view.getId()) {
            case R.id.round:
                shape="round";
                imageView1.setColorFilter(0xff878787, PorterDuff.Mode.MULTIPLY);
                imageView2.clearColorFilter();
                imageView3.clearColorFilter();
                break;
            case R.id.oval:
                shape="oval";
                imageView1.clearColorFilter();
                imageView2.setColorFilter(0xff878787, PorterDuff.Mode.MULTIPLY);
                imageView3.clearColorFilter();
                break;
            case R.id.square:
                shape="square";
                imageView1.clearColorFilter();
                imageView2.clearColorFilter();
                imageView3.setColorFilter(0xff878787, PorterDuff.Mode.MULTIPLY);
                break;
        }
        //Toast.makeText(this, String.format("%d",shape), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this,Step2Activity.class);
        intent.putExtra("care", caretype);
        intent.putExtra("shape",shape);
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
