package nishank.astudentzone.in.student;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

/**
 * Created by dell on 9/2/2017.
 */

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
{
    private List<FirebaseData> values;
    private String position;
    private Context mContext;
    private String city,loc;
    int i=0;
    String[] number=new String[11];
    String[] map=new String[11];
    String[] name=new String[11];

    public void chooseMap(int pos){
        String label =name[pos];
        String uriBegin = "geo:"+map[pos];
        String query = map[pos]+"(" + label + ")";
        String encodedQuery = Uri.encode( query  );
        String uriString = uriBegin + "?q=" + encodedQuery;
        Uri uri = Uri.parse( uriString );
        Intent in = new Intent(android.content.Intent.ACTION_VIEW, uri );
        mContext.startActivity(in);
    }

    RecyclerAdapter(Context mContext, List<FirebaseData> values){
        this.values=values;
        this.mContext=mContext;
    }

  class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemTitle,itemDetail1,itemDetail2,itemDetail3;
        ImageView iv;
        TabLayout tabLayout;



        ViewHolder(final View itemView) {
            super(itemView);
            itemTitle = (TextView)itemView.findViewById(R.id.pg_name);
            itemDetail1 = (TextView)itemView.findViewById(R.id.det1);
            itemDetail2 = (TextView)itemView.findViewById(R.id.det2);
            itemDetail3 = (TextView)itemView.findViewById(R.id.det3);
            iv=(ImageView)itemView.findViewById(R.id.imageView);
            tabLayout=(TabLayout)itemView.findViewById(R.id.tabLayout);

            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    int pos =getAdapterPosition();
                    switch(tab.getPosition()){
                        case 0:
                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", number[pos], null));
                            mContext.startActivity(intent);
                            break;
                        case 1:
                            chooseMap(pos);
                            break;
                        case 2:

                            break;
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                    int rpos=getAdapterPosition();
                    switch (tab.getPosition()) {

                        case 0:
                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", number[rpos], null));
                            mContext.startActivity(intent);
                            break;
                        case 1:
                           chooseMap(rpos);
                            break;
                        case 2:
                            break;
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    position = String.valueOf(getAdapterPosition());
                    Intent i= new Intent(mContext,Display_page.class);
                    i.putExtra("clicked_Item",position);
                    i.putExtra("city_sel",city);
                    i.putExtra("loc_sel",loc);

                    Snackbar.make(v, "Click detected on item " + position,
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    mContext.startActivity(i);

                }
            });
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.pg_list, viewGroup, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ViewHolder pgviewHolder, int position) {
        FirebaseData pg=values.get(position);
        pgviewHolder.itemDetail1.setText(pg.address);
        pgviewHolder.itemDetail2.setText(pg.share+" Available");
        pgviewHolder.itemTitle.setText(pg.name);
        pgviewHolder.itemDetail3.setText("Starting From Rs."+ pg.amount);
        if(pg.category.equalsIgnoreCase("male")){
            pgviewHolder.iv.setImageResource(R.drawable.boy);
        }
        else{
            pgviewHolder.iv.setImageResource(R.drawable.girl);
        }
        number[i]=pg.contact_no;
        map[i]=pg.map;
        name[i]=pg.name;
        if(getItemCount()==i){
            i=0;
        }
        city=pg.city_sel;
        loc=pg.loc_sel;

    i++;
    }


    @Override
    public int getItemCount() {
       return values.size();
    }

}

