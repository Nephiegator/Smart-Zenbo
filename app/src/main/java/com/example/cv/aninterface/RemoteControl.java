package com.example.cv.aninterface;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.asus.robotframework.API.MotionControl;
import com.asus.robotframework.API.RobotAPI;
import com.asus.robotframework.API.RobotCallback;
import com.asus.robotframework.API.RobotCmdState;
import com.asus.robotframework.API.RobotCommand;
import com.asus.robotframework.API.RobotErrorCode;

import org.json.JSONObject;


public class RemoteControl extends Activity {

    public RobotAPI robotAPI;
    RobotCallback robotCallback;
    RobotCallback.Listen robotListenCallback;

    public void buttonBodyForwardClicked(View view) {
        robotAPI.motion.remoteControlBody(MotionControl.Direction.Body.FORWARD);
    }
    public void buttonBodyBackwardClicked(View view) {
        robotAPI.motion.remoteControlBody(MotionControl.Direction.Body.BACKWARD);
    }

    public void buttonBodyRightClicked(View view) {
        robotAPI.motion.remoteControlBody(MotionControl.Direction.Body.TURN_RIGHT);
    }

    public void buttonBodyLeftClicked(View view) {
        robotAPI.motion.remoteControlBody(MotionControl.Direction.Body.TURN_LEFT);
    }

    public void buttonBodyStopClicked(View view) {
        robotAPI.motion.remoteControlBody(MotionControl.Direction.Body.STOP);
    }

    public static RobotCallback robotCallback = new RobotCallback() {
        @Override
        public void onResult(int cmd, int serial, RobotErrorCode err_code, Bundle result) {
            super.onResult(cmd, serial, err_code, result);

            Log.d("RobotDevSample", "onResult:"
                    + RobotCommand.getRobotCommand(cmd).name()
                    + ", serial:" + serial + ", err_code:" + err_code
                    + ", result:" + result.getString("RESULT"));
        }

        @Override
        public void onStateChange(int cmd, int serial, RobotErrorCode err_code, RobotCmdState state) {
            super.onStateChange(cmd, serial, err_code, state);
        }

        @Override
        public void initComplete() {
            super.initComplete();

        }
    };

    public static RobotCallback.Listen robotListenCallback = new RobotCallback.Listen() {
        @Override
        public void onFinishRegister() {

        }

        @Override
        public void onVoiceDetect(JSONObject jsonObject) {

        }

        @Override
        public void onSpeakComplete(String s, String s1) {

        }

        @Override
        public void onEventUserUtterance(JSONObject jsonObject) {

        }

        @Override
        public void onResult(JSONObject jsonObject) {

        }

        @Override
        public void onRetry(JSONObject jsonObject) {

        }
    };

}
