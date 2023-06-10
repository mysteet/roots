package vlad110kg.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "street")
@Data
public class Street extends IdDatedEntity {

    @Column(nullable = false)
    private String title;

    @OneToMany
    @JoinColumn(name = "street_id")
    private List<Story> stories;
}
