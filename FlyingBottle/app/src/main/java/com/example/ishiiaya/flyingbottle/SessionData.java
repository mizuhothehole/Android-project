package com.example.ishiiaya.flyingbottle;

/**
 * Created by ishiiaya on 2017/04/29.
 */

public class SessionData {

    private SessionData() {
    }

    private static SessionData mSessionData = null;

    public static SessionData getSessionInstance() {
        if (null == mSessionData) {
            mSessionData = new SessionData();
        }
        return mSessionData;
    }

    public void initialize() {
        mSessionData.setMessageGot(StringUtil.EMPTY);
        mSessionData.setMessageToBeSent(StringUtil.EMPTY);
        mSessionData.setThrown(false);
        mSessionData.setGotActive(true);
        mSessionData.setSentActive(false);
    }

    public void fillBottleData(String bottleMsg) {
        mSessionData.setMessageGot(bottleMsg);
        mSessionData.setMessageToBeSent(bottleMsg);
        mSessionData.setThrown(false);
        mSessionData.setGotActive(true);
        mSessionData.setSentActive(false);
    }

    public void makeEmptyBottle() {
        mSessionData.setMessageGot(StringUtil.EMPTY);
        mSessionData.setMessageToBeSent(StringUtil.EMPTY);
        mSessionData.setThrown(false);
        mSessionData.setGotActive(false);
        mSessionData.setSentActive(true);
    }

    private String messageGot = null;
    private String messageToBeSent = null;
    private boolean gotActive = false;
    private boolean sentActive = false;
    private boolean isThrown = false;

    public String getMessageGot() {
        return this.messageGot;
    }

    public void setMessageGot(String messageGot) {
        this.messageGot = messageGot;
    }

    public String getMessageToBeSent() {
        return this.messageToBeSent;
    }

    public void setMessageToBeSent(String messageToBeSent) {
        this.messageToBeSent = messageToBeSent;
    }

    public boolean isGotActive() {
        return gotActive;
    }

    public void setGotActive(boolean gotActive) {
        this.gotActive = gotActive;
    }

    public boolean isSentActive() {
        return sentActive;
    }

    public void setSentActive(boolean sentActive) {
        this.sentActive = sentActive;
    }

    public boolean isThrown() {
        return isThrown;
    }

    public void setThrown(boolean thrown) {
        isThrown = thrown;
    }
}
