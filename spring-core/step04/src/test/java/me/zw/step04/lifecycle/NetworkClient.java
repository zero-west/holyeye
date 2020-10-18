package me.zw.step04.lifecycle;

import lombok.Setter;

@Setter
public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url= " + url);
        connect();
        call("초기화 연결 메시지");
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    // 연결이 된 상태에서 메세지 보내기
    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("close " + url);
    }
}
