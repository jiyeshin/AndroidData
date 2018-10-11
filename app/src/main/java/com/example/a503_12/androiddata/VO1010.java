package com.example.a503_12.androiddata;

//아이폰이나 안드로이드같은 모바일 프로그래밍에서는 데이터를 나타내는 클래스를 만들 떄 구조체 형태로도 많이 만듦.
//객체 지향의 디자인 패턴을 적용하면 부피가 커지고 복잡해질 수 있기 때문.
public class VO1010 {
    public int icon;
    public String name;

    public int getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setName(String name) {
        this.name = name;
    }
}
