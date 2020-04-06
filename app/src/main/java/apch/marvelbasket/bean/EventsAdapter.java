package apch.marvelbasket.bean;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import apch.marvelbasket.R;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder> {
    private ArrayList<EventItems> mEventList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
      //  void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public EventsAdapter(ArrayList<EventItems> eventList){
        mEventList = eventList;
    }

    public static class EventsViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView1;
        public TextView mTextView2;
        public Button delete;

        public EventsViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.textView1);
            mTextView2 = itemView.findViewById(R.id.textView2);
            delete = itemView.findViewById(R.id.delete_event);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public EventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item,parent,false);
        EventsViewHolder evh = new EventsViewHolder(v,mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull EventsViewHolder holder, int position) {
        EventItems currentItem = mEventList.get(position);
        holder.mTextView1.setText(currentItem.getmText1());
        holder.mTextView2.setText(currentItem.getmText2());
    }

    @Override
    public int getItemCount() {
        return mEventList.size();
    }

}
