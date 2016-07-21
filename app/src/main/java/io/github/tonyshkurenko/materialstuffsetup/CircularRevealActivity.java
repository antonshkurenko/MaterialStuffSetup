package io.github.tonyshkurenko.materialstuffsetup;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;

public class CircularRevealActivity extends AppCompatActivity implements View.OnTouchListener {

  public static final int DEFAULT_DURATION = 600;
  private View mView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_circular_reveal);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    mView = findViewById(R.id.view);
    mView.setOnTouchListener(this);
  }

  // yeah, xml onclick :)
  public void reveal(View button) {
    switch (mView.getVisibility()) {
      case View.VISIBLE:
        exitReveal(mView);
        break;
      case View.INVISIBLE:
        enterReveal(mView);
        break;
      default:
        mView.setVisibility(View.VISIBLE);
        break;
    }
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP) private static void enterReveal(View v) {

    // get the center for the clipping circle
    //final int cx = v.getMeasuredWidth() / 2;
    final int cx = v.getMeasuredWidth();
    //final int cy = v.getMeasuredHeight() / 2;
    final int cy = v.getMeasuredHeight();

    // get the final radius for the clipping circle
    //final int finalRadius = Math.max(v.getWidth(), v.getHeight()) / 2;
    final int finalRadius = (int) Math.sqrt(Math.pow(v.getWidth(), 2) + Math.pow(v.getHeight(), 2));

    // create the animator for this view (the start radius is zero)
    final Animator anim = ViewAnimationUtils.createCircularReveal(v, cx, cy, 0, finalRadius);

    anim.setDuration(DEFAULT_DURATION);

    // make the view visible and start the animation
    v.setVisibility(View.VISIBLE);
    anim.start();
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP) private static void exitReveal(final View v) {

    // get the center for the clipping circle
    //final int cx = v.getMeasuredWidth() / 2;
    final int cx = 0;
    //final int cy = v.getMeasuredHeight() / 2;
    final int cy = 0;

    //final int initialRadius = Math.max(v.getWidth(), v.getHeight()) / 2;
    final int initialRadius =
        (int) Math.sqrt(Math.pow(v.getWidth(), 2) + Math.pow(v.getHeight(), 2));

    // create the animation (the final radius is zero)
    final Animator anim = ViewAnimationUtils.createCircularReveal(v, cx, cy, initialRadius, 0);

    anim.setDuration(DEFAULT_DURATION);

    // make the view invisible when the animation is done
    anim.addListener(new AnimatorListenerAdapter() {
      @Override public void onAnimationEnd(Animator animation) {
        super.onAnimationEnd(animation);
        v.setVisibility(View.INVISIBLE);
      }
    });

    // start the animation
    anim.start();
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP) @Override
  public boolean onTouch(final View v, MotionEvent event) {
    switch (v.getId()) {
      case R.id.view:
        if (event.getAction() == MotionEvent.ACTION_UP
            || event.getAction() == MotionEvent.ACTION_CANCEL) {
          final int initialRadius =
              (int) Math.sqrt(Math.pow(v.getWidth(), 2) + Math.pow(v.getHeight(), 2));

          // create the animation (the final radius is zero)
          final Animator anim =
              ViewAnimationUtils.createCircularReveal(v, ((int) event.getX()), ((int) event.getY()),
                  initialRadius, 0);

          anim.setDuration(DEFAULT_DURATION);

          // make the view invisible when the animation is done
          anim.addListener(new AnimatorListenerAdapter() {
            @Override public void onAnimationEnd(Animator animation) {
              super.onAnimationEnd(animation);
              v.setVisibility(View.INVISIBLE);
            }
          });

          // start the animation
          anim.start();
        }
        break;
    }
    return true;
  }
}
