package moe.shigure.acero.ui.detail;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gyf.immersionbar.ImmersionBar;

import java.text.SimpleDateFormat;

import moe.shigure.acero.R;
import moe.shigure.acero.base.inject.InjectPresenter;
import moe.shigure.acero.base.view.BaseActivity;
import moe.shigure.acero.bean.BookDetailInfo;
import moe.shigure.acero.bean.BookTags;
import moe.shigure.acero.ui.read.BookReadActivity;
import moe.shigure.acero.utils.ToastUtils;

/**
 * Created by Shigure on 2020/11/4
 **/

public class BookDetailActivity extends BaseActivity implements BookDetailContract.IBookDetailView, View.OnClickListener {

    @InjectPresenter
    private BookDetailPresenter mPresenter;

    private ImageView iv_book_thumb;
    private TextView tv_book_name;
    private TextView tv_book_favorites_num;
    private TextView tv_book_update_time;
    private TextView tv_book_tags;
    private TextView tv_book_read;

    private boolean isRandom;
    private String detailUrl;
    private String bookThumb;
    private String bookContentUrl;

    @Override
    protected int initLayout() {
        return R.layout.activity_book_detail;
    }

    @Override
    protected void initViews() {
        iv_book_thumb = $(R.id.iv_book_thumb);
        tv_book_name = $(R.id.tv_book_name);
        tv_book_favorites_num = $(R.id.tv_book_favorites_num);
        tv_book_update_time = $(R.id.tv_book_update_time);
        tv_book_tags = $(R.id.tv_book_tags);
        tv_book_read = $(R.id.tv_book_read);
    }

    @Override
    protected void initData() {
        ImmersionBar.with(this).statusBarDarkFont(true).init();

        tv_book_read.setOnClickListener(this);

        isRandom = getIntent().getBooleanExtra("random",false);
        detailUrl = getIntent().getStringExtra("url");
        bookThumb = getIntent().getStringExtra("thumb");
        mPresenter.getBookDetailInfo(detailUrl);

        if(isRandom){
            mPresenter.getRandomBookDetailInfo();
            ToastUtils.showShortToast("开始抽奖啦（๑ `▽´๑)");
        } else if(detailUrl!=null && !detailUrl.isEmpty()) {
            if(bookThumb!=null && !bookThumb.isEmpty()){
                String url;
                if(bookThumb.startsWith("/")){
                    url = "https://ero.raxianch.moe"+bookThumb;
                } else {
                    url = bookThumb;
                }
                Glide.with(this)
                        .load(url)
                        .placeholder(R.drawable.placeholder_pic_light)
                        .into(iv_book_thumb);
            }
            ToastUtils.showShortToast("正在加载...");
        }
    }

    @Override
    public void refreshBookDetailInfo(BookDetailInfo bookDetailInfo) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(detailUrl==null || detailUrl.isEmpty()){
            Glide.with(this)
                    .load(bookDetailInfo.getCover())
                    .placeholder(R.drawable.placeholder_pic_light)
                    .into(iv_book_thumb);
        }
        bookContentUrl = bookDetailInfo.getGalleries();
        mPresenter.preloadBookContent(bookContentUrl);
        tv_book_name.setText(bookDetailInfo.getTitle().getFullName());
        tv_book_favorites_num.setText(String.format("收藏：%d",bookDetailInfo.getFavorites()));
        tv_book_update_time.setText(String.format("上传时间：%s",dateFormat.format(bookDetailInfo.getUploadDate()*1000L)));
        StringBuilder tags = new StringBuilder();
        for (BookTags model : bookDetailInfo.getTags()){
            tags.append(model.getName()).append(" ");
        }
        tv_book_tags.setText("标签："+tags);
        tv_book_read.setText("开始阅读");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_book_read:
                if(bookContentUrl!=null && !bookContentUrl.isEmpty()) {
                    Intent intent = new Intent(this, BookReadActivity.class);
                    intent.putExtra("url", bookContentUrl);
                    startActivity(intent);
                }
                break;
        }
    }
}
