package sonar2.tistory.com.nailart4deafness;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import static java.lang.String.*;

public class Step4Activity extends AppCompatActivity {
    String caretype="";
    int[] f_cot = new int[10];
    int[] f_colour = new int[10];
    String shape;
    LinearLayout painLinearLayout;
    CountDownTimer countDownTimer=null;
    Timer repeatTimer;
    Vibrator vib;
    private CameraManager mCameraManager;
    private boolean isCamera;
    private  String mCameraID;
    private boolean toggleFlash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step4);

        painLinearLayout = findViewById(R.id.pain);

        Intent intent = getIntent();
        f_colour = intent.getIntArrayExtra("colour");
        f_cot = intent.getIntArrayExtra("cot");
        shape = intent.getStringExtra("shape");
        caretype = intent.getStringExtra("care");

        vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        isCamera = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        if(isCamera) {
            mCameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        }

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
            if(f_cot[i]==0) continue;
            imageViewFinger[i].setImageResource(f_cot[i]);
            imageViewFinger[i].setColorFilter(f_colour[i], PorterDuff.Mode.MULTIPLY);
        }
    }


    protected   void  startTimer() {
        final TextView rtimeTextView = findViewById(R.id.remain_time);
        int remainMin = 0;
        if(caretype.equals(getString(R.string.basiccare))) {
            remainMin=20;
        }
        if(caretype.equals(getString(R.string.nailcare))) {
            remainMin=25;
        }
        if(caretype.equals(getString(R.string.gelnail))) {
            remainMin=25;
        }

        if(countDownTimer == null) {
            countDownTimer = new CountDownTimer((remainMin * 60 + 1) * 1000, 1000) {
                @SuppressLint("DefaultLocale")
                public void onTick(long m) {
                    rtimeTextView.setText(format(getString(R.string.remaintime) , m / 60000 , m % 60000 / 1000));
                }

                public void onFinish() {
                    rtimeTextView.setText(R.string.Timeout);
                }
            };
        }
        countDownTimer.start();
    }


    public void onClickPrev(View view) {
        finish();
    }

    public void onClickHome(View view) {
        setResult(2);
        finish();
    }

    public void onClickNext(View view) {
        Intent intent = new Intent(this, Step5Activity.class);
        startActivityForResult(intent, 0);
    }

    public void onClickPain(View view) {
        painLinearLayout.setVisibility(View.VISIBLE);
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(300);
        anim.setRepeatCount(Animation.INFINITE);
        painLinearLayout.startAnimation(anim);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            repeatTimer = new Timer();
            repeatTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    toggleFlash = !toggleFlash;
                    flashLight(toggleFlash);
                }
            }, 0, 100);
        }

        long[] pattern = {0,100,100,100,150,100,100,100,150,400,200};
        vib.vibrate(pattern,0);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    void flashLight(boolean flashOn) {
        if(isCamera && (mCameraID==null)) {
            try {
                for(String id : mCameraManager.getCameraIdList()) {
                    CameraCharacteristics c = mCameraManager.getCameraCharacteristics(id);
                    Boolean flashAvailable = c.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
                    Integer lensFacing = c.get(CameraCharacteristics.LENS_FACING);
                    if(flashAvailable != null && flashAvailable && lensFacing != null && lensFacing == CameraCharacteristics.LENS_FACING_BACK) {
                        mCameraID = id;
                        break;
                    }
                }
            } catch (CameraAccessException e) {
                mCameraID = null;
                e.printStackTrace();
                return;
            }
        }

        try {
            mCameraManager.setTorchMode(mCameraID, flashOn);
        } catch(CameraAccessException e) {
            e.printStackTrace();
        }
    }

    public void onClickClose(View view) {
        FrameLayout frameLayout = findViewById(R.id.preview);
        frameLayout.setVisibility(View.GONE);
        startTimer();
    }

    public void onClickClosePain(View view) {
        //LinearLayout linearLayout = (LinearLayout)findViewById(R.id.pain);
        painLinearLayout.clearAnimation();
        painLinearLayout.setVisibility(View.INVISIBLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            flashLight(false);
            repeatTimer.cancel();
        }
        vib.cancel();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2) {
            setResult(2);
            finish();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            flashLight(false);
            repeatTimer.cancel();
        }
        if(vib != null) vib.cancel();
    }
}