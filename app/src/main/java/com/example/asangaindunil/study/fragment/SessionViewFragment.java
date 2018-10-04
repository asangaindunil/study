package com.example.asangaindunil.study.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asangaindunil.study.Activity.ViewSessionCard;
import com.example.asangaindunil.study.CardView.Adapter.SessionCardAdapter;
import com.example.asangaindunil.study.MainActivity;
import com.example.asangaindunil.study.R;
import com.example.asangaindunil.study.db.Constructor;
import com.example.asangaindunil.study.db.SessionDbHelper;
import com.example.asangaindunil.study.model.Session;

import java.util.ArrayList;
import java.util.List;

public class SessionViewFragment extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SessionCardAdapter sessionCardAdapter;
    private List<Session> SessionList;
    private OnFragmentInteractionListener onFragmentInteractionListener;

    SessionDbHelper sessionDbHelper;

    public SessionViewFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.session_fragment);

        recyclerView =findViewById(R.id.SessionRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SessionList = new ArrayList<>();
        SessionList.add(new Session("Science Homework","02:00","04:00",54));

        Cursor cursor = readSessions();
        while(cursor.moveToNext()){
            SessionList.add(new Session(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4),cursor.getString(5)));
        }
        cursor.close();
        sessionCardAdapter = new SessionCardAdapter(SessionList, this);
        recyclerView.setAdapter(sessionCardAdapter);

        sessionCardAdapter.setOnItemClickListener(new SessionCardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Session session = SessionList.get(position);
                Intent intent  = new Intent(SessionViewFragment.this,ViewSessionCard.class);
                intent.putExtra("LessonName",session.getName());
                intent.putExtra("Desc",session.getDescription());
                intent.putExtra("From",session.getFrom());
                intent.putExtra("To",session.getTo());
                intent.putExtra("Comp",session.getComplete());

                startActivity(intent);
            }
        });
    }

//    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle)
//    {
//        View view = layoutInflater.inflate(R.layout.session_fragment, viewGroup, false);
//        recyclerView =view.findViewById(R.id.SessionRecycleView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        SessionList = new ArrayList<>();
//        SessionList.add(new Session("Science Homework","02:00","04:00",54));
//
//        Cursor cursor = readSessions();
//                while(cursor.moveToNext()){
//                    SessionList.add(new Session(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4)));
//
//
//                }
//                cursor.close();
//        sessionCardAdapter = new SessionCardAdapter(SessionList, this);
//        recyclerView.setAdapter(sessionCardAdapter);
//
//        sessionCardAdapter.setOnItemClickListener(new SessionCardAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                Session session = SessionList.get(position);
//
//              //  startActivity(intent);
//            }
//        });
//    return view;
//    }

    private Cursor readSessions(){

        sessionDbHelper = new SessionDbHelper(this);
        SQLiteDatabase sqLiteDatabase = sessionDbHelper.getReadableDatabase();

        String[] projection ={
                BaseColumns._ID,
                Constructor.Session.Col_1,

                Constructor.Session.Col_3,
                Constructor.Session.Col_4,
                Constructor.Session.Col_5,
                Constructor.Session.Col_2,


        };
        String sort =Constructor.Session._ID+" DESC";

        Cursor cursor = sqLiteDatabase.query(
                Constructor.Session.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sort
        );
        return cursor;

    }

//    @Override
//
//        public void onAttach(Context context) {
//            super.onAttach(context);
//            if (context instanceof OnFragmentInteractionListener) {
//                onFragmentInteractionListener = (OnFragmentInteractionListener) context;
//            } else {
//                throw new RuntimeException(context.toString()
//                        + " must implement OnFragmentInteractionListener");
//            }
//        }
//
//        @Override
//        public void onDetach() {
//            super.onDetach();
//            onFragmentInteractionListener = null;
//            sessionDbHelper.close();
//        }

        public interface OnFragmentInteractionListener {
            void onFragmentInteraction(Uri uri);
        }

}
