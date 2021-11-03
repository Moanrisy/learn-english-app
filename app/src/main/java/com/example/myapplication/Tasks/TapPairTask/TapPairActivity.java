package com.example.myapplication.Tasks.TapPairTask;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.myapplication.Data.Repository;
import com.example.myapplication.Model.PairModel;
import com.example.myapplication.R;
import com.example.myapplication.Tasks.CustomWord;
import com.example.myapplication.Utils.ActivityNavigation;
import com.example.myapplication.Utils.Injection;
import com.nex3z.flowlayout.FlowLayout;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TapPairActivity extends AppCompatActivity {

    private static final String TAG = "TapPairActivity";

    @BindView(R.id.check_button)
    Button checkButton;

    @BindView(R.id.flow_layout)
    FlowLayout flowLayout;

    @BindView(R.id.task_progress_bar)
    ProgressBar progressBar;

    ArrayList<PairModel> pairs;
    ArrayList<CustomWord> compareWords = new ArrayList<>();

    boolean searchingPair = false;

    CustomWord customWord;

    int progressBarValue;
    int pairsFormed;
    int randomN;

    Context context = TapPairActivity.this;

    Random random = new Random();

    Repository repository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap_pair);

        ButterKnife.bind(this);

        initData();
    }

    private void initData() {

        Intent intent = getIntent();
        Toast.makeText(context, intent.getStringExtra("chapter"), Toast.LENGTH_SHORT).show();

        checkButton.setEnabled(false);

        repository = Injection.provideRepository();

        pairs = repository.getPairs();

        Hawk.init(this).build();

        progressBarValue = 0;

        if (Hawk.get("progressBarValue") != null) {

            progressBarValue = Hawk.get("progressBarValue");

            progressBar.setProgress(progressBarValue);
        }

        randomizePair();

        checkButtonListener();
    }

    private void randomizePair() {

        int randomIndex1;
        int randomIndex2;

        //We can use map here as well but it was hard to make it work as I wanted

        Collections.shuffle(pairs);

        randomN = random.nextInt(3) + 4;

        for (int i = 0; i < randomN; i++) {

            PairModel pair = pairs.get(i);

            String pair1 = pair.getPair1();
            String pair2 = pair.getPair2();

            CustomWord customWord1 = new CustomWord(this, pair1);
            CustomWord customWord2 = new CustomWord(this, pair2);

            customWord1.setTag(i);
            customWord2.setTag(i);

            customWord1.setOnTouchListener(new TouchListener());
            customWord2.setOnTouchListener(new TouchListener());

            if (flowLayout.getChildCount() != 0) {

                randomIndex1 = random.nextInt(flowLayout.getChildCount());
                randomIndex2 = random.nextInt(flowLayout.getChildCount());

                flowLayout.addView(customWord1, randomIndex1);
                flowLayout.addView(customWord2, randomIndex2);

            } else {

                flowLayout.addView(customWord1);
                flowLayout.addView(customWord2);
            }
        }
    }

    private class TouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            customWord = (CustomWord) view;

            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                if (!isSearchingPair()) {

                    compareWords.add(customWord);

                    view.setBackground(getDrawable(R.drawable.custom_word_selected));

                    setSearchingPair(true);

                } else {

                    CustomWord previousWord = compareWords.get(0);

                    if (previousWord != view) {

                        if (previousWord.getTag() == view.getTag()) {

                            Toast.makeText(TapPairActivity.this, "Correct Pair", Toast.LENGTH_SHORT).show();

                            previousWord.setEnabled(false);
                            view.setEnabled(false);

                            previousWord.setBackground(getDrawable(R.drawable.custom_word_border));
                            previousWord.setTextColor(getResources().getColor(R.color.grey_button));

                            view.setBackground(getDrawable(R.drawable.custom_word_border));
                            customWord.setTextColor(getResources().getColor(R.color.grey_button));

                            setSearchingPair(false);

                            compareWords.clear();

                            checkCompleteness();

                        } else {

                            Toast.makeText(TapPairActivity.this, "Wrong Pair", Toast.LENGTH_SHORT).show();

                            previousWord.setBackground(getDrawable(R.drawable.custom_word_border));
                            view.setBackground(getDrawable(R.drawable.custom_word_border));

                            setSearchingPair(false);

                            compareWords.clear();
                        }

                    } else {

                        previousWord.setBackground(getDrawable(R.drawable.custom_word_border));
                        view.setBackground(getDrawable(R.drawable.custom_word_border));

                        setSearchingPair(false);

                        compareWords.clear();
                    }
                }

                return true;
            }

            return false;
        }
    }

    private void checkButtonListener() {

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (progressBarValue < 10) {
//                if (progressBarValue < 100) {

                    ActivityNavigation.getInstance(context).takeToRandomTask();

                } else {

                    progressBarValue = 0;

                    ActivityNavigation.getInstance(context).lessonCompleted();

                    Hawk.put("progressBarValue", progressBarValue);
                }
            }
        });
    }

    private void checkCompleteness() {

        pairsFormed ++;

        if (pairsFormed == randomN) {

            Toast.makeText(TapPairActivity.this, "Congratulations, you found all pairs", Toast.LENGTH_SHORT).show();

            progressBarValue += 10;

            progressBar.setProgress(progressBarValue);

            Hawk.put("progressBarValue", progressBarValue);

            checkButton.setEnabled(true);
            checkButton.setText("continue");
            checkButton.setTextColor(getResources().getColor(R.color.button_task_continue));
            checkButton.setBackground(getDrawable(R.drawable.button_task_continue));
        }
    }

    public boolean isSearchingPair() {
        return searchingPair;
    }

    public void setSearchingPair(boolean searchingPair) {
        this.searchingPair = searchingPair;
    }

    @Override
    public void onBackPressed() {

        new MaterialDialog.Builder(this)
                .title("Are you sure about that?")
                .content("All progress in this lesson will be lost.")
                .positiveText("QUIT")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                        progressBarValue = 0;

                        Hawk.put("progressBarValue", progressBarValue);

                        finish();
                    }
                })
                .negativeText("CANCEL")
                .show();
    }

    @Override
    protected void onStop() {

        progressBarValue = 0;

        Hawk.put("progressBarValue", progressBarValue);

        finish();

        super.onStop();
    }
}
