package Item_info;

public class Item_equipment_weapon extends Item_equipment {
    public int attack_power; // 무기의 공격력

    // 무기 객체 생성 시, 초기화를 위한 생성자
    public Item_equipment_weapon(String name, String type, int price, String job, int level, int attack_power) {
        this.item_name = name;
        this.item_type = type;
        this.item_price = price;
        this.possible_job = job;
        this.possible_level = level;
        this.attack_power = attack_power;
    }
}
