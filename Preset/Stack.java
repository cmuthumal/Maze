class Node{
	int row;
	int column;
	Node next;
	
	public Node(int row, int column){
		this.row = row;
		this.column = column;
	}
}

class Stack{
	Node top = null;
	
	Stack(){
		top = null;
	}
	
	public boolean isEmpty(){
		return top == null;
	}
	
	public String peek(){
		String s = top.row + "," + top.column;
		return s;
	}
	
	public void push(int r, int c){
		Node n = new Node(r, c);
		n.next = top;
		top = n;
	}
	
	public String pop(){
		Node p = top;
		top = top.next;
		String s = p.row + "," + p.column;
		return s;
	}
}
