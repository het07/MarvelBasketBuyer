package apch.marvelbasket.bean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.internal.$Gson$Preconditions;

import java.util.List;

import apch.marvelbasket.FriendsEventsFragment;
import apch.marvelbasket.R;
import apch.marvelbasket.ui.friends.FriendsFragment;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendViewHolder> {
    private List<FriendItems> friendList;
    private Context context;

    private OnItemClickListener myListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        myListener = listener;
    }

    public FriendAdapter(List<FriendItems> friendList) {
        this.friendList = friendList;
    }

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        context=parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        v = inflater.inflate(R.layout.friend_item, parent, false);
        return new FriendViewHolder(v,myListener);
    }


    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder holder, int position) {
        FriendItems currentItem = friendList.get(position);
        holder.textView1.setText(currentItem.getmText1());
        holder.textView2.setText("" + currentItem.getNum());

        int left = dpToPx(6);
        int top = dpToPx(3);
        int right = dpToPx(6);
        int bottom = dpToPx(3);

        boolean first2Items = position < 2;
        boolean last2Items = position > getItemCount() - 2;

        if (first2Items) {
            top = dpToPx(6);
        }

        if (last2Items) {
            bottom = dpToPx(6);
        }

        boolean isLeftSide=(position+1)%2!=0;
        boolean isRightSide=!isLeftSide;

        if(isLeftSide){
            right = dpToPx(3);
        }
        if(isRightSide){
            left = dpToPx(3);
        }

        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) holder.cardView.getLayoutParams();
        layoutParams.setMargins(left, top, right, bottom);
        holder.cardView.setLayoutParams(layoutParams);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity= (AppCompatActivity) view.getContext();
                Fragment fragment = new FriendsEventsFragment();
                FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

    }

    private int dpToPx(int dp) {
        float px = dp * context.getResources().getDisplayMetrics().density;
        return (int) px;
    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }

    public static class FriendViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        TextView textView1;
        TextView textView2;
        CardView cardView;

        public FriendViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView1_friends);
            textView2 = itemView.findViewById(R.id.textView2_friends);
            cardView = itemView.findViewById(R.id.cardview_friends);

        }

        @Override
        public void onClick(View view) {
            AppCompatActivity activity= (AppCompatActivity) view.getContext();
            Fragment fragment = new FriendsEventsFragment();
            FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame,fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}
