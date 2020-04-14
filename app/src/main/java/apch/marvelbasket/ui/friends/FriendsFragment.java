package apch.marvelbasket.ui.friends;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import apch.marvelbasket.R;
import apch.marvelbasket.bean.FriendAdapter;
import apch.marvelbasket.bean.FriendItems;


public class FriendsFragment extends Fragment {
    private RecyclerView recyclerView;
    private FriendAdapter adapter;
    private ArrayList friendsList;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

         root = inflater.inflate(R.layout.fragment_friends, container, false);

        createEventList();
        buildRecyclerView();

        return root;
    }

    private void createEventList() {
        friendsList = new ArrayList();
        friendsList.add(new FriendItems("Prashil",4));
        friendsList.add(new FriendItems("Adil",5));
        friendsList.add(new FriendItems("Chintan",2));
        friendsList.add(new FriendItems("Het",9));
        friendsList.add(new FriendItems("Sarjak",7));
        friendsList.add(new FriendItems("Prerak",3));
        friendsList.add(new FriendItems("Darshan",4));
        friendsList.add(new FriendItems("Prashil",4));
        friendsList.add(new FriendItems("Adil",5));
        friendsList.add(new FriendItems("Chintan",2));
        friendsList.add(new FriendItems("Het",9));
        friendsList.add(new FriendItems("Sarjak",7));
        friendsList.add(new FriendItems("Prerak",3));
        friendsList.add(new FriendItems("Darshan",4));
        friendsList.add(new FriendItems("Prashil",4));
    }

    private void buildRecyclerView() {
        recyclerView = root.findViewById(R.id.recyclerView_friends);
        recyclerView.setHasFixedSize(true);
        adapter = new FriendAdapter(friendsList);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new FriendAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.e("FriendsFragment", "onItemClick: friends clicked "+friendsList.get(position).getClass());
            }
        });
    }
}
