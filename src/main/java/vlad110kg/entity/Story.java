package vlad110kg.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "story")
@Data
public class Story extends IdDatedEntity {
    @Column(nullable = false)
    private String info;

    @Column(nullable = false, name = "street_id")
    private Long streetId;
}
