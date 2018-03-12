package nishank.astudentzone.in.student;

/**
 * Created by dell on 9/6/2017.
 */

public class FirebaseData {
    public String address;
    public String contact_no;
    public String name;
    public String category;
    public String amount;
    public String share;
    public String city_sel;
    public String loc_sel;
    public String map;



    public FirebaseData() {
            // Needed for Firebase
        }

        public FirebaseData(String address,String amount,String category,String contact_no,String name,String share,String city_sel,String loc_sel,String map) {
            this.name = name;
            this.address = address;
            this.contact_no=contact_no;
            this.amount = amount;
            this.category = category;
            this.share = share;
            this.city_sel=city_sel;
            this.loc_sel=loc_sel;
            this.map=map;
        }

//        public String getName() {
//            return pgName;
//        }
//
//        public String getAddress() {
//            return pgAddr;
//        }
//
//        public String getContact() {
//        return pgNo;
//        }
//
//        public String getUid() {
//        return pgUid;
//        }

//           public void setName(String name) {
//            mName = name;
//        }


//        public void setMessage(String message) {
//            mMessage = message;
//        }


//        public void setUid(String uid) {
//            mUid = uid;
//        }

    }

