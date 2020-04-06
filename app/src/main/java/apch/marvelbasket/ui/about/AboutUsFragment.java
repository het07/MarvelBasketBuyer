package apch.marvelbasket.ui.about;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import apch.marvelbasket.R;
import apch.marvelbasket.ui.user.HomeViewModel;

public class AboutUsFragment extends Fragment {
    private apch.marvelbasket.ui.about.AboutUsViewModel aboutusViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        aboutusViewModel = ViewModelProviders.of(this).get(AboutUsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_about_us, container, false);
        final TextView textView = root.findViewById(R.id.text_about);
        aboutusViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}