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
public class FamilyFragment extends Fragment {


    public FamilyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        // Create a list of words
        final ArrayList<Word> family = new ArrayList<>();
        family.add(new Word("ayah", "father", R.drawable.family_father));
        family.add(new Word("ibu", "mother", R.drawable.family_mother));
        family.add(new Word("anak perempuan", "daughter", R.drawable.family_daughter));
        family.add(new Word("anak laki-laki", "son", R.drawable.family_son));
        family.add(new Word("adik laki-laki", "younger brother", R.drawable.family_younger_brother));
        family.add(new Word("kakak perempuan", "older sister", R.drawable.family_older_sister));
        family.add(new Word("nenek", "grandmother", R.drawable.family_grandmother));
        family.add(new Word("kakek", "grandfather", R.drawable.family_grandfather));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        final WordAdapter adapter = new WordAdapter(getActivity(), family, getActivity().getColor(R.color.category_family));

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
