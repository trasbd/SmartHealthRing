package com.yucheng.smarthealthpro.settings.uploadnativedata;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.gyf.immersionbar.ImmersionBar;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityArmBloodPressureMeasurementBinding;
import com.yucheng.smarthealthpro.framework.HealthApplication;
import com.yucheng.smarthealthpro.me.setting.SettingsDataType;
import com.yucheng.smarthealthpro.utils.Tools;
import jsc.kit.wheel.base.WheelItem;
import jsc.kit.wheel.dialog.ColumnWheelDialog;

/* loaded from: classes5.dex */
public class ArmBloodPressureMeasurementActivity extends BaseVbActivity<ActivityArmBloodPressureMeasurementBinding> {
    private TextView bp_measure;
    private TextView tv_step_instructions_next;
    private int sbp = SettingsDataType.MORE_SETTINGS;
    private int dbp = 90;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initData();
    }

    private void init() {
        changeTitle(getString(R.string.home_blood_pressure_measure_title));
        showBack();
        ImmersionBar.with(this).statusBarDarkFont(true, 0.2f).navigationBarDarkIcon(true, 0.2f).init();
        this.tv_step_instructions_next = (TextView) findViewById(R.id.tv_step_instructions_next);
        this.bp_measure = (TextView) findViewById(R.id.bp_measure);
    }

    private void initData() {
        this.bp_measure.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.settings.uploadnativedata.ArmBloodPressureMeasurementActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) throws Resources.NotFoundException {
                ArmBloodPressureMeasurementActivity.this.showDialog();
            }
        });
        this.tv_step_instructions_next.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.settings.uploadnativedata.ArmBloodPressureMeasurementActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                System.out.println("chong--------bp==" + ((Object) ArmBloodPressureMeasurementActivity.this.bp_measure.getText()));
                if ("".equals(ArmBloodPressureMeasurementActivity.this.bp_measure.getText().toString())) {
                    ArmBloodPressureMeasurementActivity armBloodPressureMeasurementActivity = ArmBloodPressureMeasurementActivity.this;
                    Toast.makeText(armBloodPressureMeasurementActivity, armBloodPressureMeasurementActivity.getString(R.string.arm_blood_pressure_measurement_blood_value), 1).show();
                } else {
                    ArmBloodPressureMeasurementActivity.this.startActivity(new Intent(ArmBloodPressureMeasurementActivity.this, (Class<?>) RealBloodPressureMeasureActivity.class));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ColumnWheelDialog showDialog() throws Resources.NotFoundException {
        ColumnWheelDialog columnWheelDialog = new ColumnWheelDialog(this);
        columnWheelDialog.show();
        columnWheelDialog.setTextSize(getResources().getDisplayMetrics().density * 16.0f);
        columnWheelDialog.setItemVerticalSpace((int) (getResources().getDisplayMetrics().density * 25.0f));
        columnWheelDialog.setTitle(getString(R.string.pls_select));
        columnWheelDialog.setCancelButton(getString(R.string.cancel), null);
        columnWheelDialog.setUnit(getString(R.string.calibration_selector_sbp), getString(R.string.calibration_selector_dbp), "", "", "");
        columnWheelDialog.setOKButton(getString(R.string.ok), new ColumnWheelDialog.OnClickCallBack<WheelItem, WheelItem, WheelItem, WheelItem, WheelItem>() { // from class: com.yucheng.smarthealthpro.settings.uploadnativedata.ArmBloodPressureMeasurementActivity.3
            @Override // jsc.kit.wheel.dialog.ColumnWheelDialog.OnClickCallBack
            public boolean callBack(View v, WheelItem item0, WheelItem item1, WheelItem item2, WheelItem item3, WheelItem item4) {
                ArmBloodPressureMeasurementActivity.this.dbp = Integer.parseInt(item1.getShowText());
                ArmBloodPressureMeasurementActivity.this.sbp = Integer.parseInt(item0.getShowText());
                if (ArmBloodPressureMeasurementActivity.this.sbp - ArmBloodPressureMeasurementActivity.this.dbp < 20 || ArmBloodPressureMeasurementActivity.this.sbp - ArmBloodPressureMeasurementActivity.this.dbp > 80) {
                    ArmBloodPressureMeasurementActivity armBloodPressureMeasurementActivity = ArmBloodPressureMeasurementActivity.this;
                    Toast.makeText(armBloodPressureMeasurementActivity, armBloodPressureMeasurementActivity.getString(R.string.blood_calibration_error1), 1).show();
                    return false;
                }
                ArmBloodPressureMeasurementActivity.this.bp_measure.setText(ArmBloodPressureMeasurementActivity.this.sbp + "/" + ArmBloodPressureMeasurementActivity.this.dbp);
                Tools.saveInt("measure_dbp", ArmBloodPressureMeasurementActivity.this.dbp, HealthApplication.getInstance());
                Tools.saveInt("measure_sbp", ArmBloodPressureMeasurementActivity.this.sbp, HealthApplication.getInstance());
                return false;
            }
        });
        columnWheelDialog.setItems(initItems(85, 220), initItems(45, Opcodes.FCMPG), initItems(0, 0), initItems(0, 0), initItems(0, 0));
        columnWheelDialog.setSelected(this.sbp - 85, this.dbp - 45, 0, 0, 0);
        return columnWheelDialog;
    }

    private WheelItem[] initItems(int min, int max) {
        WheelItem[] wheelItemArr = new WheelItem[(max - min) + 1];
        for (int i2 = min; i2 <= max; i2++) {
            wheelItemArr[i2 - min] = new WheelItem(i2 + "");
        }
        return wheelItemArr;
    }
}
