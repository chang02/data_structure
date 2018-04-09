import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Genre, Title 占쎌뱽 �꽴占썹뵳�뗫릭占쎈뮉 占쎌겫占쎌넅 占쎈쑓占쎌뵠占쎄숲甕곗쥙�뵠占쎈뮞.
 * 
 * MyLinkedList �몴占� 占쎄텢占쎌뒠占쎈퉸 揶쏄낫而� Genre占쏙옙 Title占쎈퓠 占쎈뎡占쎌뵬 占쎄땀�겫占쏙옙�읅占쎌몵嚥∽옙 占쎌젟占쎌졊占쎈쭆 占쎄맒占쎄묶�몴占�  
 * 占쎌�筌욑옙占쎈릭占쎈뮉 占쎈쑓占쎌뵠占쎄숲甕곗쥙�뵠占쎈뮞占쎌뵠占쎈뼄. 
 */
public class MovieDB {
    Genre head;
    public MovieDB() {
        
        head = new Genre(null);
    }

    public void insert(MovieDBItem item) {
        // FIXME implement this
        // Insert the given item to the MovieDB.

    	// Printing functionality is provided for the sake of debugging.
        // This code should be removed before submitting your work.
        // System.err.printf("[trace] MovieDB: INSERT [%s] [%s]\n", item.getGenre(), item.getTitle());
    	Genre curr = new Genre(null);
    	curr = head;
    	int flag = 0;
    	if(curr.next == null) {
    		flag = 3;
    	}
    	while(curr.next != null) {
    		if(curr.next.getItem().compareTo(item.getGenre()) == 0) {
    			flag = 1;
    			break;
    		}
    		else if(curr.next.getItem().compareTo(item.getGenre()) < 0) {
    			flag = 2;
    			curr = curr.getNext();
    			if(curr.next == null) {
    				flag = 3;
    				break;
    			}
    			continue;
    		}
    		else {
    			flag = 3;
    			break;
    		}
    	}
    	if(flag == 1) {
    		curr.next.movielist.add(item.getTitle());
    	}
    	else if(flag == 2) {
    		Genre genre = new Genre(item.getGenre());
    		genre.movielist.add(item.getTitle());
    		genre.next = curr.next.next.next;
    		curr.next.next = genre;
    	}
    	else if(flag == 3) {
    		Genre genre = new Genre(item.getGenre());
    		genre.movielist.add(item.getTitle());
    		genre.next = curr.next;
    		curr.next = genre;
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
        MyLinkedList<MovieDBItem> results = new MyLinkedList<MovieDBItem>();
        Genre tmp = head;
        while(tmp.next != null) {
        	tmp = tmp.next;
        	Node<String> tmp2 = tmp.movielist.head;
        	while(tmp2.getNext() != null) {
        		tmp2 = tmp2.getNext();
        		MovieDBItem item = new MovieDBItem(tmp.getItem(), tmp2.getItem());
        		results.add(item);
        	}
        }
    	return results;
    }
}

class Genre extends Node<String> implements Comparable<Genre> {
    MovieList movielist;
    Genre next;
	public Genre(String name) {
		super(name);
		this.movielist = new MovieList();
	}
	@Override
	public int compareTo(Genre o) {
		return this.getItem().compareTo(o.getItem());
	}
	@Override
	public Genre getNext() {
    	return this.next;
    }
	
// 	@Override
// 	public int hashCode() {
// 		throw new UnsupportedOperationException("not implemented yet");
// 	}

// 	@Override
// 	public boolean equals(Object obj) {
// 		throw new UnsupportedOperationException("not implemented yet");
// 	}
}

class MovieList implements ListInterface<String> {	
    Node<String> head;
    int numItems;
	public MovieList() {
        head = new Node<String>(null);
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
		Node<String> curr = head;
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

class MovieListIterator<string> implements Iterator<String> {
    // FIXME implement this
    // Implement the iterator for MyLinkedList.
    // You have to maintain the current position of the iterator.
    private MovieList list;
    private Node<String> curr;
    private Node<String> prev;

    public MovieListIterator(MovieList list) {
        this.list = list;
        this.curr = list.head;
        this.prev = null;
    }

    @Override
    public boolean hasNext() {
        return curr.getNext() != null;
    }

    @Override
    public String next() {
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