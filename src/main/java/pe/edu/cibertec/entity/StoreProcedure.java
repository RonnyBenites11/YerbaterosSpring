package pe.edu.cibertec.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(	name = "tb_store_procedure")
public class StoreProcedure implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column(name="TQuery")	
    private String query;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
