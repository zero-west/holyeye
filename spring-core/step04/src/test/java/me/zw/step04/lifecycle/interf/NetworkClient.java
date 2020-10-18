package me.zw.step04.lifecycle.interf;

import lombok.Setter;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

@Setter
public class NetworkClient implements InitializingBean, DisposableBean {
    // 현재는 인터페이스를 이용한 방식은 잘 사용하지 않는다.
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url= " + url);
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

    @Override
    public void afterPropertiesSet() throws Exception {
        // 프로퍼티 세팅이 끝나면(의존관계 주입이 끝나면)
        connect();
        call("초기화 연결 메시지");
    }

    @Override
    public void destroy() throws Exception {
        disconnect();
    }
}
