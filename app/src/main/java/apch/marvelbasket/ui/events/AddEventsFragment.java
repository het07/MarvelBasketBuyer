package apch.marvelbasket.ui.events;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import apch.marvelbasket.DatePickerFragment;
import apch.marvelbasket.R;
import apch.marvelbasket.ui.user.HomeViewModel;

public class AddEventsFragment extends Fragment {
    private static final String TAG = "AddEventsFragment";
    private TextView eventDate;
    private EditText eventName;
    private Button addEvent;



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_events, container, false);
        eventName = root.findViewById(R.id.event_name);;
        eventDate  = root.findViewById(R.id.event_date);
        addEvent = root.findViewById(R.id.add_event);

        eventDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment(eventDate);
                newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
            }

        });

        addEvent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: addEvent clicked" );
                Log.e(TAG, "onCreateView: ename :" + eventName.getText().toString() );
                Log.e(TAG, "onCreateView: edate :" + eventDate.getText().toString() );
            }

        });

        return root;
    }

}
