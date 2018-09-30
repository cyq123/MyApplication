package cyq.com.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 *  淡入淡出动画
 */
public class CrossfadeActivity extends AppCompatActivity {
    private View mContentView;
    private View mLoadingView;
    private int animTime = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crossfade);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "这是一个淡入淡出视图", Snackbar.LENGTH_LONG)
                        .setAction("try again", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mLoadingView.setVisibility(View.VISIBLE);
                                mLoadingView.setAlpha(1f);
                                crossfade();
                            }
                        }).show();
            }
        });

        mContentView = findViewById(R.id.content);
        mLoadingView = findViewById(R.id.loading_spinner);
        mContentView.setVisibility(View.GONE);

        crossfade();
    }

    /**
     * 淡入淡出动画
     */
    private void crossfade(){

        //1.内容view设置为可见，但是完全透明。
        mContentView.setAlpha(0);
        mContentView.setVisibility(View.VISIBLE);
        //2.内容view设置百分之百不透明，并clear所有的动画监听。
        mContentView.animate().alpha(1f).setDuration(animTime).setListener(null);
        //3.进度条view设置为完全透明，并在动画结束时设置view.gone
        mLoadingView.animate().alpha(0f).setDuration(animTime).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mLoadingView.setVisibility(View.GONE);
            }
        });
    }
}
