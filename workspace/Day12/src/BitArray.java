
public class BitArray {
	//1. ����ʵ�
	private Object[] base;	//���۷��� ����
	private int max;
	private int size;
	
	//2.������
	public BitArray() {
		this(10);//�Ʒ��� �Ű������� ����ϴ� �����ڸ� ��������� ȣ��!
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
	
	//4. ��� �޼���
	//overflow�� ���� ������ �ʿ��ϴ�!
	private boolean IsOverflow() {
		if(max<=size)//overflow����
			return true;
		else
			return false;
	}
	
	public void Insert(Object obj) throws Exception {		
		if(IsOverflow()==true) 
			throw new Exception("���� ������ �����մϴ�");
		
		//�ش� ��ü �ֱ�
		base[size]=obj;
		size++;					
	}
	
	//idx�� ��ȿ���� üũ
	private boolean IsUseIdx(int idx) {		
		
		//0~size-1���� ���� - ����ִ»�Ȳüũ(size=0)
		if(idx>=0 && idx<=size-1) 
			return true;
		else 
			return false;
	}
	
	public void Delete(int idx) throws Exception {
		
		if (IsUseIdx(idx)==false)
			throw new Exception("��ȿ���� ���� �ε��� �����Դϴ�.");
		
		//�ش��ε��� ����
		for(int i=idx;i<size-1;i++) {
			base[i]=base[i+1];
		}
		//������ ����
		size--;
	}
	
	public void Select() {
		for(int i=0;i<size;i++) {
			Object obj = base[i];
			
			//�ʿ��� �˰���!
		}
	}
	
	public Object getData(int idx){
		if (IsUseIdx(idx)==false)
			return null;//�ּҰ� �ʱ�ȭ�Ǿ��ִ�. Object�� ���۷����Լ�
		
		return base[idx];
	}
}
