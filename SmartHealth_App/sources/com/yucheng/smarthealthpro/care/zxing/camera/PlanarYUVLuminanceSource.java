package com.yucheng.smarthealthpro.care.zxing.camera;

import com.google.zxing.LuminanceSource;

/* loaded from: classes4.dex */
public final class PlanarYUVLuminanceSource extends LuminanceSource {
    private final int dataHeight;
    private final int dataWidth;
    private final int left;
    private final int top;
    private final byte[] yuvData;

    @Override // com.google.zxing.LuminanceSource
    public boolean isCropSupported() {
        return true;
    }

    public PlanarYUVLuminanceSource(byte[] yuvData, int dataWidth, int dataHeight, int left, int top, int width, int height) {
        super(width, height);
        if (width + left > dataWidth || height + top > dataHeight) {
            throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
        }
        this.yuvData = yuvData;
        this.dataWidth = dataWidth;
        this.dataHeight = dataHeight;
        this.left = left;
        this.top = top;
    }

    @Override // com.google.zxing.LuminanceSource
    public byte[] getRow(int y, byte[] row) {
        if (y < 0 || y >= getHeight()) {
            throw new IllegalArgumentException("Requested row is outside the image: " + y);
        }
        int width = getWidth();
        if (row == null || row.length < width) {
            row = new byte[width];
        }
        System.arraycopy(this.yuvData, ((y + this.top) * this.dataWidth) + this.left, row, 0, width);
        return row;
    }

    @Override // com.google.zxing.LuminanceSource
    public byte[] getMatrix() {
        int width = getWidth();
        int height = getHeight();
        int i2 = this.dataWidth;
        if (width == i2 && height == this.dataHeight) {
            return this.yuvData;
        }
        int i3 = width * height;
        byte[] bArr = new byte[i3];
        int i4 = (this.top * i2) + this.left;
        if (width == i2) {
            System.arraycopy(this.yuvData, i4, bArr, 0, i3);
            return bArr;
        }
        byte[] bArr2 = this.yuvData;
        for (int i5 = 0; i5 < height; i5++) {
            System.arraycopy(bArr2, i4, bArr, i5 * width, width);
            i4 += this.dataWidth;
        }
        return bArr;
    }
}
