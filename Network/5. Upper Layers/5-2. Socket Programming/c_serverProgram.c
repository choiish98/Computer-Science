#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <time.h>
#define TIME_PORT 5010

main()
{
    int sock, sock2;
    struct sockaddr_in server, client;
    int len;
    char buf[256];
    time_t today;

    // 소켓 생성
    if ((sock = socket(AF_INET, SOCK_STREAM, 0)) == -1)
        exit(1);

    // 주소 설정
    server.sin_family = AF_INET;
    server.sin_addr.s_addr = htonl(inet_addr(TIMER_SERVER));
    server.sin_port = htons(TIME_PORT);

    // 바인딩
    if (bind(sock, (struct sockaddr *)&server, sizeof(server)) < 0)
        exit(1);

    // 서버 프로세스 대기 상태
    if (listen(sock, 5))
        exit(1);

    // accept
    while (1)
    {
        if ((sock2 == accept(sock, (struct sockaddr *)&client, &len)) == -1)
            exit(1);
        time(&today);
        strcpy(buf, ctime(&today));
        send(sock2, buf, strlen(buf) + 1, 0);
        close(sock2);
    }
}