package com.yhj.app.bike.model.pojo;

import java.io.Serializable;
import java.util.ArrayList;

import android.util.SparseIntArray;

public class ImgTxtLiveImageIndex implements Serializable {
	private static final long serialVersionUID = 6035787204157303572L;
	private int index=0;
	private ArrayList<ImgTxtLiveImage> imageURLs= null;
	private SparseIntArray imageIndex2position=null;
	
	public ImgTxtLiveImageIndex(int index, ArrayList<ImgTxtLiveImage> imageURLs,SparseIntArray imageIndex2position) {
		super();
		this.index = index;
		this.imageURLs = imageURLs;
		this.imageIndex2position=imageIndex2position;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public ArrayList<ImgTxtLiveImage> getImageURLs() {
		return imageURLs;
	}

	public void setImageURLs(ArrayList<ImgTxtLiveImage> imageURLs) {
		this.imageURLs = imageURLs;
	}

	public SparseIntArray getImageIndex2position() {
		return imageIndex2position;
	}

	public void setImageIndex2position(SparseIntArray imageIndex2position) {
		this.imageIndex2position = imageIndex2position;
	}
	
}
