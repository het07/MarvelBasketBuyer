package apch.marvelbasket.ui.events;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Collection;

import apch.marvelbasket.DatePickerFragment;
import apch.marvelbasket.R;
import apch.marvelbasket.bean.EventItems;
import apch.marvelbasket.bean.EventsAdapter;


public class YourEventsFragment extends Fragment {

    private RecyclerView recyclerView;
    private EventsAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RelativeLayout parentLinearLayout;
    private ArrayList eventsList;
    private View root;


    public final void removeItem(int position) {

        eventsList.remove(position);
        adapter.notifyItemRemoved(position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_your_events, container, false);

        createEventList();
        buildRecyclerView();
        //setButtons();

        return root;
    }

    private void setButtons() {

    }

    private void buildRecyclerView() {
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        adapter = new EventsAdapter(eventsList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new EventsAdapter.OnItemClickListener() {

            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });
    }

    private void createEventList() {
        eventsList = new ArrayList<>();
        eventsList.add(new EventItems("Birth Date","07-12-1998"));
        eventsList.add(new EventItems("Anniversary","09-02-2015"));
    }

}
