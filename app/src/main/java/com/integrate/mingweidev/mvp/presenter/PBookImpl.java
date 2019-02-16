package com.integrate.mingweidev.mvp.presenter;

import android.content.Context;

import com.integrate.mingweidev.mvp.base.BasePresenter;
import com.integrate.mingweidev.mvp.contract.CBook;
import com.integrate.mingweidev.mvp.model.MBookImpl;
import com.integrate.mingweidev.mvp.view.activity.BookActivity;

import java.util.Map;


/**
 * Created by Liang_Lu on 2017/12/21.
 * P层 此类只用于处理业务逻辑 然后把最终的结果回调给V层
 */

public class PBookImpl extends BasePresenter<CBook.IVBook, MBookImpl> implements CBook.IPBook {

    BookActivity bookActivity;
    public PBookImpl(Context mContext, CBook.IVBook mView) {
        super(mContext, mView, new MBookImpl());
        if(mView instanceof BookActivity) {
            bookActivity = (BookActivity) mView;
        }
    }


    @Override
    public void pBook() {
        mView.showLoading();
//        mModel.mBook(new RxObservable<BookBean>() {
//
//            @Override
//            public void onSuccess(BookBean bean) {
//                mView.hideLoading();
//                mView.vBookSuccess(bean);
//            }
//
//            @Override
//            public void onFail(String reason) {
//                mView.hideLoading();
//                mView.vBookError(reason);
//            }
//        });
//        RxHttpUtils.downloadFile("https://appbundle.holdsoft.cn/holdstore_18102701.apk")
//                .subscribe(new DownloadObserver("mingwei.apk") {
//            @Override
//            protected void getDisposable(Disposable disposable) {
//
//            }
//
//            @Override
//            protected void onError(String s) {
//
//            }
//
//            @Override
//            protected void onSuccess(long l, long l1, float v, boolean b, String s) {
//                bookActivity.setProsess(l,l1,v,b,s);
//            }
//        });
    }

    @Override
    public void ppicBook(Map<String,String> image) {

    }

}
