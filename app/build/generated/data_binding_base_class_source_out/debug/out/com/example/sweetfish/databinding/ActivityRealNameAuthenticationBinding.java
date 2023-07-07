// Generated by view binder compiler. Do not edit!
package com.example.sweetfish.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.sweetfish.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityRealNameAuthenticationBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageFilterView back;

  @NonNull
  public final EditText identityNum;

  @NonNull
  public final EditText name;

  @NonNull
  public final ConstraintLayout topPanel;

  @NonNull
  public final TextView verify;

  private ActivityRealNameAuthenticationBinding(@NonNull ConstraintLayout rootView,
      @NonNull ImageFilterView back, @NonNull EditText identityNum, @NonNull EditText name,
      @NonNull ConstraintLayout topPanel, @NonNull TextView verify) {
    this.rootView = rootView;
    this.back = back;
    this.identityNum = identityNum;
    this.name = name;
    this.topPanel = topPanel;
    this.verify = verify;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRealNameAuthenticationBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRealNameAuthenticationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_real_name_authentication, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRealNameAuthenticationBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.back;
      ImageFilterView back = ViewBindings.findChildViewById(rootView, id);
      if (back == null) {
        break missingId;
      }

      id = R.id.identityNum;
      EditText identityNum = ViewBindings.findChildViewById(rootView, id);
      if (identityNum == null) {
        break missingId;
      }

      id = R.id.name;
      EditText name = ViewBindings.findChildViewById(rootView, id);
      if (name == null) {
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

      return new ActivityRealNameAuthenticationBinding((ConstraintLayout) rootView, back,
          identityNum, name, topPanel, verify);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
