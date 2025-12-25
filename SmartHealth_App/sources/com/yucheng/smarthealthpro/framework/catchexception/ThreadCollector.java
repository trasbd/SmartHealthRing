package com.yucheng.smarthealthpro.framework.catchexception;

/* loaded from: classes4.dex */
class ThreadCollector {
    ThreadCollector() {
    }

    static String collect(Thread thread) {
        StringBuilder sb = new StringBuilder();
        if (thread != null) {
            sb.append("id=").append(thread.getId()).append("\\nname=");
            sb.append(thread.getName()).append("\\npriority=");
            sb.append(thread.getPriority()).append("\\n");
            if (thread.getThreadGroup() != null) {
                sb.append("groupName=").append(thread.getThreadGroup().getName()).append("\\n");
            }
        }
        return sb.toString();
    }
}
