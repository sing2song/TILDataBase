
public class BitArray {
	//1. 멤버필드
	private Object[] base;	//레퍼런스 형태
	private int max;
	private int size;
	
	//2.생성자
	public BitArray() {
		this(10);//아래의 매개변수를 사용하는 생성자를 명시적으로 호출!
	}
	public BitArray(int max) {
		setMax(max);	//this.max=max;
		setSize(0);
		base = new Object[max];
	}

	//3. get & set
	
	public int getSize() {
		return size;
	}

	private void setSize(int size) {
		this.size = size;
	}

	public int getMax() {
		return max;
	}

	private void setMax(int max) {
		this.max = max;
	}
	
	//4. 기능 메서드
	//overflow에 대한 조건이 필요하다!
	private boolean IsOverflow() {
		if(max<=size)//overflow상태
			return true;
		else
			return false;
	}
	
	public void Insert(Object obj) throws Exception {		
		if(IsOverflow()==true) 
			throw new Exception("저장 공간이 부족합니다");
		
		//해당 객체 넣기
		base[size]=obj;
		size++;					
	}
	
	//idx가 유효한지 체크
	private boolean IsUseIdx(int idx) {		
		
		//0~size-1까지 시작 - 비어있는상황체크(size=0)
		if(idx>=0 && idx<=size-1) 
			return true;
		else 
			return false;
	}
	
	public void Delete(int idx) throws Exception {
		
		if (IsUseIdx(idx)==false)
			throw new Exception("유효하지 않은 인덱스 접근입니다.");
		
		//해당인덱스 삭제
		for(int i=idx;i<size-1;i++) {
			base[i]=base[i+1];
		}
		//사이즈 감소
		size--;
	}
	
	public void Select() {
		for(int i=0;i<size;i++) {
			Object obj = base[i];
			
			//필요한 알고리즘!
		}
	}
	
	public Object getData(int idx){
		if (IsUseIdx(idx)==false)
			return null;//주소가 초기화되어있다. Object는 레퍼런스함수
		
		return base[idx];
	}
}
