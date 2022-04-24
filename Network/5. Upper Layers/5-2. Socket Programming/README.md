## (5) 상위 계층
### (2) 소켓 프로그래밍
#### (1) 소켓 주소 체계와 서비스
두 개의 독립된 프로세스가 소켓을 이용해 통신하려면 소켓 주소의 개념이 필요하다. 소켓은 자체에 어떤 기능이 있는 것이 아니고, 운영체제에서 제공하는 통신 프로토콜을 편리하게 사용할 수 있도록 도와주는 역할을 한다. 따라서 소켓 주소는 사용하는 프로토콜의 종류에 따라 여러 형태의 주소 체계를 지원한다.

#### (1) 소켓의 주소 체계
소켓은 프로토콜의 종류에 따라 다양한 방식으로 주소를 부여할 수 있다. 일반 프로그래밍 환경에서는 AF_UNIX와 AF_INET의 소켓 주소 체계를 많이 사용한다.

- **유닉스 주소 체계**: AF_UNIX로 표시되는 유닉스 주소 체계는 `호스트 내부에서 실행되는 프로세스 사이의 통신을 지원`하며, 사용 하는 주소 체계는 `파일 시스템의 경로명을 기반`으로 한다. 다음은 유닉스 주소 체계 구조체의 예제이다.
    ```
    struct sockaddr_rn {
        short sun_family;   //  유닉스 주소 체계 값, AF_UNIX
        char sun_path[108]; //  파일 시스템 경로명
    }
    ```

- **인터넷 주소 체계** : 인터넷 주소 체계는 `서로 다른 호스트에서 실행되는 프로세스 사이의 통신을 지원`하며 AF_INET으로 표시한다. AF_INET 방식은 소켓이 생성되는 호스트의 `IP 주소와 포트 번호를 조합하여 소켓 주소를 표현`한다. 다음은 인터넷 주소 체계 구조체의 예이다.
    ```
    struct sockaddr_in {
        short sin_family;           // 인터넷 주소 체계 값, AF_INET
        u_short sin_port;           // 포트 번호
        struct in_addr sin_addr;    // IP 주소, in_addr 구조체 사용
        char sin_zer[8];            // 사용하지 않는 필드, 0으로 채움
                                    // sockaddr_in의 크기를 sockadrr에 맞추기 위해서 사용
    }

    struct in_addr {                // 32비트 -> 4바이트 주소 체계 변환
        u_long s_addr;              // 32비트 주소 체계를 나타내기 위해, u_long 형 사용
    }                               // => IPv4 주소를 나타내는 것
    ```

- **통합 주소 체계**: 소켓의 주소 체계는 사용하는 프로토콜에 따라 달라지므로 운영체제에서 제공하는 통신 프로토콜의 수가 증가하면 주소 체계의 표현 방식도 증가한다. 소켓 주소는 소켓 시스템 콜을 사용한다. 그러나 콜을 통해 호출할 때, `여러 구조체를 함수 매개변수 하나에 수용하는 것은 문법적으로 불가능`하다. 따라서 `여러 소켓 구조체를 통합해 일반 구조체 하나로 정의`할 필요가 있다. 다음은 통합 주소 체계 구조체의 예시이다.
    ```
    struct sockaddr {
        u_short sa_family;      // AF_UNIX, AF_INET
        char sa_data[14];
    }
    ```

- 통합 주소 체계의 활용 예: bind 함수의 두 번째 매개변수는 struct sockaddr 형의 포인터 변수만 오도록 정의 되었으나, 실제 사용되는 변수는 가 프로토콜에서 정의된 주소 체계로 선언된다.
    ```
    struct sockaddr_in addr;
    addr.sin_family = AF_INET:
    addr.sin_addr.s_addr = inet_addr("127.0.0.1");
    addr.sin_port = htons(3030);
    bind(socket, (struct sockaddr *) &addr, sizeof(addr));
    ```

##### (2) 소켓의 서비스
제공하는 서비스에 따른 소켓의 유형도 다양하다. 이는 SOCK_STREAM, SOCK_DGRAM, SOCK_RAW로 분류할 수 있다. SOCK_STREAM은 연결형 서비스, SOCK_DGRAM은 비연결형 서비스에서 사용한다. SOCK_RAW는 일반 네트워크 응용 프로그램 개발에서는 자주 사용되지 않고, IP 프로토콜을 직접 사용해 통신할 때 사용한다.

#### (2) 소켓 시스템 콜
운영체제에서 구현되는 전송 계층 프로토콜인 TCP와 UDP를 사용하려면 소켓 시스템 콜이라는 라이브러리 함수를 이용한다. 소켓은 통신을 원하는 프로세스에 할당되는 자원이며, 고유의 소켓 주소가 부여된다.

- **socket() 함수**: 데이터 전송에 사용되는 소켓을 생성할 때 호출하며, 소켓을 성공적으로 생성하면 소켓 디스크립터를 반환한다. 다음은 socket 함수의 예제이다.
    ```
    int socket(int domain, int type, int protocol);
    // domain = 프로토콜 도메인, type = 서비스 유형, protocol = 대개 0으로 지정

    sd = socket(AF_UNIX, SOCK_STREAM, 0);   // 유닉스 주소 체계의 연결형 서비스
    sd = socket(AF_UNIX, SOCK_DGRAM, 0);    // 유닉스 주소 체계의 비연결형 서비스
    sd = socket(AF_INET, SOCK_STREAM, 0);   // 인터넷 주소 체계의 연결형 서비스
    sd = socket(AF_INET, SOCK_DGRAM, 0);    // 인터넷 주소 체계의 비연결형 서비스
    ```

- **bind() 함수**: 소켓 디스크립터를 이용해 상대 프로세스와 통신하려면 먼저 생성된 소켓에 주소를 부여해야 한다. 이때 bind() 함수가 소켓에 주소를 부여한다. 이후에 connect() 함수와 accept() 함수는 연결을 설정하는 과정에서 바인딩된 소켓 주소를 사용한다. 다음은 bind 함수의 예제이다.
    ```
    int bind(int s, const struct sockaddr *name, socklen_t *namelen);
    // s = 소켓디스크립터 번호, name = 바인딩 할 소켓 주소, name = name 주소 크기
    ```

- **listen() 함수**: listen 함수는 서버 프로세스에서 실행되며, 예제는 다음과 같다.
    ```
    int listen(int s, int backlog);
    // s = 소켓 디스크립터 번호, backlog = 보통 5로 지정
    ```

- **accept() 함수**: 연결형 서비스를 지원하는 서버 프로세스가 클라이언트의 연결 요청을 받으려면 accept() 함수에서 대기해야 한다. 클라이언트-서버 환경에서 서버 프로세스는 accept() 함수를 실행해 클라이언트의 요청을 기다리고, 클라이언트 프로세스의 connect() 요청이 발생하면 연결이 설정된다. 사용법은 다음과 같다.
    ```
    int accept(int s, struct sockaddr *addr, socklent_t *addrlen);
    // s = 소켓 디스크립터 번호, addr = 연결 요청한 클라이언트 주소, addrlen = 주소 길이
    // 연결 성공 시, 클라이언트-서버 간의 연결 소켓 생성, 통신할 때 이 소켓을 사용
    ```

- 인터넷 도메인 소켓을 이용한 서버 프로그램 구현의 예
    ```
    int sd;
    struct sockaddr_un addr;

    sd = socket(AF_INET, SOCK_STREAM, 0);
    if(sd == -1) {
        perror("socket");
        exit(1);
    }

    addr.sun_family = AF_INET;
    addr.sin_addr.s_addr = inet_addr("127.0.0.1");
    addr.sin_port = htons(3030);

    if(bind(sd, (struct sockaddr *) &addr, sizeof(addr)) == -1) {
        perror("bind");
        exit(1);
    }

    if(listen(sd, 5) == -1) {
        perror("listen");
        exit(1);
    }

    while((new = accept(sd, (struct sockaddr *) &client, &client_len)) != -1) {
        if(fork() = 0) {    // fork => 하위 프로세스 생성
            // 자식 프로세스
            close(sd); 
            work(new);  // new를 이용해 통신, new는 연결 성공 시 생성 소켓
            close(new);
            exit(0);
        }
        // 부모 프로세스
        close(new);
    }
    ```

- **connect() 함수**: 연결형 서비스는 클라이언트 프로세스가 서버 프로세스에 연결 요청을 할 때 connect 함수를 사용한다. 예제는 다음과 같다.
    ```
    int connet(int s, const struct sockaddr *name, socklen_t namelen);
    // name = 연결 하려는 서버 프로세스 소켓 주소
    ```

- **send() 함수**: 소켓 연결 후, 데이터 전송 함수이다. 연결형 서비스에서는 send()를 사용하고, 비연결형 서비스에서는 sendto() 함수를 사용한다. 예제는 다음과 같다.
    ```
    ssize_t send(int s, const void *msg, size_t len, int flags);
    // msg = 전송되는 데이터, flasg = 0이면 정상, MSG_OOB이면 out of band

    ssize_t sendto(int s, const void *msg, size_t len, int flags, const struct sockaddr *to, socklen_t tolen);
    // to = 전송할 상대 프로세스 소켓 주소, tolen = 주소 길이
    ```

- **recv() 함수**: recv()와 recvfrom() 함수를 통해 상대 프로세스가 전송한 데이터 소켓을 읽을 수 있따. recv()는 연결형 서비스에서 사용하고, recvfrom() 함수는 비연결형 서비스에서 사용한다.
    ```
    ssize_t recv(int s, void *buf, size_t len, int flags);
    // buf = 저장 공간, len = 데이터 크기

    ssize_t recvfrom(int s, void *buf, size_t len, int flags, struct sockaddr *from , socklen_t *fromlen);
    // from = 송신 프로세스 소켓 주소
    ```

- 데이터 송수신 예
    ```
    int sd;
    struct sockaddr_in addr;
    char *t_data = "Test Message";
    char r_data[100];
    int t_length = sizeof(t_data) + 1;
    int r_length = sizeof(r_data);

    sd = socket(AF_INET, SOCK_STREAM, 0);
    if(sd == 1) {
        perror("socket");
        exit(1);
    }

    addr.sin_family = AF_INET;
    addr.sin_addr.s_addr = htonl(inet_addr("211.223.201.30"));
    addr.sin_port = htons(5010);

    if(connect(sd, (struct sockaddr *) &addr, sizeof(addr)) == 1) {
        perror("connet");
        exit(1);
    }

    if(send(sd, t_data, t_length, 0) == -1) {
        perror("send");
        exit(1);
    }

    if(recv(sd, r_data, r_length, 0) == -1) {
        perror("recv");
        exit(1);
    }
    ```

#### (3) 서버-클라이언트 프로그래밍
일반적으로 서버-클라이언트 모델은 네트워크 서비스를 제공하는 서비스 제공자와 서비스 이용자 사이의 관계를 표현한다. 서비스를 제공하는 프로그램을 서버 프로세스, 서버와 연결을 시도하여 서비스를 제공받는 프로그램을 클라이언트 프로세스라 한다.

##### (1) 연결형 서비스
소켓은 네트워크통신을 위한 소프트웨어 교신점이 된다. 두 개의 독립 프로세스가 네트워크를 통해 통신하려면 논리적인 연관 관계를 맺어주는 소켓이 필요하다. 소켓을 이용한 전형적인 서버-클라이언트 프로그램을 작성하는 방법은 다음과 같다.

- **서버 프로세스**: 서버 프로세스는 다수의 클라이언트에 공개되는 Well-known 포트로 자신의 소켓 주소를 정한 후에, 클라이언트의 연결 요청을 기다린다. 클라이언트 요구에 따라 연결이 설정되면 서버 프로세스가 제공하는 서비스가 시작된다. 이러한 서비스는 반복적으로 이루어지며 동작 과정은 다음과 같다.

> 1. 서비스 교신점(호스트 IP 주소, 포트 번호) 공개
> 2. 클라이언트로부터 발생하는 서비스 요구 대기
> 3. 클라이언트에 서비스 제공
> 4. 해당 클라이언트에 서비스 제공 완료
> 5. 2단계로 이동

- **클라이언트 프로세스**: 클라이언트 프로세스는 서버의 Well-known 포트를 이용해 원하는 서버와 접속을 시도하고, 연결이 이루어지면 서버가 제공하는 서비스를 이용한다.

> 1. 원하는 서비스를 제공하는 서버 확인
> 2. 해당 서버와 연결 시도
> 3. 서버에 서비스 요청
> 4. 서비스 이용 완료

![12-1  TCP를 이용한 소켓 통신 절차](https://user-images.githubusercontent.com/56579239/164968087-c777c757-7a49-4af1-a00a-1a5bbe606261.jpg)

- [연결형 서버 프로그램 예제](c_serverProgram.c)
- [연결형 클라이언트 프로그램 예제](c_clientProgram.c)

##### (2) 비연결형 서비스
비연결형 서비스에서는 connect()와 accept() 함수로 연결을 설정하는 과정이 생략되며, 데이터 송수신을 위한 send(), recv() 함수 대신에 sendto(), recvfrom() 함수를 사용한다. 비연결형 서비스에서는 전송 데이터마다 수신자의 소켓 주소를 함께 전송한다.

![12-2  UDP를 이용한 소켓 통신 절차](https://user-images.githubusercontent.com/56579239/164968093-b8a94d08-4873-4569-8566-b7986c86d604.png)

- [비연결형 서버 프로그램 예제](n_serverProgram.c)
- [비연결형 클라이언트 프로그램 예제](n_clientProgram.c)
