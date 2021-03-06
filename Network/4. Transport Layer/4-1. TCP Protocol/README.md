## (4) 전송 계층
### (1) TCP 프로토콜
#### (1) 전송 계층의 기능
**전송 계층**은 `네트워크 끝단에 위치하는 통신 주체가 중간의 논리적인 선로(라우터로 연결된 컴퓨터 네트워크)를 통해 데이터를 주고 받는다.`
데이터 링크 계층은 단순히 물리적인 선로를 통해 연결되지만, 전송 계층은 중간에 위치한 논리적 네트워크의 중개 기능을 사용해 전송 기능을 수행한다.

![9-1  전송 계층과 데이터 링크 계층의 차이](https://user-images.githubusercontent.com/56579239/165052298-554fc598-1f1b-4bb3-a6c2-ac9d6029bba0.jpg)

##### (1) 전송 계층의 주요 기능
###### (1) 흐름 제어
수신 프로세스가 송신 프로세스의 전송 속도보다 느리게 데이터를 수신하면 버퍼 용량이 초과하여 데이터를 분실할 수 있다. 이 때 흐름 제어 기능은 수신 호스트가 슬라이딩 윈도우 프로토콜의 윈도우 하단 값을 조정하여 문제를 해결한다.

###### (2) 오류 제어
데이터를 전송하는 과정에서 데이터 변형이나 데이터 분실의 오류가 발생할 수 있다. 전송 오류가 발생하면 오류 제어 기능에 의해 복구 절차가 진행된다.

###### (3) 분할과 병합
상위 계층에서 전송을 요구한 데이터의 크기가 전송 계층에서 처맇라수 있는 데이터의 크기보다 크면 데이터를 쪼개 전송해야 한다. `데이터를 전송하기 전에 적합한 크기로 나누는 과정`을 **분할**이라 하고, `수신 프로세스가 수신한 데이터를 원래 크기로 다시 모으는 과정`을 **병합**이라 한다.

###### (4) 서비스 프리미티브
**전송 서비스 프리미티브**는 전송 계층 `사용자가 전송 계층 서비스를 사용하기 위한 인터페이스이다.` 네트워크 계층에서 제공하는 서비스는 일반적으로 비신뢰성을 바탕으로 한 비연결형 서비스 프리미티브가 정의된다. 전송 계층에서 비연결형 서비스뿐 아니라, 신뢰성이 향상된 연결형 서비스도 제공한다.

##### (2) 전송 계층 설계 시 고려 사항
###### (1) 주소 표현
네트워크에서 기본적으로 필요한 일반 사항은 주소이다. TCP/IP 환경에서 사용하는 호스트의 IP 주소와 포트 번호의 조합은 네트워크 계층과 전송 계층에서 사용하는 주소 표현 방식의 하나이다. 전송 계층의 주소를 보통 **TSAP**라 한다.

###### (2) 멀티플렉싱
개별적으로 설정된 전송 계층 연결에서 전송 데이터의 단위인 TPDU의 목적지가 동일한 호스트들이면 이들 데이터를 하나의 가상 회선에 실어 전송하는 것이 유리할 수 있다.

**상방향 멀티 플렉싱**은 `다수의 전송 계층 연결에 대해 하부의 네트워크 계층에서 연결이 하나 형성`된다. 따라서 여러 전송 계층의 연결에서 발생한 데이터가 동일한 경로로 전송되면 하나의 네트워크 연결에 묶어 전송할 수 있다. 일반적인 연결 구조보다 네트워크 계층에서 만들어지는 가상 회선의 연결의 개수를 줄일 수 있으므로 연결 설정에 걸리는 시간이 단축된다.

**하방향 멀티 플렉싱**은 반대로 동작한다. 하나의 전송 연결 설정을 의미하는 포트에 다수의 가상 회선을 할당한다. 이 방식으로 데이터를 전송하면 전송 속도뿐 아니라, 전송 계층에서 발생하는 데이터의 특성에 따라 개별 가상 회선을 할당하여 효과적인 통신이 가능하다.

![9-2  멀티 플렉싱](https://user-images.githubusercontent.com/56579239/165052316-552b31b2-eb36-418a-9e10-73a5bfc0aa5c.jpg)

###### (3) 연결 설정
전송 계층의 연결 설정은 연결을 요청하는 프로세스의 연결 설정 요구인 Conn_Req와 상대편 프로세스에서 연결을 수락을 의미하는 Conn_Ack의 회신으로 완료된다. 이와 같은 2단계 설정 과정은 통신 양단의 연결 설정을 위한 최소한의 단계이다.

오류가 발생하지 않은 정상 상태에서 **3단계 설정**의 동작 과정은 다음과 같다.

![9-3  3단계 설정 절차](https://user-images.githubusercontent.com/56579239/165052328-d2886ffa-2427-4f40-8a14-3a179d97583f.jpg)

연결 요구에 대한 응답 Conn_Ack를 받은 A 프로세스는 Conn_Ack를 잘 받았다고 B 프로세스에 응답해야 한다. 따라서 Conn_ACK와 다른 종류로 응답 정보를 보내야 하지만, 이 과정을 생략하고 Data_req를 바로 전송할 수 있다. 이는 Data_Req가 응답 기능을 함께 수행하기 때문이다.

###### (4) 연결 해제
![9-4  연결 해제](https://user-images.githubusercontent.com/56579239/165052363-965928c6-999f-4b7e-bec9-61aebb37d43e.jpg)

**단방향 데이터 전송**을 지원하는 네트워크에서는 한쪽 프로세스가 일방적으로 Disc_Req를 전송해 연결 종료를 선언할 수 있다. Disc_Req에 대한 상대 프로세스의 동의가 없어도 연결이 끊기는 방식이다. 이 방식에서는 데이터 전송이 정지되면 전송 중인 데이터의 전송을 완료할 수 없다는 문제가 발생한다.

**양방향 데이터 전송**을 지원하는 네트워크 데이터 전송 과정 중에 프로세스 연결 해제 요구가 발생해도 연결 해제를 요구한 프로세스만 정지하고, 다른 프로세스는 정상 기능을 수행한다. 두 프로세스 사이의 연결을 완전히 종료하려면 양쪽에서 명시적으로 정지해야 한다.

#### (2) TCP 프로토콜
**TCP**는 IP 프로토콜 위에서 연결형 서비스를 지원하는 전송 계층 프로토콜로, 인터넷 환경에서 기본으로 사용한다. TCP에서 제공하는 주요 기능은 다음과 같다.
- 연결형 서비스를 제공한다.
- 전이중 방식의 양방향 가상 회선을 제공한다.
- 신뢰성 있는 데이터 전송을 보장한다.

![9-5  TCPIP 구조](https://user-images.githubusercontent.com/56579239/165052350-0c2e14c2-1805-415c-829e-3053bd9716e0.jpg)

다음은 인터넷 환경에서 사용하는 TCP/IP 프로토콜의 계층적인 구조를 보여준다. 인터넷에서 네트워크 계층의 기능을 제공하는 프로토콜은 IP이며, IP 프로토콜 위에서 실행되는 전송 계층 프로토콜은 서비스의 유형에 따라 두 종류로 구분한다. TCP는 연결형 서비스를 지원하는 프로토콜이고, UDP는 비연결형 서비스를 지원하는 프로토콜이다. 전송 계층 프로토콜은 운영체제의 내부 기능으로 구현된다. 따라서 이 서비스를 사용하려면 상위 게층에서 시스템 콜이라는 프로그램 호출 방식을 이용해야 한다.

##### (1) TCP 헤더 구조
![9-6  TCP 연결 설정](https://user-images.githubusercontent.com/56579239/165052382-6f073b34-2c18-4999-bbe9-44cf850aef05.jpg)

- **Source Port/Destinatiion Port**: TCP로 연결되는 가상 회선 양단의 송수신 프로세스에 할당된 네트워크 포트 주소이다. 인터넷 환경에서는 통신을 원하는 프로세스가 실행되는 호스트의 주소와 함께, 호스트 내부에서 다른 프로세스와 구분할 수 있는 프로세스 포트 번호를 사용한다.
- **Sequence Number**: 송신 프로세스가 지정하는 순서 번호이다. 세그먼트 전송 과정에서 전송되는 바이트의 수를 기준으로 증가한다.
- **Acknowledgement Number**: 수신 프로세스가 제대로 수신한 바이트의 수를 응답하기 위해 사용한다. 필드 값은 ACK 플래그 비트가 지정된 경우에만 유효하며, 다음 수신을 기대하는 데이터의 순서 번호를 표시한다.
- **Data Offset**: TCP 세그먼트가 시작된느 위치를 기준으로 데이터의 시작위치를 나타낸다.
- **Reserved**: 예약 필드이다.
- **Window**: 슬라이딩 윈도우 프로토콜에서 수신 윈도우의 버퍼 크기를 지정하깅 위해 사용한다.
- **Checksum**: TCP 세그먼트에 포함되는 프로토콜 헤더와 데이터 모두에 대한 변형 오류를 검출하려고 사용한다.
- **Urgent Pointer**: 긴급 데이터를 처리하기 위한 것으로, URG 플래그 비트가 지정된 경우에 유효하다.
- **URG**: Urgent Pointer 필드가 유효한 지를 나타낸다. 이 기능은 수신 프로세스의 응용 계층에 긴급 데이터가 도착했음을 알리는 것으로, 데이터가 응용 계층에 전달되기 전에 긴급 데이터가 왔음을 미리 알려주어 대비할 수 있게 한다.
- **ACK**: Acknowledgment Number 필드가 유효한지를 나타낸다.
- **PSH**: 현재 세그먼트에 포함된 데이터를 상위 계층에 즉시 전달하도록 지시할 때 사용한다.
- **RST**: 연결의 리셋이나 유효하지 않은 세그먼트에 대한 응답용으로 사용한다.
- **SYN**: 연결 설정 요구를 의미하는 프래그 비트이다.
- **FIN**: 연결 해제 요구를 의미하는 플래그 비트이다.

최근에 TCP에 추가된 **ECN** 기능은 `라우터가 송신 프로세스에 명시적으로 혼잡 발생을 알려주어 송신 프로세스 스스로 트래픽을 완화하는 기술`이다. TCP는 ECN 기능을 지원하기 위해 CWR 필드와 ECE 필드를 정의한다.

- **CWR**: ECE 비트를 수신한 송신 프로세스가 전송 윈도우의 크기를 줄였음을 통지하는 것이 목적이며, 더 이상 ECE를 전송하지 말라는 의미이다.
- **ECE**: 네트워크 트래픽이 많아질 때 라우터가 송신 프로세스에 명시적으로 혼잡을 알리려고 사용한다. 라우터는 송신 프로세스에 혼잡을 바로 전달하지 않고, IP 헤더의 ECN 필드에 CE 값을 지정하여 간접적으로 수신 프로세스에 알려준 후에 수신 프로세스의 중개를 거쳐 송신 프로세스에 통지한다. 이는 라우터가 TCP 프로토콜을 지원하지 않기 때문이다.

##### (2) 포트 번호
포트 번호는 TCP와 UDp가 상위 계층에 제공하는 주소 표현 방식이다. 소켓 시스템 콜을 이용해 TCP 연결 설정이 되면 통신 양단의 프로세스가 사용하는 고유 주소는 해당 호스트이 IP 주소와 호스트 내부의 포트 번호가 조합된 형태이다.

#### (3) TCP 프로토콜을 이용한 데이터 전송
TCP 프로토콜은 전이중 방식의 양방향 통신을 지원하므로 가상 회선으로 연결된 두 프로세스가 동시에 데이터를 전송할 수 있다. 따라서 전송 데이터와 응답 데이터를 함께 전송하는 피기패킹 기능을 사용한다. TCP에서 데이터를 전송하려면 연결 설정, 데이터 전송, 연결 해제라는 3단계를 순차적으로 진행해야 한다.

##### (1) 연결 설정
![9-7  TCP 헤더의 구조](https://user-images.githubusercontent.com/56579239/165052402-4bff0487-b945-4ba8-ba2a-f80af6eb9335.png)

TCP를 사용하는 프로세스가 실행하는 연결 설정은 3단계 설정이다. 위와 같이 A 프로세스가 연결 설정을 요구하고, B 프로세스가 이를 수락하는 형식이다.
A 프로세스는 TCP 헤더의 SYN 플래그를 지정한 세그먼틀르 전송함으로써 연결 설정을 요구한다. B 프로세스는 SYN과 ACK 플래그를 지정해 연결에 대한 긍정 응답을 회신한다. 세 번째 세그먼트는 B 프로세스가 전송한 연결 수락 세그먼트가 제대로 도착했음을 알린다.

##### (2) 데이터 전송

![9-8  TCP 데이터 전송](https://user-images.githubusercontent.com/56579239/165052413-b5b567e5-1115-43f5-bad7-8f05d54f0dcb.jpg)

TCP의 데이터 전송은 양쪽 프로세스가 동시에 데이터를 전송할 수 있는 전이중 방식을 지원한다. TCP 연결 설정에서 세 번째 세그먼틀르 전송하는 과정에서 전송할 데이터가 있으면 세 번째 세그먼트에 데이터를 실어서 전송할 수 있다.

TCP 데이터를 세그먼트라 부르며, 순서 번호와 함께 전송된다. 순서 번호는 상위 계층에서 보낸 데이터의 바이트 수에 기초하여 부여되고, 수신 프로세스는 이 번호를 근거로 데이터의 순서를 올바르게 정렬할 수 있다. 순서 번호가 동일한 데이터가 도착하면 중복으로 판단하여 해당 데이터를 버리고, 중간의 순서 번호가 빠지면 데이터를 분실했다고 판단한다.

TCP는 부정 응답 기능인 NAK를 사용하지 않는다. 따라서 데이터 변형과 분실 오류가 발생하면 수신 프로세스로부터 회신을 받을 수 없으므로 송신 프로세스의 타임아웃 기능에 의해 오류가 복구된다.

##### (3) 연결 해제
![9-10  TCP 연결 해제](https://user-images.githubusercontent.com/56579239/165052506-3f27e013-2a5c-4259-9e7c-ed1905d0219b.jpg)

연결 해제 단계는 연결을 해제하고자 하는 쪽에서 FIN 플래그를 지정해 요구한다. 연결 해제는 양쪽 프로세스의 동의하에 진행되기 때문에 연결 해제 세그먼틀르 받은 프로세스가 FIN 플래그로 응답할 때까지 연결은 유지된다.

##### (4) 혼잡 제어

ECN 기능을 사용하려면 TCP 연결 설정 단계에서 ECN 기능을 사용할 것인지 여부를 협상해야 한다. 데이터 전송 단계에서는 혼잡을 인지한 라우터가 수신 프로세스에 혼잡을 통지한다. 그러면 수신 프로세스는 다시 송신 프로세스에 혼잡을 통지 함으로써 전송하는 데이터의 양을 줄이는 방식을 혼잡제어가 이루어진다.

![9-11  TCP 연결 설정(ECN)](https://user-images.githubusercontent.com/56579239/165052536-12d9db59-1bbc-4f43-b30a-2177ab627d78.png)

연결 설정을 요청하는 프로세스에서 연결 설정 요구인 SYN 플래그와 함께 CWR, ECE 플래그를 지정하여 ECN 기능이 동작하는 연결 설정을 요구한다. ECN 기능을 사용할 의사가 있으면 연결 설정 요구 응답인 SYN, ACK ECE 플래그를 지정하여 응답한다. ECN 기능을 사용할 의사가 없으면 SYN, ACK 플래그만 지정하여 응답한다.

![9-12  ECN의 동작 원리](https://user-images.githubusercontent.com/56579239/165052540-184b41a7-79fd-4a8f-ba57-cbd88d75edc7.png)

임의의 시점에서 네트워크에 혼잡이 발생하고, 라우터가 이를 감지하면, 라우터는 IP 헤더에 ECT가 설정된 패킷들에 대해 CE를 설정하고 혼잡이 발생했음을 알린다. 이때, 라우터는 수신 프로스세의 중개를 거쳐 간접적으로 송신 프로세스에 혼잡을 통지한다.

라우터로부터 IP 헤더의 CE 값을 받은 프로세스는 모든 TCP 세그먼트에 대해 TCP 헤더에 ECE 플래그를 지정하여 라우터가 감지한 혼잡을 다른 프로세스에 알려준다. 혼잡을 인지한 다른 프로세스는 송신 윈도우를 조절하여 전송되는 데이터의 양을 줄이고, TCP 헤더의 CWR 플래그를 지정함으로써 조치를 취했음을 알린다.
