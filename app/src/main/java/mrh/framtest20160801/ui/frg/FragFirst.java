package mrh.framtest20160801.ui.frg;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;

import autolayout.utils.AutoUtils;
import mrh.framtest20160801.R;
import mrh.framtest20160801.configs.interfaces.Member;
import mrh.framtest20160801.configs.utils.JSONUtils;
import mrh.framtest20160801.configs.utils.ListUtils;
import mrh.framtest20160801.configs.utils.LogUtils;
import mrh.framtest20160801.mybannerview.convenientbanner.ConvenientBanner;
import mrh.framtest20160801.mybannerview.convenientbanner.holder.CBViewHolderCreator;
import mrh.framtest20160801.mybannerview.convenientbanner.holder.Holder;
import mrh.framtest20160801.mybannerview.convenientbanner.listener.OnItemClickListener;
import mrh.framtest20160801.ui.base.BaseFragment;
import mrh.framtest20160801.ui.base.ImageLoader;
import xutils.http.RequestParams;
import xutils.image.ImageOptions;
import xutils.view.annotation.ViewInject;


/**
 * @author Yiku
 * @date 2016/7/20 17:33
 */
public class FragFirst extends BaseFragment implements OnItemClickListener {
    private Member member;
    @ViewInject(R.id.fra1_image)
    private ImageView imageView;
    @ViewInject(R.id.index_banner)
    private ConvenientBanner banner;
    private ArrayList<Integer> localImages = new ArrayList<Integer>();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        banner.setPages(new );
        if (!ListUtils.isEmpty(localImages))localImages.clear();
        for (int position = 0; position < 7; position++)
            localImages.add(getResId("ic_test_" + position, R.drawable.class));

        //本地图片例子
        banner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, localImages)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.home_ad_select, R.drawable.home_as_default})
                //设置指示器的方向
//                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
//                .setOnPageChangeListener(this)//监听翻页事件
                .setOnItemClickListener(this);
    }
    public static int getResId(String variableName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }
    @Override
    protected int getLayoutResId() {
        return R.layout.frag_first;
    }

    @Override
    protected void initialized() {
        member = new Member();
    }

    // 开始自动翻页
    @Override
    public void onResume() {
        super.onResume();
        //开始自动翻页
        banner.startTurning(2000);
    }

    // 停止自动翻页
    @Override
    public void onPause() {
        super.onPause();
        //停止翻页
    }


    @Override
    protected void requestData() {
        showProgressContent();
        member.info(this, "120029");
    }

    @Override
    public void onComplete(RequestParams params, String result) {
        super.onComplete(params, result);
        if (params.getUri().contains("info")) {
            Map<String, String> map = JSONUtils.parseDataToMap(result);
            ImageLoader imageLoader = new ImageLoader();
            ImageOptions options = new ImageOptions.Builder()
                    // setSize方法中的参数改成和item布局中图片大小一样
                    .setSize(AutoUtils.getPercentWidthSize(172), AutoUtils.getPercentWidthSize(172))
                    // 加载图片和加载失败图片方法中默认大小图片修改同上（差不多的也可以）
                    .setLoadingDrawableId(R.drawable.index1)
                    .setFailureDrawableId(R.drawable.index1)
                    .setFadeIn(true).setImageScaleType(ImageView.ScaleType.FIT_XY)
                    .setUseMemCache(true).build();
            imageLoader.setImageOptions(options);
            imageLoader.disPlay(imageView, map.get("head"));
            LogUtils.error(map.toString());
        }
    }

    @Override
    public void onItemClick(int position) {

    }


    class LocalImageHolderView implements Holder<Integer> {
        private ImageView imageView;
        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, Integer data) {
            imageView.setImageResource(data);
        }
    }

}
