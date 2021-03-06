## 2. 선형 구조
### 1. Array
**(1) 배열의 정의**
![2-1  배열의 예시](https://user-images.githubusercontent.com/56579239/163946562-8adc8108-378d-45af-9871-9ca1ae8467e2.png)

배열은 변수에 메모리 상의 연속적인 선형 공간을 부여하고, 각 공간에 인덱스를 지정하여 공간을 관리한다. 그리고 할당 받은 공간에 데이터를 삽입, 수정, 삭제하는 작업이 이루어진다. 배열은 할당된 `공간`에 주목을 하며, `인덱스`를 중요히 한다.

**(2) 배열의 특징**
- Index를 통해 데이터에 빠르게 접근할 수 있다.
- 인덱스를 통해, n번째 데이터의 조회, 삽입, 삭제가 편리하다.
- 다른 자료구조에 비해 오버헤드가 거의 발생하지 않는다.
- 새로운 원소를 삽입하고 삭제하는데 오래 걸린다.
- 배열의 크기를 처음 선언하기 때문에 배열을 다 사용하지 않을 경우, 메모리 낭비가 심하다.
  
**(3) 배열의 선언**
배열의 선언은 변수에 연속적인 공간을 부여한다는 의미를 가지고 있다. 그래서 배열을 선언할 땐, 공간의 크기를 지정해야 한다. 배열은 초기에 선언될 때, 0으로 초기화 된다. 만약 선언과 동시에 데이터를 삽입하고 싶다면, 배열은 선언과 데이터 초기화를 동시에 할 수 있다. 그리고 배열은 첫 번째 공간의 인덱스가 0부터 시작한다는 특징이 있다.

- 배열 선언의 예
![2-2  배열의 선언](https://user-images.githubusercontent.com/56579239/163946594-7b429620-9352-4892-b515-1fbb7e4e8dd4.png)

- 배열 선언 및 초기화의 예
![2-3  배열의 선언 및 초기화](https://user-images.githubusercontent.com/56579239/163946613-2fb001d4-2421-4b0a-a7b0-9923346ededc.png)

- 코드 예시
```
int[] Arr1 = new int[5];                  // 선언 및 크기 할당, 이때 배열은 모두 0으로 초기화 된다.
int[] Arr2 = {1, 2, 3};                   // 선언 및 초기화

int[][] Arr4 = new int[5][4];             // 이차원 배열의 선언 및 크기 할당
int[][] Arr5 = {{1, 2, 3}, {1, 2, 3}};    // 이차원 배열의 선언 및 초기화
```

**(4) 배열의 삽입, 수정, 삭제**
배열은 기본적으로 인덱스를 통하여 배열에 접근할 수 있다. 원하는 위치에 데이터를 삽입하고 싶다면, 인덱스를 지정하여 배열에 데이터를 삽입할 수 있다. 여러 데이터를 한 번에 넣고 싶다면, 반복문을 통해 데이터를 삽입하게 된다. 그래서 배열은 반복문을 자주 이용하는 자료구조 중 하나이다.

만약 삽입하려는 공간에 데이터가 이미 존재하고 있다면, 데이터 수정 작업이 이루어진다. 데이터를 수정하지 않고 데이터 사이에 새로운 데이터를 삽입하고 싶다면, 기존 데이터를 한 칸 뒤의 인덱스로 저장 후 빈 인덱스에 삽입하여 저장*해야 한다.

배열에 데이터를 삭제한다면, 데이터는 0으로 초기화 되고 인덱스와 공간은 그대로 남아있다.

- 삽입의 예

![2-4  배열의 삽입 1](https://user-images.githubusercontent.com/56579239/163946635-ea7bcfcd-dc70-42af-b55c-d943a8af805e.png)

![2-5  배열의 삽입 2](https://user-images.githubusercontent.com/56579239/163946643-dba20932-d9d8-4f08-988a-a38018c6d716.png)

- 수정의 예

![2-6  배열의 수정 1](https://user-images.githubusercontent.com/56579239/163946649-f363b6b1-daff-49d7-91ba-406806d4d30c.png)

![2-7  배열의 수정 2](https://user-images.githubusercontent.com/56579239/163946653-864269e2-466e-4d33-afac-f97e7324d080.png)

- 삽입의 예 2

![2-8  배열의 삽입 2-1](https://user-images.githubusercontent.com/56579239/163946659-7bdb2443-a161-4826-89b7-408d539958b3.png)

![2-9  배열의 삽입 2-2](https://user-images.githubusercontent.com/56579239/163946665-2c1442eb-efda-4670-89d4-2423484b63aa.png)

![2-10  배열의 삽입 2-3](https://user-images.githubusercontent.com/56579239/163946754-92748003-eb43-4288-946f-181fc2f8381f.png)

- 삭제의 예

![2-11  배열의 삭제 1](https://user-images.githubusercontent.com/56579239/163946765-d0402e87-d288-4404-9449-7a1d646dfad3.png)

![2-12  배열의 삭제 2](https://user-images.githubusercontent.com/56579239/163946774-c5868a04-e736-43bd-8660-998198eaa04c.png)

- 구현 예
```
int[] Arr = new int[5];            // 배열의 크기가 5인 Arr 배열 선언
for(int i = 0; i < 3; i++)         // 배열에 1, 2, 3을 차례로 삽입
  Arr[i] = i + 1;                  // Arr = {1, 2, 3, 0, 0}

Arr[3] = 5;                        // 배열의 4번째 원소에 5를 삽입한다.
                                   // Arr = {1, 2, 3, 5, 0}

Arr[3] = 5;                        // 배열의 4번째 원소의 데이터가 4로 수정된다.
                                   // Arr = {1, 2, 3, 4, 0}

for(int i = 4; i > 0; i--) {       // 배열의 인덱스 0 ~ 3까지의 데이터를 인덱스 1 ~ 4까지로 이동
  Arr[i] = Arr[i-1];               // Arr = {0, 1, 2, 3, 4}
}
Arr[0] = 9;                        // 배열의 0번째 인덱스에 9 데이터 삽입
                                   // Arr = {9, 1, 2, 3, 4}

Arr[0] = 0;                        // 배열의 삭제 
```


