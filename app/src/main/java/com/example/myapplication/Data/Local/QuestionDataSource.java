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

    public QuestionModel getRandomQuestionObj(String chapter) {

        switch (chapter) {
            case "11":
                //Question
                question.add("Dia makan apel");
                question.add("Dia makan");


                //Answer
                answer.add("She eats apple");
                answer.add("He eats");

//                question.add("Bagaimana kabarmu?");
//                answer.add("How are you?");
//                question.add("Saya baik-baik saja, terimakasih");
//                answer.add("I am fine, thanks");
//                question.add("Siapa namamu?");
//                answer.add("What is your name?");
//                question.add("Nama saya Sasha");
//                answer.add("My name is Sasha");
//                question.add("Berapa umurmu?");
//                answer.add("How old are you?");
//                question.add("Umur saya sebelas tahun");
//                answer.add("I am eleven years old");
//                question.add("Dimana kamu belajar?");
//                answer.add("Where do you study?");
//                question.add("Saya belajar di SDN 5 Garut");
//                answer.add("I study in SDN Garut 5");
//                question.add("Dimana kamu tinggal?");
//                answer.add("Where do you live?");
//                question.add("Saya tinggal di Garut");
//                answer.add("I live in Garut");
//                question.add("Dengan siapa kamu tinggal?");
//                answer.add("Who do you live with?");
//                question.add("Saya tinggal dengan keluarga saya");
//                answer.add(" I live with my family");
//                question.add("Siapa nama ayahmu?");
//                answer.add("What is your father's name?");
//                question.add("Nama ayah saya adalah Tony");
//                answer.add("My father's name is Tony");
//                question.add("Siapa nama ibumu?");
//                answer.add("What is your mother's name?");
//                question.add("Nama ibu saya adalah Abby");
//                answer.add("My mother's name is Abby");
//                question.add("Berapa banyak saudara laki-laki yang kamu punya?");
//                answer.add("How many brothers do you have?");
//                question.add("Saya punya satu saudara laki-laki");
//                answer.add("I have one brother");
//                question.add("Berapa banyak saudara perempuan yang kamu punya?");
//                answer.add("How many sisters do you have?");
//                question.add("Saya punya dua saudara perempuan");
//                answer.add("I have two sisters");
                break;
            case "12":
                question.add("Kamu seorang perempuan");
                question.add("Kamu seorang laki-laki");

                answer.add("You are a woman");
                answer.add("You are a boy");

                break;
            case "13":
                question.add("Apa yang terjadi");
                question.add("Saya seorang laki-laki");

                answer.add("What happened");
                answer.add("I am a boy");
                break;
        }



        int randomIndex = random.nextInt(question.size());

        questionModel = new QuestionModel(
                question.get(randomIndex),
                answer.get(randomIndex));

        return questionModel;
    }

    @Override
    public ArrayList<PairModel> getPairs(String chapter) {

        ArrayList<PairModel> pairs = new ArrayList<>();

        switch (chapter) {
            case "11":
                pairs.add(new PairModel("apel", "apple"));
                pairs.add(new PairModel("susu", "milk"));
                pairs.add(new PairModel("bread", "roti"));
                pairs.add(new PairModel("boy", "laki-laki"));
                break;
            case "12":
                pairs.add(new PairModel("dia", "she"));
                pairs.add(new PairModel("a", "sebuah"));
                pairs.add(new PairModel("he", "dia"));
                pairs.add(new PairModel("girl", "perempuan"));
                break;
            case "13":
                pairs.add(new PairModel("itu", "the"));
                pairs.add(new PairModel("kamu", "you"));
                pairs.add(new PairModel("minum", "drink"));
                pairs.add(new PairModel("air", "water"));
                break;
        }

        return pairs;
    }

    @Override
    public ArrayList<String> getAnswer() {
        return answer;
    }
}
