package vlad110kg.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@MappedSuperclass
public abstract class IdEntity {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
}
