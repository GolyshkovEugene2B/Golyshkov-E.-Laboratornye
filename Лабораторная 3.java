public class Test {
 
    public static void main(String[] args) {
 
        PriorityQueue<Integer> myPriorityQueue = new PriorityQueue<Integer>();
 
        myPriorityQueue.add(1);
        myPriorityQueue.add(2);
        myPriorityQueue.add(3);
        for(int pq : myPriorityQueue) {
            System.out.println(pq);
        }
    }
}
