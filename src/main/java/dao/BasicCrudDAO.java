package dao;

import java.util.List;

public interface BasicCrudDAO<ENTITY> {
    
    public ENTITY select(int id);

    public List<ENTITY> select();
    
    public int insert(ENTITY entidade);
    
    public int update(ENTITY entidade);
    
    public int delete(int id);
}
