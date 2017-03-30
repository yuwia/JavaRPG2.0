public class MultiPurposeStack {

	/* 
	 * Variables for the Master of the Stack
	 * Head is the first node in the stack
	 * Foot is the last node in the stack
	 * Head and foot allow the program to use this class as either a stack or a que 
	 * 
	 */
	private Node head, foot;
	private int length;
	
	public MultiPurposeStack(){
		//initializes the variables for the stack or queue
		length = 0;
		head = null;
		foot = null;
	}
	public boolean isEmpty(){
		//checks to see if the array is empty or not;
		if(length == 0){
			return true;
		}else{
			return false;
		}
	}
	public void addBeginning(int value){
		//creates a new node and assigns the integer value to the input value
		//then assigns the parent pointer of the old head to the new node
		//and changes the head pointer to point to the new node
		//increases the length of the master class
		Node newNode = new Node();
		newNode.setIntValue(value);
		newNode.setParent(null);
		if(isEmpty()){
			newNode.setChild(null);
			head = newNode;
			foot = newNode;
		}else{
			newNode.setChild(head);
			head.setParent(newNode);
			head = newNode;
		}
		length++;
	}
	public void addBeginning(String value){
		//creates a new node and assigns the string value to the input value
				//then assigns the parent pointer of the old head to the new node
				//and changes the head pointer to point to the new node
				//increases the length of the master class
		Node newNode = new Node();
		newNode.setStringValue(value);
		newNode.setParent(null);
		
		if(isEmpty()){
			newNode.setChild(null);
			head = newNode;
			foot = newNode;
		}else{
			newNode.setChild(head);
			head.setParent(newNode);
			head = newNode;
		}
		length++;
	}
	public void addBeginning(Coin value){
		//creates a new node and assigns the string value to the input value
				//then assigns the parent pointer of the old head to the new node
				//and changes the head pointer to point to the new node
				//increases the length of the master class
		Node newNode = new Node();
		newNode.setCoinValue(value);
		newNode.setParent(null);

		if(isEmpty()){
			newNode.setChild(null);
			head = newNode;
			foot = newNode;
		}else{
			newNode.setChild(head);
			head.setParent(newNode);
			head = newNode;
		}
		length++;
	}
	public void addBeginning(Monster value){
		//creates a new node and assigns the string value to the input value
		//then assigns the parent pointer of the old head to the new node
		// and changes the head pointer to point to the new node
		//increases the length of the master class
		Node newNode = new Node();
		newNode.setMonsterValue(value);
		newNode.setParent(null);

		if(isEmpty()){
			newNode.setChild(null);
			head = newNode;
			foot = newNode;
		}else{
			newNode.setChild(head);
			head.setParent(newNode);
			head = newNode;
		}
		length++;
	}
	public void addEnd(int value){
		//creates a new node and assigns the integer value to the input value
		//then assigns the child pointer of the old foot to the new node
		//and changes the foot pointer to point to the new node
		//increases the length of the master class
		Node newNode = new Node();
		newNode.setIntValue(value);
		newNode.setChild(null);
		
		if(isEmpty()){
			newNode.setParent(null);
			head = newNode;
			foot = newNode;
		}else{
			newNode.setParent(foot);
			foot.setChild(newNode);
			foot = newNode;
		}
		length++;
	}
	public void addEnd(String value){
		//creates a new node and assigns the string value to the input value
		//then assigns the child pointer of the old foot to the new node
		//and changes the foot pointer to point to the new node
		//increases the length of the master class
		Node newNode = new Node();
		newNode.setStringValue(value);
		newNode.setChild(null);
		
		if(isEmpty()){
			newNode.setParent(null);
			head = newNode;
			foot = newNode;
		}else{
			newNode.setParent(foot);
			foot.setChild(newNode);
			foot = newNode;
		}
		length++;
	}
	public void addEnd(Coin value){
		//creates a new node and assigns the string value to the input value
		//then assigns the child pointer of the old foot to the new node
		//and changes the foot pointer to point to the new node
		//increases the length of the master class
		Node newNode = new Node();
		newNode.setCoinValue(value);
		newNode.setChild(null);

		if(isEmpty()){
			newNode.setParent(null);
			head = newNode;
			foot = newNode;
		}else{
			newNode.setParent(foot);
			foot.setChild(newNode);
			foot = newNode;
		}
		length++;
	}
	public void addEnd(Monster value){
		//creates a new node and assigns the string value to the input value
		//then assigns the child pointer of the old foot to the new node
		//and changes the foot pointer to point to the new node
		//increases the length of the master class
		Node newNode = new Node();
		newNode.setMonsterValue(value);
		newNode.setChild(null);

		if(isEmpty()){
			newNode.setParent(null);
			head = newNode;
			foot = newNode;
		}else{
			newNode.setParent(foot);
			foot.setChild(newNode);
			foot = newNode;
		}
		length++;
	}
	public int getLength(){
		//returns the length of the stack or queue
		return length;
	}
	public void deleteEnd(){
		//reassigns the pointers on the node above and reassigns the present node's pointers to null
		//has conditions for if there is only one node in the system and if there is no nodes
		//adjusts the length of the system as well
		if(!isEmpty()){
			if(foot.equals(head)){
				foot.Destory();
			}else{
				foot = foot.getParent();
				foot.getChild().Destory();
			}
			length--;
		}
	}
	public void deleteBeginning(){
		//reassigns the pointers on the node below and reassigns the current node's pointers to null
		//has conditions for if there is only one node in the system and if there is no nodes
		//adjusts the length of the system as well
		if(!isEmpty()){
			if(head.equals(foot)){
				head.Destory();
			}else{
				head = head.getChild();
				head.getParent().Destory();
			}
			length--;
		}
	}
	public boolean deleteNode(Node node){
		Node inQuestion = getHead();
		if(getFoot() == node){
			deleteEnd();
			return true;
		}
		if(getHead() == node){
			deleteBeginning();
			return true;
		}
		while(inQuestion != getFoot()){
			if(inQuestion == node){
				inQuestion.Destory();
				length--;
				return true;
			}
			inQuestion = inQuestion.getChild();
		}
		return false;
	}
	public void deleteAt(int index){
		if(!isEmpty()){
			if(index == 1){
				deleteBeginning();
				length--;
				return;
			}
			if(index == length){
				deleteEnd();
				length--;
				return;
			}
		
			Node inQuestion = head;
			for(int i = 1; i < index; i++){
				inQuestion = inQuestion.getChild();
			}
			inQuestion.Destory();
			length--;
		}
	}
	public Node getNodeAt(int a){
		Node que = head;
		for(int i = 1; i < a; i++){
			if(que!=foot){
				que = que.getChild();
			}
		}
		return que;
	}
	public Node getNodeWithValue(int id){
		Node inQuestion = getHead();
		while (inQuestion != getFoot()){
			if(inQuestion.getMonsterValue().getId() == id){
				return inQuestion;
			}
			inQuestion = inQuestion.getChild();
		}
		if(inQuestion.getMonsterValue().getId() == id){
			return inQuestion;
		}
		return null;
	}
	//this can probably be written in a better fashion. like with a for loop
	public void deleteValue(int value){
		Node inQuestion = head;
		if(length == 1 && head.getIntValue() == value){
			purge();
			return;
		}
		while(!inQuestion.equals(foot)){
			if(inQuestion.getIntValue() == value){
				if(inQuestion.equals(head)){
					head = inQuestion.getChild();
				}
				inQuestion.Destory();
				length--;
				return;
			}
			inQuestion = inQuestion.getChild();
		}
		if(inQuestion.getIntValue() == value && inQuestion.equals(foot)){
			foot = inQuestion.getParent();
			inQuestion.Destory();
			length--;
		}
		
	}
	public void purge(){
		Node inQuestion = head, yeh;
		while(!inQuestion.equals(foot)){
			yeh = inQuestion.getChild();
			inQuestion.Destory();
			inQuestion = yeh;
		}
		inQuestion.Destory();
		head = null;
		foot = null;
		length = 0;
	}
	public void deleteValue(String value){
		if(length == 1 && head.getStringValue().equals(value)){
			purge();
			return;
		}
		Node inQuestion = head;
		while(!inQuestion.equals(foot)){
			if(inQuestion.getStringValue().equals(value)){
				if(inQuestion.equals(head)){
					head = inQuestion.getChild();
				}
				inQuestion.Destory();
				return;
			}
			inQuestion = inQuestion.getChild();
		}
		if(inQuestion.getStringValue().equals(value)){
			foot = inQuestion.getParent();
			inQuestion.Destory();
		}
		length--;
	}
	public boolean deleteCoinIndex(int index){
		if(length == 1 && head.getCoinValue().getIndex() == index){
			System.out.println(head.getCoinValue().getValue());
			purge();
			return true;
		}
		Node inQuestion = head;
		while(inQuestion != foot){
			if(inQuestion.getCoinValue().getIndex() == index){
				if(inQuestion == head){
					head = inQuestion.getChild();
				}
				System.out.println(inQuestion.getCoinValue().getValue());
				inQuestion.Destory();
				length--;
				return true;
			}
			inQuestion = inQuestion.getChild();
		}
		if(inQuestion.getCoinValue().getIndex() == index){
			foot = inQuestion.getParent();
			System.out.println(inQuestion.getCoinValue().getValue());
			inQuestion.Destory();
			length--;
			return true;
		}
		return false;
	}
	public Node getHead(){
		return head;
	}
	public Node getFoot(){
		return  foot;
	}
	public void displayMonserValues(){
		Node inque = head;
		while(inque != (foot)){
			System.out.print(inque.getMonsterValue().getId() + ",");
			inque = inque.getChild();
		}
		System.out.println(foot.getMonsterValue().getId());
	}
	
}

class Node{
	int intValue;
	String stringValue;
	Coin coinValue;
	Monster monsterValue;
	Node child, parent;
	
	public void Destory(){
		if(child != null && parent != null){
			child.setParent(parent);
			parent.setChild(child);
		}else if(child != null){
			child.setParent(null);
		}else if(parent != null){
			parent.setChild(null);
		}else{
			child = null;
			parent = null;
		}
		child = null;
		parent = null;
	}
	public void displayContent(){
		System.out.println(intValue);
		System.out.println(stringValue);
		System.out.println(coinValue);
		System.out.println(monsterValue);
		System.out.println();

	}
	//Setters and Getters*****
	public String getStringValue() {
		return stringValue;
	}
	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	public int getIntValue() {
		return intValue;
	}
	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}
	public Node getChild() {
		return child;
	}
	public void setChild(Node child) {
		this.child = child;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public void setCoinValue(Coin coin){
		coinValue = coin;
	}
	public Coin getCoinValue(){
		return coinValue;
	}
	public Monster getMonsterValue(){
		return monsterValue;
	}
	public void setMonsterValue(Monster monster){
		monsterValue = monster;
	}
	//*****//
}