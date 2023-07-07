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

public final class FragmentNotificationsBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageFilterView avatar;

  @NonNull
  public final TextView nullText;

  @NonNull
  public final ConstraintLayout topPanel;

  @NonNull
  public final TextView username;

  @NonNull
  public final RecyclerView users;

  private FragmentNotificationsBinding(@NonNull ConstraintLayout rootView,
      @NonNull ImageFilterView avatar, @NonNull TextView nullText,
      @NonNull ConstraintLayout topPanel, @NonNull TextView username, @NonNull RecyclerView users) {
    this.rootView = rootView;
    this.avatar = avatar;
    this.nullText = nullText;
    this.topPanel = topPanel;
    this.username = username;
    this.users = users;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentNotificationsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentNotificationsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_notifications, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentNotificationsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.avatar;
      ImageFilterView avatar = ViewBindings.findChildViewById(rootView, id);
      if (avatar == null) {
        break missingId;
      }

      id = R.id.nullText;
      TextView nullText = ViewBindings.findChildViewById(rootView, id);
      if (nullText == null) {
        break missingId;
      }

      id = R.id.topPanel;
      ConstraintLayout topPanel = ViewBindings.findChildViewById(rootView, id);
      if (topPanel == null) {
        break missingId;
      }

      id = R.id.username;
      TextView username = ViewBindings.findChildViewById(rootView, id);
      if (username == null) {
        break missingId;
      }

      id = R.id.users;
      RecyclerView users = ViewBindings.findChildViewById(rootView, id);
      if (users == null) {
        break missingId;
      }

      return new FragmentNotificationsBinding((ConstraintLayout) rootView, avatar, nullText,
          topPanel, username, users);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}