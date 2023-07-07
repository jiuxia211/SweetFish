// Generated by view binder compiler. Do not edit!
package com.example.sweetfish.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.sweetfish.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMyGivenBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageFilterView back;

  @NonNull
  public final RecyclerView commodities;

  @NonNull
  public final ConstraintLayout topPanel;

  @NonNull
  public final TextView verify;

  private ActivityMyGivenBinding(@NonNull ConstraintLayout rootView, @NonNull ImageFilterView back,
      @NonNull RecyclerView commodities, @NonNull ConstraintLayout topPanel,
      @NonNull TextView verify) {
    this.rootView = rootView;
    this.back = back;
    this.commodities = commodities;
    this.topPanel = topPanel;
    this.verify = verify;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMyGivenBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMyGivenBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_my_given, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMyGivenBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.back;
      ImageFilterView back = ViewBindings.findChildViewById(rootView, id);
      if (back == null) {
        break missingId;
      }

      id = R.id.commodities;
      RecyclerView commodities = ViewBindings.findChildViewById(rootView, id);
      if (commodities == null) {
        break missingId;
      }

      id = R.id.topPanel;
      ConstraintLayout topPanel = ViewBindings.findChildViewById(rootView, id);
      if (topPanel == null) {
        break missingId;
      }

      id = R.id.verify;
      TextView verify = ViewBindings.findChildViewById(rootView, id);
      if (verify == null) {
        break missingId;
      }

      return new ActivityMyGivenBinding((ConstraintLayout) rootView, back, commodities, topPanel,
          verify);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
