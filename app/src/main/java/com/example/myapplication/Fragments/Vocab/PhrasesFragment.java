package com.example.myapplication.Fragments.Vocab;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.Adapters.WordAdapter;
import com.example.myapplication.Model.Word;
import com.example.myapplication.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhrasesFragment extends Fragment {

    public PhrasesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);


        // Create a list of words
        final ArrayList<Word> phrases = new ArrayList<>();
        phrases.add(new Word("Kamu pergi kemana?", "Where are you going?"));
        phrases.add(new Word("Siapa namamu?", "What is your name?"));
        phrases.add(new Word("Nama saya adalah...", "My name is..."));
        phrases.add(new Word("Bagaimana perasaanmu?", "How are you feeling?"));
        phrases.add(new Word("Saya merasa baik", "I’m feeling good."));
        phrases.add(new Word("Apakah kamu akan datang?", "Are you coming?"));
        phrases.add(new Word("Ya, aku akan datang", "Yes, I’m coming."));
        phrases.add(new Word("Ayo pergi", "Let’s go."));
        phrases.add(new Word("Datanglah kemari", "Come here."));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        final WordAdapter adapter = new WordAdapter(getActivity(), phrases, getActivity().getColor(R.color.category_phrases));

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = rootView.findViewById(R.id.root);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);


        return rootView;
    }
}
