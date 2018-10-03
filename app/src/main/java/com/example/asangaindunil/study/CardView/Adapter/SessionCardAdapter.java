package com.example.asangaindunil.study.CardView.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.asangaindunil.study.R;
import com.example.asangaindunil.study.db.SessionDbHelper;
import com.example.asangaindunil.study.model.Session;

import java.util.List;

public class SessionCardAdapter extends RecyclerView.Adapter<SessionCardAdapter.SessionViewHolder>{
    Context context;
    SessionDbHelper sessionDbHelper;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
    }

    private List<Session> SessionItemList;

    public SessionCardAdapter(List<Session> SessionItemList, Context context){
        this.SessionItemList = SessionItemList;
        this.context =context;
    }


    @NonNull
    @Override
    public SessionCardAdapter.SessionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        sessionDbHelper = new SessionDbHelper(viewGroup.getContext());
        View SessionView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_session_card, viewGroup, false);
        SessionCardAdapter.SessionViewHolder as = new  SessionCardAdapter.SessionViewHolder(SessionView, onItemClickListener);
        return as;
    }

    @Override
    public void onBindViewHolder(@NonNull SessionCardAdapter.SessionViewHolder sessionViewHolder, final int i) {

        sessionViewHolder.txtName.setText(SessionItemList.get(i).getName()+"");
        sessionViewHolder.txtFrom.setText(SessionItemList.get(i).getFrom()+"");
        sessionViewHolder.txtto.setText(SessionItemList.get(i).getTo()+"");
        sessionViewHolder.progressBar.setProgress(SessionItemList.get(i).getComplete());



    }

    @Override
    public int getItemCount() {
        return SessionItemList.size();
    }

    public class SessionViewHolder extends RecyclerView.ViewHolder{
        TextView txtName, txtFrom, txtto;
        ProgressBar progressBar;

        LinearLayout layout;
        public SessionViewHolder(@NonNull View itemView, final SessionCardAdapter.OnItemClickListener listener) {
            super(itemView);
            txtName =itemView.findViewById(R.id.tTile);
            txtFrom =itemView.findViewById(R.id.tfrom);
            txtto = itemView.findViewById(R.id.tto);
            progressBar=itemView.findViewById(R.id.comp);



        }
    }
}
