# DataBase

물리적으론 파일, 논리적으론 시스템.

데이터란?

데이터의 집합체다

여러 응용시스템들의 통합된 정보들을 저장하여 운영할 수 있는 공용데이터의 집합.

효율적으로 저장, 검색, 갱신할 수 있도록 데이터 집합들끼리 연관시키고 조직화 되어야한다.

`DBMS` : 데이터베이스 관리 시스템. 소프트웨어



> File IO와 Database의 차이

File IO : 직접 파일에 접근해서 가져오는 것.

Database : 파일을 관리하는 데이터베이스에다 명령을 내려서 시스템을 통해 파일에 간접적으로 접근하는 것.

데이터 종속성을 보완하고 중복성을 제거한다.



SQL언어는 다양하다. 그 중 우리는 `MySQL`이라는 언어로 명령을 내려서 DB를 관리할 것.



## DBMS 성질

1. 정의기능(DDL) : 건물의 영역을 구성하고 그 영역 안에 **객체**를 생성관리하는 것. (Create, Drop, Alter)
2. 조작기능(DML) : 데이터 관리 (Insert, Update, Delete) / Select(DQL,Query)
3. 제어기능(DCL) : 데이터베이스의 정확성과 안정성을 유지하도록 제어하는 기능. 권한, 트랜잭션 관련된 부분.

=> SQL문 제공되고 있다!



DBA : 데이터베이스 관리자. SQL문으로 DB를 관리하게 된다.



## 데이터베이스 종류

RDB(realations database) : 관계형 데이터 베이스. 데이터 자체를 테이블 구조로 관리하는 것.  매우 간단한 원칙의 개념의 데이터 베이스. SQL로 조작 또는 조회를 할 수 있다.

엔티티(Entity : Table), 속성으로 구성되어있다.

데이터 값이 항상 올바르게 저장 되고 관리 될 수 있도록 무결성 제약 조건들이 존재한다.

ORDB(object realations database) : 객체 관계형 데이터 베이스. 

NoSQL : SQL을 사용하지 않고 DB를 다룰 수 있는 것.





## 테이블

열 (Column) : 단일 종류의 데이터. 

행 (Row) : column들의 값의 조합. 레코드라고 불린다.





## MySQL Server설치

1. https://dev.mysql.com/downloads/windows/installer/8.0.html

해당 사이트에서 들어가 최신 MySQL로 들어간다.



![image-20210219100630705](md-images/image-20210219100630705.png)

2. 64bit사용하고 있지만 현재 설치하는 버전은 32bit만 제공하지만 설치해도 문제 없으니 설치!!



![image-20210219100543910](md-images/image-20210219100543910.png)

3. 따로 설정 줄 부분이 없어서 바로 Next - Execute를 눌러 설치한다.



![image-20210219101307037](md-images/image-20210219101307037.png)

4. 비밀번호는 ssong



![image-20210219101544412](md-images/image-20210219101544412.png)

5. 서버네임도 디폴트!



![image-20210219101747208](md-images/image-20210219101747208.png)

![image-20210219101805186](md-images/image-20210219101805186.png)

6. 서버가 잘 돌아가는지 체크!



7. C:\Program Files\MySQL\MySQL Server 8.0\bin

   mysql.exe를 누르면 시작된다!!



8. 어디서든 사용할 수 있게 환경변수에 등록하자!(편집)

   Path에 위 경로를 등록! 

![image-20210219103453780](md-images/image-20210219103453780.png)



9. cmd창에서 확인!

![image-20210219103526900](md-images/image-20210219103526900.png)

잘 돌아가면 mysql> 로 출력된다!

```bash
# -u(계정)을 root로 -p(패스워드)
mysql -u root -p
password : ssong
```



10. test

![image-20210219104251669](md-images/image-20210219104251669.png)



## MySQL 사용

> 예제 테이블을 추가

```mysql
mysql> create database sampleDB;
mysql> use sampleDB;

mysql>show tables; -sample
```

`mysql.txt`에 있는 sql문들을 집어넣는다

해당 파일은 C:\Users\32153256\Desktop\ssong\soltlux\TILDataBase 폴더 안에 저장해둠!



```mysql
select * from salgrade;	--5줄
select * from emp;	--4줄
select * from dept; --14줄

show tables;
3개의 테이블이 나와야한다!
```



## 실습

1. dept테이블의 모든정보를 출력

   select * from dept;

2. dept테이블에서 dname과 loc의 정보만 출력

   select dname,loc from dept;

3. Emp 테이블의 스키마 정보(어떤 컬럼들을 갖고있는지)를 알고 싶다.

   `스키마` : 어디에 사용되냐에 따라 개념이 조금씩 달라지는데 위에선 어떤 구조를 가지고있는지 확인하고 싶은 것이다.

   desc emp;



varchar는 가변데이터

char는 고정값



### Select 구문의 기본문형

> 구조

```mysql
select 출력할 결과물..컬럼의 이름
from 대상 테이블명
where 조건절, 필터링, 원하는 로우 데이터만 요청
```



`DISTINCT` 중복행을 제거

`ALIAS` 나타날 컬럼에 대한 다른 이름을 부여 =>as

`from` 선택한 컬럼이 있는 테이블을 명시시킨다.

```mysql
select job from emp; 
select distinct job from emp; 
select distinct job as 업무 from emp; 
select distinct job 업무 from emp; 

select distinct job 업 무 from emp; #error
```

공백에 의미가 크다! '무'에 대한 처리가 되지 않으므로 에러가 난다.

공백을 넣고 싶을 때 ' '(작은 따옴표)를 사용하면 된다. 

" "(큰따옴표)도 사용가능하다.

```mysql
select distinct job '업 무' from emp;
select distinct job "업 무" from emp;
```



> 문자열 결합함수 `concat`

```mysql
select concat(ename,' ',sal)
as '이름과 급여'
from emp;
```



Quiz

1. emp테이블에서 이름이(ename) smith인 사람의 모든 정보를 출력!

   ```mysql
   select * from emp where ename='smith';
   ```

2. emp테이블에서 급여가 1000과 2000사이인 회원의 이름과 급여를 출력!

```mysql
select ename, sal from emp where sal>=1000 and sal<=2000;
select ename, sal from emp where sal between 1000 and 2000;
```



3. emp테이블에서 입사일(hiredate)이 1981년 이전에 입사한 직원의 이름과 입사일을 출력

   ```mysql
   select ename, hiredate 
   from emp 
   where date(hiredate)<=date('1981-01-01');
   
   select ename, hiredate 
   from emp 
   where year(hiredate)<1981;
   ```

   

## 비교연산자

### IN

나열된 것 중에서 하나만 만족해도 참! or의 개념

dept_emp테이블에서 부서 번호가 d005나 d009에 속한의 사번, 부서번호 출력

```mysql
select emp_no,dept_no
from dept_emp
where dept_no in ('d005','d009');
```



### LIKE

와일드 카드를 사용하여 특정 문자를 포함한 값에 대한 조건을 처리.

1. Emp테이블에서 이름이 **s로 시작되는** 직원들의 ename과 job을 출력

```mysql
select ename, job
from emp
where ename like 's%';
```



2. Emp테이블에서 이름의 **두번째 문자**가 L인 직원들의 ename과 job출력

```mysql
select ename, job
from emp
where ename like '_L%';
```



### order by절

select 칼럼명 from 테이블명 order by 칼럼이나 표현식(asc또는 desc);

1. Emp테이블에서 이름을 오름차순, 연봉은 내림차순, 연봉이 높은 사람부터 출력

```mysql
select ename, sal
from emp
order by ename asc, sal desc;
```



### Group by 절, having 절

select 출력할 결과물...(컬럼의 이름)

from 대상 테이블명

where 조건절, 필터링, 원하는 로우 데이터만 요청

group by 그룹을 구성할 컬럼 이름

having 그룹을 기반으로한 필터링

order by 정렬기본 asc/desc



> 그룹함수(집계함수) ↔ group by 절, having절

하나의 row값을 반환. 그룹별 하나의 row값 반환

ex) 그룹 안에서(group by) 80점 이상인것들(having)

having은 groupby에 종속적, 조건을 주는 것이다.



집계(그룹)함수의 출력함수는 무조건 1개가 나오게 된다. 

이를 방지하기 위해 group by를 이용한다.



select절에 집계함수와 컬럼을 같이 출력할 수 없다.

select empno, AVG(sal) from emp group by empno;

**But,** group by 절에서 사용한 컬럼은 당연히 select절에 사용되게 된다.

group by에 대한 조건은 having을 사용한다.

> Error

```mysql
SELECT empno, AVG(sal)
 FROM emp
 WHERE AVG(salary) > 50000
GROUP BY empno
```

> correct

```mysql
SELECT empno, AVG(sal)
 FROM emp
 GROUP BY empno
 having AVG(sal) > 50000

```



## Join

하나이상의 테이블로부터 연관된 데이터를 검색해 오는 방법

primary Key와 Foreign Key값의 연관에 의해 JOIN이 성립



카타시안 프로덕트(곱) : join조건이 없을 때

시각적으로 join 문장인지 확인할 수 있는 방법. (from 절에 테이블이 2개 이상)

```mysql
select * from emp,dept;
컬럼의 개수 : 8 + 3 =11개
로우 데이터의 개수 : 14 * 4 = 56개
```



Join을 할 떄는 기준 테이블이 있다. 정상적인 Join을 수행하면 기준 테이블의 로우 데이터 개수의 크기로 반환!