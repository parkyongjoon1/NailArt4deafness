package sonar2.tistory.com.nailart4deafness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Step3Activity extends AppCompatActivity {
    String caretype="";
    int[] f_cot = new int[10];
    int[] f_colour = new int[10];
    String shape;
    int selectedcolor=0;
    int numofcolor = 40; //손가락에 칠할 칼라의 갯수 xml파일과 일치해야 함.
    int[] selcolour ={
            0xff995e3e, 	0xff725449, 	0xff1c0c0c, 	0xff2a1115, 	0xff46241a, 	0xff9d7c6d, 	0xff55384c, 	0xff423733, 	0xff927f54, 	0xff907653,
            0xff2f2121, 	0xff8f8b80, 	0xff1c0c0c, 	0xffb5b0b4, 	0xffbcbaad, 	0xff0f0d0e, 	0xff272b37, 	0xff353c46, 	0xff6b6a66, 	0xffe7e7e7,
            0xff732636, 	0xff6f383b, 	0xffb69896, 	0xffc8b6a2, 	0xffcf1d2b, 	0xffcf1b62, 	0xffe9338a, 	0xffdb74b9, 	0xffc4959f, 	0xffe0a4c0,
            0xff080e48, 	0xff970707, 	0xff621c1c, 	0xff380808, 	0xff25181f, 	0xff390f10, 	0xff770505, 	0xffc92629, 	0xffbc181f, 	0xffd40b1e,
            0xff583b71, 	0xff471d29, 	0xff21152d, 	0xff321436, 	0xff34163c, 	0xff5b2698, 	0xff883771, 	0xff7f4184, 	0xff9644b3, 	0xff9f76b0,
            0xff4f1f08, 	0xff776559, 	0xffc8ad78, 	0xffba9b31, 	0xffcfc15e, 	0xff9d800b, 	0xffa18507, 	0xffc0b002, 	0xffbeb325, 	0xffc5bd75,
            0xff803c27, 	0xffad8b7f, 	0xffbba18a, 	0xffdc270a, 	0xffe93e2c, 	0xffd04e3e, 	0xffd9726b, 	0xffe1a492, 	0xffd3a359, 	0xffdfba9f,
            0xff73aa8d, 	0xff41888e, 	0xff1f80a0, 	0xff007467, 	0xff326584, 	0xff222953, 	0xff979ca0, 	0xff112b82, 	0xff0952b0, 	0xff377eb2,
    };
    String[] seltext ={
            "995e3e", 	"725449", 	"1c0c0c", 	"2a1115", 	"46241a", 	"9d7c6d", 	"55384c", 	"423733", 	"927f54", 	"907653",
            "2f2121", 	"8f8b80", 	"1c0c0c", 	"b5b0b4", 	"bcbaad", 	"0f0d0e", 	"272b37", 	"353c46", 	"6b6a66", 	"e7e7e7",
            "732636", 	"6f383b", 	"b69896", 	"c8b6a2", 	"cf1d2b", 	"cf1b62", 	"e9338a", 	"db74b9", 	"c4959f", 	"e0a4c0",
            "080e48", 	"970707", 	"621c1c", 	"380808", 	"25181f", 	"390f10", 	"770505", 	"c92629", 	"bc181f", 	"d40b1e",
            "583b71", 	"471d29", 	"21152d", 	"321436", 	"34163c", 	"5b2698", 	"883771", 	"7f4184", 	"9644b3", 	"9f76b0",
            "4f1f08", 	"776559", 	"c8ad78", 	"ba9b31", 	"cfc15e", 	"9d800b", 	"a18507", 	"c0b002", 	"beb325", 	"c5bd75",
            "803c27", 	"ad8b7f", 	"bba18a", 	"dc270a", 	"e93e2c", 	"d04e3e", 	"d9726b", 	"e1a492", 	"d3a359", 	"dfba9f",
            "73aa8d", 	"41888e", 	"1f80a0", 	"007467", 	"326584", 	"222953", 	"979ca0", 	"112b82", 	"0952b0", 	"377eb2",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step3);

        Intent intent = getIntent();
        f_cot = intent.getIntArrayExtra("cot");
        shape = intent.getStringExtra("shape");
        caretype = intent.getStringExtra("care");


        ImageView[] imageViewFinger ={
                findViewById(R.id.finger_0),
                findViewById(R.id.finger_1),
                findViewById(R.id.finger_2),
                findViewById(R.id.finger_3),
                findViewById(R.id.finger_4),
                findViewById(R.id.finger_5),
                findViewById(R.id.finger_6),
                findViewById(R.id.finger_7),
                findViewById(R.id.finger_8),
                findViewById(R.id.finger_9),
        };

        switch(shape) {
            case "round":
                for(int i=0;i<10;i++) {
                    imageViewFinger[i].setBackgroundResource(R.drawable.finger_round);
                }
                break;
            case "oval":
                for(int i=0;i<10;i++) {
                    imageViewFinger[i].setBackgroundResource(R.drawable.finger_oval);
                }
                break;
            case "square":
                for(int i=0;i<10;i++) {
                    imageViewFinger[i].setBackgroundResource(R.drawable.finger_square);
                }
                break;
        }

        for(int i=0;i<10;i++) {
            imageViewFinger[i].setImageResource(f_cot[i]);
        }

        for(int i=0; i<numofcolor; i++) {
            ImageView imageView;
            TextView textView;
            int rid;
            rid = getResources().getIdentifier("color_" + i, "id",  this.getBaseContext().getPackageName());
            imageView = findViewById(rid);
            imageView.setColorFilter(selcolour[i], PorterDuff.Mode.MULTIPLY);

            rid = getResources().getIdentifier("color_text_" + i, "id",  this.getBaseContext().getPackageName());
            textView = findViewById(rid);
            textView.setText(seltext[i]);
        }
    }


    public void onClickColour(View view) {
        //ImageView imageView = findViewById(view.getId());
        selectedcolor = Integer.parseInt(view.getResources().getResourceEntryName(view.getId()).substring(6));
        //Toast.makeText(this,selcolor,Toast.LENGTH_SHORT).show();  //color_0
    }

    public void onClickFinger(View view) {
        ImageView imageView = findViewById(view.getId());
        int selfinger = Integer.parseInt(view.getResources().getResourceEntryName(view.getId()).substring(7));
        f_colour[selfinger] = selcolour[selectedcolor];
        imageView.setColorFilter(selcolour[selectedcolor], PorterDuff.Mode.MULTIPLY);
    }

    public void onClickPrev(View view) {
        finish();
    }

    public void onClickHome(View view) {
        setResult(2);
        finish();
    }

    public void onClickNext(View view) {
        Intent intent = new Intent(this,Step4Activity.class);
        intent.putExtra("care",caretype);
        intent.putExtra("shape",shape);
        intent.putExtra("cot",f_cot);
        intent.putExtra("colour",f_colour);
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