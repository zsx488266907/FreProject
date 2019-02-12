package zsx.com.myfresco;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class FrescoActivity extends AppCompatActivity {
Button but1,but2,but3,but4,but5,but6,but7,but8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco);
        but1 = findViewById(R.id.but1);
        but2 = findViewById(R.id.but2);
        but3 = findViewById(R.id.but3);
        but4 = findViewById(R.id.but4);
        but7 = findViewById(R.id.but7);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://p4.ssl.qhimg.com/t01fc1659fd1fb6e811.png");
                SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.image1);
                draweeView.setImageURI(uri);

            }
        });
       but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://p4.ssl.qhimg.com/t01fc1659fd1fb6e811.png");
                SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.image2);
                draweeView.setImageURI(uri);

            }
        });
        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://p4.ssl.qhimg.com/t01fc1659fd1fb6e811.png");
                SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.image3);
                draweeView.setImageURI(uri);

            }
        });
        but4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://p4.ssl.qhimg.com/t01fc1659fd1fb6e811.png");
                SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.image4);
                //draweeView.setImageURI(uri);
                ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                        .setProgressiveRenderingEnabled(true)
                        .build();
                PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                        .setImageRequest(request)
                        .build();
               /* draweeView.setController(Fresco.newDraweeControllerBuilder()
                        .setImageRequest(ImageRequestBuilder.newBuilderWithSource(
                                Uri.parse("http://image5.tuku.cn/pic/wallpaper/fengjing/menghuandaziranmeijingbizhi/009.jpg"))
                                .setProgressiveRenderingEnabled(true)
                                .build())
                        .setOldController(draweeView.getController())
                        .build());*/
               draweeView.setController(controller);
            }


        });
       /* but7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
                    @Override
                    public void onFinalImageSet(
                            String id,
                            @Nullable ImageInfo imageInfo,
                            @Nullable Animatable anim) {
                        if (imageInfo == null) {
                            return;
                        }
                        QualityInfo qualityInfo = imageInfo.getQualityInfo();
                        FLog.d("Final image received! " +
                                        "Size %d x %d",
                                "Quality level %d, good enough: %s, full quality: %s",
                                imageInfo.getWidth(),
                                imageInfo.getHeight(),
                                qualityInfo.getQuality(),
                                qualityInfo.isOfGoodEnoughQuality(),
                                qualityInfo.isOfFullQuality());
                    }

                    @Override
                    public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
                        FLog.d("Intermediate image received");
                    }

                    @Override
                    public void onFailure(String id, Throwable throwable) {
                        FLog.e(getClass(), throwable, "Error loading %s", id)
                    }
                };

                Uri uri;
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setControllerListener(controllerListener)
                        .setUri(uri)
                        // other setters
                        .build();
                mSimpleDraweeView.setController(controller);

            }
        });*/

    }
}
