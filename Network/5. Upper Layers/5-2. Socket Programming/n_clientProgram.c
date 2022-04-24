#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#define TIMER_SERVER "211.223.201.30"
#define TIME_PORT 5010

main()
{
    int sock;                          // 파일 디스크립터
    struct sockaddr_in server, client; // 소켓 주소
    int server_len = sizeof(server);   // 주소 길이
    char buf[256];                     // 정보 보관 데이터 공간
    int buf_len;                       // 공간 길이

    // 서버 주소 설정
    server.sin_family = AF_INET;
    server.sin_addr.s_addr = htonl(inet_addr(TIMER_SERVER));
    server.sin_port = htons(TIME_PORT);

    // 소켓 생성
    if ((sock = socket(AF_INET, SOCK_DGRAM, 0)) == -1)
        exit(1);

    // 자기 소켓 주소 생성
    client.sin_family = AF_INET;
    client.sin_addr.s_addr = htonl(INADDR_ANY);
    client.sin_port = htons(0);

    // 바인딩
    if (bind(sock, (struct sockaddr *)&client, sizeof(client)) < 0)
        exit(1);

    // 데이터 전송
    buf[0] = '?';
    buf[1] = '\0';
    buf_len = sendto(sock, buf, strlen(buf) + 1, 0, (struct sockaddr *)&server, server_len);
    if (buf_len < 0)
        exit(1);

    buf_len = recvfrom(sock, buf, 256, 0, (struct sockaddr *)0, (int *)0);
    if (buf_len < 0)
        eixt(1);
    printf("Time information from server is %s", buf);
    close(sock);
}