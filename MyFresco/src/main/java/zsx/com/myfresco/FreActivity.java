package zsx.com.myfresco;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class FreActivity extends AppCompatActivity implements View.OnClickListener {

    private Button but1;
    private Button but2;
    private Button but3;
    private Button but4;
    private Button but5;
    private Button but6;
    private Button but7;
    private Button but8;
    private SimpleDraweeView image;
    private SimpleDraweeView draweeView;
    private Uri uri,uri2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fre);
        initView();
        uri = Uri.parse("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3817191486,3276892468&fm=27&gp=0.jpg");
        uri2 = Uri.parse("http://ww1.sinaimg.cn/large/85d77acdgw1f4hqsbwnhmg20dw09mb29.jpg");
        draweeView = (SimpleDraweeView) findViewById(R.id.image);
        draweeView.setImageURI(uri);
    }

    private void initView() {
        but1 = (Button) findViewById(R.id.but1);
        but2 = (Button) findViewById(R.id.but2);
        but3 = (Button) findViewById(R.id.but3);
        but4 = (Button) findViewById(R.id.but4);
        but5 = (Button) findViewById(R.id.but5);
        but6 = (Button) findViewById(R.id.but6);
        but7 = (Button) findViewById(R.id.but7);
        but8 = (Button) findViewById(R.id.but8);
        image = (SimpleDraweeView) findViewById(R.id.image);

        but1.setOnClickListener(this);
        but2.setOnClickListener(this);
        but3.setOnClickListener(this);
        but4.setOnClickListener(this);
        but5.setOnClickListener(this);
        but6.setOnClickListener(this);
        but7.setOnClickListener(this);
        but8.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but1:
                RoundingParams roundingParams = RoundingParams.fromCornersRadius(50f);
                roundingParams.setBorderColor(Color.RED);
                // roundingParams.setRoundAsCircle(true);
                draweeView.getHierarchy().setRoundingParams(roundingParams);
                draweeView.setImageURI(uri);

                break;
            case R.id.but2:
                RoundingParams roundingParams1 = RoundingParams.fromCornersRadius(20f);
                roundingParams1.setBorderColor(Color.RED);
                roundingParams1.setRoundAsCircle(true);
                draweeView.getHierarchy().setRoundingParams(roundingParams1);
                draweeView.setImageURI(uri);
                break;
            case R.id.but3:
                draweeView.setAspectRatio(2f);
                Toast.makeText(this,"成功",Toast.LENGTH_SHORT).show();
                break;
            case R.id.but4:
                ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(uri)
                        .setProgressiveRenderingEnabled(true)
                        .setAutoRotateEnabled(true)    //设置自动旋转
                        .build();

                //监听事件
                ControllerListener controllerListener = new BaseControllerListener() {
                    @Override
                    public void onSubmit(String id, Object callerContext) {
                        super.onSubmit(id, callerContext);
                        //提交请求之前的调用的方法
                        Log.d("===", "onSubmit" + id);
                    }

                    @Override
                    public void onFinalImageSet(String id, Object imageInfo, Animatable animatable) {
                        super.onFinalImageSet(id, imageInfo, animatable);
                        //所有图片的都加载成功是触发的方法
                        Log.d("===", "onFinalImageSet" + id);
                    }

                    @Override
                    public void onIntermediateImageSet(String id, Object imageInfo) {
                        super.onIntermediateImageSet(id, imageInfo);
                        //当中间图片下载成功的时候触发  用于多图请求
                    }

                    @Override
                    public void onIntermediateImageFailed(String id, Throwable throwable) {
                        super.onIntermediateImageFailed(id, throwable);
                        //当中间图片下载失败的时候触发  用于多图请求
                    }

                    @Override
                    public void onFailure(String id, Throwable throwable) {
                        super.onFailure(id, throwable);

                    }

                    @Override
                    public void onRelease(String id) {
                        super.onRelease(id);
                        //释放资源
                        Log.d("===", "onRelease" + id);

                    }
                };
                DraweeController build = Fresco.newDraweeControllerBuilder()
                        //加载的图片URI地址
                        .setImageRequest(imageRequest)
                        //设置旧的Controller
                        .setOldController(draweeView.getController())
                        //设置控制器
                        .setControllerListener(controllerListener)
                        //构建
                        .build();
                //设置给控件
                draweeView.setController(build);
                break;
            case R.id.but5:

                break;
            case R.id.but6:
                AbstractDraweeController build1 = Fresco.newDraweeControllerBuilder()
                        .setUri(uri2)
                        .setTapToRetryEnabled(true)
                        .setAutoPlayAnimations(true)   //设置为true将循环播放Gif动画
                        .setOldController(draweeView.getController())
                        .build();
                //设置给控件
                draweeView.setController(build1);
                break;
            case R.id.but7:

                break;
            case R.id.but8:

                break;
        }
    }
}
