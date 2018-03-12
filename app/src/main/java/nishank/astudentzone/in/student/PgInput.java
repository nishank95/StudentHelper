package nishank.astudentzone.in.student;

/**
 * Created by dell on 9/18/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.R.layout.simple_spinner_dropdown_item;

public class PgInput extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner sp,sp1;
    Button b;
    String city,locality;
    ArrayAdapter<String> c_adapter;
    ArrayAdapter<String> adapter1;
    ArrayAdapter <String> adapter2;
    DatabaseReference myRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_fields);

        // Intializing firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //referenceVar=database.getReference("name field");
        myRef = database.getReference();
        sp=(Spinner)findViewById(R.id.city_spinner);
        sp.setOnItemSelectedListener(this);
        sp1=(Spinner)findViewById(R.id.locality_spinner);
        //sp1.setOnItemSelectedListener(this);
        b=(Button)findViewById(R.id.button);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locality=sp1.getSelectedItem().toString();
                myRef.child("user_input").child("locality").setValue(locality);
                myRef.child("user_input").child("city").setValue(city);

                Intent i=new Intent(PgInput.this,Show_list.class);
                i.putExtra("city",city);
                i.putExtra("loc",locality);
                startActivity(i);
            }
        });



        c_adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.city_drp));
        c_adapter.setDropDownViewResource(simple_spinner_dropdown_item);
        sp.setAdapter(c_adapter);




    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
        Object obj=adapterView.getItemAtPosition(pos);
        city=obj.toString();
        if(pos==0) {
            adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.locality_ban));
            adapter1.setDropDownViewResource(simple_spinner_dropdown_item);
            sp1.setAdapter(adapter1);
        }
        else {
            adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.locality_hyd));
            adapter2.setDropDownViewResource(simple_spinner_dropdown_item);
            sp1.setAdapter(adapter2);

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}
