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
public class ColorsFragment extends Fragment {


    public ColorsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        // Create a list of words
        final ArrayList<Word> colors = new ArrayList<>();
        colors.add(new Word("merah", "red", R.drawable.color_red));
        colors.add(new Word("hijau", "green", R.drawable.color_green));
        colors.add(new Word("coklat", "brown", R.drawable.color_brown));
        colors.add(new Word("abu-abu", "gray", R.drawable.color_gray));
        colors.add(new Word("hitam", "black", R.drawable.color_black));
        colors.add(new Word("putih", "white", R.drawable.color_white));
        colors.add(new Word("kuning sawi", "mustard yellow", R.drawable.color_mustard_yellow));
        colors.add(new Word("kuning berdebu", "dusty yellow", R.drawable.color_dusty_yellow));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        final WordAdapter adapter = new WordAdapter(getActivity(), colors, getActivity().getColor(R.color.category_colors));

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = rootView.findViewById(R.id.root);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);

        // Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Release the media player if it currently exists because we are about to
                // play a different sound file

                // Get the {@link Word} object at the given position the user clicked on
                Word word = colors.get(position);
            }
        });

        return rootView;
    }
}
