package com.fearless.water;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * Created by zhouwei on 17/1/11.
 */

public class Html5Activity  extends Activity {


    WebView webview;


    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5);
        webview=(WebView) findViewById(R.id.webview);

        //SoftInputBoard softInputBoard=new
        WebSettings webSettings = webview.getSettings();
        webSettings.setSavePassword(false);
        webSettings.setSaveFormData(false);
        // 下面的一句话是必须的，必须要打开javaScript不然所做一切都是徒劳的
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(false);


       // webview.setWebChromeClient(new MyWebChromeClient());

               // 看这里用到了 addJavascriptInterface 这就是我们的重点中的重点
             // 我们再看他的DemoJavaScriptInterface这个类。还要这个类一定要在主线程中
        webview.addJavascriptInterface(new DemoJavaScriptInterface(), "asdasd");
        webview.addJavascriptInterface(new myHellowWorld(),"my");

        webview.loadUrl("file:///android_asset/main.html");
    }


  private Handler mHandler=new Handler();

    class myHellowWorld{
              myHellowWorld(){

                  }
               public void show(){
                     mHandler.post(new Runnable(){

                                   @Override
                          public void run() {
                                    Toast.makeText(Html5Activity.this,"HELLOW WORLD", Toast.LENGTH_LONG).show();
                              }
                        });
                     }
             }
       // 这是他定义由 addJavascriptInterface 提供的一个Object
                final class DemoJavaScriptInterface {
                DemoJavaScriptInterface() {
                   }

                       /**
         72          * This is not called on the UI thread. Post a runnable to invoke
         73          * 这不是呼吁界面线程。发表一个运行调用
         74          * loadUrl on the UI thread.
         75          * loadUrl在UI线程。
         76          */
                        public void clickOnAndroid() {        // 注意这里的名称。它为clickOnAndroid(),注意，注意，严重注意
                      mHandler.post(new Runnable() {
                                public void run() {
                                       // 此处调用 HTML 中的javaScript 函数
                                        webview.loadUrl("javascript:wave()");
                                    }
                            });
                   }
           }
}
