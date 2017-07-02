package com.example.enchanter21.socialdating;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by enchanter21 on 2/7/17.
 */

public class frndslist implements Parcelable {

    String sno;
    String ur_name;
    String ur_image;
    String other_details;


    protected frndslist(Parcel in) {
        sno = in.readString();
        ur_name = in.readString();
        ur_image = in.readString();
        other_details = in.readString();
    }

    public static final Creator<frndslist> CREATOR = new Creator<frndslist>() {
        @Override
        public frndslist createFromParcel(Parcel in) {
            return new frndslist(in);
        }

        @Override
        public frndslist[] newArray(int size) {
            return new frndslist[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(sno);
        parcel.writeString(ur_name);
        parcel.writeString(ur_image);
        parcel.writeString(other_details);
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getUr_name() {
        return ur_name;
    }

    public void setUr_name(String ur_name) {
        this.ur_name = ur_name;
    }

    public String getUr_image() {
        return ur_image;
    }

    public void setUr_image(String ur_image) {
        this.ur_image = ur_image;
    }

    public String getOther_details() {
        return other_details;
    }

    public void setOther_details(String other_details) {
        this.other_details = other_details;
    }
}