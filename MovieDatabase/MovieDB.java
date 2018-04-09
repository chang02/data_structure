import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Genre, Title 을 관리하는 영화 데이터베이스.
 * 
 * MyLinkedList 를 사용해 각각 Genre와 Title에 따라 내부적으로 정렬된 상태를  
 * 유지하는 데이터베이스이다. 
 */
public class MovieDB {
    MyLinkedList<Genre> list;
    public MovieDB() {
        // FIXME implement this
    	
    	// HINT: MovieDBGenre 클래스를 정렬된 상태로 유지하기 위한 
    	// MyLinkedList 타입의 멤버 변수를 초기화 한다.
        list = new MyLinkedList<Genre>();
    }

    public void insert(MovieDBItem item) {
        // FIXME implement this
        // Insert the given item to the MovieDB.

    	// Printing functionality is provided for the sake of debugging.
        // This code should be removed before submitting your work.
        // System.err.printf("[trace] MovieDB: INSERT [%s] [%s]\n", item.getGenre(), item.getTitle());
        Iterator it = list.iterator();
        int flag = 0;
        while(it.hasNext()){
            it.next();
            if(it.curr.getItem().compareTo(item.getGenre()) == 0){
                it.curr.movielist.add(item.getTitle());
            }
            else if(it.curr.getItem().compareTo(item.getGenre()) < 0){
                if(!it.hasNext())
                    flag = 1;
            }
            else{
                flag = 2;
                break;
            }
        }
        if(flag == 1){
            list.numItems++;
            Genre genre = new Genre(item.getGenre());
            genre.movielist.add(item.getTitle());
            it.curr.setNext(genre);
        }
        else if(flag == 2){
            list.numItems++;
            Genre genre = new Genre(item.getGenre());
            genre.movielist.add(item.getTitle());
            it.prev.setNext(genre);
        }
    }

    public void delete(MovieDBItem item) {
        // FIXME implement this
        // Remove the given item from the MovieDB.
    	
    	// Printing functionality is provided for the sake of debugging.
        // This code should be removed before submitting your work.
        System.err.printf("[trace] MovieDB: DELETE [%s] [%s]\n", item.getGenre(), item.getTitle());
    }

    public MyLinkedList<MovieDBItem> search(String term) {
        // FIXME implement this
        // Search the given term from the MovieDB.
        // You should return a linked list of MovieDBItem.
        // The search command is handled at SearchCmd class.
    	
    	// Printing search results is the responsibility of SearchCmd class. 
    	// So you must not use System.out in this method to achieve specs of the assignment.
    	
        // This tracing functionality is provided for the sake of debugging.
        // This code should be removed before submitting your work.
    	System.err.printf("[trace] MovieDB: SEARCH [%s]\n", term);
    	
    	// FIXME remove this code and return an appropriate MyLinkedList<MovieDBItem> instance.
    	// This code is supplied for avoiding compilation error.   
        MyLinkedList<MovieDBItem> results = new MyLinkedList<MovieDBItem>();

        return results;
    }
    
    public MyLinkedList<MovieDBItem> items() {
        MyLinkedList<MovieDBItem> results = list;
        
    	return results;
    }
}

class Genre extends Node<String> implements Comparable<Genre> {
    MovieList movielist;
	public Genre(String name) {
		super(name);
        movielist = new MovieList();
	}
	
	@Override
	public int compareTo(Genre o) {
		return this.item.compareTo(o.item);
	}

// 	@Override
// 	public int hashCode() {
// 		throw new UnsupportedOperationException("not implemented yet");
// 	}

// 	@Override
// 	public boolean equals(Object obj) {
// 		throw new UnsupportedOperationException("not implemented yet");
// 	}
// }

class MovieList implements ListInterface<String> {	
    Node<string> head;
    int numItems;
	public MovieList() {
        head = new Node<string>();
        numItems = 0;
	}

	@Override
	public Iterator<String> iterator() {
		return new MovieListIterator<String>(this);
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
	public void add(String item) {
		Node<T> curr = head;
        while (curr.getNext() != null) {
            if(curr.getNext().getItem().compareTo(item) == 0)
                return;
            else if(curr.getNext().getItem().compareTo(item) < 0)
                curr = curr.getNext();
            else
                break;
        }
        curr.insertNext(item);
        numItems += 1;
	}

	@Override
	public String first() {
		return head.getNext().getItem();
	}

	@Override
	public void removeAll() {
		head.setNext(null);
	}
}

class MovieListIterator<String> implements Iterator<String> {
    // FIXME implement this
    // Implement the iterator for MyLinkedList.
    // You have to maintain the current position of the iterator.
    private MovieList<String> list;
    private Node<String> curr;
    private Node<String> prev;

    public MovieListIterator(MyLinkedList<String> list) {
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