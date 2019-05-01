class Node {
    constructor(value) {
        this.value=value;
        this.next=null;
    }
}
class LinkedList {
    constructor() {
        this.first = null;
        this.last = null;
        this.size=0;

    }

    add(value) {
        let newNode=new Node(value)
        if (this.first) {
            this.last.next = newNode;
        } else {
            this.first=newNode;
        }
        this.last = newNode;
        this.size++;
    }

    get(index) {
        if (index < 0 || index >= this.size) {

        }
    }
}
