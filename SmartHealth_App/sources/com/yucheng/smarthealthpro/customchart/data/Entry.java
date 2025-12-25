package com.yucheng.smarthealthpro.customchart.data;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.ParcelFormatException;
import android.os.Parcelable;
import com.yucheng.smarthealthpro.customchart.utils.Utils;

/* loaded from: classes4.dex */
public class Entry extends BaseEntry implements Parcelable {
    public static final Parcelable.Creator<Entry> CREATOR = new Parcelable.Creator<Entry>() { // from class: com.yucheng.smarthealthpro.customchart.data.Entry.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Entry createFromParcel(Parcel source) {
            return new Entry(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Entry[] newArray(int size) {
            return new Entry[size];
        }
    };
    private float mClose;
    private float mOpen;
    private float mShadowHigh;
    private float mShadowLow;
    private float maxtemper;
    private float mintemper;
    private float time;
    private float x;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Entry() {
        this.x = 0.0f;
    }

    public Entry(float x, float y) {
        super(y);
        this.x = x;
    }

    public Entry(float x, float y, Object data) {
        super(y, data);
        this.x = x;
    }

    public Entry(float x, float y, Drawable icon) {
        super(y, icon);
        this.x = x;
    }

    public Entry(float x, float y, Drawable icon, Object data) {
        super(y, icon, data);
        this.x = x;
    }

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public Entry copy() {
        return new Entry(this.x, getY(), getData());
    }

    public boolean equalTo(Entry e2) {
        return e2 != null && e2.getData() == getData() && Math.abs(e2.x - this.x) <= Utils.FLOAT_EPSILON && Math.abs(e2.getY() - getY()) <= Utils.FLOAT_EPSILON;
    }

    public String toString() {
        return "Entry, x: " + this.x + " y: " + getY();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.x);
        dest.writeFloat(getY());
        if (getData() != null) {
            if (getData() instanceof Parcelable) {
                dest.writeInt(1);
                dest.writeParcelable((Parcelable) getData(), flags);
                return;
            }
            throw new ParcelFormatException("Cannot parcel an Entry with non-parcelable data");
        }
        dest.writeInt(0);
    }

    protected Entry(Parcel in) {
        this.x = 0.0f;
        this.x = in.readFloat();
        setY(in.readFloat());
        if (in.readInt() == 1) {
            setData(in.readParcelable(Object.class.getClassLoader()));
        }
    }

    public void setMaxtemper(float maxtemper) {
        this.maxtemper = maxtemper;
    }

    public void setMintemper(float mintemper) {
        this.mintemper = mintemper;
    }

    public void setShadowHigh(float mShadowHigh) {
        this.mShadowHigh = mShadowHigh;
    }

    public void setShadowLow(float mShadowLow) {
        this.mShadowLow = mShadowLow;
    }

    public void setOpen(float mOpen) {
        this.mOpen = mOpen;
    }

    public void setClose(float mClose) {
        this.mClose = mClose;
    }

    public float getTime() {
        return this.time;
    }

    public float getMaxtemper() {
        return this.maxtemper;
    }

    public float getMintemper() {
        return this.mintemper;
    }

    public float getmShadowHigh() {
        return this.mShadowHigh;
    }

    public float getmShadowLow() {
        return this.mShadowLow;
    }

    public float getmClose() {
        return this.mClose;
    }

    public float getmOpen() {
        return this.mOpen;
    }
}
