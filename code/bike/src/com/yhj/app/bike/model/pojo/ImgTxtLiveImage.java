package com.yhj.app.bike.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class ImgTxtLiveImage implements Parcelable{
	String imgurl;
	String smallimgurl;
	String desc;

	public ImgTxtLiveImage(){
	}
	
	private ImgTxtLiveImage(Parcel in){
		this.imgurl = in.readString();
		this.smallimgurl = in.readString();
		this.desc = in.readString();
	}
	
	public String getSmallimgurl() {
		return smallimgurl;
	}

	public void setSmallimgurl(String smallimgurl) {
		this.smallimgurl = smallimgurl;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}


	
	 @Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(imgurl);
		dest.writeString(smallimgurl);
		dest.writeString(desc);
	}

	public static final Parcelable.Creator<ImgTxtLiveImage> CREATOR
             = new Parcelable.Creator<ImgTxtLiveImage>() {
         public ImgTxtLiveImage createFromParcel(Parcel in) {
             return new ImgTxtLiveImage(in);
         }

         public ImgTxtLiveImage[] newArray(int size) {
             return new ImgTxtLiveImage[size];
         }
     };

}
