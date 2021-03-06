package cn.hikyson.godeye.core.internal.modules.battery;

import android.content.Context;

import cn.hikyson.godeye.core.internal.Install;
import cn.hikyson.godeye.core.internal.ProduceableSubject;
import cn.hikyson.godeye.core.utils.L;


public class Battery extends ProduceableSubject<BatteryInfo> implements Install<BatteryContext> {
    private BatteryEngine mBatteryEngine;

    @Override
    public synchronized void install(BatteryContext config) {
        if (mBatteryEngine != null) {
            L.d("battery already installed, ignore.");
            return;
        }
        mBatteryEngine = new BatteryEngine(config.context(), this, config.intervalMillis());
        mBatteryEngine.work();
        L.d("battery installed.");
    }

    @Override
    public synchronized void uninstall() {
        if (mBatteryEngine == null) {
            L.d("battery already uninstalled, ignore.");
            return;
        }
        mBatteryEngine.shutdown();
        mBatteryEngine = null;
        L.d("battery uninstalled.");
    }
}
