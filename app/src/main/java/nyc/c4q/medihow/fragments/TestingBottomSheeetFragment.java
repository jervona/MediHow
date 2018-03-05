package nyc.c4q.medihow.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.medihow.activities.MapsActivity;
import nyc.c4q.medihow.OfficeListAdapter;
import nyc.c4q.medihow.R;
import nyc.c4q.medihow.model.MedicareOffice;

/**
 * Created by jervon.arnoldd on 3/4/18.
 */

public class TestingBottomSheeetFragment extends BottomSheetDialogFragment {

    List<MedicareOffice> hello;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.cycle_view, null);
        hello = new ArrayList<>();
        RecyclerView recyclerView = contentView.findViewById(R.id.bottom_cycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(contentView.getContext()));
        hello = MapsActivity.medicareOfficeList;
        if (hello != null) {
            OfficeListAdapter adapter = new OfficeListAdapter(hello);
            recyclerView.setAdapter(adapter);
        }
        dialog.setContentView(contentView);
        CoordinatorLayout.LayoutParams layoutParams =
                (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = layoutParams.getBehavior();
        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
    }

}

