package com.yucheng.smarthealthpro.database.room.migration;

import android.database.SQLException;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.facebook.appevents.UserDataStore;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HealthMigration40_41.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/yucheng/smarthealthpro/database/room/migration/HealthMigration40_41;", "Landroidx/room/migration/Migration;", "<init>", "()V", "migrate", "", UserDataStore.DATE_OF_BIRTH, "Landroidx/sqlite/db/SupportSQLiteDatabase;", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class HealthMigration40_41 extends Migration {
    public HealthMigration40_41() {
        super(40, 41);
    }

    @Override // androidx.room.migration.Migration
    public void migrate(SupportSQLiteDatabase db) throws SQLException {
        Intrinsics.checkNotNullParameter(db, "db");
        db.execSQL("CREATE TABLE IF NOT EXISTS \"BODY_DATA_DB\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"QUERY_ID\" INTEGER NOT NULL ,\"TIME\" INTEGER NOT NULL ,\"TIME_YEAR_TO_DATE\" TEXT,\"LOAD_INDEX_INTEGER\" INTEGER NOT NULL ,\"LOAD_INDEX_FLOAT\" INTEGER NOT NULL ,\"HRV_INTEGER\" INTEGER NOT NULL ,\"HRV_FLOAT\" INTEGER NOT NULL ,\"PRESSURE_INTEGER\" INTEGER NOT NULL ,\"PRESSURE_FLOAT\" INTEGER NOT NULL ,\"BODY_INTEGER\" INTEGER NOT NULL ,\"BODY_FLOAT\" INTEGER NOT NULL ,\"SYMPATHETIC_INTEGER\" INTEGER NOT NULL ,\"SYMPATHETIC_FLOAT\" INTEGER NOT NULL ,\"SDN\" INTEGER NOT NULL ,\"MAXIMAL_OXYGEN_INTAKE\" INTEGER NOT NULL ,\"USER_ID\" TEXT,\"DEVICE_TYPE\" TEXT,\"DEVICE_MAC\" TEXT,\"BELONG_DATA_GROUP_ID\" TEXT,\"IS_UPLOAD\" INTEGER NOT NULL );");
    }
}
