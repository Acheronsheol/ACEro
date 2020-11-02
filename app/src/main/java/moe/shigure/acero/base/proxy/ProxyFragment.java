package moe.shigure.acero.base.proxy;

import moe.shigure.acero.base.view.IBaseView;

public class ProxyFragment<V extends IBaseView> extends ProxyImpl {
    public ProxyFragment(V view) {
        super(view);
    }
}