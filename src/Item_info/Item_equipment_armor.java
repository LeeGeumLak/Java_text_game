package Item_info;

public class Item_equipment_armor extends Item_equipment {
    public int defense_power; // 방어구의 방어력

    // 방어구 객체 생성 시, 초기화를 위한 생성자
    public Item_equipment_armor(String name, String type, int price, String job, int level, int defense_power) {
        this.item_name = name;
        this.item_type = type;
        this.item_price = price;
        this.possible_job = job;
        this.possible_level = level;
        this.defense_power = defense_power;
    }
}
