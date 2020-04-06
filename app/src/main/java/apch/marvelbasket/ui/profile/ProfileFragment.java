package apch.marvelbasket.ui.profile;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import apch.marvelbasket.R;
import apch.marvelbasket.ui.user.HomeViewModel;

public class ProfileFragment extends Fragment {

    int flag=0;
    private EditText t[];
    private TextView textView;
    private ImageButton btn;

    private apch.marvelbasket.ui.profile.ProfileViewModel profileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        View v = inflater.inflate(R.layout.fragment_profile, container, false);


        t = new EditText[]{v.findViewById(R.id.firstname), v.findViewById(R.id.mobile), v.findViewById(R.id.lastname), v.findViewById(R.id.Address),
                v.findViewById(R.id.changepassword)};

    //Image Button Functionality
        final ImageButton button = v.findViewById(R.id.editacc);
        button.setBackgroundResource(R.drawable.edit_pencil);
        final int[] checkedit = {0};



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkedit[0] == 0) {
                    for (int i = 0; i < t.length; i++) {
                        t[i].setEnabled(true);
                    }
                    Toast.makeText(getContext(), "Enter New Details", Toast.LENGTH_SHORT).show();
                    button.setBackgroundResource(0);
                    button.setBackgroundResource(R.drawable.ic_check_black_24dp);
                    checkedit[0] = 1;
                } else {
                    button.setBackgroundResource(0);
                    button.setBackgroundResource(R.drawable.edit_pencil);
                    for (int i = 0; i < t.length; i++) {
                        t[i].setText(t[i].getText().toString());
                        t[i].setEnabled(false);
                    }
                    Toast.makeText(getContext(), "Changes Succesfully Saved", Toast.LENGTH_SHORT).show();
                    checkedit[0] = 0;
                }

            }
        });

        //Eye Icon Functionality

        final ImageButton showHideBtn = v.findViewById(R.id.showHideBtn);
        showHideBtn.setBackgroundResource(R.drawable.ic_eye);
        showHideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (t[4].getText().toString().equals("")) {
                } else {
                    if (flag == 0) {
                        //Show Password
                        t[4].setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        showHideBtn.setBackgroundResource(0);
                        showHideBtn.setBackgroundResource(R.drawable.ic_visibility_off_black_24dp);
                        flag = 1;
                    } else {
                        //Hide Password
                        t[4].setTransformationMethod(PasswordTransformationMethod.getInstance());
                        showHideBtn.setBackgroundResource(0);
                        showHideBtn.setBackgroundResource(R.drawable.ic_eye);
                        flag = 0;
                    }
                }
            }
        });

        return v;
    }
}
