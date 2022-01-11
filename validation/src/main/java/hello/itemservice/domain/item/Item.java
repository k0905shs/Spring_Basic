package hello.itemservice.domain.item;

import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10000", message = "10000원 넘게 입력해주세요")
//위와같은 글로벌 오류 같은 경우에는 자바 코드로 작성하는 것이 더 효율적임
//아래 그룹스 기능은 잘 사용하지 않음, 너무 복잡하기 때문, 보통 실무에서는 등록용 폼 객체랑, 수정용 폼 객체를 다르게 사용함
public class Item {

//    @NotNull(groups = UpdateCheck.class)
    private Long id;

//    @NotBlank(message = "공백 x",groups = {UpdateCheck.class,SaveCheck.class})
    private String itemName;

//    @NotNull(groups = {UpdateCheck.class,SaveCheck.class})
//    @Range(min=1000, max = 100000, groups = {SaveCheck.class,UpdateCheck.class},message = "1000~10000")
    private Integer price;

//    @NotNull(groups = {UpdateCheck.class,SaveCheck.class})
//    @Max(value = 9999,groups = {UpdateCheck.class,SaveCheck.class})
    private Integer quantity;

    public Item(String itemName, Integer price, Integer quantity){
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
