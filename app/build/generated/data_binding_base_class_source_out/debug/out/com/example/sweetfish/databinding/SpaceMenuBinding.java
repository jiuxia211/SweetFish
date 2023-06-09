// Generated by view binder compiler. Do not edit!
package com.example.sweetfish.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.sweetfish.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class SpaceMenuBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView setAvatar;

  @NonNull
  public final TextView setBackground;

  private SpaceMenuBinding(@NonNull LinearLayout rootView, @NonNull TextView setAvatar,
      @NonNull TextView setBackground) {
    this.rootView = rootView;
    this.setAvatar = setAvatar;
    this.setBackground = setBackground;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static SpaceMenuBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static SpaceMenuBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.space_menu, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static SpaceMenuBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.setAvatar;
      TextView setAvatar = ViewBindings.findChildViewById(rootView, id);
      if (setAvatar == null) {
        break missingId;
      }

      id = R.id.setBackground;
      TextView setBackground = ViewBindings.findChildViewById(rootView, id);
      if (setBackground == null) {
        break missingId;
      }

      return new SpaceMenuBinding((LinearLayout) rootView, setAvatar, setBackground);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
