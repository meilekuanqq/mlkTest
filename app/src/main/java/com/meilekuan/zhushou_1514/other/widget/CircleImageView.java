package com.meilekuan.zhushou_1514.other.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.meilekuan.zhushou_1514.other.utils.ZhuShouHttpUtil;

/**
 * function ：显示圆形图片的imageview
 * <p/>
 * author：Meilekuan
 * date: 2016/1/14 13:52
 */

public class CircleImageView extends ImageView {

    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置ImageView的Url
     *
     * @param httpUrl
     */
    public void setImageUrl(final String httpUrl) {
        new Thread() {
            @Override
            public void run() {
                final Bitmap bitmap = ZhuShouHttpUtil.downLoadBitmap(httpUrl);

                //加工在圆形图
                final Bitmap cicrcleBitmap = createCircleBitmap(bitmap);

                //在主线程设置背景图片
                post(new Runnable() {
                    @Override
                    public void run() {
                        setImageBitmap(cicrcleBitmap);
                    }
                });
            }
        }.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //在外面画一个边框
        int width = getWidth();
        Paint strokeP = new Paint();
        strokeP.setColor(Color.YELLOW);
        strokeP.setStrokeWidth(5);
        strokeP.setAntiAlias(true);
        strokeP.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(width / 2, width / 2, (width - 5) / 2, strokeP);

    }

    /**
     * 把 源图片 加工成 圆形图片
     *
     * @param resource
     * @return
     */
    private Bitmap createCircleBitmap(Bitmap resource) {

        int width = resource.getWidth();

        Paint paint = new Paint();
        //画圆或者弧形图，需要抗锯齿
        paint.setAntiAlias(true);

        //创建一张空图片，这张图片只有宽高，没有内容
        Bitmap circleBitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);

        //创建一个画布
        Canvas canvas = new Canvas(circleBitmap);


        //画一个和原图片宽高一样的内切圆
        canvas.drawCircle(resource.getWidth() / 2, resource.getWidth() / 2, (width - 5) / 2, paint);

        //取靓图的交集，也就是重合的部分
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //把原图覆盖上去
        canvas.drawBitmap(resource, 0, 0, paint);


        return circleBitmap;
    }
}
