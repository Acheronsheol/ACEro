package moe.shigure.acero.ui.detail;

import java.util.ArrayList;

import moe.shigure.acero.base.presenter.IBasePresenter;
import moe.shigure.acero.base.view.IBaseView;
import moe.shigure.acero.bean.BookDetailInfo;
import moe.shigure.acero.bean.BookSimpleInfo;

/**
 * Created by Shigure on 2020/11/4
 **/

public class BookDetailContract {

    interface IBookDetailView extends IBaseView {

        void refreshBookDetailInfo(BookDetailInfo bookDetailInfo);

    }

    interface IBookDetailPresenter extends IBasePresenter {

        void getBookDetailInfo(String url);

    }

}
