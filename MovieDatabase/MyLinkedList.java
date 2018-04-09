
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements ListInterface<T> {
	// dummy head
	Node<T> head;
	int numItems;

	public MyLinkedList() {
		head = new Node<T>(null);
	}

    /**
     * {@code Iterable<T>}�몴占� �뤃�뗭겱占쎈릭占쎈연 iterator() 筌롫뗄�꺖占쎈굡�몴占� 占쎌젫�⑤벏釉�占쎈뮉 占쎄깻占쎌삋占쎈뮞占쎌벥 占쎌뵥占쎈뮞占쎄쉘占쎈뮞占쎈뮉
     * 占쎈뼄占쎌벉�⑨옙 揶쏆늿占� 占쎌쁽獄쏉옙 for-each �눧紐껋씩占쎌벥 占쎌굺占쎄문占쎌뱽 癰귨옙 占쎈땾 占쎌뿳占쎈뼄.
     * 
     * <pre>
     *  for (T item: iterable) {
     *  	item.someMethod();
     *  }
     * </pre>
     * 
     * @see PrintCmd#apply(MovieDB)
     * @see SearchCmd#apply(MovieDB)
     * @see java.lang.Iterable#iterator()
     */
    public final Iterator<T> iterator() {
    	return new MyLinkedListIterator<T>(this);
    }

	@Override
	public boolean isEmpty() {
		return head.getNext() == null;
	}

	@Override
	public int size() {
		return numItems;
	}

	@Override
	public T first() {
		return head.getNext().getItem();
	}

	@Override
	public void add(T item) {
		Node<T> last = head;
		while (last.getNext() != null) {
			last = last.getNext();
		}
		last.insertNext(item);
		numItems += 1;
	}

	@Override
	public void removeAll() {
		head.setNext(null);
	}
}

class MyLinkedListIterator<T> implements Iterator<T> {
	// FIXME implement this
	// Implement the iterator for MyLinkedList.
	// You have to maintain the current position of the iterator.
	private MyLinkedList<T> list;
	private Node<T> curr;
	private Node<T> prev;

	public MyLinkedListIterator(MyLinkedList<T> list) {
		this.list = list;
		this.curr = list.head;
		this.prev = null;
	}

	@Override
	public boolean hasNext() {
		return curr.getNext() != null;
	}

	@Override
	public T next() {
		if (!hasNext())
			throw new NoSuchElementException();

		prev = curr;
		curr = curr.getNext();

		return curr.getItem();
	}
	public Node<T> getCurr() {
		return curr;
	}
	public Node<T> getPrev() {
		return prev;
	}

	@Override
	public void remove() {
		if (prev == null)
			throw new IllegalStateException("next() should be called first");
		if (curr == null)
			throw new NoSuchElementException();
		prev.removeNext();
		list.numItems -= 1;
		curr = prev;
		prev = null;
	}
}