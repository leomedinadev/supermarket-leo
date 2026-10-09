package ec.com.leodev.supermarket.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVO {

    private int categoryId;
    private String category;
    private boolean active;

}
