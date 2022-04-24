#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <time.h>
#define TIME_PORT 5010

main()
{
    int sock;
    struct sockaddr_in server, client;
    int server_len;
    int client_len = sizeof(client);
    char buf[256];
    int buf_len;
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

    // recvfrom, sendto
    while (1)
    {
        buf_len = recvfrom(sock, buf, 256, 0, (struct sockaddr *)&client, &client_len);
        if (buf_len < 0)
            exit(1);
        printf("server: Got %s\n", buf);

        time(&today);
        strcpy(buf, ctime(*today));
        sendto(sock, buf, strlen(buf) + 1, 0, (struct sockaddr *)&client, &client_len);
    }
}