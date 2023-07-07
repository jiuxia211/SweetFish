// Generated by view binder compiler. Do not edit!
package com.example.sweetfish.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.sweetfish.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLoginBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final CheckBox autoLogin;

  @NonNull
  public final CircleImageView cover;

  @NonNull
  public final EditText editAccount;

  @NonNull
  public final EditText editPassword;

  @NonNull
  public final Button login;

  @NonNull
  public final Button register;

  @NonNull
  public final CheckBox rememberPassword;

  @NonNull
  public final TextView title;

  private ActivityLoginBinding(@NonNull LinearLayout rootView, @NonNull CheckBox autoLogin,
      @NonNull CircleImageView cover, @NonNull EditText editAccount, @NonNull EditText editPassword,
      @NonNull Button login, @NonNull Button register, @NonNull CheckBox rememberPassword,
      @NonNull TextView title) {
    this.rootView = rootView;
    this.autoLogin = autoLogin;
    this.cover = cover;
    this.editAccount = editAccount;
    this.editPassword = editPassword;
    this.login = login;
    this.register = register;
    this.rememberPassword = rememberPassword;
    this.title = title;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.autoLogin;
      CheckBox autoLogin = ViewBindings.findChildViewById(rootView, id);
      if (autoLogin == null) {
        break missingId;
      }

      id = R.id.cover;
      CircleImageView cover = ViewBindings.findChildViewById(rootView, id);
      if (cover == null) {
        break missingId;
      }

      id = R.id.editAccount;
      EditText editAccount = ViewBindings.findChildViewById(rootView, id);
      if (editAccount == null) {
        break missingId;
      }

      id = R.id.editPassword;
      EditText editPassword = ViewBindings.findChildViewById(rootView, id);
      if (editPassword == null) {
        break missingId;
      }

      id = R.id.login;
      Button login = ViewBindings.findChildViewById(rootView, id);
      if (login == null) {
        break missingId;
      }

      id = R.id.register;
      Button register = ViewBindings.findChildViewById(rootView, id);
      if (register == null) {
        break missingId;
      }

      id = R.id.rememberPassword;
      CheckBox rememberPassword = ViewBindings.findChildViewById(rootView, id);
      if (rememberPassword == null) {
        break missingId;
      }

      id = R.id.title;
      TextView title = ViewBindings.findChildViewById(rootView, id);
      if (title == null) {
        break missingId;
      }

      return new ActivityLoginBinding((LinearLayout) rootView, autoLogin, cover, editAccount,
          editPassword, login, register, rememberPassword, title);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}