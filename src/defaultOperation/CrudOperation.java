package defaultOperation;

import java.util.List;

public interface CrudOperation<T>{
	boolean Create(T entity);
	List<T> Read();
	T Read(Long id);
	boolean Update(T entity);
	boolean Delete(T entity);
	boolean Delete(Long id);
}
