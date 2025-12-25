package com.yucheng.smarthealthpro.me.bean;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes5.dex */
public class MeHelpIssueBean implements Serializable {
    public int code;
    public List<DataBean> data;
    public String message;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return this.data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        public String code;
        public String createBy;
        public String createTime;
        public int level;
        public String moduleId;
        public String name;
        public String questionId;
        public Object updateBy;
        public Object updateTime;
        public String url;

        public DataBean(String questionId, String moduleId, String url, int level, String name, String code, String createBy, String createTime, Object updateBy, Object updateTime) {
            this.questionId = questionId;
            this.moduleId = moduleId;
            this.url = url;
            this.level = level;
            this.name = name;
            this.code = code;
            this.createBy = createBy;
            this.createTime = createTime;
            this.updateBy = updateBy;
            this.updateTime = updateTime;
        }

        public String getQuestionId() {
            return this.questionId;
        }

        public void setQuestionId(String questionId) {
            this.questionId = questionId;
        }

        public String getModuleId() {
            return this.moduleId;
        }

        public void setModuleId(String moduleId) {
            this.moduleId = moduleId;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getLevel() {
            return this.level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return this.code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCreateBy() {
            return this.createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return this.createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateBy() {
            return this.updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public Object getUpdateTime() {
            return this.updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }
    }
}
