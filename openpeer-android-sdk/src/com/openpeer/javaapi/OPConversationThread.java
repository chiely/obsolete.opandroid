/*******************************************************************************
 *
 *  Copyright (c) 2014 , Hookflash Inc.
 *  All rights reserved.
 *  
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *  
 *  1. Redistributions of source code must retain the above copyright notice, this
 *  list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright notice,
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 *  
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 *  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 *  ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *  
 *  The views and conclusions contained in the software and documentation are those
 *  of the authors and should not be interpreted as representing official policies,
 *  either expressed or implied, of the FreeBSD Project.
 *******************************************************************************/
package com.openpeer.javaapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.openpeer.sdk.app.OPDataManager;
import com.openpeer.sdk.model.OPUser;

import android.text.format.Time;
import android.util.Log;

public class OPConversationThread {

    /**
     * Helper function to make sure required fields are populated.
     * 
     * @param messageID
     * @return
     */
    public OPMessage getMessageById(String messageID) {
        OPMessage message = getMessage(messageID);
        OPContact from = message.getFrom();
        OPUser user = OPDataManager.getInstance().getUserForMessage(from, this);
        message.setSenderId(user.getUserId());
        return message;
    }

    private long nativeClassPointer;

    // START OF JNI -- DON'T TOUCH THE SIGNATURES!!!
    public static native String toString(MessageDeliveryStates state);

    public static native String toString(ContactConnectionStates state);

    public static native String toDebugString(OPConversationThread thread,
            boolean includeCommaPrefix);

    public static native OPConversationThread create(OPAccount account,
            List<OPIdentityContact> identityContactsOfSelf);

    public static native List<OPConversationThread> getConversationThreads(
            OPAccount account);

    public static native OPConversationThread getConversationThreadByID(
            OPAccount account, String threadID);

    public native long getID();

    public native String getThreadID();

    public native boolean amIHost();

    public native OPAccount getAssociatedAccount();

    public native List<OPContact> getContacts();

    public native List<OPIdentityContact> getIdentityContactList(
            OPContact contact);

    public native ContactConnectionStates getContactConnectionState(
            OPContact contact);

    public native void addContacts(
            List<OPContactProfileInfo> contactProfileInfos);

    public native void removeContacts(List<OPContact> contacts);

    /**
     * sending a message will cause the message to be delivered to all the contacts currently in the conversation
     * 
     * @param messageID
     * @param replacesMessageID
     *            The existing messageId to be replaced
     * @param messageType
     * @param message
     * @param signMessage
     *            whether or not to sign the message
     */
    public native void sendMessage(String messageID, String replacesMessageID,
            String messageType, String message, boolean signMessage);

    private native OPMessage getMessage(String messageID);

    public native MessageDeliveryStates getMessageDeliveryState(String messageID);

    public static native OPElement createEmptyStatus();

    public native OPElement getContactStatus(OPContact contact);

    public native void setStatusInThread(OPElement contactStatusInThreadOfSelf);

    public native void markAllMessagesRead();

    private native void releaseCoreObjects();

    protected void finalize() throws Throwable {

        if (nativeClassPointer != 0) {
            Log.d("output", "Cleaning conversation thread core objects");
            releaseCoreObjects();
        }

        super.finalize();
    }

    // END OF JNI

    @Override
    public boolean equals(Object o) {
        return o instanceof OPConversationThread
                && this.getID() == ((OPConversationThread) o).getID();
    }

}
