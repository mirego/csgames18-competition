package com.mirego.csmapapplication.activity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toolbar;
import com.mirego.csmapapplication.MapPingApplication;
import com.mirego.csmapapplication.R;
import com.mirego.csmapapplication.R.id;
import com.mirego.csmapapplication.fragment.ListSegmentFragment;
import com.mirego.csmapapplication.fragment.MapSegmentFragment;
import com.mirego.csmapapplication.service.GitHubService;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public final class MainActivity extends FragmentActivity {
    private final ListSegmentFragment listFragment = new ListSegmentFragment();
    private final MapSegmentFragment mapFragment = new MapSegmentFragment();
    private int selectedSegmentIndex;
    private List segmentButtons;
    ArrayList<HashMap<String, String>> pinList = new ArrayList<>();
    public Retrofit retrofit;
    private static final String SELECTED_SEGMENT_INDEX_KEY = "SELECTED_SEGMENT_INDEX_KEY";
    private HashMap _$_findViewCache;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @NotNull
    public final Retrofit getRetrofit() {
        Retrofit var10000 = this.retrofit;
        if(this.retrofit == null) {
            Intrinsics.throwUninitializedPropertyAccessException("retrofit");
        }

        return var10000;
    }

    public final void setRetrofit(@NotNull Retrofit var1) {
        Intrinsics.checkParameterIsNotNull(var1, "<set-?>");
        this.retrofit = var1;
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        Application var10000 = this.getApplication();
        if(var10000 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.mirego.csmapapplication.MapPingApplication");
        } else {
            ((MapPingApplication)var10000).getNetComponent().inject(this);
            this.segmentButtons = CollectionsKt.listOf(new ImageButton[]{(ImageButton)this._$_findCachedViewById(id.listButton), (ImageButton)this._$_findCachedViewById(id.mapButton), (ImageButton)this._$_findCachedViewById(id.arButton)});
            this.setActionBar((Toolbar)this._$_findCachedViewById(id.toolbar));
            if(savedInstanceState == null) {
                this.setupMainView();
            }

            this.setupButtons();
//            this.downloadData();
            this.parseData();
            
        }




    }

    private final void downloadData() {
        Retrofit var10000 = this.retrofit;
        if(this.retrofit == null) {
            Intrinsics.throwUninitializedPropertyAccessException("retrofit");
        }

        ((GitHubService)var10000.create(GitHubService.class)).listRepos("olivierpineau").enqueue((Callback)(new Callback() {
            public void onFailure(@Nullable Call call, @Nullable Throwable t) {
                Log.d("street's test", "Oops");
            }

            public void onResponse(@Nullable Call call, @Nullable Response response) {
                Log.d("street's test", "That's it");
            }
        }));
    }


    protected void parseData() {
        try {
            JSONObject jsonObj = new JSONObject();
            JSONArray map = jsonObj.getJSONArray("mapping.json");

            // looping through All map
            for (int i = 0; i < map.length(); i++) {
                JSONObject c = map.getJSONObject(i);
                String name = c.getString("name");
                String component = c.getString("component");
                String notes = c.getString("notes");
                String type = c.getString("type");
                double lat = c.getDouble("lat");
                double lon = c.getDouble("lon");
                String address = c.getString("address");

                // tmp hash map for single contact
                HashMap<String, String> pin = new HashMap<>();

                // adding each child node to HashMap key => value
                pin.put("name", name);
                pin.put("component", component);
                pin.put("notes", notes);
                pin.put("type", type);
                pin.put("lat", lat+"");
                pin.put("lon", lon+"");
                pin.put("address", address);

                // adding contact to contact list
                pinList.add(pin);
            }
        } catch (final JSONException e) {

        }

    }

    private final void setupMainView() {
        FragmentTransaction var10000 = this.getSupportFragmentManager().beginTransaction();
        FrameLayout var10001 = (FrameLayout)this._$_findCachedViewById(id.fragmentRoot);
        Intrinsics.checkExpressionValueIsNotNull(var10001, "fragmentRoot");
        var10000.add(var10001.getId(), (Fragment)this.listFragment).commit();
    }

    private final void onSegmentButtonClicked(ImageButton button) {
        if(Intrinsics.areEqual(button, (ImageButton)this._$_findCachedViewById(id.listButton))) {


            //new
            mRecyclerView = findViewById(R.id.List_View);
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);



            Log.d("ss", pinList.size()+"");
            mAdapter = new MyAdapter(pinList);
            mRecyclerView.setAdapter(mAdapter);

            //listFragment.setArguments(bundle);
            //this.replaceFragment((Fragment)this.listFragment);
        } else if(Intrinsics.areEqual(button, (ImageButton)this._$_findCachedViewById(id.mapButton))) {
            this.replaceFragment((Fragment)this.mapFragment);
        } else if(Intrinsics.areEqual(button, (ImageButton)this._$_findCachedViewById(id.arButton))) {
            this.startActivity(new Intent((Context)this, ArActivity.class));
        }

    }

    private final void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        FrameLayout var10001 = (FrameLayout)this._$_findCachedViewById(id.fragmentRoot);
        Intrinsics.checkExpressionValueIsNotNull(var10001, "fragmentRoot");
        transaction.replace(var10001.getId(), fragment);
        transaction.commit();
    }

    private final void setupButtons() {
        OnClickListener listener = (OnClickListener)(new OnClickListener() {
            public final void onClick(@NotNull View view) {
                Intrinsics.checkParameterIsNotNull(view, "view");
                Iterator var3 = CollectionsKt.withIndex((Iterable)MainActivity.access$getSegmentButtons$p(MainActivity.this)).iterator();

                while(var3.hasNext()) {
                    IndexedValue var2 = (IndexedValue)var3.next();
                    int index = var2.component1();
                    ImageButton segmentButton = (ImageButton)var2.component2();
                    if(Intrinsics.areEqual(segmentButton, (ImageButton)view)) {
                        MainActivity.this.selectedSegmentIndex = index;
                        if(Intrinsics.areEqual(view, (ImageButton)MainActivity.this._$_findCachedViewById(id.arButton)) ^ true) {
                            MainActivity.this.updateSegmentButtonsColor();
                        }

                        MainActivity.this.onSegmentButtonClicked(segmentButton);
                        break;
                    }
                }

            }
        });
        List var10000 = this.segmentButtons;
        if(this.segmentButtons == null) {
            Intrinsics.throwUninitializedPropertyAccessException("segmentButtons");
        }

        Iterator var3 = var10000.iterator();

        while(var3.hasNext()) {
            ImageButton button = (ImageButton)var3.next();
            button.setOnClickListener(listener);
        }

    }

    protected void onSaveInstanceState(@Nullable Bundle outState) {
        super.onSaveInstanceState(outState);
        if(outState != null) {
            outState.putInt("SELECTED_SEGMENT_INDEX_KEY", this.selectedSegmentIndex);
        }

    }

    protected void onRestoreInstanceState(@Nullable Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null) {
            int var2 = savedInstanceState.getInt("SELECTED_SEGMENT_INDEX_KEY");
            this.selectedSegmentIndex = var2;
            this.updateSegmentButtonsColor();
        }

    }

    private final void updateSegmentButtonsColor() {
        List var10000 = this.segmentButtons;
        if(this.segmentButtons == null) {
            Intrinsics.throwUninitializedPropertyAccessException("segmentButtons");
        }

        Iterator var2 = CollectionsKt.withIndex((Iterable)var10000).iterator();

        while(var2.hasNext()) {
            IndexedValue var1 = (IndexedValue)var2.next();
            int index = var1.component1();
            ImageButton segmentButton = (ImageButton)var1.component2();
            if(index == this.selectedSegmentIndex) {
                this.tintSegmentButton(segmentButton, true);
            } else {
                this.tintSegmentButton(segmentButton, false);
            }
        }

    }

    private final void tintSegmentButton(ImageButton button, boolean selected) {
        button.setColorFilter(ContextCompat.getColor((Context)this, selected?2131034142:2131034156));
    }

    // $FF: synthetic method
    @NotNull
    public static final List access$getSegmentButtons$p(MainActivity $this) {
        List var10000 = $this.segmentButtons;
        if($this.segmentButtons == null) {
            Intrinsics.throwUninitializedPropertyAccessException("segmentButtons");
        }

        return var10000;
    }

    // $FF: synthetic method
    public static final void access$setSegmentButtons$p(MainActivity $this, @NotNull List var1) {
        $this.segmentButtons = var1;
    }

    // $FF: synthetic method
    public static final int access$getSelectedSegmentIndex$p(MainActivity $this) {
        return $this.selectedSegmentIndex;
    }

    public View _$_findCachedViewById(int var1) {
        if(this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }

        View var2 = (View)this._$_findViewCache.get(Integer.valueOf(var1));
        if(var2 == null) {
            var2 = this.findViewById(var1);
            this._$_findViewCache.put(Integer.valueOf(var1), var2);
        }

        return var2;
    }

    public void _$_clearFindViewByIdCache() {
        if(this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }

    }


}
