package moe.shigure.acero.ui;

import java.util.ArrayList;

import moe.shigure.acero.base.presenter.IBasePresenter;
import moe.shigure.acero.base.view.IBaseView;

/**
 * Created by Shigure on 2020/11/4
 **/

public class MainContract{

    interface IMainView extends IBaseView {

        void getPic(ArrayList<String> picStr);

    }

    interface IMainPresenter extends IBasePresenter {

        void getData();

    }

}
