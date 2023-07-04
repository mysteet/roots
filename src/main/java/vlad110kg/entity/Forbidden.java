package vlad110kg.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
public class Forbidden extends IdEntity {
    @Column(nullable = false)
    private String key;
    @Column(nullable = false)
    private String value;
}
