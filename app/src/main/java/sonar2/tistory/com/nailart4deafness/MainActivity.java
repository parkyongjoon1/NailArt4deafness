package sonar2.tistory.com.nailart4deafness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backPressCloseHandler = new BackPressCloseHandler(this);
    }

    public void onClickOk(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivityForResult(intent, 0);
    }

    public void onClickLater(View view) {
        Intent intent = new Intent(this, LaterActivity.class);
        //Intent intent = new Intent(this, Step5Activity.class);
        startActivity(intent);
    }

    @Override public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed(); }
}
