package com.fearless.water;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.fearless.customview.MyselfView;
import com.fearless.customview.RoundProgressBar;
import com.fearless.entity.ADEntity;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {


    private MyselfView mMyselfView;
    private RoundProgressBar mRoundProgressBar;
    private ListView lv_test;
    private Button btn_rxjava;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMyselfView=(MyselfView)findViewById(R.id.adt_tip);
        btn_rxjava=(Button) findViewById(R.id.btn_rxjava);
        List<ADEntity> ls=new ArrayList<ADEntity>();
        //lv_test=(ListView) findViewById(R.id.lv_test);
        //lv_test.setAdapter(new RoundProgressBarAdapter(this));
        //ls.add(new ADEntity("京东","大促销7.8折","www.baidu.com"));
        //mRoundProgressBar=(RoundProgressBar)findViewById(R.id.roundProgressBar);
       // mRoundProgressBar.setProgress(50);
        //adt_tip.setEntityList(ls);
        this.hashCode();


        btn_rxjava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testOnclick();
            }
        });

    }



    private void Observer1() {
        Observable<String> observable= Observable.create(new Observable.OnSubscribe<String>() {
    @Override
    public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext("Hello World");
            subscriber.onCompleted();
    }
});

        Subscriber<String> subscriber=new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.e("onCompleted","onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("onError","onError");
            }

            @Override
            public void onNext(String s) {
              // tv_name.setText(s);
                Log.e("AAAAAAAAAAAAAAAAAA",s);
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
            }
        };


        observable.subscribe(subscriber);
    }

    private void observer2(){
        Observable<String> observable=Observable.just("I'm a programmer.....");

        Action1<String> onNextAction=new Action1<String>() {
            @Override
            public void call(String s) {
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
            }
        };

        observable.subscribe(onNextAction);


    }

    private void observer3(){

        Action1<String> onNextAction=new Action1<String>() {
            @Override
            public void call(String s) {
                Toast.makeText(MainActivity.this,s+"333333",Toast.LENGTH_LONG).show();
            }
        };

       Observable.just("I'm a programmer.....").subscribe(onNextAction);





    }



    private void observer4(){
        Observable.just("I'm a programmer.....").map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s+"44444444";
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }
        });
    }

    private void observer5(){
        Observable.just("I'm a programmer.....")
                .map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s+"44444444";
            }
        })
        .map(new Func1<String, Integer>() {

            @Override
            public Integer call(String s) {
                return s.hashCode();
            }
        }).map(new Func1<Integer, String>() {

            @Override
            public String call(Integer integer) {
                return integer.toString();
            }
        }).subscribe(new Action1<String>() {

            @Override
            public void call(String o) {
                Toast.makeText(getApplicationContext(),o,Toast.LENGTH_LONG).show();
            }
        });
    }



    private void observer6(){
       Observable.from(new String[]{"url111","url222","url333"}).subscribe(new Action1<String>() {
           @Override
           public void call(String s) {
               Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
           }
       });


    }

    private void observer7(){


        final Observable<List<String>> observer=Observable.create(new Observable.OnSubscribe<List<String>>() {

            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                List<String> ls=new ArrayList<String>();
                ls.add("www.baidu.com");
                ls.add("www.alibaba.com");
                ls.add("www.tencent.com");
                ls.add("www.wangyi.com");


                subscriber.onNext(ls);
                subscriber.onCompleted();
            }
        });


        observer.flatMap(new Func1<List<String>, Observable<String>>() {
            @Override
            public Observable<String> call(List<String> strings) {
                return Observable.from(strings);
            }
        }).flatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String url) {
                return getTitle(url);
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }
        });

    }

    // 返回网站的标题，如果404了就返回null
    Observable<String> getTitle(final String URL){


        Observable<String> observerble=Observable.create(new Observable.OnSubscribe<String>(){

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext(URL);

            }
        });
        return observerble;
    }


    private void observer8(){


        final Observable<List<String>> observer=Observable.create(new Observable.OnSubscribe<List<String>>() {

            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                List<String> ls=new ArrayList<String>();
                ls.add("www.baidu.com");
                ls.add("www.alibaba.com");
                ls.add("www.souhu.com");
                ls.add("www.tencent.com");
                ls.add("www.wangyi.com");



                subscriber.onNext(ls);
                subscriber.onCompleted();
            }
        });


        observer.flatMap(new Func1<List<String>, Observable<String>>() {
            @Override
            public Observable<String> call(List<String> strings) {
                return Observable.from(strings);
            }
        }).flatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String url) {
                return getTitle(url);
            }
        }).filter(new Func1<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                Log.e("字符串类型",s+"内容与类型"+s.getClass());
                return !TextUtils.isEmpty(s);
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }
        });

    }

    Observable<List<String>> query(String keyWords){

        Observable<List<String>> observable=Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                List<String> ls=new ArrayList<String>();
                ls.add("骨法粗烧鸡");
                ls.add("骨法顿萝卜");
                ls.add("嘻哈大泡菜");
                ls.add("糖醋里脊");
                ls.add("变相炒猪蹄");
                ls.add("嘻哈大泡菜");
                subscriber.onNext(ls);
            }
        });

        return observable;
    }

    void observer9(){
        query("hellow fine food").subscribe(new Action1<List<String>>() {
            @Override
            public void call(List<String> strings) {
                for (String s:strings
                     ) {
                    Toast(s);
                }
            }
        });
    }


     void Toast(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

    void observer10(){
        query("hellow fine food").subscribe(new Action1<List<String>>() {
            @Override
            public void call(List<String> strings) {
                Observable.from(strings).subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Toast(s);
                    }
                });
            }
        });

    }



    void observer11(){
        query("hellow fine food").flatMap(new Func1<List<String>, Observable<String>>() {
            @Override
            public Observable<String> call(List<String> strings) {
                return Observable.from(strings);
            }
        }).flatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
                return getFlag(s);
            }
        }).filter(new Func1<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                return !s.equals("嘻哈大泡菜XX");
            }
        }).take(2)
                .doOnNext(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e("美餐输出",s);
            }
        })
                .subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Toast(s);
            }
        });

    }


    void testOnclick(){
        //Observer1();
        //observer2();
        //observer3();
        //observer4();
        //observer5();
        //observer6();
        //observer7();
        //observer8();
        //observer9();
        //observer10();
        observer11();
    }

    private Observable<String> getFlag(final String url){
        Observable<String> observerable=Observable.create(new Observable.OnSubscribe<String>(

        ) {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                  if(url.substring(0,1).equals("骨")){
                      subscriber.onNext(url+"PP");
                  }else{
                      subscriber.onNext(url+"XX");
                  }

                }


            }
        );


        return observerable;
    }
}
