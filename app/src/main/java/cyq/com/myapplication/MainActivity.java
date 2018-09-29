package cyq.com.myapplication;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("开始", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                va.start();
                                Toast.makeText(MainActivity.this, "点击了action", Toast.LENGTH_SHORT).show();

                            }
                        }).show();
            }
        });

        TextView textView = findViewById(R.id.textview);
        textView.setText("valueAnimator");

        imageView = findViewById(R.id.img);
        valueAnimator();
    }
    private ValueAnimator va;
    private void valueAnimator(){
        va = ValueAnimator.ofFloat(0,1720);
        va.setDuration(1000);
        va.setInterpolator(new AccelerateInterpolator());
        va.setRepeatCount(1);
        va.setRepeatMode(ValueAnimator.REVERSE);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float s = (float) valueAnimator.getAnimatedValue();
//                textView.setText(s+"");
//                imageView.setRotation(s);
//                textView.setTranslationX(s);
                imageView.setTranslationY(s);
//                textView.setTranslationZ(s);
            }
        });

        va.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.toleft) {
            Toast.makeText(this, "点击了settings", Toast.LENGTH_SHORT).show();
            ObjectAnimator.ofFloat(imageView,"translationX",900,0).setDuration(2000).start();
            return true;
        }else if (id == R.id.toright){
            ObjectAnimator.ofFloat(imageView,"translationX",0,900).setDuration(2000).start();
        }

        return super.onOptionsItemSelected(item);
    }

}
