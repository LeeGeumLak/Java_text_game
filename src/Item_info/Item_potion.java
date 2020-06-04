package Item_info;

public class Item_potion extends Item {
    public int recovery_amount; // 포션의 회복량 (체력 / 마력)

    // 포션 객체 생성 시, 초기화를 위한 생성자
    public Item_potion(String name, String type, int price, int recovery_amount) {
        this.item_name = name;
        this.item_type = type;
        this.item_price = price;
        this.recovery_amount = recovery_amount;
    }
}
