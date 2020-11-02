package moe.shigure.acero.utils;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.widget.Toast;

import moe.shigure.acero.app.ShigureApp;

import androidx.annotation.StringRes;

public class ToastUtils {

    private ToastUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    private static Toast mToast;
    private static Handler mHandler = new Handler(Looper.getMainLooper());

    /**
     * 安全地显示短时吐司
     *
     * @param text 文本
     */
    public static void showShortToastSafe(final CharSequence text) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                showToast(text, Toast.LENGTH_SHORT);
            }
        });
    }

    /**
     * 安全地显示短时吐司
     *
     * @param resId 资源Id
     */
    public static void showShortToastSafe(final @StringRes int resId) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                showToast(resId, Toast.LENGTH_SHORT);
            }
        });
    }

    /**
     * 安全地显示短时吐司
     *
     * @param resId 资源Id
     * @param args  参数
     */
    public static void showShortToastSafe(final @StringRes int resId, final Object... args) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                showToast(resId, Toast.LENGTH_SHORT, args);
            }
        });
    }

    /**
     * 安全地显示短时吐司
     *
     * @param format 格式
     * @param args   参数
     */
    public static void showShortToastSafe(final String format, final Object... args) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                showToast(format, Toast.LENGTH_SHORT, args);
            }
        });
    }

    /**
     * 安全地显示长时吐司
     *
     * @param text 文本
     */
    public static void showLongToastSafe(final CharSequence text) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                showToast(text, Toast.LENGTH_LONG);
            }
        });
    }

    /**
     * 安全地显示长时吐司
     *
     * @param resId 资源Id
     */
    public static void showLongToastSafe(final @StringRes int resId) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                showToast(resId, Toast.LENGTH_LONG);
            }
        });
    }

    /**
     * 安全地显示长时吐司
     *
     * @param resId 资源Id
     * @param args  参数
     */
    public static void showLongToastSafe(final @StringRes int resId, final Object... args) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                showToast(resId, Toast.LENGTH_LONG, args);
            }
        });
    }

    /**
     * 安全地显示长时吐司
     *
     * @param format 格式
     * @param args   参数
     */
    public static void showLongToastSafe(final String format, final Object... args) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                showToast(format, Toast.LENGTH_LONG, args);
            }
        });
    }

    /**
     * 显示短时吐司
     *
     * @param text 文本
     */
    public static void showShortToast(CharSequence text) {
        showToast(text, Toast.LENGTH_SHORT);
    }

    /**
     * 显示短时吐司
     *
     * @param resId 资源Id
     */
    public static void showShortToast(@StringRes int resId) {
        showToast(resId, Toast.LENGTH_SHORT);
    }

    /**
     * 显示短时吐司
     *
     * @param resId 资源Id
     * @param args  参数
     */
    public static void showShortToast(@StringRes int resId, Object... args) {
        showToast(resId, Toast.LENGTH_SHORT, args);
    }

    /**
     * 显示短时吐司
     *
     * @param format 格式
     * @param args   参数
     */
    public static void showShortToast(String format, Object... args) {
        showToast(format, Toast.LENGTH_SHORT, args);
    }

    /**
     * 显示长时吐司
     *
     * @param text 文本
     */
    public static void showLongToast(CharSequence text) {
        showToast(text, Toast.LENGTH_LONG);
    }

    /**
     * 显示长时吐司
     *
     * @param resId 资源Id
     */
    public static void showLongToast(@StringRes int resId) {
        showToast(resId, Toast.LENGTH_LONG);
    }

    /**
     * 显示长时吐司
     *
     * @param resId 资源Id
     * @param args  参数
     */
    public static void showLongToast(@StringRes int resId, Object... args) {
        showToast(resId, Toast.LENGTH_LONG, args);
    }

    /**
     * 显示长时吐司
     *
     * @param format 格式
     * @param args   参数
     */
    public static void showLongToast(String format, Object... args) {
        showToast(format, Toast.LENGTH_LONG, args);
    }

    /**
     * 显示吐司
     *
     * @param resId    资源Id
     * @param duration 显示时长
     */
    private static void showToast(@StringRes int resId, int duration) {
        showToast(ShigureApp.getContext().getResources().getText(resId).toString(), duration);
    }

    /**
     * 显示吐司
     *
     * @param resId    资源Id
     * @param duration 显示时长
     * @param args     参数
     */
    private static void showToast(@StringRes int resId, int duration, Object... args) {
        showToast(String.format(ShigureApp.getContext().getResources().getString(resId), args), duration);
    }

    /**
     * 显示吐司
     *
     * @param format   格式
     * @param duration 显示时长
     * @param args     参数
     */
    private static void showToast(String format, int duration, Object... args) {
        showToast(String.format(format, args), duration);
    }

    /**
     * 显示吐司
     *
     * @param text     文本
     * @param duration 显示时长
     */
    private static void showToast(CharSequence text, int duration) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.P) {
            Toast toast = Toast.makeText(ShigureApp.getContext(), text, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            if (mToast == null) {
                mToast = Toast.makeText(ShigureApp.getContext(), text, duration);
                mToast.setGravity(Gravity.CENTER, 0, 0);
            } else {
                mToast.setDuration(duration);
                mToast.setText(text);
            }
            mToast.show();
        }
    }

}
