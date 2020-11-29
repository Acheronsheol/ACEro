package moe.shigure.acero.ui.read;

import java.util.ArrayList;

import moe.shigure.acero.base.presenter.IBasePresenter;
import moe.shigure.acero.base.view.IBaseView;

/**
 * Created by Shigure on 2020/11/4
 **/

public class BookReadContract {

    interface IMainView extends IBaseView {

        void getPic(ArrayList<String> picStr);

    }

    interface IMainPresenter extends IBasePresenter {

        void getBookContent(String url);

    }

}
