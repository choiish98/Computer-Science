#include <sys/types.h>
#include <sys/socket.h>

#include <netinet/in.h>

#define TIMER_SERVER "211.223.201.30"
#define TIME_PORT 5010

main()
{
    int sock;                  // 파일 디스크립터
    struct sockaddr_in server; // 소켓 주소
    char buf[256];             // 정보 보관 데이터 공간

    // 소켓 생성
    if ((sock = socket(AF_INET, SOCK_STREAM, 0)) == -1)
        exit(1);

    // 소켓이 연결할 주소 설정
    server.sin_family = AF_INET;
    server.sin_addr.s_addr = htonl(inet_addr(TIMER_SERVER));
    server.sin_port = htons(TIME_PORT);

    // 소켓 연결 시도
    if (connect(sock, (struct sockaddr *)&server, sizeof(server)))
        exit(1);

    // 정보 받기
    if (recv(sock, buf, sizeof(buf), 0) == -1)
        exit(1);

    printf("Time information from server is %s", buf);
    close(sock);
}