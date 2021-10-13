package com.example.myapplication.Data.Local;

import com.example.myapplication.Data.DataSource;
import com.example.myapplication.Model.PairModel;
import com.example.myapplication.Model.QuestionModel;

import java.util.ArrayList;
import java.util.Random;

public class QuestionDataSource implements DataSource.Local {

    private static QuestionDataSource INSTANCE;

    QuestionModel questionModel;

    ArrayList<String> question = new ArrayList<>();
    ArrayList<String> answer = new ArrayList<>();

    Random random = new Random();

    public static QuestionDataSource getInstance() {

        if (INSTANCE == null) {

            INSTANCE = new QuestionDataSource();
        }

        return INSTANCE;
    }

    public QuestionModel getRandomQuestionObj() {

        //Question
        question.add("Dia makan apel");
        question.add("Dia makan");
        question.add("Kamu seorang perempuan");
        question.add("Kamu seorang laki-laki");
        question.add("Apa yang terjadi");
        question.add("Saya seorang laki-laki");

        //Answer
        answer.add("She eats apple");
        answer.add("He eats");
        answer.add("You are a woman");
        answer.add("You are a boy");
        answer.add("What happened");
        answer.add("I am a boy");

        int randomIndex = random.nextInt(question.size());

        questionModel = new QuestionModel(
                question.get(randomIndex),
                answer.get(randomIndex));

        return questionModel;
    }

    @Override
    public ArrayList<PairModel> getPairs() {

        ArrayList<PairModel> pairs = new ArrayList<>();

        pairs.add(new PairModel("apel", "apple"));
        pairs.add(new PairModel("susu", "milk"));
        pairs.add(new PairModel("bread", "roti"));
        pairs.add(new PairModel("boy", "laki-laki"));
        pairs.add(new PairModel("dia", "she"));
        pairs.add(new PairModel("a", "sebuah"));
        pairs.add(new PairModel("he", "dia"));
        pairs.add(new PairModel("girl", "perempuan"));
        pairs.add(new PairModel("itu", "the"));
        pairs.add(new PairModel("kamu", "you"));
        pairs.add(new PairModel("minum", "drink"));
        pairs.add(new PairModel("air", "water"));

        return pairs;
    }

    @Override
    public ArrayList<String> getAnswer() {
        return answer;
    }
}
