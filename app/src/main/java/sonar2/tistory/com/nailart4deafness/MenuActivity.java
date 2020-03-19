package sonar2.tistory.com.nailart4deafness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onClickLater(View view) {
        Intent intent = new Intent(this, LaterActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickBasic(View view) {
        Intent intent = new Intent(this, Proc1Activity.class);
        intent.putExtra("care", getString(R.string.basiccare));
        startActivityForResult(intent,0);
    }

    public void onClickColor(View view) {
        Intent intent = new Intent(this, Proc1Activity.class);
        intent.putExtra("care", getString(R.string.nailcare));
        startActivityForResult(intent,0);
    }

    public void onClickGel(View view) {
        Intent intent = new Intent(this, Proc1Activity.class);
        intent.putExtra("care", getString(R.string.gelnail));
        startActivityForResult(intent,0);
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
