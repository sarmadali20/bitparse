package com.pxlim.bitparse;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ParseFragment extends Fragment {

    @BindView(R.id.editTextInput)
    EditText editTextInput;
    @BindView(R.id.parse)
    Button buttonParse;
    @BindView(R.id.textViewResult)
    TextView textViewResult;


    private Set<String> english_dict;

    public ParseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ParseFragment.
     */
    public static ParseFragment newInstance() {
        ParseFragment fragment = new ParseFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parse, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.parse)
    public void parse(View view) {
        if(editTextInput.getText().toString().trim().equals("")) {
            editTextInput.setError("Please provide input text");
        }
        buttonParse.setEnabled(false);
        textViewResult.setText("");
        if(english_dict == null) {
            DictionaryMaker task = new DictionaryMaker(editTextInput.getText().toString());
            task.execute();
            return;
        }

        parseAndUpdateResults(editTextInput.getText().toString());

    }

    public void parseAndUpdateResults(String input) {
        LinkedHashSet<String> results = new LinkedHashSet<>();
        Stack<String> stack = new Stack<>();
        ParseUtils.search(input,english_dict,stack,results);
        StringBuilder sb = new StringBuilder();
        for (String word : results) {
                sb.append(word);
                sb.append("\n");
        }
        textViewResult.setText(sb.toString());
        buttonParse.setEnabled(true);
    }


    public class DictionaryMaker extends AsyncTask<Void, Void, Set<String>> {
        private String input;
        public DictionaryMaker(String input) {
            this.input = input;
        }
        @Override
        protected Set<String> doInBackground(Void... params) {
            Set<String> result = new HashSet<>();
            try{
                InputStream file = ParseFragment.this.getResources().openRawResource(R.raw.english_dict);
                BufferedReader reader = new BufferedReader(new InputStreamReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    result.add(line);
                }
                reader.close();
            } catch(IOException ioe){
                Log.e(ParseFragment.class.toString(), ioe.getMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(Set<String> result) {
            ParseFragment.this.english_dict = result;
            ParseFragment.this.parseAndUpdateResults(input);
        }


    }

}
