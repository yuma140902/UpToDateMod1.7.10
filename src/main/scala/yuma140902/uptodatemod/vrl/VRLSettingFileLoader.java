package yuma140902.uptodatemod.vrl;

import com.google.gson.Gson;
import org.apache.commons.codec.digest.DigestUtils;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.vrl.model.VRLSetting;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Jarに埋め込まれたVRLの設定ファイルを読み込む
 */
public class VRLSettingFileLoader {
    private String settingFileName;

    public VRLSettingFileLoader(String settingFileName) {
        this.settingFileName = settingFileName;
    }
    public String loadSettingHash() throws VRLException {
        try {
            InputStream stream = ModUpToDateMod.class.getResourceAsStream(settingFileName);
            if(stream == null) throw new VRLException(settingFileName + " not found");
            return DigestUtils.md5Hex(stream);
        } catch (IOException e) {
           throw new VRLException("Failed to Open VRL " + settingFileName, e);
        }
    }

    public VRLSetting loadSetting() throws VRLException {
        Gson gson = new Gson();
        InputStream stream = ModUpToDateMod.class.getResourceAsStream(settingFileName);
        if(stream == null) throw new VRLException(settingFileName + " not found");
        InputStreamReader reader = new InputStreamReader(stream);
        return gson.fromJson(reader, VRLSetting.class);
    }
}
