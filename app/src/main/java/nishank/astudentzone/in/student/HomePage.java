package nishank.astudentzone.in.student;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by dell on 9/16/2017.
 */

public class HomePage extends Fragment {

    View myView;
    String text;
    DatabaseReference myRef;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myRef = FirebaseDatabase.getInstance().getReference("homepage/item");
        getActivity().setTitle(getResources().getText(R.string.home_page));

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.home_page,container,false);
        final TextView tv=(TextView)myView.findViewById(R.id.textView2);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                text=dataSnapshot.getValue().toString();
                tv.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return myView;
    }

}
