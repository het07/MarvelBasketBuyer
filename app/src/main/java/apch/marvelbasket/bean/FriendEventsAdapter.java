package apch.marvelbasket.bean;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import apch.marvelbasket.R;

public class FriendEventsAdapter extends RecyclerView.Adapter<FriendEventsAdapter.FriendEventsViewHolder> {
    private ArrayList<EventItems> mFriendEventsList;
    private EventsAdapter.OnItemClickListener mListener;


    public FriendEventsAdapter(ArrayList<EventItems> eventList){
        mFriendEventsList = eventList;
    }

    public static class FriendEventsViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView1;
        public TextView mTextView2;


        public FriendEventsViewHolder(@NonNull View itemView, final EventsAdapter.OnItemClickListener listener) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.textView1_friend_events);
            mTextView2 = itemView.findViewById(R.id.textView2_friend_events);

        }
    }

    @NonNull
    @Override
    public FriendEventsAdapter.FriendEventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_event_item,parent,false);
        FriendEventsAdapter.FriendEventsViewHolder fevh = new FriendEventsAdapter.FriendEventsViewHolder(v,mListener);
        return fevh;
    }

    @Override
    public void onBindViewHolder(@NonNull FriendEventsViewHolder holder, int position) {
        EventItems currentItem = mFriendEventsList.get(position);
        holder.mTextView1.setText(currentItem.getmText1());
        holder.mTextView2.setText(currentItem.getmText2());
    }

    @Override
    public int getItemCount() {
        return mFriendEventsList.size();
    }

}
