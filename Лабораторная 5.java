// Создаем новый экземпляр двусвязного списка
List<Integer> list = new LinkedList<>();

// Добавляем/удаляем элементы
list.add(1);
list.add(2);
list.add(null);
list.add(3);
list.add(4);
list.remove(1);
list.add(null);

System.out.println(list); // => [1, null, 3, 4, null]

System.out.println(list.size()); // => 5
System.out.println(list.get(0)); // => 1
System.out.println(list.indexOf(3)); // => 2

LinkedList<Integer> linkedList = (LinkedList<Integer>) list;

// Получаем, но не удаляем первый элемент списка
System.out.println(linkedList.peek()); // => 1
System.out.println(list); // => [1, null, 3, 4, null]

// Получаем и удаляем первый элемент списка
System.out.println(linkedList.poll()); // => 1
System.out.println(linkedList); // => [null, 3, 4, null]

// Вставляем элемент в начало списка
linkedList.addFirst(3);
System.out.println(linkedList); // => [3, null, 3, 4, null]

for (Integer num: linkedList) {
    System.out.println("int: " + num);
}
