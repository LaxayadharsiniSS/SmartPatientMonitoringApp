package com.example.techcroods;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

@IgnoreExtraProperties //Prevents crashes when incorrectly retrieving objects from firestore [ignores extra fields retrieved by query and only adds the fields from the object class]
public class userDetails {

       private @ServerTimestamp Date timestamp;
        private String name;
        private String mailid;
        private String password;
        private String wardAlloted;
//        private String nursePhn_or_doctorPhn;
 //       private String guardianPhn_or_peerNursePhn;
        private String contact;
        public userDetails(String name, String mailid, String password, String wardAlloted, String contact, Date timestamp) {
            this.name = name;
            this.mailid = mailid;
            this.password = password;
            this.wardAlloted = wardAlloted;
            this.contact = contact;
            this.timestamp = timestamp;  //setting to null will trigger the insertion of the timestamp
        }
        public userDetails(){}

        public String getName() {
                return this.name;
        }
        public void setName(String name) { this.name = name;}

        public String getMailid() {
                return this.mailid;
        }
        public void setMailid(String mailid) { this.mailid = mailid;}

        public String getPassword() { return this.password; }
        public void setPassword(String password) { this.password = password;}

        public String getWardAlloted() { return this.wardAlloted; }
        public void setWardAlloted(String wardAlloted) { this.wardAlloted = wardAlloted;}

        public String getContact() { return this.contact; }
        public void setContact(String contact) { this.contact = contact;}
}
