package com.cai.hystrixdemo;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

/**
 * Created by cai on 2017/7/13.
 */
public class ObserveCommand {
    public static void main(String[] args) throws InterruptedException {
        HelloWorldCommand helloWorldCommand = new HelloWorldCommand("World");
        //注册观察者事件拦截
        Observable<String> fs = helloWorldCommand.observe();
        //注册结果回调事件
       fs.subscribe(new Action1<String>() {
            @Override
            public void call(String result) {
                //执行结果处理,result 为HelloWorldCommand返回的结果
                //用户对结果做二次处理.
                System.out.println("回调事件1: " + result);
            }
        });


        //注册完整执行生命周期事件
       fs.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                // onNext/onError完成之后最后回调
                System.out.println("execute onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                // 当产生异常时回调
                System.out.println("onError " + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onNext(String v) {
                // 获取结果后回调
                System.out.println("onNext: " + v);
            }
        });

        //注册结果回调事件
        fs.subscribe(new Action1<String>() {
            @Override
            public void call(String result) {
                //执行结果处理,result 为HelloWorldCommand返回的结果
                //用户对结果做二次处理.
                System.out.println("回调事件2: " + result);
            }
        });

        Thread.sleep(9000);

       //String execute = helloWorldCommand.execute();
       // System.out.println("execute结果："+execute);
    }
}
