public class Test {//создаем класс
 
    public static void main(String[] args) {
 
        PriorityQueue<Integer> myPriorityQueue = new PriorityQueue<Integer>();
 
        myPriorityQueue.add(1);//добавляем в очередь
        myPriorityQueue.add(2);
        myPriorityQueue.add(3);
        for(int pq : myPriorityQueue) {
            System.out.println(pq);//выводим очередь на экран
        }
    }
}
