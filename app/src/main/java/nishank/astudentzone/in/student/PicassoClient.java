package nishank.astudentzone.in.student;

import android.content.Context;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

/**
 * Created by dell on 9/12/2017.
 */

public class PicassoClient {
public static void downloading(Context c, String url, ImageView img){

    if(url!= null && url.length()>0){
        Picasso.with(c).load(url).into(img);
    }
    else{
        Picasso.with(c).load(R.drawable.main_bg).into(img);
    }

}
}
