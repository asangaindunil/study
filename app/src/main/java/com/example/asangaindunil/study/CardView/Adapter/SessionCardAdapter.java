package com.example.asangaindunil.study.CardView.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.service.textservice.SpellCheckerService;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asangaindunil.study.Activity.UpdateSessionActivity;
import com.example.asangaindunil.study.R;
import com.example.asangaindunil.study.db.Constructor;
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




            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View view) {
                            if (listener != null) {
                                int position = getAdapterPosition();
                                if (position != RecyclerView.NO_POSITION) {
                                    openOptionMenu(view, position);
                                }
                            }
                            return true;
                        }
                    });

                }

            }
                public void openOptionMenu(final View view, final int position) {
                    PopupMenu popup = new PopupMenu(view.getContext(), view);
                    popup.getMenuInflater().inflate(R.menu.menu_popup_items, popup.getMenu());
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            Session session = SessionItemList.get(position);
                            switch (item.getItemId()) {
                                case R.id.update_item_option:
                                    Intent intent = new Intent(context, UpdateSessionActivity.class);
                                    intent.putExtra("Title", session.getName());
                                    intent.putExtra("descript", session.getDescription());
                                    intent.putExtra("from", session.getFrom());
                                    intent.putExtra("to", session.getTo());
                                    intent.putExtra("complete", session.getComplete());

                                    context.startActivity(intent);
                                    break;
                                case R.id.delete_item_option:
                                    SQLiteDatabase db = sessionDbHelper.getReadableDatabase();
                                    // Define 'where' part of query.
                                    String selection = Constructor.Session.Col_1 + " = ?";
                                    // Specify arguments in placeholder order.
                                    String[] selectionArgs = {session.getName()};
                                    // Issue SQL statement.
                                    int deletedRow = db.delete(Constructor.Session.TABLE_NAME, selection, selectionArgs);
                                    //delete item from list
                                    SessionItemList.remove(position);
                                    //update recycleview
                                    notifyItemRemoved(position);

                                    //delete from db
                                    if (deletedRow != 0) {
                                        Toast.makeText(view.getContext(), "Successfully deleted!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(view.getContext(), "failed to delete!", Toast.LENGTH_SHORT).show();
                                    }
                                    break;
                                default:
                                    Toast.makeText(view.getContext(), "nothing pressed", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            return true;
                        }
                    });

                    popup.show();
                }
        }
