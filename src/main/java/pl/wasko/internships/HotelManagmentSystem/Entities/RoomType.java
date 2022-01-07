package pl.wasko.internships.HotelManagmentSystem.Entities;

public enum RoomType {
    HOUSE(1), FLAT(2), ROOM(3), APARTMENT(4) ,OTHER(5);
    private final int value;

    RoomType(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }
}