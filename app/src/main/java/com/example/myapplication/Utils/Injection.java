package com.example.myapplication.Utils;

import com.example.myapplication.Data.Local.QuestionDataSource;
import com.example.myapplication.Data.Remote.FirebaseDatabaseHelper;
import com.example.myapplication.Data.Repository;

public class Injection {

    public static Repository provideRepository() {

        return Repository.getInstance(
                QuestionDataSource.getInstance(),
                FirebaseDatabaseHelper.getHelperInstance()
        );
    }

    public static FirebaseAuthHelper providesAuthHelper() {

        return FirebaseAuthHelper.getClassInstance();
    }
}
