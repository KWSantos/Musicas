package example.com.musics.domain.model;

import example.com.musics.domain.Enum.ETypeRole;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private ETypeRole name;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public ETypeRole getName() {
        return name;
    }
    public void setName(ETypeRole name) {
        this.name = name;
    }

}
