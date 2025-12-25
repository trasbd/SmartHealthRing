package com.yucheng.smarthealthpro.me.setting.camera;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.collection.SparseArrayCompat;

/* loaded from: classes5.dex */
public class AspectRatio implements Comparable<AspectRatio>, Parcelable {
    private final int mX;
    private final int mY;
    private static final SparseArrayCompat<SparseArrayCompat<AspectRatio>> sCache = new SparseArrayCompat<>(16);
    public static final Parcelable.Creator<AspectRatio> CREATOR = new Parcelable.Creator<AspectRatio>() { // from class: com.yucheng.smarthealthpro.me.setting.camera.AspectRatio.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AspectRatio createFromParcel(Parcel source) {
            return AspectRatio.of(source.readInt(), source.readInt());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AspectRatio[] newArray(int size) {
            return new AspectRatio[size];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static AspectRatio of(int x, int y) {
        int iGcd = gcd(x, y);
        int i2 = x / iGcd;
        int i3 = y / iGcd;
        SparseArrayCompat<SparseArrayCompat<AspectRatio>> sparseArrayCompat = sCache;
        SparseArrayCompat<AspectRatio> sparseArrayCompat2 = sparseArrayCompat.get(i2);
        if (sparseArrayCompat2 == null) {
            AspectRatio aspectRatio = new AspectRatio(i2, i3);
            SparseArrayCompat<AspectRatio> sparseArrayCompat3 = new SparseArrayCompat<>();
            sparseArrayCompat3.put(i3, aspectRatio);
            sparseArrayCompat.put(i2, sparseArrayCompat3);
            return aspectRatio;
        }
        AspectRatio aspectRatio2 = sparseArrayCompat2.get(i3);
        if (aspectRatio2 != null) {
            return aspectRatio2;
        }
        AspectRatio aspectRatio3 = new AspectRatio(i2, i3);
        sparseArrayCompat2.put(i3, aspectRatio3);
        return aspectRatio3;
    }

    public static AspectRatio parse(String s) {
        int iIndexOf = s.indexOf(58);
        if (iIndexOf == -1) {
            throw new IllegalArgumentException("Malformed aspect ratio: " + s);
        }
        try {
            return of(Integer.parseInt(s.substring(0, iIndexOf)), Integer.parseInt(s.substring(iIndexOf + 1)));
        } catch (NumberFormatException e2) {
            throw new IllegalArgumentException("Malformed aspect ratio: " + s, e2);
        }
    }

    private AspectRatio(int x, int y) {
        this.mX = x;
        this.mY = y;
    }

    public int getX() {
        return this.mX;
    }

    public int getY() {
        return this.mY;
    }

    public boolean matches(Size size) {
        int iGcd = gcd(size.getWidth(), size.getHeight());
        return this.mX == size.getWidth() / iGcd && this.mY == size.getHeight() / iGcd;
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof AspectRatio)) {
            return false;
        }
        AspectRatio aspectRatio = (AspectRatio) o;
        return this.mX == aspectRatio.mX && this.mY == aspectRatio.mY;
    }

    public String toString() {
        return this.mX + ":" + this.mY;
    }

    public float toFloat() {
        return this.mX / this.mY;
    }

    public int hashCode() {
        int i2 = this.mY;
        int i3 = this.mX;
        return i2 ^ ((i3 >>> 16) | (i3 << 16));
    }

    @Override // java.lang.Comparable
    public int compareTo(AspectRatio another) {
        if (equals(another)) {
            return 0;
        }
        return toFloat() - another.toFloat() > 0.0f ? 1 : -1;
    }

    public AspectRatio inverse() {
        return of(this.mY, this.mX);
    }

    private static int gcd(int a2, int b2) {
        while (true) {
            int i2 = b2;
            int i3 = a2;
            a2 = i2;
            if (a2 == 0) {
                return i3;
            }
            b2 = i3 % a2;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mX);
        dest.writeInt(this.mY);
    }
}
