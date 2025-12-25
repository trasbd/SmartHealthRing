package com.yucheng.smarthealthpro.database.room;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.yucheng.smarthealthpro.database.room.dao.BloodKetonesDao;
import com.yucheng.smarthealthpro.database.room.dao.BloodLipidsDao;
import com.yucheng.smarthealthpro.database.room.dao.BloodPressureDao;
import com.yucheng.smarthealthpro.database.room.dao.BodyDataDao;
import com.yucheng.smarthealthpro.database.room.dao.EcgMeasureDao;
import com.yucheng.smarthealthpro.database.room.dao.HealthMetricDao;
import com.yucheng.smarthealthpro.database.room.dao.HeartRateDao;
import com.yucheng.smarthealthpro.database.room.dao.MotionPatternDao;
import com.yucheng.smarthealthpro.database.room.dao.PhysiotherapyDao;
import com.yucheng.smarthealthpro.database.room.dao.SleepDao;
import com.yucheng.smarthealthpro.database.room.dao.SportRecordDao;
import com.yucheng.smarthealthpro.database.room.dao.StepDao;
import com.yucheng.smarthealthpro.database.room.dao.UricAcidDao;
import com.yucheng.smarthealthpro.database.room.migration.HealthMigration40_41;
import com.yucheng.smarthealthpro.database.room.migration.HealthMigration41_42;
import com.yucheng.smarthealthpro.utils.Constant;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppDatabase.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\rH&J\b\u0010\u000e\u001a\u00020\u000fH&J\b\u0010\u0010\u001a\u00020\u0011H&J\b\u0010\u0012\u001a\u00020\u0013H&J\b\u0010\u0014\u001a\u00020\u0015H&J\b\u0010\u0016\u001a\u00020\u0017H&J\b\u0010\u0018\u001a\u00020\u0019H&J\b\u0010\u001a\u001a\u00020\u001bH&J\b\u0010\u001c\u001a\u00020\u001dH&¨\u0006\u001f"}, d2 = {"Lcom/yucheng/smarthealthpro/database/room/AppDatabase;", "Landroidx/room/RoomDatabase;", "<init>", "()V", "bloodKetonesDao", "Lcom/yucheng/smarthealthpro/database/room/dao/BloodKetonesDao;", "bloodLipidsDao", "Lcom/yucheng/smarthealthpro/database/room/dao/BloodLipidsDao;", "bloodPressureDao", "Lcom/yucheng/smarthealthpro/database/room/dao/BloodPressureDao;", "bodyDataDao", "Lcom/yucheng/smarthealthpro/database/room/dao/BodyDataDao;", "ecgMeasureDao", "Lcom/yucheng/smarthealthpro/database/room/dao/EcgMeasureDao;", "healthMetricDao", "Lcom/yucheng/smarthealthpro/database/room/dao/HealthMetricDao;", "heartRateDao", "Lcom/yucheng/smarthealthpro/database/room/dao/HeartRateDao;", "motionPatternDao", "Lcom/yucheng/smarthealthpro/database/room/dao/MotionPatternDao;", "physiotherapyDao", "Lcom/yucheng/smarthealthpro/database/room/dao/PhysiotherapyDao;", "sleepDao", "Lcom/yucheng/smarthealthpro/database/room/dao/SleepDao;", "sportRecordDao", "Lcom/yucheng/smarthealthpro/database/room/dao/SportRecordDao;", "stepDao", "Lcom/yucheng/smarthealthpro/database/room/dao/StepDao;", "uricAcidDao", "Lcom/yucheng/smarthealthpro/database/room/dao/UricAcidDao;", "Companion", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public abstract class AppDatabase extends RoomDatabase {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public abstract BloodKetonesDao bloodKetonesDao();

    public abstract BloodLipidsDao bloodLipidsDao();

    public abstract BloodPressureDao bloodPressureDao();

    public abstract BodyDataDao bodyDataDao();

    public abstract EcgMeasureDao ecgMeasureDao();

    public abstract HealthMetricDao healthMetricDao();

    public abstract HeartRateDao heartRateDao();

    public abstract MotionPatternDao motionPatternDao();

    public abstract PhysiotherapyDao physiotherapyDao();

    public abstract SleepDao sleepDao();

    public abstract SportRecordDao sportRecordDao();

    public abstract StepDao stepDao();

    public abstract UricAcidDao uricAcidDao();

    /* compiled from: AppDatabase.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ \u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0005H\u0002R\u0014\u0010\u0004\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0015"}, d2 = {"Lcom/yucheng/smarthealthpro/database/room/AppDatabase$Companion;", "", "<init>", "()V", "DB_NAME", "", "getDB_NAME", "()Ljava/lang/String;", "getInstance", "Lcom/yucheng/smarthealthpro/database/room/AppDatabase;", "context", "Landroid/content/Context;", "shouldDeleteOldDatabase", "", "databaseFile", "Ljava/io/File;", "oldVersionThreshold", "", "deleteDatabase", "", "dbName", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final String getDB_NAME() {
            if (Constant.isSmartHealth()) {
                return "smart_health.db";
            }
            return "health.db";
        }

        public final AppDatabase getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            File databasePath = context.getDatabasePath(getDB_NAME());
            Intrinsics.checkNotNull(databasePath);
            if (shouldDeleteOldDatabase(context, databasePath, 40)) {
                deleteDatabase(context, getDB_NAME());
                File file = new File(databasePath.getParent(), getDB_NAME() + "-journal");
                File file2 = new File(databasePath.getParent(), getDB_NAME() + "-shm");
                File file3 = new File(databasePath.getParent(), getDB_NAME() + "-wal");
                file.delete();
                file2.delete();
                file3.delete();
            }
            Context applicationContext = context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            return (AppDatabase) Room.databaseBuilder(applicationContext, AppDatabase.class, getDB_NAME()).addMigrations(new HealthMigration40_41(), new HealthMigration41_42()).build();
        }

        private final boolean shouldDeleteOldDatabase(Context context, File databaseFile, int oldVersionThreshold) throws Throwable {
            boolean z = false;
            if (!databaseFile.exists()) {
                return false;
            }
            SQLiteDatabase sQLiteDatabase = null;
            try {
                try {
                    SQLiteDatabase sQLiteDatabaseOpenDatabase = SQLiteDatabase.openDatabase(databaseFile.getPath(), null, 1);
                    try {
                        Cursor cursorRawQuery = sQLiteDatabaseOpenDatabase.rawQuery("PRAGMA user_version", null);
                        Intrinsics.checkNotNullExpressionValue(cursorRawQuery, "rawQuery(...)");
                        int i2 = cursorRawQuery.moveToFirst() ? cursorRawQuery.getInt(0) : 0;
                        cursorRawQuery.close();
                        if (i2 > 0 && i2 < oldVersionThreshold) {
                            z = true;
                        }
                        if (sQLiteDatabaseOpenDatabase != null) {
                            sQLiteDatabaseOpenDatabase.close();
                        }
                        return z;
                    } catch (Exception e2) {
                        e = e2;
                        sQLiteDatabase = sQLiteDatabaseOpenDatabase;
                        e.printStackTrace();
                        if (sQLiteDatabase == null) {
                            return true;
                        }
                        sQLiteDatabase.close();
                        return true;
                    } catch (Throwable th) {
                        th = th;
                        sQLiteDatabase = sQLiteDatabaseOpenDatabase;
                        if (sQLiteDatabase != null) {
                            sQLiteDatabase.close();
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e3) {
                e = e3;
            }
        }

        private final void deleteDatabase(Context context, String dbName) {
            context.deleteDatabase(dbName);
        }
    }
}
