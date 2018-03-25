package com.mirego.csmapapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.mirego.csmapapplication.ExtraKeys;
import com.mirego.csmapapplication.MapPingApplication;
import com.mirego.csmapapplication.R;
import com.mirego.csmapapplication.model.Piece;

public class PieceDetailActivity extends Activity {

    private Integer pieceIndex;

    private Toolbar toolbar;
    private TextView nameTextView;
    private TextView componentTextView;
    private TextView notesTextView;
    private TextView addressLabelTextView;
    private TextView addressTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piece_detail);

        toolbar = findViewById(R.id.toolbar);
        nameTextView = findViewById(R.id.name_value);
        componentTextView = findViewById(R.id.component_value);
        notesTextView = findViewById(R.id.notes_value);
        addressLabelTextView = findViewById(R.id.address_label);
        addressTextView = findViewById(R.id.address_value);

        setActionBar(toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        pieceIndex = intent.getIntExtra(ExtraKeys.PIECE_INDEX, 0);

        refreshData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void refreshData() {
        Piece piece = MapPingApplication.pieces.get(pieceIndex);

        nameTextView.setText(piece.getName());
        componentTextView.setText(piece.getComponent());
        notesTextView.setText(piece.getNotes());

        if (piece.getAddress() == null || piece.getAddress().isEmpty()) {
            addressLabelTextView.setVisibility(View.INVISIBLE);
            addressTextView.setVisibility(View.INVISIBLE);
        }
        else {
            addressLabelTextView.setVisibility(View.VISIBLE);
            addressTextView.setVisibility(View.VISIBLE);

            addressTextView.setText(piece.getAddress());
        }
    }
}
