package cyq.com.myapplication;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * https://shapeshifter.design/  这是一个生成svg图片的工具
 * 这个类实现了svg动画
 */
public class AnimaterVectorDrawableActivity extends AppCompatActivity {
    private ImageView imageView,anmator_img;
    private Drawable drawable,smile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animater_vector_drawable);

        imageView = findViewById(R.id.imageView);
        anmator_img = findViewById(R.id.animate_img);

        /**
         * 三角形旋转动画
         */
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              drawable =  imageView.getBackground();
              if (drawable instanceof Animatable){
                  ((Animatable) drawable).start();
              }
            }
        });


        anmator_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smile =  anmator_img.getBackground();
                if (smile instanceof Animatable){
                    handler.post(runnable);
                }
            }
        });

    }
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            ((Animatable) smile).start();
            handler.postDelayed(this,2000);
        }
    };

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(smile!=null){
            ((Animatable) smile).stop();
        }

    }
}
