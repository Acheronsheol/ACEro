package moe.shigure.acero.base.proxy;

import moe.shigure.acero.base.view.IBaseView;

public class ProxyActivity<V extends IBaseView> extends ProxyImpl {
    public ProxyActivity(V view) {
        super(view);
    }
}
