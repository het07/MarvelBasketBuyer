package apch.marvelbasket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import apch.marvelbasket.bean.EventItems;
import apch.marvelbasket.bean.EventsAdapter;
import apch.marvelbasket.bean.FriendEventsAdapter;


public class FriendsEventsFragment extends Fragment {
    private RecyclerView recyclerView;
    private FriendEventsAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RelativeLayout parentLinearLayout;
    private ArrayList<EventItems> eventsList;
    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_friends_events, container, false);

        createEventList();
        buildRecyclerView();


        return root;
    }

    private void buildRecyclerView() {
        recyclerView = root.findViewById(R.id.recyclerView_friend_events);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        adapter = new FriendEventsAdapter(eventsList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    private void createEventList() {
        eventsList = new ArrayList<>();
        eventsList.add(new EventItems("Birth Date","07-12-1998"));
        eventsList.add(new EventItems("Anniversary","09-02-2015"));
    }

}
