## version설명

CE(Community Edition) - 오픈소스기반

EE(Enterprise Edtion) - CE+고급기능추가

> Open Sources 버전 표시

Edge (alptha버전), Stable (beta버전), LTS (정식버전) / GA(Generally Available)



OS를 제외한 제일 큰 오픈소스 = 웹브라우저 ex. chrome(8 core 인스턴스)

크롬은 오픈소스인가? => X

chromium - open source(0), chrome - open source(X), edge



## mysqldump(mysql업뎃할때) vs Enterprise Backup vs innodbbackup

mysqldump : 백업이 시작되면 멈출 수 없음

fork : create process

MySQL : mariaDB의 사촌격/ perconaDB



> MySQL Storage Engine

*MyISAM vs InnoDB

MyISAM(원조) - 외래키 지원X , 트랜잭션 X

InnoDB(요즘 기본) - 외래키 지원 O, 트랜잭션 O

*트랜잭션 : 데이터베이스의 상태를 변환시키는 하나의 논리적 기능을 수행하기 위한 작업의 단위 또는 한꺼번에 모두 수행되어야 할 일련의 연산들을 의미한다.



MariaDB를 쓰는 이유

더 빨라서(MySQL) 5~7%

라이센스

유료기능





# MongoDB

## 사용법

![image-20210303103518225](md-images/image-20210303103518225.png)

지정된 포트번호가 있다.

ex)

Oracle		1521

MySQL		3306

MongoDB	27017



collection을 table로 document를 record로 생각하면 편하다.

> MySQL의 예시

```mysql
create database sample;
show databases;
use sample;
show tables;
create table xxxx;

desc xxxx;
show create table xxxx;

select * from xxxx;
```



Front-end <- 통신(HTTP) -> Back-end(DBMS/Server)

전용프로토콜(통신규약, CRUD가능) vs HTTP GET/POST



HTTP는 CRUD를 사용할수가 없어서 확장판을 지원하게 되었다! => REST