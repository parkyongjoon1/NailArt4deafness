package sonar2.tistory.com.nailart4deafness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Step2Activity extends AppCompatActivity {

    String caretype ="";
    String shape="";
    int  drawable=0;
    int[] f_cot =new int[10];
    int cot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step2);

        Intent intent = getIntent();
        caretype = intent.getStringExtra("care");
        shape = intent.getStringExtra("shape");

        ImageView imageView1 = findViewById(R.id.full);
        ImageView imageView2 = findViewById(R.id.half);
        ImageView imageView3 = findViewById(R.id.french);
        ImageView imageView4 = findViewById(R.id.roundc);

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
                imageView1.setBackgroundResource(R.drawable.finger_round);
                imageView2.setBackgroundResource(R.drawable.finger_round);
                imageView3.setBackgroundResource(R.drawable.finger_round);
                imageView4.setBackgroundResource(R.drawable.finger_round);
                for(int i=0;i<10;i++) {
                    imageViewFinger[i].setBackgroundResource(R.drawable.finger_round);
                    imageViewFinger[i].setImageResource(R.drawable.finger_round);
                }
                imageView1.setImageResource(R.drawable.nail_round_full);
                imageView2.setImageResource(R.drawable.nail_round_half);
                imageView3.setImageResource(R.drawable.nail_round_french);
                imageView4.setImageResource(R.drawable.nail_round_roundc);
                break;
            case "oval":
                imageView1.setBackgroundResource(R.drawable.finger_oval);
                imageView2.setBackgroundResource(R.drawable.finger_oval);
                imageView3.setBackgroundResource(R.drawable.finger_oval);
                imageView4.setBackgroundResource(R.drawable.finger_oval);
                for(int i=0;i<10;i++) {
                    imageViewFinger[i].setBackgroundResource(R.drawable.finger_oval);
                    imageViewFinger[i].setImageResource(R.drawable.finger_oval);
                }
                imageView1.setImageResource(R.drawable.nail_oval_full);
                imageView2.setImageResource(R.drawable.nail_oval_half);
                imageView3.setImageResource(R.drawable.nail_oval_french);
                imageView4.setImageResource(R.drawable.nail_oval_roundc);
                break;
            case "square":
                imageView1.setBackgroundResource(R.drawable.finger_square);
                imageView2.setBackgroundResource(R.drawable.finger_square);
                imageView3.setBackgroundResource(R.drawable.finger_square);
                imageView4.setBackgroundResource(R.drawable.finger_square);
                for(int i=0;i<10;i++) {
                    imageViewFinger[i].setBackgroundResource(R.drawable.finger_square);
                    imageViewFinger[i].setImageResource(R.drawable.finger_square);
                }
                imageView1.setImageResource(R.drawable.nail_square_full);
                imageView2.setImageResource(R.drawable.nail_square_half);
                imageView3.setImageResource(R.drawable.nail_square_french);
                imageView4.setImageResource(R.drawable.nail_square_roundc);
                break;
        }
        //Toast.makeText(this,data + ":"+ shape,Toast.LENGTH_SHORT).show();
    }

    public void onClick(View view) {
        ImageView imageView1 = findViewById(R.id.full);
        ImageView imageView2 = findViewById(R.id.half);
        ImageView imageView3 = findViewById(R.id.french);
        ImageView imageView4 = findViewById(R.id.roundc);

        switch(view.getId()) {
            case R.id.full:
                imageView1.setColorFilter(0xff878787, PorterDuff.Mode.MULTIPLY);
                imageView2.clearColorFilter();
                imageView3.clearColorFilter();
                imageView4.clearColorFilter();
                if(shape.equals("round")) drawable = R.drawable.nail_round_full;
                if(shape.equals("square")) drawable = R.drawable.nail_square_full;
                if(shape.equals("oval")) drawable = R.drawable.nail_oval_full;
                break;
            case R.id.half:
                imageView1.clearColorFilter();
                imageView2.setColorFilter(0xff878787, PorterDuff.Mode.MULTIPLY);
                imageView3.clearColorFilter();
                imageView4.clearColorFilter();
                if(shape.equals("round")) drawable = R.drawable.nail_round_half;
                if(shape.equals("square")) drawable = R.drawable.nail_square_half;
                if(shape.equals("oval")) drawable = R.drawable.nail_oval_half;
                break;
            case R.id.french:
                imageView1.clearColorFilter();
                imageView2.clearColorFilter();
                imageView3.setColorFilter(0xff878787, PorterDuff.Mode.MULTIPLY);
                imageView4.clearColorFilter();
                if(shape.equals("round")) drawable = R.drawable.nail_round_french;
                if(shape.equals("square")) drawable = R.drawable.nail_square_french;
                if(shape.equals("oval")) drawable = R.drawable.nail_oval_french;
                break;
            case R.id.roundc:
                imageView1.clearColorFilter();
                imageView2.clearColorFilter();
                imageView3.clearColorFilter();
                imageView4.setColorFilter(0xff878787, PorterDuff.Mode.MULTIPLY);
                if(shape.equals("round")) drawable = R.drawable.nail_round_roundc;
                if(shape.equals("square")) drawable = R.drawable.nail_square_roundc;
                if(shape.equals("oval")) drawable = R.drawable.nail_oval_roundc;
                break;
        }
        cot = drawable;
        //Toast.makeText(this,String.format("%d",drawable),Toast.LENGTH_SHORT).show();
    }

    public void onClickFinger(View view) {
        String id = view.getResources().getResourceName(view.getId());
        int fn = Character.getNumericValue(id.charAt(id.length()-1));
        ImageView imageView = findViewById(view.getId());
        imageView.setImageResource(drawable);
        imageView.setColorFilter(0xff878787, PorterDuff.Mode.MULTIPLY);
        f_cot[fn] = cot;
        //Toast.makeText(this,String.format("%d-%s", fn, cot),Toast.LENGTH_SHORT).show();
    }

    public void onClickNext(View view) {
        Intent intent = new Intent(this,Step3Activity.class);
        intent.putExtra("care", caretype);
        intent.putExtra("shape",shape);
        intent.putExtra("cot",f_cot);
        startActivityForResult(intent, 0);
    }

    public void onClickPrev(View view) {
        finish();
    }

    public void onClickHome(View view) {
        setResult(2);
        finish();
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