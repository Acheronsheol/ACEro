package moe.shigure.acero.ui.search;

import java.util.ArrayList;

import moe.shigure.acero.base.presenter.IBasePresenter;
import moe.shigure.acero.base.view.IBaseView;
import moe.shigure.acero.bean.BookSimpleInfo;
import moe.shigure.acero.bean.NHentaiSearchResult;

/**
 * Created by Shigure on 2020/11/4
 **/

public class SearchContract {

    interface ISearchView extends IBaseView {

        void refreshSearchResult(NHentaiSearchResult searchResult, ArrayList<BookSimpleInfo> bookSimpleInfos);

    }

    interface ISearchPresenter extends IBasePresenter {

        void getSearchResult(String keyWord, int page);

    }

}
