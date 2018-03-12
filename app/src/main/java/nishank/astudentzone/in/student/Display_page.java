package nishank.astudentzone.in.student;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by dell on 9/3/2017.
 */

public class Display_page extends AppCompatActivity {
    String address,contact_no,name,amount,share_det,city_sel,loc_sel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_page);
        final TextView addr=(TextView)findViewById(R.id.address_content);
        final TextView contact=(TextView)findViewById(R.id.contact);
        final TextView amt_val=(TextView)findViewById(R.id.val2);
        final TextView dep_val=(TextView)findViewById(R.id.val3);
        final ImageView iv=(ImageView)findViewById(R.id.imageView4);
        final ImageView pg_img=(ImageView)findViewById(R.id.imageView2);





        final String item_clicked = getIntent().getStringExtra("clicked_Item");
        city_sel= getIntent().getStringExtra("city_sel");
        loc_sel= getIntent().getStringExtra("loc_sel");



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("cities").child(city_sel).child("localities").child(loc_sel);


        myRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                   String pgId= ds.child("pgId").getValue(String.class);

                    if(pgId.equalsIgnoreCase(item_clicked))
                    {
                        address= ds.child("address").getValue(String.class);
                        contact_no= ds.child("contact_no").getValue(String.class);
                        name= ds.child("name").getValue(String.class);
                        amount = ds.child("amount").getValue(String.class);
                        share_det=ds.child("share").getValue(String.class);
                        //Toast.makeText(Display_page.this,pgId,Toast.LENGTH_SHORT).show();
                        addr.setText(address);
                        contact.setText("+91"+contact_no);
                        amt_val.setText(amount);
                        dep_val.setText(amount);
                        String url=ds.child("image_url").getValue(String.class);
                        setTitle(name);
                        PicassoClient.downloading(Display_page.this,url,pg_img);
                        iv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "+91"+contact_no, null));
                                startActivity(intent);
                            }
                        });

                    }

                    //Log.d(TAG, "Value is: \t" + address[c] + "\nContact is\t" + contact_no[c] + "\nName is\t" + name[c]);

                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }




        });


        //Toast.makeText(this,clickedItem,Toast.LENGTH_SHORT).show();
    }
}
