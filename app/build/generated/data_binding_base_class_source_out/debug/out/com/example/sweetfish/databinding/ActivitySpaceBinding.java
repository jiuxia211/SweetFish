// Generated by view binder compiler. Do not edit!
package com.example.sweetfish.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.example.sweetfish.R;
import com.google.android.material.tabs.TabLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySpaceBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageFilterView avatar;

  @NonNull
  public final TextView fans;

  @NonNull
  public final TextView fansNum;

  @NonNull
  public final Button follow;

  @NonNull
  public final ImageButton individuation;

  @NonNull
  public final ImageButton search;

  @NonNull
  public final TextView sellNum;

  @NonNull
  public final Button sendMsg;

  @NonNull
  public final TabLayout tb;

  @NonNull
  public final TextView username;

  @NonNull
  public final ViewPager2 vp;

  @NonNull
  public final ImageView wallpaperImageView;

  private ActivitySpaceBinding(@NonNull ConstraintLayout rootView, @NonNull ImageFilterView avatar,
      @NonNull TextView fans, @NonNull TextView fansNum, @NonNull Button follow,
      @NonNull ImageButton individuation, @NonNull ImageButton search, @NonNull TextView sellNum,
      @NonNull Button sendMsg, @NonNull TabLayout tb, @NonNull TextView username,
      @NonNull ViewPager2 vp, @NonNull ImageView wallpaperImageView) {
    this.rootView = rootView;
    this.avatar = avatar;
    this.fans = fans;
    this.fansNum = fansNum;
    this.follow = follow;
    this.individuation = individuation;
    this.search = search;
    this.sellNum = sellNum;
    this.sendMsg = sendMsg;
    this.tb = tb;
    this.username = username;
    this.vp = vp;
    this.wallpaperImageView = wallpaperImageView;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySpaceBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySpaceBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_space, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySpaceBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.avatar;
      ImageFilterView avatar = ViewBindings.findChildViewById(rootView, id);
      if (avatar == null) {
        break missingId;
      }

      id = R.id.fans;
      TextView fans = ViewBindings.findChildViewById(rootView, id);
      if (fans == null) {
        break missingId;
      }

      id = R.id.fansNum;
      TextView fansNum = ViewBindings.findChildViewById(rootView, id);
      if (fansNum == null) {
        break missingId;
      }

      id = R.id.follow;
      Button follow = ViewBindings.findChildViewById(rootView, id);
      if (follow == null) {
        break missingId;
      }

      id = R.id.individuation;
      ImageButton individuation = ViewBindings.findChildViewById(rootView, id);
      if (individuation == null) {
        break missingId;
      }

      id = R.id.search;
      ImageButton search = ViewBindings.findChildViewById(rootView, id);
      if (search == null) {
        break missingId;
      }

      id = R.id.sellNum;
      TextView sellNum = ViewBindings.findChildViewById(rootView, id);
      if (sellNum == null) {
        break missingId;
      }

      id = R.id.sendMsg;
      Button sendMsg = ViewBindings.findChildViewById(rootView, id);
      if (sendMsg == null) {
        break missingId;
      }

      id = R.id.tb;
      TabLayout tb = ViewBindings.findChildViewById(rootView, id);
      if (tb == null) {
        break missingId;
      }

      id = R.id.username;
      TextView username = ViewBindings.findChildViewById(rootView, id);
      if (username == null) {
        break missingId;
      }

      id = R.id.vp;
      ViewPager2 vp = ViewBindings.findChildViewById(rootView, id);
      if (vp == null) {
        break missingId;
      }

      id = R.id.wallpaperImageView;
      ImageView wallpaperImageView = ViewBindings.findChildViewById(rootView, id);
      if (wallpaperImageView == null) {
        break missingId;
      }

      return new ActivitySpaceBinding((ConstraintLayout) rootView, avatar, fans, fansNum, follow,
          individuation, search, sellNum, sendMsg, tb, username, vp, wallpaperImageView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}