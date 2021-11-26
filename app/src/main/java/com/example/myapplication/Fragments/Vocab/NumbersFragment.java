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
public class NumbersFragment extends Fragment {


    public NumbersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        // Create a list of words
        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("satu","one", R.drawable.number_one));
        words.add(new Word("dua","two", R.drawable.number_two));
        words.add(new Word("tiga","three", R.drawable.number_three));
        words.add(new Word("empat","four", R.drawable.number_four));
        words.add(new Word("lima","five", R.drawable.number_five));
        words.add(new Word("enam","six", R.drawable.number_six));
        words.add(new Word("tujuh","seven", R.drawable.number_seven));
        words.add(new Word("delapan","eight", R.drawable.number_eight));
        words.add(new Word("sembilan","nine", R.drawable.number_nine));
        words.add(new Word("sepuluh","ten", R.drawable.number_ten));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        final WordAdapter adapter = new WordAdapter(getActivity(), words, getActivity().getColor(R.color.category_numbers));

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
