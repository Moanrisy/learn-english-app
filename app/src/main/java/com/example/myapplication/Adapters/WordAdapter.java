package com.example.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Model.Word;
import com.example.myapplication.R;

import java.util.ArrayList;

/**
 * {@link WordAdapter} is an {@link ArrayAdapter} that can provide the layout
 * for each list based on a data source,
 * which is a list of {@link Word} objects.
 */
public class WordAdapter extends ArrayAdapter<Word> {

    /** Resource ID for the background color for this list of words */
    private int mColorResourceId;

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context The current context. Used to inflate the layout file.
     * @param words A List of word objects to display in a list
     * @param colorResourceId view BackgroundColor
     */
    public WordAdapter(Context context, ArrayList<Word> words, int colorResourceId) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // The second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView,
        // the adapter is not going to use this second argument,
        // so it can be any value. Here, we used 0.
        super(context, 0, words);

        this.mColorResourceId = colorResourceId;
    }


    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view.
        View listItemView = convertView;
        if (listItemView == null)
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        // Get the Word object located at this position in the list
        final Word item = getItem(position);
        if (item != null) {
            // Find the TextView in the list_item.xml layout with the ID version_name
            // Get the text (default word) from the current Word object
            // Set this text on the defaultWord TextView
            TextView defaultWord = listItemView.findViewById(R.id.default_language_text_view);
            defaultWord.setText(item.getDefaultWord());

            TextView englishWord = listItemView.findViewById(R.id.english_text_view);
            englishWord.setText(item.getEnglishWord());

            // Find the ImageView in the list_item.xml layout with the ID image.
            ImageView img = listItemView.findViewById(R.id.img);
            // Check if an image is provided for this word or not
            if (item.hasImage()) {
                // Make sure the view is visible
                img.setVisibility(View.VISIBLE);
                // If an image is available, display the provided image based on the resource ID
                img.setImageResource(item.getImageResourceId());
            }
            else {
                // Otherwise hide the ImageView (set visibility to GONE)
                img.setVisibility(View.GONE);
            }

            // Set the background color of the text container View
            listItemView.findViewById(R.id.layout).setBackgroundColor(this.mColorResourceId);
        }

        // Return the whole list item layout (containing 2 TextViews)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
