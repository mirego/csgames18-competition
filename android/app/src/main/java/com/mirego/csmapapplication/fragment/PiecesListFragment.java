package com.mirego.csmapapplication.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mirego.csmapapplication.ExtraKeys;
import com.mirego.csmapapplication.MapPingApplication;
import com.mirego.csmapapplication.R;
import com.mirego.csmapapplication.activity.PieceDetailActivity;
import com.mirego.csmapapplication.adapters.PiecesAdapter;
import com.mirego.csmapapplication.model.Piece;

import java.util.ArrayList;
import java.util.List;

public class PiecesListFragment extends Fragment implements PiecesAdapter.OnItemClickListener {

    private RecyclerView rcPieces;
    private PiecesAdapter mPiecesAdapter;

    public PiecesListFragment() {
        // Required empty public constructor
    }


    public static PiecesListFragment newInstance() {
        PiecesListFragment fragment = new PiecesListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pieces_list, container, false);
        rcPieces = view.findViewById(R.id.rcPieces);
        rcPieces.setLayoutManager(new LinearLayoutManager(getActivity()));

        mPiecesAdapter = new PiecesAdapter(getActivity(), (List<Piece>) MapPingApplication.pieces);
        mPiecesAdapter.setOnItemClickListener(this);
        rcPieces.setAdapter(mPiecesAdapter);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onItemClick(View v, Piece item, int position) {
        Intent intent = new Intent(getActivity(), PieceDetailActivity.class);
        intent.putExtra(ExtraKeys.PIECE_INDEX, position);
        startActivity(intent);
    }
}
