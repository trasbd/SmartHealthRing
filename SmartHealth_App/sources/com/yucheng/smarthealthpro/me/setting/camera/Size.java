package com.yucheng.smarthealthpro.me.setting.camera;

/* loaded from: classes5.dex */
public class Size implements Comparable<Size> {
    private final int mHeight;
    private final int mWidth;

    public Size(int width, int height) {
        this.mWidth = width;
        this.mHeight = height;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof Size)) {
            return false;
        }
        Size size = (Size) o;
        return this.mWidth == size.mWidth && this.mHeight == size.mHeight;
    }

    public String toString() {
        return this.mWidth + "x" + this.mHeight;
    }

    public int hashCode() {
        int i2 = this.mHeight;
        int i3 = this.mWidth;
        return i2 ^ ((i3 >>> 16) | (i3 << 16));
    }

    @Override // java.lang.Comparable
    public int compareTo(Size another) {
        return (this.mWidth * this.mHeight) - (another.mWidth * another.mHeight);
    }
}
