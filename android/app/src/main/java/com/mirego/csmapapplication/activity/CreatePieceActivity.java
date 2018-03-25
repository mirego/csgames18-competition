package com.mirego.csmapapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;

import com.mirego.csmapapplication.MapPingApplication;
import com.mirego.csmapapplication.R;
import com.mirego.csmapapplication.model.Piece;

public class CreatePieceActivity extends Activity {

    private View rootView;

    private Toolbar toolbar;
    private EditText nameText;
    private EditText typeText;
    private EditText componentText;
    private EditText notesText;
    private EditText addressText;
    private Button createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_piece);

        toolbar = findViewById(R.id.toolbar);
        rootView = findViewById(R.id.content_root);
        nameText = findViewById(R.id.name_value);
        typeText = findViewById(R.id.type_value);
        componentText = findViewById(R.id.component_value);
        notesText = findViewById(R.id.notes_value);
        addressText = findViewById(R.id.address_value);
        createButton = findViewById(R.id.create_button);

        setActionBar(toolbar);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPiece();
            }
        });
    }

    private void createPiece() {
        Piece piece = new Piece();

        piece.setName(nameText.getText().toString());
        piece.setType(typeText.getText().toString());
        piece.setComponent(componentText.getText().toString());
        piece.setNotes(notesText.getText().toString());
        piece.setAddress(addressText.getText().toString());
        piece.setLatitude(46.781746);
        piece.setLongitude(-71.274742);

        MapPingApplication.pieces.add(piece);

        Snackbar.make(rootView, R.string.new_piece_created, Snackbar.LENGTH_SHORT).show();
        finish();
    }
}
