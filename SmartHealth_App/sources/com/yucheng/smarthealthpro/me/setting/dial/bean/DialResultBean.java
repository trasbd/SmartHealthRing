package com.yucheng.smarthealthpro.me.setting.dial.bean;

import com.dd.plist.ASCIIPropertyListParser;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class DialResultBean implements Serializable {
    public int code;
    public List<Data> data = new ArrayList();
    public String message;

    public class Data implements Serializable {
        public String backgroundImgUrl;
        public int blockNumber;
        public String deviceType;
        public String dialVersion;
        public int dialplateId;
        public boolean enable = true;
        public String fileName;
        public int height;
        public String imgName;
        public boolean isCanDelete;
        public boolean isDelete;
        public boolean isUpdate;
        public String name;
        public int progress;
        public int state;
        public int width;

        public Data() {
        }

        public String toString() {
            return "Data{dialplateId=" + this.dialplateId + ", name='" + this.name + "', blockNumber=" + this.blockNumber + ", fileName='" + this.fileName + "', imgName='" + this.imgName + "', progress=" + this.progress + ", state=" + this.state + ", isUpdate=" + this.isUpdate + ", enable=" + this.enable + ", width=" + this.width + ", height=" + this.height + ", deviceType='" + this.deviceType + "', isCanDelete=" + this.isCanDelete + ", isDelete=" + this.isDelete + ", dialVersion='" + this.dialVersion + "', backgroundImgUrl='" + this.backgroundImgUrl + "'}";
        }
    }

    public Data getData() {
        return new Data();
    }

    public String toString() {
        return "DialResultBean{code=" + this.code + ", message='" + this.message + "', data=" + this.data + ASCIIPropertyListParser.DICTIONARY_END_TOKEN;
    }
}
