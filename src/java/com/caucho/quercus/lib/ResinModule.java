package com.caucho.quercus.lib;

import com.caucho.quercus.module.*;
import com.caucho.quercus.env.Value;

import java.util.Map;

public class ResinModule implements com.caucho.quercus.module.QuercusModule {
    public Map<String, Value> getConstMap() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
    public String[] getLoadedExtensions() {
        return new String[0];  //To change body of implemented methods use File | Settings | File Templates.
    }
    public IniDefinitions getIniDefinitions() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}