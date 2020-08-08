package com.example.layoutinflaterinflateparamstudy.usecase;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.layoutinflaterinflateparamstudy.R;

/**
 * @author wangzhichao
 * @date 7/20/20
 */
public class MyFragment extends Fragment {
    private static final String TAG = "MyFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: container=" + container);
        // 打印信息: onCreateView: container=android.widget.FrameLayout{8e9d76b V.E...... ......ID 0,0-0,0 #7f070059 app:id/fl_container}
        // 第一种: ok
         return inflater.inflate(R.layout.my_fragment, container, false);
        // 第二种: 没有布局参数
//        return inflater.inflate(R.layout.my_fragment, null, false);
        // 第三种: 会报错
        /*
 FATAL EXCEPTION: main
 Process: com.example.layoutinflaterinflateparamstudy, PID: 26867
 java.lang.IllegalStateException: The specified child already has a parent. You must call removeView() on the child's parent first.
 	at android.view.ViewGroup.addViewInner(ViewGroup.java:5104)
 	at android.view.ViewGroup.addView(ViewGroup.java:4929)
 	at android.view.ViewGroup.addView(ViewGroup.java:4869)
 	at android.view.ViewGroup.addView(ViewGroup.java:4842)
 	at androidx.fragment.app.FragmentManagerImpl.moveToState(FragmentManagerImpl.java:887)
 	at androidx.fragment.app.FragmentManagerImpl.moveFragmentToExpectedState(FragmentManagerImpl.java:1238)
 	at androidx.fragment.app.FragmentManagerImpl.moveToState(FragmentManagerImpl.java:1303)
 	at androidx.fragment.app.BackStackRecord.executeOps(BackStackRecord.java:439)
 	at androidx.fragment.app.FragmentManagerImpl.executeOps(FragmentManagerImpl.java:2079)
 	at androidx.fragment.app.FragmentManagerImpl.executeOpsTogether(FragmentManagerImpl.java:1869)
 	at androidx.fragment.app.FragmentManagerImpl.removeRedundantOperationsAndExecute(FragmentManagerImpl.java:1824)
 	at androidx.fragment.app.FragmentManagerImpl.execPendingActions(FragmentManagerImpl.java:1727)
 	at androidx.fragment.app.FragmentManagerImpl.dispatchStateChange(FragmentManagerImpl.java:2663)
 	at androidx.fragment.app.FragmentManagerImpl.dispatchActivityCreated(FragmentManagerImpl.java:2613)
 	at androidx.fragment.app.FragmentController.dispatchActivityCreated(FragmentController.java:246)
 	at androidx.fragment.app.FragmentActivity.onStart(FragmentActivity.java:542)
 	at androidx.appcompat.app.AppCompatActivity.onStart(AppCompatActivity.java:201)
 	at android.app.Instrumentation.callActivityOnStart(Instrumentation.java:1391)
 	at android.app.Activity.performStart(Activity.java:7252)
 	at android.app.ActivityThread.handleStartActivity(ActivityThread.java:3001)
 	at android.app.servertransaction.TransactionExecutor.performLifecycleSequence(TransactionExecutor.java:185)
 	at android.app.servertransaction.TransactionExecutor.cycleToPath(TransactionExecutor.java:170)
 	at android.app.servertransaction.TransactionExecutor.executeLifecycleState(TransactionExecutor.java:147)
 	at android.app.servertransaction.TransactionExecutor.execute(TransactionExecutor.java:73)
 	at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1859)
 	at android.os.Handler.dispatchMessage(Handler.java:106)
 	at android.os.Looper.loop(Looper.java:201)
 	at android.app.ActivityThread.main(ActivityThread.java:6831)
 	at java.lang.reflect.Method.invoke(Native Method)
 	at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:547)
 	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:927)
        */
//        return inflater.inflate(R.layout.my_fragment, container, true);
        // 第四种: 有警告信息:
//        return inflater.inflate(R.layout.my_fragment, null);
    }

    public static MyFragment newInstance() {

        Bundle args = new Bundle();

        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
