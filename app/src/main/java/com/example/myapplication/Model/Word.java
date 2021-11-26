package com.example.myapplication.Model;

/**
 * Contains word and it's translation.
 */
public class Word {
    private String mDefaultWord;
    private String mEnglishWord;
    private int mImageResourceId;

    /** Constant value that represents no image was provided for this word */
    private static final int NO_IMAGE_PROVIDED = -1;

    /**
     * Create a new Word object.
     * @param defaultWord is the word in the Default language
     * @param englishWord is the word in the English language
     */
    public Word(String defaultWord, String englishWord) {
        this.mDefaultWord = defaultWord;
        this.mEnglishWord = englishWord;
        this.mImageResourceId = NO_IMAGE_PROVIDED;
    }

    /**
     * Create a new Word object.
     * @param defaultWord is the word in the Default language
     * @param englishWord is the word in the English language
     * @param imageResourceId is image resource id
     */
    public Word(String defaultWord, String englishWord, int imageResourceId) {
        this.mDefaultWord = defaultWord;
        this.mEnglishWord = englishWord;
        this.mImageResourceId = imageResourceId;
    }

    /**
     * Get string of default word
     */
    public String getDefaultWord() {
        return mDefaultWord;
    }

    /**
     * Get string of english word
     */
    public String getEnglishWord() {
        return mEnglishWord;
    }

    /**
     * Get image resource id
     */
    public int getImageResourceId() {
        return mImageResourceId;
    }

    /**
     * Returns whether or not there is an image for this word.
     */
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }
}
