package com.example.myapplication.Tasks.TranslateSentenceTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Utils.ActivityNavigation;
import com.example.myapplication.Utils.Injection;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.myapplication.Data.Repository;
import com.example.myapplication.Model.QuestionModel;
import com.example.myapplication.R;
import com.orhanobut.hawk.Hawk;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TSTaskActivity extends AppCompatActivity{

    @BindView(R.id.question)
    TextView tvQuestion;

    @BindView(R.id.check_button)
    Button checkButton;

    @BindView(R.id.user_answer)
    EditText etUserAnswer;

    @BindView(R.id.task_progress_bar)
    ProgressBar progressBar;

    QuestionModel questionModel;

    int progressBarValue;

    Repository repository;

    Context context = TSTaskActivity.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tstask);

        ButterKnife.bind(this);

        initData();
    }

    private void initData() {

        checkButton.setEnabled(false);

        repository = Injection.provideRepository();

        questionModel = repository.getRandomQuestionObj("11");

        progressBarValue = 0;

        Hawk.init(this).build();

        if (Hawk.get("progressBarValue") != null) {

            progressBarValue = Hawk.get("progressBarValue");

            progressBar.setProgress(progressBarValue);
        }

        tvQuestion.setText(questionModel.getQuestion());

        checkAnswer();
        enableButton();
    }

    private void checkAnswer() {

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userAnswer = etUserAnswer.getText().toString();

                if (checkButton.getText().equals("check")) {

                    if (questionModel.getAnswer().toLowerCase().equals(userAnswer.toLowerCase())) {

                        Toast.makeText(context, "You Are Correct!", Toast.LENGTH_SHORT).show();

                        progressBarValue += 10;

                        progressBar.setProgress(progressBarValue);

                        Hawk.put("progressBarValue", progressBarValue);

                        lockEditText();

                    } else {

                        Toast.makeText(context, "That's not correct!" + questionModel.getAnswer(), Toast.LENGTH_SHORT).show();

                        if (progressBarValue > 10) {

                            progressBarValue -= 10;

                        } else {

                            progressBarValue = 0;
                        }

                        progressBar.setProgress(progressBarValue);

                        Hawk.put("progressBarValue", progressBarValue);

                        lockEditText();
                    }

                    checkButton.setText("continue");
                    checkButton.setTextColor(getResources().getColor(R.color.button_task_continue));
                    checkButton.setBackground(getDrawable(R.drawable.button_task_continue));

                } else if (checkButton.getText().equals("continue")) {

                    if (progressBarValue < 100) {

                        ActivityNavigation.getInstance(context).takeToRandomTask();

                    } else {

                        progressBarValue = 0;

                        Hawk.put("progressBarValue", progressBarValue);
                    }

                }
            }
        });
    }

    private void lockEditText() {

        etUserAnswer.setEnabled(false);
    }

    private void enableButton() {

        etUserAnswer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (i2 > 0) {

                    checkButton.setBackground(getDrawable(R.drawable.button_task_continue));
                    checkButton.setTextColor(getResources().getColor(R.color.button_task_continue));

                    checkButton.setEnabled(true);

                } else {

                    checkButton.setBackground(getDrawable(R.drawable.button_task_check));
                    checkButton.setTextColor(getResources().getColor(R.color.button_task_check));

                    checkButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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
