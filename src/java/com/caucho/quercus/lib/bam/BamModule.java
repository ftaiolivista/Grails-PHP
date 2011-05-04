package com.caucho.quercus.lib.bam;

import com.caucho.quercus.module.QuercusModule;
import com.caucho.quercus.module.IniDefinitions;
import com.caucho.quercus.env.Value;
import com.caucho.quercus.env.StringValue;

import java.util.Map;

public class BamModule implements QuercusModule {
    public Map<StringValue, Value> getConstMap() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
    public String[] getLoadedExtensions() {
        return new String[0];  //To change body of implemented methods use File | Settings | File Templates.
    }
    public IniDefinitions getIniDefinitions() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
