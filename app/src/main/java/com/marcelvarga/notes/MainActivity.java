package com.marcelvarga.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = findViewById(R.id.gridView);
        String element = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas augue mauris, pharetra non neque mollis, egestas placerat libero. Quisque at mauris iaculis, cursus sem a, volutpat ligula. Fusce libero neque, volutpat sit amet venenatis in, maximus et urna. Etiam aliquam, ante et congue pulvinar, dolor odio commodo diam, sit amet pellentesque felis purus commodo massa. Nam risus risus, fermentum vel massa ac, viverra vestibulum orci. Cras convallis leo sed ligula venenatis cursus. Nam vitae semper metus. Proin suscipit condimentum tincidunt. Ut malesuada malesuada ante. Suspendisse dignissim ex eu eleifend euismod. Curabitur nec lobortis purus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Fusce blandit congue lectus, eu pulvinar risus suscipit eu. Nulla eu rhoncus sem. Vivamus facilisis, purus ac porttitor imperdiet, libero erat molestie massa, blandit blandit nisl augue nec lectus.";
        List<String> list = new ArrayList<>(Arrays.asList(element, element, element, element, element, element, element, element, element, element));
                ListAdapter listAdapter = new ArrayAdapter<>(this, R.layout.linear_layout, list);

        gridView.setAdapter(listAdapter);
    }
}