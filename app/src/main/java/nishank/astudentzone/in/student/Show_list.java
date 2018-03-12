package nishank.astudentzone.in.student;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by dell on 9/2/2017.
 */

public class Show_list extends AppCompatActivity {
    int c=0;
    String address,name,contact_no,amount,category,share,map;
    private static final String TAG ="";
    public List<FirebaseData> result;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listings);

        result=new ArrayList<>();
        recyclerView =(RecyclerView)findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter(this,result);
        final String city_sel = getIntent().getStringExtra("city").toLowerCase().replaceAll("\\s","");
        final String loc_sel=getIntent().getStringExtra("loc").toLowerCase().replaceAll("\\s","");

        //Toast.makeText(this, "loc:"+loc_sel, Toast.LENGTH_SHORT).show();


        // Intializing firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //referenceVar=database.getReference("name field");
        DatabaseReference myRef = database.getReference().child("cities").child(city_sel).child("localities").child(loc_sel);


        myRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    address= ds.child("address").getValue(String.class);
                    contact_no= ds.child("contact_no").getValue(String.class);
                    name= ds.child("name").getValue(String.class);
                    amount=ds.child("amount").getValue(String.class);
                    category=ds.child("category").getValue(String.class);
                    share=ds.child("share").getValue(String.class);
                    map=ds.child("co_ords").getValue(String.class);

                    //Toast.makeText(Show_list.this,"Value is: \t" + address[c] + "\nContact is\t" + contact_no[c] + "\nName is\t" + name[c], Toast.LENGTH_SHORT).show();
                    result.add(new FirebaseData(address,amount,category,contact_no,name,share,city_sel,loc_sel,map));

                    //Log.d(TAG, "Value is: \t" + address[c] + "\nContact is\t" + contact_no[c] + "\nName is\t" + name[c]);

                }
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }




        });


//Firebase Code Ends Here



    }


}
