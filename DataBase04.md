# Database

## 프로시저

데이터 베이스 안에 영구적으로 저장됨으로 계속 활용해서 사용이 가능하다.



1. 사용할 DB로 접근하기

```mysql
C:\Users\32153256>mysql -u root -p
Enter password: *****

mysql> use sampledb;
Database changed
```



2. 프로시저 등록 코드

```mysql
delimiter //
create procedure pr1()
begin
select * from account;
select * from account;#2개가 사용되는걸 볼려도 두번 넣은것
end //

delimiter ;

```



3. 프로시저 사용

```mysql
call pr1;
```

![image-20210224142603742](md-images/image-20210224142603742.png)



4. 인자 사용해보기

```mysql
delimiter //
create procedure pr2(id INT)
begin
select * from account where accid=id;
end //

delimiter ;
```



5. 호출

```mysql
call pr2(111);
```

![image-20210224142929888](md-images/image-20210224142929888.png)





## 함수

프로시저는 리턴이 없지만 함수는 리턴이 있다.

[인자사용하기 : OUT]

pr3프로시저 설명: accid값을 검색해서 이름을 얻어오는 것

```mysql
delimiter //
create procedure pr3(IN id INT,OUT retname VARCHAR(30))
begin
select name into retname from account where accid=id;
end //
#name으로 select되는 값을 retname에 넣어달라는 뜻
delimiter ;
```



```mysql
call pr3(111, @Retname);

select @Retname;
```

![image-20210224150828271](md-images/image-20210224150828271.png)





## 트리거

트리거(trigger)는 테이블에 대해 어떠한 처리를 실행하면 이에 반응하여 설정해 둔 명령이 자동으로 실행되는 구조를 말합니다. 이때, 이러한 과정이 총의 방아쇠를 당기는 것과 같다하여 트리거(방아쇠)라고 합니다.

insert나 update,delete 등의 명령이 실행될 때

처리를 기록하거나 만약 처리가 실패했을 때 대비해서 만들어 놓으면 좋다!

트리거를 활용하기 위해서 
Account테이블의 스키마만 복사
새로운 테이블을 생성



```mysql
create table account1 like account;

desc account1;
#복사가 되어있지만 select는 불가능. 스키마만 복사된 상태다.
```



> 트리거 작성하기

```mysql
CREATE TRIGGER 트리거_이름 BEFORE(또는 AFTER) DELETE 등의 명령
ON 테이블_이름 FOR EACH ROW
BEGIN
#변경 전(OLD.칼럼_이름) 또는 변경 후(NEW.칼럼_이름)을 이용한 처리
END

```



> 트리거 생성

```mysql
DELIMITER //
CREATE TRIGGER tr1 BEFORE DELETE ON account FOR EACH ROW	#삭제할 데이터가 있는 테이블
BEGIN
INSERT INTO account1 VALUES(OLD.accid, OLD.name, OLD.balance, OLD.newtime);	
#삭제한 데이터를 저장 테이블 – 위에서 before를 사용하므로 위를 삭제하기 전에 old데이터들을 가져와서 집어넣겠다!
END //
delimiter ;
```



> 트리거 실행 확인

```mysql
#account 테이블의 삭제처리를 해야한다!

delete from account where accid=222;

-- account1의 테이블 확인---!!
```





# Day13의 DB내용을 프로시저로 바꿔보기

## 기능 (AccountDB1.java)



구성해둔 메서드들

> MakeAccount

```java
public boolean MakeAccount(int accnum,String name, int balance) {
		try {
			String Insert = "insert into account(accid,name,balance) values(?,?,?);";
			PreparedStatement sment = con.prepareStatement(Insert);

			sment.setInt(1, accnum);
			sment.setString(2, name);
			sment.setInt(3, balance);
			
			int i= sment.executeUpdate();
            sment.close();
			if(i>0) {
				con.commit();
				return true;
			}
			return false;

		} catch (Exception e) {
			return false;
		}		
	}
```



>SelectAccount

```java
public Account SelectAccount(int accnum) {
		try {
			String sql = "select * from account where accid = ?;";
			PreparedStatement sment = con.prepareStatement(sql);	
			sment.setInt(1, accnum);
			//--------------------------------------------------------
			ResultSet rs = sment.executeQuery();//위에서 sql을 이미 담았음!

			rs.next();
			int accid = rs.getInt(1);
			String name = rs.getString(2);
			int balance = rs.getInt(3);
			Timestamp ntime = rs.getTimestamp(4);
			sment.close();

			Account acc = new Account(accid, name, balance, ntime);
			return acc;
		}
		catch(Exception ex) {
			return null;
		}		
	}
```



>InputAccount

```java
public boolean InputAccount(int accnum, int balance) {
		try {
			String sql = "update account set balance = balance + ? where accid=?;";
			PreparedStatement sment = con.prepareStatement(sql);			
			sment.setInt(1,  accnum);
			sment.setInt(2,  balance);
			int i = sment.executeUpdate();
			sment.close();   //<===================================
			if( i > 0) {
				con.commit();
				return true;
			}	
			return false;
		}
		catch(Exception ex) {
			return false;
		}
	}
```



>OutputAccount

```java
public boolean OutputAccount(int accnum, int balance) {
		try {
			//잔액이 부족한 경우?????
			String sql = "update account set balance = balance - ? where accid=? and balance >=?";
			PreparedStatement sment = con.prepareStatement(sql);			
			sment.setInt(1,  balance);
			sment.setInt(2,  accnum);
			sment.setInt(3,  balance);
			int i = sment.executeUpdate();
			sment.close();   //<===================================
			if( i > 0) {
				con.commit();
				return true;
			}	
			return false;
		}
		catch(Exception ex) {
			return false;
		}
	}
```



>DeleteAccount

```java
public boolean DeleteAccount(int id) {
		try {
			String Delete = "delete from account where accid=?;";
			PreparedStatement sment = con.prepareStatement(Delete);

			sment.setInt(1, id);
			sment.close();
			int i= sment.executeUpdate();
			if(i>0) {
				con.commit();
				return true;
			}
			return false;

		} catch (Exception e) {
			return false;
		}	
	}
```



## 프로시저구현

MakeAccount

IN : 계좌번호, 이름, 입금액

```mysql
delimiter //
create procedure MakeAccount(IN id INT, IN name VARCHAR(30), IN bal INT)
begin
insert into account(accid,name,balance) values(id,name,bal);
end //

delimiter ;

```



SelectAccount

IN : 계좌번호

OUT : 이름, 입금액, 날짜

```mysql
delimiter //
create procedure SelectAccount(IN  id INT, OUT retname varchar(20), OUT retbalance int, OUT  retnewtime timestamp)
begin
select name, balance, newtime into retname, retbalance, retnewtime from account where accid = id;
end //
delimiter ;


```



InputAccount

IN : 계좌번호, 입금액

```mysql
delimiter //
create procedure InputAccount(id int, money int)
begin
update account set balance = balance + money where accid = id;
end //
delimiter ;

```



OutputAccount

IN : 계좌번호, 출금액

```mysql
delimiter //
create procedure OutputAccount(id int, money int)
begin
update account set balance = balance - money where accid = id and balance >= money;
end //
delimiter ;

```



DeleteAccount

IN : 계좌번호

```mysql
delimiter //
create procedure DeleteAccount(id int)
begin
delete from account where accid = id;
end //
delimiter ;

```



## java에서

bit.procedure 패키지를 만들어서 안에 Account/AccountDB1/Start 클래스를 만들어준다

MakeAccount메서드부터 **프로시저를 이용할 수 있게** 수정해준다!

쿼리문만 수정해줬다

```java
public boolean MakeAccount(int accnum,String name, int balance) {
		try {
			String query = "{call MakeAccount(?,?,?)};";
			PreparedStatement sment = con.prepareStatement(query);

			sment.setInt(1, accnum);
			sment.setString(2, name);
			sment.setInt(3, balance);
			int i= sment.executeUpdate();
			sment.close();


			if(i>0) {
				con.commit();
				return true;
			}
			return false;

		} catch (Exception e) {
			return false;
		}		
	}
```



PreparedStatement에서 CallableStatment로 바꿔보자

```java
public Account SelectAccount(int accnum) {
		try {
			String sql = "{call SelectAccount(?,?,?,?)};";
			CallableStatement sment = con.prepareCall(sql);	
			sment.setInt(1, accnum);
			sment.registerOutParameter(2, Types.VARCHAR);
			sment.registerOutParameter(3,  Types.INTEGER);
			sment.registerOutParameter(4,  Types.TIMESTAMP);
			//--------------------------------------------------------
			sment.execute();			
	
			String name = sment.getString(2);
			int balance = sment.getInt(3);
			Timestamp ntime = sment.getTimestamp(4);
			
			sment.close();
			
			Account acc = new Account(accnum, name, balance, ntime);
			return acc;
		}
		catch(Exception ex) {
			System.out.println("예외 발생");
			return null;
		}		
	}

```

