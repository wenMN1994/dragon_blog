package com.dragon.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dragon.common.utils.http.HttpUtils;

import java.io.Serializable;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/8/16 22:26
 * @description：QQ信息获取工具类
 * @modified By：
 * @version: 1.0.0
 */
public final class QQUtil {
    //QQ 头像和昵称查询地址
    private static final String QQ_QUERY_URL = "https://users.qzone.qq.com/fcg-bin/cgi_get_portrait.fcg";

    public static class QQInfo implements Serializable {
        private long QQNum;
        private String nickname;
        private String avatar;
        private String email;

        public QQInfo(long QQNum, String nickname, String avatar, String email) {
            this.QQNum = QQNum;
            this.nickname = nickname;
            this.avatar = avatar;
            this.email = email;
        }

        public long getQQNum() {
            return QQNum;
        }

        public void setQQNum(long QQNum) {
            this.QQNum = QQNum;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    /**
     * 根据QQ号码获取QQ信息
     *
     * @param QQNum qq号码
     * @return Optional
     */
    public static QQInfo getQQByQQNum(long QQNum) {
        String json = HttpUtils.sendGet(QQ_QUERY_URL, "uins=" + QQNum);
        QQInfo qqInfo = null;
        if (!StringUtils.isEmpty(json)) {
            json = json.replaceAll("portraitCallBack|\\\\s*|\\t|\\r|\\n", "");
            json = json.substring(1, json.length() - 1);
            JSONObject object = JSONObject.parseObject(json);
            JSONArray array = object.getJSONArray(String.valueOf(QQNum));
            qqInfo = new QQInfo(QQNum, array.getString(6), "https://q1.qlogo.cn/g?b=qq&nk=" + QQNum + "&s=40", QQNum + "@qq.com");
        }
        return qqInfo;
    }


    public static void main(String[] args) {
        getQQByQQNum(920310436);
    }
}
