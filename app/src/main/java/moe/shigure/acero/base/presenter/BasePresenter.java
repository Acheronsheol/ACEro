package moe.shigure.acero.base.presenter;

import moe.shigure.acero.base.view.IBaseView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.SoftReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public abstract class BasePresenter<V extends IBaseView> implements IBasePresenter {

    private SoftReference<IBaseView> mReferenceView;
    private V mProxyView;

    @SuppressWarnings({"unchecked"})
    @Override
    public void attach(IBaseView view) {
        //使用软引用创建对象
        mReferenceView = new SoftReference<>(view);
        //使用动态代理做统一的逻辑判断 aop 思想
        mProxyView = (V) Proxy.newProxyInstance(view.getClass().getClassLoader(), view.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                if (mReferenceView == null || mReferenceView.get() == null) {
                    return null;
                }
                return method.invoke(mReferenceView.get(), objects);
            }
        });
    }

    public V getView() {
        return mProxyView;
    }

    @Override
    public void detach() {
        mReferenceView.clear();
        mReferenceView = null;
    }
}
