package ec.com.leodev.supermarket.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseItemVO {

    private int productId;
    private int quantity;
    private double total;
    private boolean active;

}
