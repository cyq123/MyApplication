package cyq.com.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

/**
 * 卡片旋转正反面动画
 */
public class CardFlipActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardflip);
        FrameLayout fl = findViewById(R.id.container);
        fl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCard();
            }
        });

        if (savedInstanceState==null){

            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container,new CardFrontFragment())
                    .commit();

        }


    }

    public static class CardFrontFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            return inflater.inflate(R.layout.fragment_card_front,container,false);
        }
    }

    public static class CardBackFragment extends Fragment{
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            return inflater.inflate(R.layout.fragment_card_back,container,false);
        }
    }

    private boolean mShowingBack=false;
    private void flipCard() {
        if (mShowingBack) {
            getFragmentManager().popBackStack();
            mShowingBack = false;
            return;
        }

        // Flip to the back.

        mShowingBack = true;

        // Create and commit a new fragment transaction that adds the fragment for
        // the back of the card, uses custom animations, and is part of the fragment
        // manager's back stack.

        getFragmentManager()
                .beginTransaction()

                // Replace the default fragment animations with animator resources
                // representing rotations when switching to the back of the card, as
                // well as animator resources representing rotations when flipping
                // back to the front (e.g. when the system Back button is pressed).
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out)

                // Replace any fragments currently in the container view with a
                // fragment representing the next page (indicated by the
                // just-incremented currentPage variable).
                .replace(R.id.container, new CardBackFragment())

                // Add this transaction to the back stack, allowing users to press
                // Back to get to the front of the card.
                .addToBackStack(null)

                // Commit the transaction.
                .commit();
    }
}
