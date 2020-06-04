package Character_info;

import Item_info.Item_equipment_armor;
import Item_info.Item_equipment_weapon;
import Item_info.Item_potion;

public class Character_merchant extends Character {
    public Item_potion[] having_potion = new Item_potion[8]; // 상인이 파는 포션 객체 배열
    public Item_equipment_armor having_armor[] = new Item_equipment_armor[12]; // 상인이 파는 방어구 객체 배열
    public Item_equipment_weapon having_weapon[] = new Item_equipment_weapon[12]; // 상인이 파는 무기 객체 배열

    // 상인 객체가 생성될 때, 초기화를 위한 생성자
    public Character_merchant() {
        this.name = "상인";
        this.having_money = 0;

        // 보유 아이템 객체
        // 체력 포션
        this.having_potion[0] = new Item_potion("소형 체력 포션", "체력 포션", 100, 10);
        this.having_potion[1] = new Item_potion("중형 체력 포션", "체력 포션", 500, 100);
        this.having_potion[2] = new Item_potion("대형 체력 포션", "체력 포션", 1000, 150);
        this.having_potion[6] = new Item_potion("초대형 체력 포션", "체력 포션", 2000, 300);

        // 마력 포션
        this.having_potion[3] = new Item_potion("소형 마력 포션", "마력 포션", 100, 10);
        this.having_potion[4] = new Item_potion("중형 마력 포션", "마력 포션", 500, 100);
        this.having_potion[5] = new Item_potion("대형 마력 포션", "마력 포션", 1000, 150);
        this.having_potion[7] = new Item_potion("초대형 마력 포션", "마력 포션", 2000, 300);

        // 방어구
        this.having_armor[0] = new Item_equipment_armor("[ 기사 ]의 갑옷", "방어구", 500, "기사", 4, 100);
        this.having_armor[1] = new Item_equipment_armor("[ 팔라딘 ]의 갑옷", "방어구", 3000, "팔라딘", 7, 200);
        this.having_armor[2] = new Item_equipment_armor("[ 다크나이트 ]의 갑옷", "방어구", 3000, "다크나이트", 7, 150);

        this.having_armor[3] = new Item_equipment_armor("[ 무사 ]의 무복", "방어구", 500, "무사", 4, 50);
        this.having_armor[4] = new Item_equipment_armor("[ 검사 ]의 무복", "방어구", 3000, "검사", 7, 100);
        this.having_armor[5] = new Item_equipment_armor("[ 광전사 ]의 무복", "방어구", 3000, "광전사", 7, 100);

        this.having_armor[6] = new Item_equipment_armor("[ 마법사 ]의 로브", "방어구", 500, "마법사", 4, 20);
        this.having_armor[6].special_ability = 350; // 마력 +350

        this.having_armor[7] = new Item_equipment_armor("[ 화법사 ]의 로브", "방어구", 3000, "화법사", 7, 50);
        this.having_armor[7].special_ability = 1000; // 마력 +1000

        this.having_armor[8] = new Item_equipment_armor("[ 빙법사 ]의 로브", "방어구", 3000, "빙법사", 7, 50);
        this.having_armor[8].special_ability = 1000; // 마력 +1000


        this.having_armor[9] = new Item_equipment_armor("[ 기사왕 ]의 갑옷", "방어구", 10000, "기사왕", 10, 500);

        this.having_armor[10] = new Item_equipment_armor("[ 무신 ]의 무복", "방어구", 10000, "무신", 10, 250);

        this.having_armor[11] = new Item_equipment_armor("[ 대마법사 ]의 로브", "방어구", 10000, "대마법사", 10, 100);
        this.having_armor[11].special_ability = 3000; // 마력 +3000


        // 무기
        this.having_weapon[0] = new Item_equipment_weapon("[ 기사 ]의 검", "무기", 500, "기사", 4, 20);
        this.having_weapon[1] = new Item_equipment_weapon("[ 팔라딘 ]의 검", "무기", 3000, "팔라딘", 7, 150);
        this.having_weapon[2] = new Item_equipment_weapon("[ 다크나이트 ]의 검", "무기", 3000, "다크나이트", 7, 250);

        this.having_weapon[3] = new Item_equipment_weapon("[ 무사 ]의 검", "무기", 500, "무사", 4, 40);
        this.having_weapon[4] = new Item_equipment_weapon("[ 검사 ]의 검", "무기", 3000, "검사", 7, 400);
        this.having_weapon[5] = new Item_equipment_weapon("[ 광전사 ]의 검", "무기", 3000, "광전사", 7, 400);

        this.having_weapon[6] = new Item_equipment_weapon("[ 마법사 ]의 마법서", "무기", 500, "마법사", 4, 10);
        this.having_weapon[6].special_ability = 500; // 마력 +500

        this.having_weapon[7] = new Item_equipment_weapon("[ 화법사 ]의 마법서", "무기", 3000, "화법사", 7, 20);
        this.having_weapon[7].special_ability = 1500; // 마력 +1500

        this.having_weapon[8] = new Item_equipment_weapon("[ 빙법사 ]의 마법서", "무기", 3000, "빙법사", 7, 20);
        this.having_weapon[8].special_ability = 1500; // 마력 +1500


        this.having_weapon[9] = new Item_equipment_weapon("[ 기사왕 ]의 검", "무기", 10000, "기사왕", 10, 1000);

        this.having_weapon[10] = new Item_equipment_weapon("[ 무신 ]의 검", "무기", 10000, "무신", 10, 2000);

        this.having_weapon[11] = new Item_equipment_weapon("[ 대마법사 ]의 마법서", "무기", 10000, "대마법사", 10, 50);
        this.having_weapon[11].special_ability = 4000; // 마력 +4000
    }

    // 상인이 파는 방어구의 리스트를 보여주는 메서드
    public void armor_list_up (Character_combatant_player player) {
        System.out.println("1. " + this.having_armor[0].item_name + ", 종류 : " + this.having_armor[0].item_type
                + ", 가격 : " + this.having_armor[0].item_price + "원, 사용 가능 레벨 : " + this.having_armor[0].possible_level
                + ", 사용 가능 직업 : " + this.having_armor[0].possible_job + ", 방어력 : " + this.having_armor[0].defense_power);

        System.out.println("2. " + this.having_armor[1].item_name + ", 종류 : " + this.having_armor[1].item_type
                + ", 가격 : " + this.having_armor[1].item_price + "원, 사용 가능 레벨 : " + this.having_armor[1].possible_level
                + ", 사용 가능 직업 : " + this.having_armor[1].possible_job + ", 방어력 : " + this.having_armor[1].defense_power);

        System.out.println("3. " + this.having_armor[2].item_name + ", 종류 : " + this.having_armor[2].item_type
                + ", 가격 : " + this.having_armor[2].item_price + "원, 사용 가능 레벨 : " + this.having_armor[2].possible_level
                + ", 사용 가능 직업 : " + this.having_armor[2].possible_job + ", 방어력 : " + this.having_armor[2].defense_power);

        System.out.println("\n4. " + this.having_armor[3].item_name + ", 종류 : " + this.having_armor[3].item_type
                + ", 가격 : " + this.having_armor[3].item_price + "원, 사용 가능 레벨 : " + this.having_armor[3].possible_level
                + ", 사용 가능 직업 : " + this.having_armor[3].possible_job + ", 방어력 : " + this.having_armor[3].defense_power);

        System.out.println("5. " + this.having_armor[4].item_name + ", 종류 : " + this.having_armor[4].item_type
                + ", 가격 : " + this.having_armor[4].item_price + "원, 사용 가능 레벨 : " + this.having_armor[4].possible_level
                + ", 사용 가능 직업 : " + this.having_armor[4].possible_job+ ", 방어력 : " + this.having_armor[4].defense_power);

        System.out.println("6. " + this.having_armor[5].item_name + ", 종류 : " + this.having_armor[5].item_type
                + ", 가격 : " + this.having_armor[5].item_price+ "원, 사용 가능 레벨 : " + this.having_armor[5].possible_level
                + ", 사용 가능 직업 : " + this.having_armor[5].possible_job + ", 방어력 : " + this.having_armor[5].defense_power);

        System.out.println("\n7. " + this.having_armor[6].item_name + ", 종류 : " + this.having_armor[6].item_type
                + ", 가격 : " + this.having_armor[6].item_price + "원, 사용 가능 레벨 : " + this.having_armor[6].possible_level
                + ", 사용 가능 직업 : " + this.having_armor[6].possible_job + ", 방어력 : " + this.having_armor[6].defense_power
                + ", 추가 마력 : " + this.having_armor[6].special_ability);

        System.out.println("8. " + this.having_armor[7].item_name + ", 종류 : " + this.having_armor[7].item_type
                + ", 가격 : " + this.having_armor[7].item_price + "원, 사용 가능 레벨 : " + this.having_armor[7].possible_level
                + ", 사용 가능 직업 : " + this.having_armor[7].possible_job + ", 방어력 : " + this.having_armor[7].defense_power
                + ", 추가 마력 : " + this.having_armor[7].special_ability);

        System.out.println("9. " + this.having_armor[8].item_name + ", 종류 : " + this.having_armor[8].item_type
                + ", 가격 : " + this.having_armor[8].item_price + "원, 사용 가능 레벨 : " + this.having_armor[8].possible_level
                + ", 사용 가능 직업 : " + this.having_armor[8].possible_job + ", 방어력 : " + this.having_armor[8].defense_power
                + ", 추가 마력 : " + this.having_armor[8].special_ability);

        // 플레이어가 10레벨이어야 볼 수 있는 아이템 리스트
        if(player.level == 10) {
            System.out.println("\n\n-------------------만렙(10레벨) 달성 시, 구매 가능한 상품입니다.------------------------");
            System.out.println("10. " + this.having_armor[9].item_name + ", 종류 : " + this.having_armor[9].item_type
                    + ", 가격 : " + this.having_armor[9].item_price + "원, 사용 가능 레벨 : " + this.having_armor[9].possible_level
                    + ", 사용 가능 직업 : " + this.having_armor[9].possible_job + ", 공격력 : " + this.having_armor[9].defense_power);

            System.out.println("\n11. " + this.having_armor[10].item_name + ", 종류 : " + this.having_armor[10].item_type
                    + ", 가격 : " + this.having_armor[10].item_price + "원, 사용 가능 레벨 : " + this.having_armor[10].possible_level
                    + ", 사용 가능 직업 : " + this.having_armor[10].possible_job + ", 공격력 : " + this.having_armor[10].defense_power);

            System.out.println("\n12. " + this.having_armor[11].item_name + ", 종류 : " + this.having_armor[11].item_type
                    + ", 가격 : " + this.having_armor[11].item_price + "원, 사용 가능 레벨 : " + this.having_armor[11].possible_level
                    + ", 사용 가능 직업 : " + this.having_armor[11].possible_job + ", 공격력 : " + this.having_armor[11].defense_power);
            System.out.println("------------------------------------------------------------------------------------------");
        }

        System.out.println("\n0. 뒤로가기");
    }

    // 상인이 파는 무기의 리스트를 보여주는 메서드
    public void weapon_list_up (Character_combatant_player player) {
        System.out.println("1. " + this.having_weapon[0].item_name + ", 종류 : " + this.having_weapon[0].item_type
                + ", 가격 : " + this.having_weapon[0].item_price + "원, 사용 가능 레벨 : " + this.having_weapon[0].possible_level
                + ", 사용 가능 직업 : " + this.having_weapon[0].possible_job + ", 공격력 : " + this.having_weapon[0].attack_power);

        System.out.println("2. " + this.having_weapon[1].item_name + ", 종류 : " + this.having_weapon[1].item_type
                + ", 가격 : " + this.having_weapon[1].item_price + "원, 사용 가능 레벨 : " + this.having_weapon[1].possible_level
                + ", 사용 가능 직업 : " + this.having_weapon[1].possible_job + ", 공격력 : " + this.having_weapon[1].attack_power);

        System.out.println("3. " + this.having_weapon[2].item_name + ", 종류 : " + this.having_weapon[2].item_type
                + ", 가격 : " + this.having_weapon[2].item_price + "원, 사용 가능 레벨 : " + this.having_weapon[2].possible_level
                + ", 사용 가능 직업 : " + this.having_weapon[2].possible_job + ", 공격력 : " + this.having_weapon[2].attack_power);

        System.out.println("\n4. " + this.having_weapon[3].item_name + ", 종류 : " + this.having_weapon[3].item_type
                + ", 가격 : " + this.having_weapon[3].item_price + "원, 사용 가능 레벨 : " + this.having_weapon[3].possible_level
                + ", 사용 가능 직업 : " + this.having_weapon[3].possible_job + ", 공격력 : " + this.having_weapon[3].attack_power);

        System.out.println("5. " + this.having_weapon[4].item_name + ", 종류 : " + this.having_weapon[4].item_type
                + ", 가격 : " + this.having_weapon[4].item_price + "원, 사용 가능 레벨 : " + this.having_weapon[4].possible_level
                + ", 사용 가능 직업 : " + this.having_weapon[4].possible_job + ", 공격력 : " + this.having_weapon[4].attack_power);

        System.out.println("6. " + this.having_weapon[5].item_name + ", 종류 : " + this.having_weapon[5].item_type
                + ", 가격 : " + this.having_weapon[5].item_price + "원, 사용 가능 레벨 : " + this.having_weapon[5].possible_level
                + ", 사용 가능 직업 : " + this.having_weapon[5].possible_job + ", 공격력 : " + this.having_weapon[5].attack_power);

        System.out.println("\n7. " + this.having_weapon[6].item_name + ", 종류 : " + this.having_weapon[6].item_type
                + ", 가격 : " + this.having_weapon[6].item_price + "원, 사용 가능 레벨 : " + this.having_weapon[6].possible_level
                + ", 사용 가능 직업 : " + this.having_weapon[6].possible_job + ", 공격력 : " + this.having_weapon[6].attack_power
                + ", 추가 마력 : " + this.having_weapon[6].special_ability);

        System.out.println("8. " + this.having_weapon[7].item_name + ", 종류 : " + this.having_weapon[7].item_type
                + ", 가격 : " + this.having_weapon[7].item_price + "원, 사용 가능 레벨 : " + this.having_weapon[7].possible_level
                + ", 사용 가능 직업 : " + this.having_weapon[7].possible_job + ", 공격력 : " + this.having_weapon[7].attack_power
                + ", 추가 마력 : " + this.having_weapon[7].special_ability);

        System.out.println("9. " + this.having_weapon[8].item_name + ", 종류 : " + this.having_weapon[8].item_type
                + ", 가격 : " + this.having_weapon[8].item_price + "원, 사용 가능 레벨 : " + this.having_weapon[8].possible_level
                + ", 사용 가능 직업 : " + this.having_weapon[8].possible_job + ", 공격력 : " + this.having_weapon[8].attack_power
                + ", 추가 마력 : " + this.having_weapon[8].special_ability);

        // 플레이어가 10레벨이어야 볼 수 있는 아이템 리스트
        if(player.level == 10) {
            System.out.println("\n\n-------------------만렙(10레벨) 달성 시, 구매 가능한 상품입니다.------------------------");
            System.out.println("10. " + this.having_weapon[9].item_name + ", 종류 : " + this.having_weapon[9].item_type
                    + ", 가격 : " + this.having_weapon[9].item_price + "원, 사용 가능 레벨 : " + this.having_weapon[9].possible_level
                    + ", 사용 가능 직업 : " + this.having_weapon[9].possible_job + ", 공격력 : " + this.having_weapon[9].attack_power);

            System.out.println("\n11. " + this.having_weapon[10].item_name + ", 종류 : " + this.having_weapon[10].item_type
                    + ", 가격 : " + this.having_weapon[10].item_price + "원, 사용 가능 레벨 : " + this.having_weapon[10].possible_level
                    + ", 사용 가능 직업 : " + this.having_weapon[10].possible_job + ", 공격력 : " + this.having_weapon[10].attack_power);

            System.out.println("\n12. " + this.having_weapon[11].item_name + ", 종류 : " + this.having_weapon[11].item_type
                    + ", 가격 : " + this.having_weapon[11].item_price + "원, 사용 가능 레벨 : " + this.having_weapon[11].possible_level
                    + ", 사용 가능 직업 : " + this.having_weapon[11].possible_job + ", 공격력 : " + this.having_weapon[11].attack_power);
            System.out.println("------------------------------------------------------------------------------------------");
        }

        System.out.println("\n0. 뒤로가기");
    }

    // 상인이 파는 포션의 리스트를 보여주는 메서드
    public void potion_list_up (Character_combatant_player player) {
        System.out.println("1. " + this.having_potion[0].item_name + ", 종류 : " + this.having_potion[0].item_type
                + ", 가격 : " + this.having_potion[0].item_price + "원, 체력회복량 : " + this.having_potion[0].recovery_amount);

        System.out.println("2. " + this.having_potion[1].item_name + ", 종류 : " + this.having_potion[1].item_type
                + ", 가격 : " + this.having_potion[1].item_price + "원, 체력회복량 : " + this.having_potion[1].recovery_amount);

        System.out.println("3. " + this.having_potion[2].item_name + ", 종류 : " + this.having_potion[2].item_type
                + ", 가격 : " + this.having_potion[2].item_price + "원, 체력회복량 : " + this.having_potion[2].recovery_amount);

        System.out.println("\n4. " + this.having_potion[3].item_name + ", 종류 : " + this.having_potion[3].item_type
                + ", 가격 : " + this.having_potion[3].item_price + "원, 마력회복량 : " + this.having_potion[3].recovery_amount);

        System.out.println("5. " + this.having_potion[4].item_name + ", 종류 : " + this.having_potion[4].item_type
                + ", 가격 : " + this.having_potion[4].item_price + "원, 마력회복량 : " + this.having_potion[4].recovery_amount);

        System.out.println("6. " + this.having_potion[5].item_name + ", 종류 : " + this.having_potion[5].item_type
                + ", 가격 : " + this.having_potion[5].item_price + "원, 마력회복량 : " + this.having_potion[5].recovery_amount);

        // 플레이어가 10레벨이어야 볼 수 있는 아이템 리스트
        if(player.level == 10) {
            System.out.println("\n\n-------------------만렙(10레벨) 달성 시, 구매 가능한 상품입니다.------------------------");
            System.out.println("7. " + this.having_potion[6].item_name + ", 종류 : " + this.having_potion[6].item_type
                    + ", 가격 : " + this.having_potion[6].item_price + "원, 체력회복량 : " + this.having_potion[6].recovery_amount);

            System.out.println("8. " + this.having_potion[7].item_name + ", 종류 : " + this.having_potion[7].item_type
                    + ", 가격 : " + this.having_potion[7].item_price + "원, 마력회복량 : " + this.having_potion[7].recovery_amount);
            System.out.println("------------------------------------------------------------------------------------------");
        }

        System.out.println("\n0. 뒤로가기");
    }
}