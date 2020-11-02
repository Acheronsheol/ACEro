package moe.shigure.acero.base.presenter;

import moe.shigure.acero.base.view.IBaseView;

public interface IBasePresenter {

    void attach(IBaseView view);

    void detach();

}