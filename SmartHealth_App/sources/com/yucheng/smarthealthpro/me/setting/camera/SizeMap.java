package com.yucheng.smarthealthpro.me.setting.camera;

import android.util.ArrayMap;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/* loaded from: classes5.dex */
public class SizeMap {
    private final ArrayMap<AspectRatio, SortedSet<Size>> mRatios = new ArrayMap<>();

    public boolean add(Size size) {
        for (AspectRatio aspectRatio : this.mRatios.keySet()) {
            if (aspectRatio.matches(size)) {
                SortedSet<Size> sortedSet = this.mRatios.get(aspectRatio);
                if (sortedSet.contains(size)) {
                    return false;
                }
                sortedSet.add(size);
                return true;
            }
        }
        TreeSet treeSet = new TreeSet();
        treeSet.add(size);
        this.mRatios.put(AspectRatio.of(size.getWidth(), size.getHeight()), treeSet);
        return true;
    }

    public void remove(AspectRatio ratio) {
        this.mRatios.remove(ratio);
    }

    Set<AspectRatio> ratios() {
        return this.mRatios.keySet();
    }

    SortedSet<Size> sizes(AspectRatio ratio) {
        return this.mRatios.get(ratio);
    }

    void clear() {
        this.mRatios.clear();
    }

    boolean isEmpty() {
        return this.mRatios.isEmpty();
    }
}
