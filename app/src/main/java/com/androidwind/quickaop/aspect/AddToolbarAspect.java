package com.androidwind.quickaop.aspect;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.androidwind.quickaop.R;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import androidx.fragment.app.FragmentActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */

@Aspect
public class AddToolbarAspect {

    private final String TAG = "AddToolbarAspect";

    @After("execution(* android.app.Activity.onCreate(..)) && within(@com.androidwind.quickaop.annotation.AddToolbar *)")
    public void addToolbar(JoinPoint joinPoint) throws Throwable {
        FragmentActivity activity = null;
        String signatureStr = joinPoint.getSignature().toString();
        Log.d(TAG, "[addToolbar]" + signatureStr);
        final Object object = joinPoint.getThis();
        if (object instanceof FragmentActivity) {
            activity = (FragmentActivity) object;
            TextView tv = activity.findViewById(R.id.toolbar);
            tv.setVisibility(View.VISIBLE);
        }
    }

    @Before("call(* android.widget.Toast.show())")
    public void changeToast(JoinPoint joinPoint) throws Throwable {
        Toast toast = (Toast) joinPoint.getTarget();
        toast.setText("修改后的toast");
        Log.d(TAG, "[changeToast]");
    }
}
