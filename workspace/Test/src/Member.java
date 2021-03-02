

import java.sql.Timestamp;
import java.util.Calendar;

public class Member {
	private String memberID;
	private String name;
	private String level;
	
	public Member() {
		
	}
	public Member(String memberID, String name, String level) {
		this.setMemberID(memberID);
		this.setName(name);
		this.setLevel(level);
	}
	
	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	//메서드	
	public void Print() {
		System.out.print("[회원아이디]" + memberID+"  ");
		System.out.print("[이름]" + name+"  ");
		System.out.print("[레벨]" + level);
	}

	
}
