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

public class ContactUs extends Fragment {

    View myView;
    String text;
    DatabaseReference myRef;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myRef = FirebaseDatabase.getInstance().getReference("contactus");
        getActivity().setTitle(getResources().getText(R.string.contact_page));


    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.contact_us,container,false);

        final TextView addr=(TextView)myView.findViewById(R.id.address);
        final TextView phone=(TextView)myView.findViewById(R.id.number);
        final TextView mail=(TextView)myView.findViewById(R.id.mail);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                text=dataSnapshot.child("address").getValue(String.class);
                addr.setText(text);
                text=dataSnapshot.child("number").getValue(String.class);
                phone.setText(text);
                text=dataSnapshot.child("mail").getValue(String.class);
                mail.setText(text);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return myView;
    }

}
